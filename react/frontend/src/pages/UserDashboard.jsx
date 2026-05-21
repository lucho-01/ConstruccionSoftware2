import { useEffect, useRef, useState } from 'react'
import Sidebar from '../components/Sidebar'
import ConfirmDialog from '../components/ConfirmDialog'
import {
  createHumanResourceEmployee,
  deleteHumanResourceEmployee,
  fetchHumanResourceEmployees,
  fetchSupportAppointments,
  fetchSupportBillings,
  fetchSupportEmergencyContacts,
  fetchSupportEmployees,
  fetchSupportPatients,
  updateHumanResourceEmployee,
} from '../services/api'
import './AdminDashboard.css'

const emptyEmployeeForm = {
  fullName: '',
  document: '',
  email: '',
  birthdate: '',
  address: '',
  phoneNumber: '',
  userName: '',
  password: '',
  age: '',
  roleType: 'DOCTORS',
}

const normalizeRole = (role) => String(role || '').trim().toUpperCase()
const displayNameFor = (user) => user?.fullName || user?.username || 'Usuario'

function UserDashboard({ user }) {
  const role = normalizeRole(user?.role)

  if (role === 'HUMANRESOURCES') {
    return <HumanResourcesDashboard user={user} />
  }

  if (role === 'INFORMATIONSUPPORT') {
    return <InformationSupportDashboard user={user} />
  }

  return <GenericUserDashboard user={user} />
}

function HumanResourcesDashboard({ user }) {
  const [currentSection, setCurrentSection] = useState('home')
  const [expandedSection, setExpandedSection] = useState('')
  const [employees, setEmployees] = useState([])
  const [employeeForm, setEmployeeForm] = useState(emptyEmployeeForm)
  const [editingEmployeeId, setEditingEmployeeId] = useState(null)
  const [employeeSearch, setEmployeeSearch] = useState('')
  const [confirmData, setConfirmData] = useState(null)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')
  const [isLoading, setIsLoading] = useState(false)

  const sections = [{ id: 'employees', label: 'Empleados' }]

  useEffect(() => {
    if (currentSection === 'employees') loadEmployees()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentSection])

  const ensureArray = (value) => Array.isArray(value) ? value : Array.isArray(value?.data) ? value.data : []
  const showSuccess = (text) => { setError(''); setMessage(text); window.setTimeout(() => setMessage(''), 3000) }
  const showError = (text) => { setMessage(''); setError(text); window.setTimeout(() => setError(''), 3000) }
  const apiError = (err, fallback) => {
    const data = err.response?.data
    return typeof data === 'string' ? data : data?.message || data?.error || err.message || fallback
  }

  const formatDateToDDMMYYYY = (value) => {
    if (!value) return ''
    const [year, month, day] = value.split('-')
    return day && month && year ? `${day}/${month}/${year}` : value
  }

  const loadEmployees = async () => {
    try {
      setIsLoading(true)
      const response = await fetchHumanResourceEmployees()
      setEmployees(ensureArray(response.data))
    } catch (err) {
      showError(apiError(err, 'No se pudieron cargar los empleados'))
    } finally {
      setIsLoading(false)
    }
  }

  const buildPayload = () => ({
    fullName: employeeForm.fullName,
    document: Number(employeeForm.document),
    email: employeeForm.email,
    birthdate: formatDateToDDMMYYYY(employeeForm.birthdate),
    address: employeeForm.address,
    phoneNumber: Number(employeeForm.phoneNumber),
    userName: employeeForm.userName,
    password: employeeForm.password,
    age: Number(employeeForm.age),
    roleType: employeeForm.roleType,
  })

  const resetForm = () => {
    setEmployeeForm(emptyEmployeeForm)
    setEditingEmployeeId(null)
    setExpandedSection('')
  }

  const handleCreateEmployee = async () => {
    try {
      await createHumanResourceEmployee(buildPayload())
      showSuccess('Empleado creado correctamente')
      resetForm()
      loadEmployees()
    } catch (err) {
      showError(apiError(err, 'Error al crear empleado'))
    }
  }

  const handleEditEmployee = (employee) => {
    setEditingEmployeeId(employee.id)
    setEmployeeForm({
      fullName: employee.fullName || '',
      document: employee.document || '',
      email: employee.email || '',
      birthdate: employee.birthdate ? String(employee.birthdate).split('/').reverse().join('-') : '',
      address: employee.address || '',
      phoneNumber: employee.phoneNumber || '',
      userName: employee.userName || '',
      password: '',
      age: employee.age || '',
      roleType: employee.role?.name || employee.role || employee.roleType || 'DOCTORS',
    })
    setExpandedSection('employees')
  }

  const handleUpdateEmployee = async () => {
    try {
      await updateHumanResourceEmployee(editingEmployeeId, buildPayload())
      showSuccess('Empleado actualizado correctamente')
      resetForm()
      loadEmployees()
    } catch (err) {
      showError(apiError(err, 'Error al actualizar empleado'))
    }
  }

  const confirmDelete = async () => {
    try {
      await deleteHumanResourceEmployee(confirmData.id)
      showSuccess('Empleado eliminado correctamente')
      loadEmployees()
    } catch (err) {
      showError(apiError(err, 'No se puede eliminar porque este empleado tiene registros asignados.'))
    } finally {
      setConfirmData(null)
    }
  }

  const filteredEmployees = employees.filter((employee) => {
    if (!employeeSearch) return true
    const query = employeeSearch.toLowerCase()
    return String(employee.document || '').includes(employeeSearch) ||
      String(employee.fullName || '').toLowerCase().includes(query) ||
      String(employee.userName || '').toLowerCase().includes(query)
  })

  const renderSection = () => {
    if (currentSection !== 'employees') return <HomeSection user={user} title="Panel de Recursos Humanos" />

    return (
      <SectionPanel
        title="Empleados"
        isLoading={isLoading}
        formOpen={expandedSection === 'employees'}
        onToggleForm={() => setExpandedSection(expandedSection === 'employees' ? '' : 'employees')}
        createLabel={editingEmployeeId ? 'Editar Empleado' : 'Crear Empleado'}
        itemCount={filteredEmployees.length}
        emptyText="No hay empleados registrados"
        renderForm={() => renderEmployeeForm()}
        renderList={() => renderEmployeeList(filteredEmployees)}
      />
    )
  }

  return (
    <div className="dashboard-layout">
      <Sidebar currentSection={currentSection} onSectionChange={setCurrentSection} sections={sections} />
      <main className="dashboard-content">
        {message && <div className="alert alert-success">{message}</div>}
        {error && <div className="alert alert-error">{error}</div>}
        {renderSection()}
        <ConfirmDialog
          isOpen={!!confirmData}
          title="Eliminar empleado"
          message="¿Estás seguro de que deseas eliminar este empleado?"
          onConfirm={confirmDelete}
          onCancel={() => setConfirmData(null)}
          confirmText="Sí, eliminar"
        />
      </main>
    </div>
  )

  function renderEmployeeForm() {
    return (
      <div className="inline-form-card">
        <h3>{editingEmployeeId ? 'Editar empleado' : 'Nuevo empleado'}</h3>
        <div className="form-card">
          <label>Nombre completo<input value={employeeForm.fullName} onChange={(e) => setEmployeeForm({ ...employeeForm, fullName: e.target.value })} placeholder="Ej: María García" /></label>
          <label>Documento<input type="number" value={employeeForm.document} onChange={(e) => setEmployeeForm({ ...employeeForm, document: e.target.value })} placeholder="Ej: 1234567890" /></label>
          <label>Email<input type="email" value={employeeForm.email} onChange={(e) => setEmployeeForm({ ...employeeForm, email: e.target.value })} placeholder="Ej: maria@example.com" /></label>
          <label>Usuario<input value={employeeForm.userName} onChange={(e) => setEmployeeForm({ ...employeeForm, userName: e.target.value })} placeholder="Ej: maria.garcia" /></label>
          <label>Contraseña<input type="password" value={employeeForm.password} onChange={(e) => setEmployeeForm({ ...employeeForm, password: e.target.value })} placeholder="Ej: clave segura" /></label>
          <label>Edad<input type="number" value={employeeForm.age} onChange={(e) => setEmployeeForm({ ...employeeForm, age: e.target.value })} placeholder="Ej: 30" /></label>
          <label>Nacimiento<input type="date" value={employeeForm.birthdate} onChange={(e) => setEmployeeForm({ ...employeeForm, birthdate: e.target.value })} placeholder="Ej: 1994-05-15" title="Ej: 1994-05-15" /></label>
          <label>Teléfono<input type="number" value={employeeForm.phoneNumber} onChange={(e) => setEmployeeForm({ ...employeeForm, phoneNumber: e.target.value })} placeholder="Ej: 3001234567" /></label>
          <label>Dirección<input value={employeeForm.address} onChange={(e) => setEmployeeForm({ ...employeeForm, address: e.target.value })} placeholder="Ej: Calle Principal 123" /></label>
          <label>
            Rol
            <select value={employeeForm.roleType} onChange={(e) => setEmployeeForm({ ...employeeForm, roleType: e.target.value })}>
              <option value="HUMANRESOURCES">Recursos Humanos</option>
              <option value="INFORMATIONSUPPORT">Soporte de Información</option>
              <option value="NURSES">Enfermera</option>
              <option value="DOCTORS">Doctor</option>
            </select>
          </label>
          <div className="form-actions">
            <button className="btn-create" onClick={editingEmployeeId ? handleUpdateEmployee : handleCreateEmployee} type="button">
              {editingEmployeeId ? 'Actualizar empleado' : 'Guardar empleado'}
            </button>
            <button className="btn-secondary" onClick={resetForm} type="button">Cancelar</button>
          </div>
        </div>
      </div>
    )
  }

  function renderEmployeeList(rows) {
    return (
      <div>
        <div className="list-actions">
          <input type="text" placeholder="Buscar por documento, nombre o usuario" value={employeeSearch} onChange={(e) => setEmployeeSearch(e.target.value)} />
          <button className="btn-secondary" onClick={() => { setEmployeeSearch(''); loadEmployees() }} type="button">Limpiar</button>
        </div>
        <TableList
          headers={['Documento', 'Nombre', 'Email', 'Usuario', 'Rol', 'Teléfono', 'Dirección', 'Acciones']}
          rows={rows}
          renderRow={(employee, index) => (
            <tr key={employee.id || index}>
              <td>{employee.document}</td>
              <td>{employee.fullName}</td>
              <td>{employee.email}</td>
              <td>{employee.userName || '-'}</td>
              <td>{translateRole(employee.role?.name || employee.role || employee.roleType)}</td>
              <td>{employee.phoneNumber || '-'}</td>
              <td>{employee.address || '-'}</td>
              <td>
                <button className="btn-secondary" onClick={() => handleEditEmployee(employee)}>Editar</button>
                <button className="btn-danger" onClick={() => setConfirmData({ id: employee.id })}>Eliminar</button>
              </td>
            </tr>
          )}
        />
      </div>
    )
  }
}

function InformationSupportDashboard({ user }) {
  const [currentSection, setCurrentSection] = useState('home')
  const [rows, setRows] = useState([])
  const [search, setSearch] = useState('')
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')
  const [isLoading, setIsLoading] = useState(false)
  const latestLoadRef = useRef(0)

  const sections = [
    { id: 'patients', label: 'Pacientes' },
    { id: 'employees', label: 'Empleados' },
    { id: 'appointments', label: 'Citas' },
    { id: 'billings', label: 'Facturas' },
    { id: 'emergency', label: 'Contactos de Emergencia' },
  ]

  useEffect(() => {
    setRows([])
    if (currentSection !== 'home') loadSection(currentSection)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentSection])

  const ensureArray = (value) => Array.isArray(value) ? value : Array.isArray(value?.data) ? value.data : []
  const apiError = (err, fallback) => {
    const data = err.response?.data
    return typeof data === 'string' ? data : data?.message || data?.error || err.message || fallback
  }

  const loadSection = async (section) => {
    const loadId = latestLoadRef.current + 1
    latestLoadRef.current = loadId

    try {
      setIsLoading(true)
      setError('')
      setSearch('')
      setRows([])
      const loaders = {
        patients: fetchSupportPatients,
        employees: fetchSupportEmployees,
        appointments: fetchSupportAppointments,
        billings: fetchSupportBillings,
        emergency: fetchSupportEmergencyContacts,
      }
      const loader = loaders[section]
      if (!loader) {
        throw new Error('Sección no disponible')
      }

      const response = await loader()
      if (latestLoadRef.current === loadId) {
        setRows(ensureArray(response.data))
        setMessage('Información cargada correctamente')
        window.setTimeout(() => setMessage(''), 1800)
      }
    } catch (err) {
      if (latestLoadRef.current === loadId) {
        setRows([])
        setError(apiError(err, 'No se pudo cargar la información'))
      }
    } finally {
      if (latestLoadRef.current === loadId) {
        setIsLoading(false)
      }
    }
  }

  const getSearchableText = (row) => {
    if (!row || typeof row !== 'object') return String(row || '')

    return Object.values(row)
      .map((value) => {
        if (!value || typeof value !== 'object') return String(value || '')
        return Object.values(value).join(' ')
      })
      .join(' ')
  }

  const filteredRows = rows.filter((row) => !search || getSearchableText(row).toLowerCase().includes(search.toLowerCase()))

  const renderSection = () => {
    if (currentSection === 'home') {
      return <HomeSection user={user} title="Panel de Soporte de Información" />
    }

    const title = sections.find((section) => section.id === currentSection)?.label || 'Información'
    return (
      <ReadOnlySection
        title={title}
        isLoading={isLoading}
        itemCount={filteredRows.length}
        emptyText="No hay datos para mostrar"
        renderList={() => renderReadOnlyList(currentSection, filteredRows)}
      />
    )
  }

  return (
    <div className="dashboard-layout">
      <Sidebar currentSection={currentSection} onSectionChange={setCurrentSection} sections={sections} />
      <main className="dashboard-content">
        {message && <div className="alert alert-success">{message}</div>}
        {error && <div className="alert alert-error">{error}</div>}
        {currentSection !== 'home' && (
          <div className="list-actions">
            <input type="text" placeholder="Buscar en la información" value={search} onChange={(e) => setSearch(e.target.value)} />
            <button className="btn-secondary" onClick={() => setSearch('')} type="button">Limpiar</button>
          </div>
        )}
        {renderSection()}
      </main>
    </div>
  )
}

function GenericUserDashboard({ user }) {
  return (
    <div className="dashboard-layout">
      <Sidebar currentSection="home" onSectionChange={() => {}} sections={[]} />
      <main className="dashboard-content">
        <HomeSection user={user} title="Panel de Usuario" />
      </main>
    </div>
  )
}

function renderReadOnlyList(section, rows) {
  if (section === 'patients') {
    return <TableList headers={['Documento', 'Nombre', 'Email', 'Teléfono', 'Aseguradora', 'Vigencia']} rows={rows} renderRow={(item, index) => (
      <tr key={item.id || index}><td>{formatDisplayValue(item.document)}</td><td>{formatDisplayValue(item.fullName)}</td><td>{formatDisplayValue(item.email)}</td><td>{formatDisplayValue(item.phoneNumber)}</td><td>{formatDisplayValue(item.insuranceCompanyName)}</td><td>{formatDisplayValue(item.policyValidity)} / {formatDisplayValue(item.policyEndDate)}</td></tr>
    )} />
  }

  if (section === 'employees') {
    return <TableList headers={['Documento', 'Nombre', 'Email', 'Usuario', 'Rol', 'Teléfono']} rows={rows} renderRow={(item, index) => (
      <tr key={item.id || index}><td>{formatDisplayValue(item.document)}</td><td>{formatDisplayValue(item.fullName)}</td><td>{formatDisplayValue(item.email)}</td><td>{formatDisplayValue(item.userName)}</td><td>{translateRole(item.role?.name || item.role)}</td><td>{formatDisplayValue(item.phoneNumber)}</td></tr>
    )} />
  }

  if (section === 'appointments') {
    return <TableList headers={['Fecha', 'Hora', 'Paciente', 'Doctor']} rows={rows} renderRow={(item, index) => (
      <tr key={item.id || index}><td>{formatDate(item.date)}</td><td>{formatDisplayValue(item.time || formatTime(item.date))}</td><td>{formatPerson(item.patientName, item.patientDocument)}</td><td>{formatPerson(item.doctorName, item.doctorDocument)}</td></tr>
    )} />
  }

  if (section === 'billings') {
    return <TableList headers={['Póliza', 'Paciente', 'Edad', 'Aseguradora', 'Vigencia']} rows={rows} renderRow={(item, index) => (
      <tr key={item.id || index}><td>{formatDisplayValue(item.policyNumber)}</td><td>{formatDisplayValue(item.patientDocument || item.patientName?.document || item.patientName)}</td><td>{formatDisplayValue(item.patientAge)}</td><td>{formatDisplayValue(item.insuranceCompanyName)}</td><td>{formatDisplayValue(item.policyValidity)} / {formatDisplayValue(item.policyEndDate)}</td></tr>
    )} />
  }

  return <TableList headers={['Paciente', 'Documento', 'Nombre', 'Apellido', 'Teléfono']} rows={rows} renderRow={(item, index) => (
    <tr key={item.id || index}>
      <td>{formatDisplayValue(item.patient?.fullName || item.patientName)}</td>
      <td>{formatDisplayValue(item.patient?.document || item.patientDocument)}</td>
      <td>{formatDisplayValue(item.name)}</td>
      <td>{formatDisplayValue(item.lastName)}</td>
      <td>{formatDisplayValue(item.phoneNumber)}</td>
    </tr>
  )} />
}

function SectionPanel({ title, isLoading, formOpen, onToggleForm, createLabel, itemCount, emptyText, renderForm, renderList }) {
  return (
    <div className="section-content">
      <div className="section-header">
        <h2>{title}</h2>
        <button className="btn-create" onClick={onToggleForm} type="button">
          {formOpen ? 'Cerrar formulario' : createLabel}
        </button>
      </div>
      {isLoading ? (
        <div className="loading">Cargando...</div>
      ) : (
        <>
          <div className="section-meta">{itemCount} registros</div>
          {formOpen && renderForm()}
          {itemCount > 0 ? renderList() : !formOpen ? <div className="empty-state"><p>{emptyText}</p></div> : null}
        </>
      )}
    </div>
  )
}

function ReadOnlySection({ title, isLoading, itemCount, emptyText, renderList }) {
  return (
    <div className="section-content">
      <div className="section-header">
        <h2>{title}</h2>
      </div>
      {isLoading ? (
        <div className="loading">Cargando...</div>
      ) : (
        <>
          <div className="section-meta">{itemCount} registros</div>
          {itemCount > 0 ? renderList() : <div className="empty-state"><p>{emptyText}</p></div>}
        </>
      )}
    </div>
  )
}

function TableList({ headers, rows, renderRow }) {
  return (
    <div className="table-list">
      <table>
        <thead>
          <tr>{headers.map((header) => <th key={header}>{header}</th>)}</tr>
        </thead>
        <tbody>{rows.map(renderRow)}</tbody>
      </table>
    </div>
  )
}

function HomeSection({ user, title }) {
  return (
    <div className="section-content">
      <div className="welcome-card">
        <h2>Bienvenido, {displayNameFor(user)}</h2>
        <p>{title}. Selecciona una opción en el menú lateral para comenzar.</p>
      </div>
    </div>
  )
}

function translateRole(role) {
  const roles = {
    ADMINISTRATOR: 'Administrador',
    HUMANRESOURCES: 'Recursos Humanos',
    INFORMATIONSUPPORT: 'Soporte de Información',
    NURSES: 'Enfermera',
    DOCTORS: 'Doctor',
    PATIENT: 'Paciente',
  }
  return roles[normalizeRole(role)] || role || '-'
}

function formatDate(value) {
  if (!value) return '-'
  return String(value).split('T')[0]
}

function formatTime(value) {
  if (!value || !String(value).includes('T')) return '-'
  return String(value).split('T')[1].slice(0, 5)
}

function formatPerson(name, document) {
  const safeName = formatDisplayValue(name)
  const safeDocument = formatDisplayValue(document)

  if (safeName !== '-' && safeDocument !== '-') return `${safeName} (${safeDocument})`
  return safeName !== '-' ? safeName : safeDocument
}

function formatDisplayValue(value) {
  if (value == null || value === '') return '-'
  if (typeof value !== 'object') return String(value)
  return value.fullName || value.name || value.document || value.id || '-'
}

export default UserDashboard
