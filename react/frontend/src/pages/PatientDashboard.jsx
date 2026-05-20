import { useEffect, useState } from 'react'
import Sidebar from '../components/Sidebar'
import ConfirmDialog from '../components/ConfirmDialog'
import {
  createPatientAppointment,
  deletePatientAppointment,
  fetchPatientAppointments,
  fetchPatientDoctors,
  fetchPatientMedicalRecords,
  fetchPatientProfile,
} from '../services/api'
import './AdminDashboard.css'

const emptyAppointmentForm = {
  doctorDocument: '',
  date: '',
  time: '',
}

function PatientDashboard({ user }) {
  const [currentSection, setCurrentSection] = useState('home')
  const [profile, setProfile] = useState(null)
  const [doctors, setDoctors] = useState([])
  const [appointments, setAppointments] = useState([])
  const [records, setRecords] = useState([])
  const [appointmentForm, setAppointmentForm] = useState(emptyAppointmentForm)
  const [confirmData, setConfirmData] = useState(null)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')
  const [isLoading, setIsLoading] = useState(false)

  const sections = [
    { id: 'profile', label: 'Mi Perfil' },
    { id: 'appointments', label: 'Mis Citas' },
    { id: 'records', label: 'Historial Médico' },
    { id: 'doctors', label: 'Doctores' },
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
    return typeof data === 'string' ? data : data?.message || data?.error || err.message || fallback
  }

  const loadProfile = async () => {
    try {
      setIsLoading(true)
      const response = await fetchPatientProfile()
      setProfile(response.data)
    } catch (err) {
      showError(apiError(err, 'No se pudo cargar tu perfil'))
    } finally {
      setIsLoading(false)
    }
  }

  const loadDoctors = async () => {
    try {
      setIsLoading(true)
      const response = await fetchPatientDoctors()
      setDoctors(ensureArray(response.data))
    } catch (err) {
      showError(apiError(err, 'No se pudieron cargar los doctores'))
    } finally {
      setIsLoading(false)
    }
  }

  const loadAppointments = async () => {
    try {
      setIsLoading(true)
      const response = await fetchPatientAppointments()
      setAppointments(ensureArray(response.data))
    } catch (err) {
      showError(apiError(err, 'No se pudieron cargar tus citas'))
    } finally {
      setIsLoading(false)
    }
  }

  const loadRecords = async () => {
    try {
      setIsLoading(true)
      const response = await fetchPatientMedicalRecords()
      setRecords(ensureArray(response.data))
    } catch (err) {
      showError(apiError(err, 'No se pudo cargar tu historial médico'))
    } finally {
      setIsLoading(false)
    }
  }

  useEffect(() => {
    // eslint-disable-next-line react-hooks/set-state-in-effect
    if (currentSection === 'profile') loadProfile()
    if (currentSection === 'doctors') loadDoctors()
    if (currentSection === 'appointments') {
      loadAppointments()
      loadDoctors()
    }
    if (currentSection === 'records') loadRecords()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentSection])

  const handleCreateAppointment = async (event) => {
    event.preventDefault()
    try {
      await createPatientAppointment({
        doctor: { document: Number(appointmentForm.doctorDocument) },
        date: appointmentForm.date,
        time: appointmentForm.time,
      })
      showSuccess('Cita médica agendada correctamente')
      setAppointmentForm(emptyAppointmentForm)
      loadAppointments()
    } catch (err) {
      showError(apiError(err, 'Error al agendar la cita'))
    }
  }

  const confirmCancelAppointment = async () => {
    try {
      await deletePatientAppointment(confirmData.id)
      showSuccess('Cita médica cancelada correctamente')
      loadAppointments()
    } catch (err) {
      showError(apiError(err, 'Error al cancelar la cita'))
    } finally {
      setConfirmData(null)
    }
  }

  const renderSection = () => {
    if (currentSection === 'profile') return renderProfile()
    if (currentSection === 'appointments') return renderAppointments()
    if (currentSection === 'records') return renderRecords()
    if (currentSection === 'doctors') return renderDoctors()
    return <HomeSection user={user} />
  }

  const renderProfile = () => (
    <div className="section-content">
      <div className="section-header">
        <h2>Mi Perfil</h2>
      </div>
      {isLoading ? <div className="loading">Cargando...</div> : (
        <div className="dashboard-card">
          <p><strong>Nombre:</strong> {profile?.fullName || '-'}</p>
          <p><strong>Usuario:</strong> {profile?.userName || user?.username || '-'}</p>
          <p><strong>Documento:</strong> {profile?.document || '-'}</p>
          <p><strong>Email:</strong> {profile?.email || '-'}</p>
          <p><strong>Teléfono:</strong> {profile?.phoneNumber || '-'}</p>
          <p><strong>Dirección:</strong> {profile?.address || '-'}</p>
        </div>
      )}
    </div>
  )

  const renderAppointments = () => (
    <div className="section-content">
      <div className="section-header">
        <h2>Mis Citas</h2>
      </div>
      <div className="inline-form-card">
        <h3>Agendar cita</h3>
        <form className="form-card" onSubmit={handleCreateAppointment}>
          <label>
            Doctor
            <select required value={appointmentForm.doctorDocument} onChange={(e) => setAppointmentForm({ ...appointmentForm, doctorDocument: e.target.value })}>
              <option value="">Selecciona un doctor</option>
              {doctors.map((doctor) => (
                <option key={doctor.id || doctor.document} value={doctor.document}>
                  {doctor.fullName} - {doctor.document}
                </option>
              ))}
            </select>
          </label>
          <label>
            Fecha
            <input required type="date" value={appointmentForm.date} onChange={(e) => setAppointmentForm({ ...appointmentForm, date: e.target.value })} />
          </label>
          <label>
            Hora
            <input required type="time" value={appointmentForm.time} onChange={(e) => setAppointmentForm({ ...appointmentForm, time: e.target.value })} />
          </label>
          <div className="form-actions">
            <button className="btn-create" type="submit">Agendar cita</button>
          </div>
        </form>
      </div>
      {isLoading ? <div className="loading">Cargando...</div> : (
        appointments.length > 0 ? (
          <TableList
            headers={['Fecha', 'Hora', 'Doctor', 'Documento', 'Acciones']}
            rows={appointments}
            renderRow={(appointment, index) => (
              <tr key={appointment.appointmentId || appointment.id || index}>
                <td>{formatDate(appointment.date)}</td>
                <td>{appointment.time || formatTime(appointment.date)}</td>
                <td>{appointment.doctorName || '-'}</td>
                <td>{appointment.doctorDocument || '-'}</td>
                <td>
                  <button className="btn-danger" onClick={() => setConfirmData({ id: appointment.appointmentId || appointment.id })}>
                    Cancelar
                  </button>
                </td>
              </tr>
            )}
          />
        ) : <div className="empty-state"><p>No tienes citas agendadas</p></div>
      )}
    </div>
  )

  const renderRecords = () => (
    <ReadOnlySection
      title="Historial Médico"
      isLoading={isLoading}
      itemCount={records.length}
      emptyText="No tienes registros médicos disponibles"
      renderList={() => (
        <TableList
          headers={['Fecha', 'Doctor', 'Sintomatología', 'Motivo', 'Diagnóstico']}
          rows={records}
          renderRow={(record, index) => (
            <tr key={record.id || index}>
              <td>{formatDate(record.date)}</td>
              <td>{record.doctorName || record.doctorDocument || '-'}</td>
              <td>{record.symptomatology || '-'}</td>
              <td>{record.reasonConsultation || '-'}</td>
              <td>{record.diagnosis || '-'}</td>
            </tr>
          )}
        />
      )}
    />
  )

  const renderDoctors = () => (
    <ReadOnlySection
      title="Doctores"
      isLoading={isLoading}
      itemCount={doctors.length}
      emptyText="No hay doctores disponibles"
      renderList={() => (
        <TableList
          headers={['Documento', 'Nombre', 'Email', 'Teléfono']}
          rows={doctors}
          renderRow={(doctor, index) => (
            <tr key={doctor.id || index}>
              <td>{doctor.document}</td>
              <td>{doctor.fullName}</td>
              <td>{doctor.email || '-'}</td>
              <td>{doctor.phoneNumber || '-'}</td>
            </tr>
          )}
        />
      )}
    />
  )

  return (
    <div className="dashboard-layout">
      <Sidebar currentSection={currentSection} onSectionChange={setCurrentSection} sections={sections} />
      <main className="dashboard-content">
        {message && <div className="alert alert-success">{message}</div>}
        {error && <div className="alert alert-error">{error}</div>}
        {renderSection()}
        <ConfirmDialog
          isOpen={!!confirmData}
          title="Cancelar cita"
          message="¿Estás seguro de que deseas cancelar esta cita?"
          onConfirm={confirmCancelAppointment}
          onCancel={() => setConfirmData(null)}
          confirmText="Sí, cancelar"
        />
      </main>
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

function HomeSection({ user }) {
  const displayName = user?.fullName || user?.username || 'Paciente'
  return (
    <div className="section-content">
      <div className="welcome-card">
        <h2>Bienvenido, {displayName}</h2>
        <p>Panel de Paciente. Selecciona una opción en el menú lateral para comenzar.</p>
      </div>
    </div>
  )
}

function formatDate(value) {
  if (!value) return '-'
  return String(value).split('T')[0]
}

function formatTime(value) {
  if (!value || !String(value).includes('T')) return '-'
  return String(value).split('T')[1].slice(0, 5)
}

export default PatientDashboard
