import { useEffect, useState } from 'react'
import Sidebar from '../components/Sidebar'
import ConfirmDialog from '../components/ConfirmDialog'
import {
  createMedicalRecord,
  createOrder,
  deleteMedicalRecord,
  deleteOrder,
  fetchMedicalRecord,
  fetchMedicalRecords,
  fetchOrders,
  fetchOrdersByPatient,
  updateMedicalRecord,
  updateOrder,
} from '../services/api'
import './AdminDashboard.css'

const emptyOrderForm = {
  patientDocument: '',
  medications: '',
  procedure: '',
  diagnosticAid: '',
}

const emptyRecordForm = {
  patientDocument: '',
  doctorDocument: '',
  symptomatology: '',
  reasonConsultation: '',
  diagnosis: '',
  date: '',
}

function DoctorDashboard({ user }) {
  const [currentSection, setCurrentSection] = useState('home')
  const [expandedSection, setExpandedSection] = useState('')
  const [showConfirm, setShowConfirm] = useState(false)
  const [confirmData, setConfirmData] = useState(null)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')
  const [isLoading, setIsLoading] = useState(false)
  const [isSaving, setIsSaving] = useState(false)

  const [orders, setOrders] = useState([])
  const [records, setRecords] = useState([])
  const [orderForm, setOrderForm] = useState(emptyOrderForm)
  const [recordForm, setRecordForm] = useState(emptyRecordForm)
  const [editingOrderId, setEditingOrderId] = useState(null)
  const [editingRecordId, setEditingRecordId] = useState(null)
  const [orderSearch, setOrderSearch] = useState('')
  const [recordSearch, setRecordSearch] = useState('')

  const sections = [
    { id: 'orders', label: 'Órdenes Médicas' },
    { id: 'records', label: 'Registros' },
  ]

  const ensureArray = (value) => {
    if (Array.isArray(value)) return value
    if (Array.isArray(value?.data)) return value.data
    return value ? [value] : []
  }

  const showSuccess = (text) => {
    setError('')
    setMessage(text)
    window.setTimeout(() => setMessage(''), 3000)
  }

  const showError = (text) => {
    setMessage('')
    setError(text)
    window.setTimeout(() => setError(''), 3000)
  }

  const apiError = (err, fallback) => {
    const data = err.response?.data
    if (typeof data === 'string') return data
    if (data?.message) return data.message
    if (data?.error) return data.error
    return err.message || fallback
  }

  const getPatientDocument = (item) => item.patient?.document || item.patientDocument || ''
  const getPatientName = (item) => item.patient?.fullName || item.patientName || '-'
  const getDoctorDocument = (item) => item.doctor?.document || item.doctorDocument || ''

  const formatDateInput = (value) => {
    if (!value) return ''
    return String(value).split('T')[0]
  }

  const loadOrders = async () => {
    try {
      setIsLoading(true)
      const response = await fetchOrders()
      setOrders(ensureArray(response.data))
    } catch (err) {
      showError(apiError(err, 'No se pudieron cargar las órdenes médicas'))
    } finally {
      setIsLoading(false)
    }
  }

  const loadRecords = async () => {
    try {
      setIsLoading(true)
      const response = await fetchMedicalRecords()
      setRecords(ensureArray(response.data))
    } catch (err) {
      showError(apiError(err, 'No se pudieron cargar los registros médicos'))
    } finally {
      setIsLoading(false)
    }
  }

  useEffect(() => {
    // eslint-disable-next-line react-hooks/set-state-in-effect
    if (currentSection === 'orders') loadOrders()
    if (currentSection === 'records') loadRecords()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentSection])

  const handleOrderSubmit = async (event) => {
    event.preventDefault()
    setError('')
    setMessage(editingOrderId ? 'Actualizando orden...' : 'Guardando orden...')
    setIsSaving(true)
    if (editingOrderId) {
      await handleOrderUpdate()
    } else {
      await handleOrderCreate()
    }
    setIsSaving(false)
  }

  const handleOrderCreate = async () => {
    try {
      const payload = {
        patient: { document: Number(orderForm.patientDocument) },
        medications: orderForm.medications,
        procedure: orderForm.procedure,
        diagnosticAid: orderForm.diagnosticAid,
      }
      const response = await createOrder(payload)
      setOrders((currentOrders) => [...currentOrders, response.data])
      showSuccess('Orden médica creada correctamente')
      setOrderForm(emptyOrderForm)
      setExpandedSection('')
      loadOrders()
    } catch (err) {
      showError(apiError(err, 'Error al crear orden médica'))
    }
  }

  const handleOrderEdit = (order) => {
    setEditingOrderId(order.orderId || order.id)
    setOrderForm({
      patientDocument: getPatientDocument(order),
      medications: order.medications || '',
      procedure: order.procedure || '',
      diagnosticAid: order.diagnosticAid || '',
    })
    setExpandedSection('orders')
  }

  const handleOrderUpdate = async () => {
    try {
      const payload = {
        patient: orderForm.patientDocument ? { document: Number(orderForm.patientDocument) } : undefined,
        medications: orderForm.medications,
        procedure: orderForm.procedure,
        diagnosticAid: orderForm.diagnosticAid,
      }
      await updateOrder(editingOrderId, payload)
      showSuccess('Orden médica actualizada correctamente')
      setEditingOrderId(null)
      setOrderForm(emptyOrderForm)
      setExpandedSection('')
      loadOrders()
    } catch (err) {
      showError(apiError(err, 'Error al actualizar orden médica'))
    }
  }

  const handleOrderSearch = async () => {
    if (!orderSearch) return loadOrders()
    try {
      const response = await fetchOrdersByPatient(Number(orderSearch))
      setOrders(ensureArray(response.data))
      showSuccess('Búsqueda de órdenes completada')
    } catch (err) {
      showError(apiError(err, 'Error al buscar órdenes'))
    }
  }

  const handleRecordSubmit = async (event) => {
    event.preventDefault()
    setError('')
    setMessage(editingRecordId ? 'Actualizando registro...' : 'Guardando registro...')
    setIsSaving(true)
    if (editingRecordId) {
      await handleRecordUpdate()
    } else {
      await handleRecordCreate()
    }
    setIsSaving(false)
  }

  const handleRecordCreate = async () => {
    try {
      const payload = {
        patient: { document: Number(recordForm.patientDocument) },
        doctor: { document: Number(recordForm.doctorDocument) },
        symptomatology: recordForm.symptomatology,
        reasonConsultation: recordForm.reasonConsultation,
        diagnosis: recordForm.diagnosis,
        date: recordForm.date,
      }
      const response = await createMedicalRecord(payload)
      setRecords((currentRecords) => [...currentRecords, response.data])
      showSuccess('Registro médico creado correctamente')
      setRecordForm(emptyRecordForm)
      setExpandedSection('')
      loadRecords()
    } catch (err) {
      showError(apiError(err, 'Error al crear registro médico'))
    }
  }

  const handleRecordEdit = (record) => {
    setEditingRecordId(record.id)
    setRecordForm({
      patientDocument: getPatientDocument(record),
      doctorDocument: getDoctorDocument(record),
      symptomatology: record.symptomatology || '',
      reasonConsultation: record.reasonConsultation || '',
      diagnosis: record.diagnosis || '',
      date: formatDateInput(record.date),
    })
    setExpandedSection('records')
  }

  const handleRecordUpdate = async () => {
    try {
      const payload = {
        symptomatology: recordForm.symptomatology,
        reasonConsultation: recordForm.reasonConsultation,
        diagnosis: recordForm.diagnosis,
        date: recordForm.date,
      }
      await updateMedicalRecord(editingRecordId, payload)
      showSuccess('Registro médico actualizado correctamente')
      setEditingRecordId(null)
      setRecordForm(emptyRecordForm)
      setExpandedSection('')
      loadRecords()
    } catch (err) {
      showError(apiError(err, 'Error al actualizar registro'))
    }
  }

  const handleRecordSearch = async () => {
    if (!recordSearch) return loadRecords()
    try {
      const response = await fetchMedicalRecord(Number(recordSearch))
      setRecords(ensureArray(response.data))
      showSuccess('Búsqueda de registros completada')
    } catch (err) {
      showError(apiError(err, 'Error al buscar historial médico'))
    }
  }

  const confirmDelete = async () => {
    try {
      if (confirmData?.type === 'order') {
        await deleteOrder(confirmData.id)
        showSuccess('Orden médica eliminada correctamente')
        loadOrders()
      }
      if (confirmData?.type === 'record') {
        await deleteMedicalRecord(confirmData.id)
        showSuccess('Registro médico eliminado correctamente')
        loadRecords()
      }
    } catch (err) {
      showError(apiError(err, 'Error al eliminar'))
    } finally {
      setShowConfirm(false)
      setConfirmData(null)
    }
  }

  const toggleSection = (section) => {
    setExpandedSection(expandedSection === section ? '' : section)
  }

  const renderSection = () => {
    switch (currentSection) {
      case 'orders':
        return (
          <SectionPanel
            title="Órdenes Médicas"
            isLoading={isLoading}
            formOpen={expandedSection === 'orders'}
            onToggleForm={() => toggleSection('orders')}
            createLabel={editingOrderId ? 'Editar Orden' : 'Crear Orden'}
            itemCount={orders.length}
            emptyText="No hay órdenes médicas registradas"
            renderForm={() => renderOrderForm()}
            renderList={() => renderOrderList()}
          />
        )
      case 'records':
        return (
          <SectionPanel
            title="Registros Médicos"
            isLoading={isLoading}
            formOpen={expandedSection === 'records'}
            onToggleForm={() => toggleSection('records')}
            createLabel={editingRecordId ? 'Editar Registro' : 'Crear Registro'}
            itemCount={records.length}
            emptyText="No hay registros médicos registrados"
            renderForm={() => renderRecordForm()}
            renderList={() => renderRecordList()}
          />
        )
      default:
        return <HomeSection user={user} />
    }
  }

  const renderOrderForm = () => (
    <div className="inline-form-card">
      <h3>{editingOrderId ? 'Editar orden médica' : 'Nueva orden médica'}</h3>
      <form className="form-card" onSubmit={handleOrderSubmit}>
        <label>
          Documento del paciente
          <input required type="text" value={orderForm.patientDocument} onChange={(e) => setOrderForm({ ...orderForm, patientDocument: e.target.value })} placeholder="Ej: 1234567890" />
        </label>
        <label>
          Medicamentos
          <input type="text" value={orderForm.medications} onChange={(e) => setOrderForm({ ...orderForm, medications: e.target.value })} placeholder="Ej: Paracetamol" />
        </label>
        <label>
          Procedimiento
          <input type="text" value={orderForm.procedure} onChange={(e) => setOrderForm({ ...orderForm, procedure: e.target.value })} placeholder="Ej: Examen de laboratorio" />
        </label>
        <label>
          Ayuda diagnóstica
          <input type="text" value={orderForm.diagnosticAid} onChange={(e) => setOrderForm({ ...orderForm, diagnosticAid: e.target.value })} placeholder="Ej: Radiografía" />
        </label>
        <div className="form-actions">
          <button className="btn-create" disabled={isSaving} type="submit">
            {isSaving ? 'Guardando...' : editingOrderId ? 'Actualizar orden' : 'Guardar orden'}
          </button>
          <button className="btn-secondary" onClick={() => { setEditingOrderId(null); setOrderForm(emptyOrderForm); setExpandedSection('') }} type="button">Cancelar</button>
        </div>
      </form>
    </div>
  )

  const renderRecordForm = () => (
    <div className="inline-form-card">
      <h3>{editingRecordId ? 'Editar registro médico' : 'Nuevo registro médico'}</h3>
      <form className="form-card" onSubmit={handleRecordSubmit}>
        {!editingRecordId && (
          <>
            <label>
              Documento del paciente
              <input required type="text" value={recordForm.patientDocument} onChange={(e) => setRecordForm({ ...recordForm, patientDocument: e.target.value })} placeholder="Ej: 1234567890" />
            </label>
            <label>
              Documento del doctor
              <input required type="text" value={recordForm.doctorDocument} onChange={(e) => setRecordForm({ ...recordForm, doctorDocument: e.target.value })} placeholder="Ej: 9876543210" />
            </label>
          </>
        )}
        <label>
          Sintomatología
          <input type="text" value={recordForm.symptomatology} onChange={(e) => setRecordForm({ ...recordForm, symptomatology: e.target.value })} placeholder="Ej: Dolor abdominal y fiebre" />
        </label>
        <label>
          Motivo de consulta
          <input type="text" value={recordForm.reasonConsultation} onChange={(e) => setRecordForm({ ...recordForm, reasonConsultation: e.target.value })} placeholder="Ej: Control médico general" />
        </label>
        <label>
          Diagnóstico
          <input type="text" value={recordForm.diagnosis} onChange={(e) => setRecordForm({ ...recordForm, diagnosis: e.target.value })} placeholder="Ej: Gastritis aguda" />
        </label>
        <label>
          Fecha
          <input type="date" value={recordForm.date} onChange={(e) => setRecordForm({ ...recordForm, date: e.target.value })} placeholder="Ej: 2026-05-19" title="Ej: 2026-05-19" />
        </label>
        <div className="form-actions">
          <button className="btn-create" disabled={isSaving} type="submit">
            {isSaving ? 'Guardando...' : editingRecordId ? 'Actualizar registro' : 'Guardar registro'}
          </button>
          <button className="btn-secondary" onClick={() => { setEditingRecordId(null); setRecordForm(emptyRecordForm); setExpandedSection('') }} type="button">Cancelar</button>
        </div>
      </form>
    </div>
  )

  const renderOrderList = () => (
    <div>
      <SearchBar value={orderSearch} onChange={setOrderSearch} onSearch={handleOrderSearch} onClear={() => { setOrderSearch(''); loadOrders() }} />
      <TableList
        headers={['ID', 'Paciente', 'Documento', 'Medicamentos', 'Procedimiento', 'Ayuda diagnóstica', 'Acciones']}
        rows={orders}
        renderRow={(order, index) => (
          <tr key={order.orderId || order.id || `new-order-${index}`}>
            <td>{order.orderId || order.id || 'Nuevo'}</td>
            <td>{getPatientName(order)}</td>
            <td>{getPatientDocument(order) || '-'}</td>
            <td>{order.medications || '-'}</td>
            <td>{order.procedure || '-'}</td>
            <td>{order.diagnosticAid || '-'}</td>
            <td>
              {order.orderId || order.id ? (
                <>
                  <button className="btn-secondary" onClick={() => handleOrderEdit(order)}>Editar</button>
                  <button className="btn-danger" onClick={() => { setConfirmData({ type: 'order', id: order.orderId || order.id }); setShowConfirm(true) }}>Eliminar</button>
                </>
              ) : (
                <span>Guardado</span>
              )}
            </td>
          </tr>
        )}
      />
    </div>
  )

  const renderRecordList = () => (
    <div>
      <SearchBar value={recordSearch} onChange={setRecordSearch} onSearch={handleRecordSearch} onClear={() => { setRecordSearch(''); loadRecords() }} />
      <TableList
        headers={['ID', 'Paciente', 'Documento', 'Doctor', 'Fecha', 'Sintomatología', 'Motivo', 'Diagnóstico', 'Acciones']}
        rows={records}
        renderRow={(record, index) => (
          <tr key={record.id || `new-record-${index}`}>
            <td>{record.id || 'Nuevo'}</td>
            <td>{getPatientName(record)}</td>
            <td>{getPatientDocument(record) || '-'}</td>
            <td>{getDoctorDocument(record) || '-'}</td>
            <td>{formatDateInput(record.date) || '-'}</td>
            <td>{record.symptomatology || '-'}</td>
            <td>{record.reasonConsultation || '-'}</td>
            <td>{record.diagnosis || '-'}</td>
            <td>
              {record.id ? (
                <>
                  <button className="btn-secondary" onClick={() => handleRecordEdit(record)}>Editar</button>
                  <button className="btn-danger" onClick={() => { setConfirmData({ type: 'record', id: record.id }); setShowConfirm(true) }}>Eliminar</button>
                </>
              ) : (
                <span>Guardado</span>
              )}
            </td>
          </tr>
        )}
      />
    </div>
  )

  return (
    <div className="dashboard-layout">
      <Sidebar currentSection={currentSection} onSectionChange={setCurrentSection} sections={sections} />
      <main className="dashboard-content">
        {message && <div className="alert alert-success">{message}</div>}
        {error && <div className="alert alert-error">{error}</div>}
        {renderSection()}
        <ConfirmDialog
          isOpen={showConfirm}
          title="Eliminar registro"
          message="¿Estás seguro de que quieres eliminar este elemento? Esta acción no se puede deshacer."
          onConfirm={confirmDelete}
          onCancel={() => setShowConfirm(false)}
          confirmText="Sí, eliminar"
        />
      </main>
    </div>
  )
}

function SearchBar({ value, onChange, onSearch, onClear }) {
  return (
    <div className="list-actions">
      <input type="text" placeholder="Buscar por documento del paciente" value={value} onChange={(e) => onChange(e.target.value)} />
      <button className="btn-secondary" onClick={onSearch} type="button">Buscar</button>
      <button className="btn-secondary" onClick={onClear} type="button">Limpiar</button>
    </div>
  )
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

function HomeSection({ user }) {
  const displayName = user?.fullName || user?.username || 'Doctor'

  return (
    <div className="section-content">
      <div className="welcome-card">
        <h2>Bienvenido, {displayName}</h2>
        <p>Panel de Doctor. Selecciona una opción en el menú lateral para comenzar.</p>
      </div>
    </div>
  )
}

export default DoctorDashboard
