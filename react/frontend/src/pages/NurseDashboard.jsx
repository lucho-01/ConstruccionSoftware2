import { useEffect, useState } from 'react'
import Sidebar from '../components/Sidebar'
import ConfirmDialog from '../components/ConfirmDialog'
import {
  createNurseEmergencyContact,
  deleteNurseEmergencyContact,
  deleteVisit,
  fetchNurseEmergencyContacts,
  fetchVisitByPatient,
  fetchVisits,
  registerVisit,
  searchOrder,
  updateNurseEmergencyContact,
  updateVisit,
} from '../services/api'
import './AdminDashboard.css'

const emptyVisitForm = {
  patientDocument: '',
  bloodPressure: '',
  temperature: '',
  pulse: '',
  oxygenLevel: '',
  medications: '',
  procedure: '',
  diagnosticAid: '',
}

const emptyContactForm = {
  patientDocument: '',
  name: '',
  lastName: '',
  phoneNumber: '',
}

function NurseDashboard({ user }) {
  const [currentSection, setCurrentSection] = useState('home')
  const [expandedSection, setExpandedSection] = useState('')
  const [showConfirm, setShowConfirm] = useState(false)
  const [confirmData, setConfirmData] = useState(null)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')
  const [isLoading, setIsLoading] = useState(false)

  const [orderSearch, setOrderSearch] = useState('')
  const [searchResults, setSearchResults] = useState([])
  const [visits, setVisits] = useState([])
  const [visitSearch, setVisitSearch] = useState('')
  const [visitForm, setVisitForm] = useState(emptyVisitForm)
  const [editingVisitId, setEditingVisitId] = useState(null)
  const [emergencyContacts, setEmergencyContacts] = useState([])
  const [contactSearch, setContactSearch] = useState('')
  const [contactForm, setContactForm] = useState(emptyContactForm)
  const [editingContactId, setEditingContactId] = useState(null)

  const sections = [
    { id: 'search', label: 'Buscar Orden' },
    { id: 'visits', label: 'Visitas' },
    { id: 'emergencyContacts', label: 'Contactos de Emergencia' },
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

  const loadVisits = async () => {
    try {
      setIsLoading(true)
      const response = await fetchVisits()
      setVisits(ensureArray(response.data).filter(Boolean))
    } catch (err) {
      showError(apiError(err, 'No se pudieron cargar las visitas'))
    } finally {
      setIsLoading(false)
    }
  }

  const loadEmergencyContacts = async () => {
    try {
      setIsLoading(true)
      const response = await fetchNurseEmergencyContacts()
      setEmergencyContacts(ensureArray(response.data).filter(Boolean))
    } catch (err) {
      showError(apiError(err, 'No se pudieron cargar los contactos de emergencia'))
    } finally {
      setIsLoading(false)
    }
  }

  useEffect(() => {
    // eslint-disable-next-line react-hooks/set-state-in-effect
    if (currentSection === 'visits') loadVisits()
    if (currentSection === 'emergencyContacts') loadEmergencyContacts()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentSection])

  const handleSearchOrders = async (event) => {
    event.preventDefault()
    setSearchResults([])
    setMessage('')
    setError('')

    try {
      const payload = { patient: { document: Number(orderSearch) } }
      const response = await searchOrder(payload)
      setSearchResults(ensureArray(response.data))
      showSuccess('Búsqueda completada')
    } catch (err) {
      showError(apiError(err, 'Error al buscar orden médica'))
    }
  }

  const handleVisitCreate = async () => {
    try {
      const payload = buildVisitPayload(true)
      await registerVisit(payload)
      showSuccess('Visita registrada correctamente')
      setVisitForm(emptyVisitForm)
      setExpandedSection('')
      loadVisits()
    } catch (err) {
      showError(apiError(err, 'Error al registrar visita'))
    }
  }

  const handleVisitEdit = (visit) => {
    setEditingVisitId(visit.id)
    setVisitForm({
      patientDocument: getPatientDocument(visit),
      bloodPressure: visit.bloodPressure ?? '',
      temperature: visit.temperature ?? '',
      pulse: visit.pulse ?? '',
      oxygenLevel: visit.oxygenLevel ?? '',
      medications: visit.medications || '',
      procedure: visit.procedure || '',
      diagnosticAid: visit.diagnosticAid || '',
    })
    setExpandedSection('visits')
  }

  const handleVisitUpdate = async () => {
    try {
      const payload = buildVisitPayload(false)
      await updateVisit(editingVisitId, payload)
      showSuccess('Visita actualizada correctamente')
      setEditingVisitId(null)
      setVisitForm(emptyVisitForm)
      setExpandedSection('')
      loadVisits()
    } catch (err) {
      showError(apiError(err, 'Error al actualizar visita'))
    }
  }

  const handleVisitSearch = async () => {
    if (!visitSearch) return loadVisits()
    try {
      const response = await fetchVisitByPatient(Number(visitSearch))
      setVisits(ensureArray(response.data).filter(Boolean))
      showSuccess('Búsqueda de visitas completada')
    } catch (err) {
      showError(apiError(err, 'Error al buscar visita'))
    }
  }

  const buildVisitPayload = (includePatient) => ({
    patient: includePatient || visitForm.patientDocument ? { document: Number(visitForm.patientDocument) } : undefined,
    bloodPressure: Number(visitForm.bloodPressure),
    temperature: Number(visitForm.temperature),
    pulse: Number(visitForm.pulse),
    oxygenLevel: Number(visitForm.oxygenLevel),
    medications: visitForm.medications,
    procedure: visitForm.procedure,
    diagnosticAid: visitForm.diagnosticAid,
  })

  const buildContactPayload = (includePatient) => ({
    name: contactForm.name,
    lastName: contactForm.lastName,
    phoneNumber: contactForm.phoneNumber,
    patient: includePatient || contactForm.patientDocument ? { document: Number(contactForm.patientDocument) } : undefined,
  })

  const handleContactCreate = async () => {
    try {
      await createNurseEmergencyContact(buildContactPayload(true))
      showSuccess('Contacto de emergencia creado correctamente')
      setContactForm(emptyContactForm)
      setExpandedSection('')
      loadEmergencyContacts()
    } catch (err) {
      showError(apiError(err, 'Error al crear contacto de emergencia'))
    }
  }

  const handleContactEdit = (contact) => {
    setEditingContactId(contact.id)
    setContactForm({
      patientDocument: getPatientDocument(contact),
      name: contact.name || '',
      lastName: contact.lastName || '',
      phoneNumber: contact.phoneNumber || '',
    })
    setExpandedSection('emergencyContacts')
  }

  const handleContactUpdate = async () => {
    try {
      await updateNurseEmergencyContact(editingContactId, buildContactPayload(false))
      showSuccess('Contacto de emergencia actualizado correctamente')
      setEditingContactId(null)
      setContactForm(emptyContactForm)
      setExpandedSection('')
      loadEmergencyContacts()
    } catch (err) {
      showError(apiError(err, 'Error al actualizar contacto de emergencia'))
    }
  }

  const handleContactSearch = () => {
    if (!contactSearch) return loadEmergencyContacts()
    const term = contactSearch.toLowerCase()
    setEmergencyContacts((contacts) => contacts.filter((contact) => {
      const document = String(getPatientDocument(contact)).toLowerCase()
      const patientName = String(getPatientName(contact)).toLowerCase()
      const contactName = `${contact.name || ''} ${contact.lastName || ''}`.toLowerCase()
      return document.includes(term) || patientName.includes(term) || contactName.includes(term)
    }))
  }

  const confirmDelete = async () => {
    try {
      if (confirmData?.type === 'visit') {
        await deleteVisit(confirmData.id)
        showSuccess('Visita eliminada correctamente')
        loadVisits()
      }
      if (confirmData?.type === 'contact') {
        await deleteNurseEmergencyContact(confirmData.id)
        showSuccess('Contacto de emergencia eliminado correctamente')
        loadEmergencyContacts()
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
      case 'search':
        return renderOrderSearch()
      case 'visits':
        return (
          <SectionPanel
            title="Visitas"
            isLoading={isLoading}
            formOpen={expandedSection === 'visits'}
            onToggleForm={() => toggleSection('visits')}
            createLabel={editingVisitId ? 'Editar Visita' : 'Crear Visita'}
            itemCount={visits.length}
            emptyText="No hay visitas registradas"
            renderForm={() => renderVisitForm()}
            renderList={() => renderVisitList()}
          />
        )
      case 'emergencyContacts':
        return (
          <SectionPanel
            title="Contactos de Emergencia"
            isLoading={isLoading}
            formOpen={expandedSection === 'emergencyContacts'}
            onToggleForm={() => toggleSection('emergencyContacts')}
            createLabel={editingContactId ? 'Editar Contacto' : 'Crear Contacto'}
            itemCount={emergencyContacts.length}
            emptyText="No hay contactos de emergencia registrados"
            renderForm={() => renderEmergencyContactForm()}
            renderList={() => renderEmergencyContactList()}
          />
        )
      default:
        return <HomeSection user={user} />
    }
  }

  const renderOrderSearch = () => (
    <div className="section-content">
      <div className="section-header">
        <h2>Buscar Orden Médica</h2>
      </div>
      <div className="dashboard-card">
        <form className="form-card order-search-form" onSubmit={handleSearchOrders}>
          <label>
            Documento del paciente
            <input value={orderSearch} onChange={(e) => setOrderSearch(e.target.value)} placeholder="Ingrese documento" />
          </label>
          <button type="submit" className="btn-create btn-search-order">Buscar</button>
        </form>
        {searchResults.length > 0 && (
          <TableList
            headers={['ID', 'Paciente', 'Documento', 'Medicamentos', 'Procedimiento', 'Ayuda diagnóstica']}
            rows={searchResults}
            renderRow={(item, index) => (
              <tr key={item.orderId || item.id || index}>
                <td>{item.orderId || item.id || '-'}</td>
                <td>{getPatientName(item)}</td>
                <td>{getPatientDocument(item) || '-'}</td>
                <td>{item.medications || '-'}</td>
                <td>{item.procedure || '-'}</td>
                <td>{item.diagnosticAid || '-'}</td>
              </tr>
            )}
          />
        )}
      </div>
    </div>
  )

  const renderVisitForm = () => (
    <div className="inline-form-card">
      <h3>{editingVisitId ? 'Editar visita' : 'Registrar visita'}</h3>
      <div className="form-card">
        {!editingVisitId && (
          <label>
            Documento del paciente
            <input type="text" value={visitForm.patientDocument} onChange={(e) => setVisitForm({ ...visitForm, patientDocument: e.target.value })} placeholder="Ej: 1234567890" />
          </label>
        )}
        <label>
          Presión arterial
          <input type="number" value={visitForm.bloodPressure} onChange={(e) => setVisitForm({ ...visitForm, bloodPressure: e.target.value })} placeholder="120" />
        </label>
        <label>
          Temperatura
          <input type="number" step="0.1" value={visitForm.temperature} onChange={(e) => setVisitForm({ ...visitForm, temperature: e.target.value })} placeholder="36.7" />
        </label>
        <label>
          Pulso
          <input type="number" value={visitForm.pulse} onChange={(e) => setVisitForm({ ...visitForm, pulse: e.target.value })} placeholder="72" />
        </label>
        <label>
          Nivel de oxígeno
          <input type="number" value={visitForm.oxygenLevel} onChange={(e) => setVisitForm({ ...visitForm, oxygenLevel: e.target.value })} placeholder="98" />
        </label>
        <label>
          Medicamentos
          <input type="text" value={visitForm.medications} onChange={(e) => setVisitForm({ ...visitForm, medications: e.target.value })} placeholder="Ej: Ibuprofeno" />
        </label>
        <label>
          Procedimiento
          <input type="text" value={visitForm.procedure} onChange={(e) => setVisitForm({ ...visitForm, procedure: e.target.value })} placeholder="Ej: Control de signos" />
        </label>
        <label>
          Ayuda diagnóstica
          <input type="text" value={visitForm.diagnosticAid} onChange={(e) => setVisitForm({ ...visitForm, diagnosticAid: e.target.value })} placeholder="Ej: Electrocardiograma" />
        </label>
        <div className="form-actions">
          <button className="btn-create" onClick={editingVisitId ? handleVisitUpdate : handleVisitCreate} type="button">
            {editingVisitId ? 'Actualizar visita' : 'Guardar visita'}
          </button>
          <button className="btn-secondary" onClick={() => { setEditingVisitId(null); setVisitForm(emptyVisitForm); setExpandedSection('') }} type="button">Cancelar</button>
        </div>
      </div>
    </div>
  )

  const renderVisitList = () => (
    <div>
      <SearchBar value={visitSearch} onChange={setVisitSearch} onSearch={handleVisitSearch} onClear={() => { setVisitSearch(''); loadVisits() }} />
      <TableList
        headers={['ID', 'Paciente', 'Documento', 'Presión', 'Temperatura', 'Pulso', 'Oxígeno', 'Medicamentos', 'Procedimiento', 'Ayuda', 'Acciones']}
        rows={visits}
        renderRow={(visit, index) => (
          <tr key={visit.id || index}>
            <td>{visit.id || '-'}</td>
            <td>{getPatientName(visit)}</td>
            <td>{getPatientDocument(visit) || '-'}</td>
            <td>{visit.bloodPressure}</td>
            <td>{visit.temperature}</td>
            <td>{visit.pulse}</td>
            <td>{visit.oxygenLevel}</td>
            <td>{visit.medications || '-'}</td>
            <td>{visit.procedure || '-'}</td>
            <td>{visit.diagnosticAid || '-'}</td>
            <td>
              <button className="btn-secondary" onClick={() => handleVisitEdit(visit)}>Editar</button>
              <button className="btn-danger" onClick={() => { setConfirmData({ type: 'visit', id: visit.id }); setShowConfirm(true) }}>Eliminar</button>
            </td>
          </tr>
        )}
      />
    </div>
  )

  const renderEmergencyContactForm = () => (
    <div className="inline-form-card">
      <h3>{editingContactId ? 'Editar contacto de emergencia' : 'Nuevo contacto de emergencia'}</h3>
      <div className="form-card">
        {!editingContactId && (
          <label>
            Documento del paciente
            <input type="text" value={contactForm.patientDocument} onChange={(e) => setContactForm({ ...contactForm, patientDocument: e.target.value })} placeholder="Ej: 1234567890" />
          </label>
        )}
        <label>
          Nombre
          <input type="text" value={contactForm.name} onChange={(e) => setContactForm({ ...contactForm, name: e.target.value })} placeholder="Ej: Ana" />
        </label>
        <label>
          Apellido
          <input type="text" value={contactForm.lastName} onChange={(e) => setContactForm({ ...contactForm, lastName: e.target.value })} placeholder="Ej: Pérez" />
        </label>
        <label>
          Teléfono
          <input type="text" value={contactForm.phoneNumber} onChange={(e) => setContactForm({ ...contactForm, phoneNumber: e.target.value })} placeholder="10 dígitos" />
        </label>
        <div className="form-actions">
          <button className="btn-create" onClick={editingContactId ? handleContactUpdate : handleContactCreate} type="button">
            {editingContactId ? 'Actualizar contacto' : 'Guardar contacto'}
          </button>
          <button className="btn-secondary" onClick={() => { setEditingContactId(null); setContactForm(emptyContactForm); setExpandedSection('') }} type="button">Cancelar</button>
        </div>
      </div>
    </div>
  )

  const renderEmergencyContactList = () => (
    <div>
      <SearchBar value={contactSearch} onChange={setContactSearch} onSearch={handleContactSearch} onClear={() => { setContactSearch(''); loadEmergencyContacts() }} />
      <TableList
        headers={['ID', 'Paciente', 'Documento', 'Nombre', 'Apellido', 'Teléfono', 'Acciones']}
        rows={emergencyContacts}
        renderRow={(contact, index) => (
          <tr key={contact.id || index}>
            <td>{contact.id || '-'}</td>
            <td>{getPatientName(contact)}</td>
            <td>{getPatientDocument(contact) || '-'}</td>
            <td>{contact.name || '-'}</td>
            <td>{contact.lastName || '-'}</td>
            <td>{contact.phoneNumber || '-'}</td>
            <td>
              <button className="btn-secondary" onClick={() => handleContactEdit(contact)}>Editar</button>
              <button className="btn-danger" onClick={() => { setConfirmData({ type: 'contact', id: contact.id }); setShowConfirm(true) }}>Eliminar</button>
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
          title={confirmData?.type === 'contact' ? 'Eliminar contacto' : 'Eliminar visita'}
          message="¿Estás seguro de que deseas eliminar este registro? Esta acción no se puede deshacer."
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
  const displayName = user?.fullName || user?.username || 'Enfermería'

  return (
    <div className="section-content">
      <div className="welcome-card">
        <h2>Bienvenido, {displayName}</h2>
        <p>Panel de Enfermería. Selecciona una opción en el menú lateral para comenzar.</p>
      </div>
    </div>
  )
}

export default NurseDashboard
