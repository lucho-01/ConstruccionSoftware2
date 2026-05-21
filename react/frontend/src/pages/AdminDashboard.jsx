import { useEffect, useState } from 'react'
import Sidebar from '../components/Sidebar'
import ConfirmDialog from '../components/ConfirmDialog'
import {
  createPatient,
  updatePatient,
  deletePatient,
  createAppointment,
  createBilling,
  createEmergencyContact,
  createEmployee,
  updateEmployee,
  deleteEmployee,
  fetchPatients,
  fetchEmployees,
  fetchAppointments,
  fetchBillings,
  fetchEmergencyContacts,
  updateBilling,
  updateAppointment,
  updateEmergencyContact,
  deleteAppointment,
  deleteBilling,
  deleteEmergencyContact,
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

const colombianDocumentRegex = /^[1-9][0-9]{5,9}$/
const colombianPhoneRegex = /^(3[0-9]{9}|60[1-8][0-9]{7})$/

function AdminDashboard({ user }) {
  const [currentSection, setCurrentSection] = useState('home')
  const [expandedSection, setExpandedSection] = useState('')
  const [showConfirm, setShowConfirm] = useState(false)
  const [confirmData, setConfirmData] = useState(null)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')
  const [isLoading, setIsLoading] = useState(false)

  const [patients, setPatients] = useState([])
  const [employees, setEmployees] = useState([])
  const [appointments, setAppointments] = useState([])
  const [billings, setBillings] = useState([])
  const [emergencyContacts, setEmergencyContacts] = useState([])

  const [editingBillingId, setEditingBillingId] = useState(null)
  const [editingEmergencyId, setEditingEmergencyId] = useState(null)

  const [patientForm, setPatientForm] = useState({
    document: '',
    fullName: '',
    email: '',
    birthdate: '',
    address: '',
    phoneNumber: '',
    gender: 'MALE',
    weigth: '',
    size: '',
    doctorDocument: '',
    policyNumber: '',
    insuranceCompanyName: '',
    policyValidity: '',
    policyEndDate: '',
  })
  const [editingPatientId, setEditingPatientId] = useState(null)
  const [patientSearch, setPatientSearch] = useState('')
  const [editingEmployeeId, setEditingEmployeeId] = useState(null)
  const [employeeSearch, setEmployeeSearch] = useState('')
  const [appointmentSearch, setAppointmentSearch] = useState('')
  const [billingSearch, setBillingSearch] = useState('')
  const [emergencySearch, setEmergencySearch] = useState('')

  const [employeeForm, setEmployeeForm] = useState(emptyEmployeeForm)

  const [appointmentForm, setAppointmentForm] = useState({
    patientDocument: '',
    doctorDocument: '',
    date: '',
    time: '',
  })
  const [editingAppointmentId, setEditingAppointmentId] = useState(null)

  const [billingForm, setBillingForm] = useState({
    patientDocument: '',
    policyNumber: '',
    patientAge: '',
    doctorDocument: '',
    patientNameDocument: '',
    insuranceCompanyName: '',
    policyValidity: '',
    policyEndDate: '',
  })

  const [emergencyForm, setEmergencyForm] = useState({
    patientDocument: '',
    name: '',
    lastName: '',
    phoneNumber: '',
  })

  const formatDateToDDMMYYYY = (value) => {
    if (!value) return ''
    const [year, month, day] = value.split('-')
    return day && month && year ? `${day}/${month}/${year}` : value
  }

  const formatAppointmentDate = (value) => {
    if (!value) return '-'
    const [datePart] = String(value).split('T')
    const [year, month, day] = datePart.split('-')
    return day && month && year ? `${day}/${month}/${year}` : datePart
  }

  const formatAppointmentTime = (value) => {
    if (!value || !String(value).includes('T')) return '-'
    const timePart = String(value).split('T')[1]
    return timePart ? timePart.slice(0, 5) : '-'
  }

  const normalizePatient = (patient) => ({
    ...patient,
    policyNumber: patient.policyNumber ?? patient.policy_number ?? patient.policy?.number ?? '',
    insuranceCompanyName: patient.insuranceCompanyName ?? patient.insurance_company_name ?? patient.policy?.insuranceCompanyName ?? '',
    policyValidity: patient.policyValidity ?? patient.policy_validity ?? patient.policy?.validity ?? '',
    policyEndDate: patient.policyEndDate ?? patient.policy_end_date ?? patient.policy?.endDate ?? '',
  })

  const formatPolicyValidity = (patient) => {
    if (!patient.policyValidity && !patient.policyEndDate) return 'Sin vigencia registrada'
    if (patient.policyValidity && patient.policyEndDate) return `${patient.policyValidity} - ${patient.policyEndDate}`
    return patient.policyValidity || patient.policyEndDate
  }

  const formatPerson = (name, document, nestedPerson) => {
    const resolvedName = name || nestedPerson?.fullName
    const resolvedDocument = document || nestedPerson?.document

    if (resolvedName && resolvedDocument) return `${resolvedName} (${resolvedDocument})`
    if (resolvedName) return resolvedName
    if (resolvedDocument) return String(resolvedDocument)
    return '-'
  }

  const showSuccess = (message) => {
    setError('')
    setMessage(message)
    setTimeout(() => setMessage(''), 3000)
  }

  const showError = (errorMsg) => {
    setMessage('')
    setError(errorMsg)
    setTimeout(() => setError(''), 3000)
  }

  const getApiErrorMessage = (err, defaultMessage) => {
    const status = err.response?.status

    if (status === 401 || status === 403) {
      return 'No autorizado o sesión expirada. Vuelve a iniciar sesión.'
    }

    return err.response?.data?.error || err.response?.data?.message || err.response?.data || defaultMessage
  }

  const translateRole = (role) => {
    if (!role) return '-'
    const normalized = String(role).trim().toUpperCase()
    const map = {
      ADMINISTRATOR: 'Administrador',
      ADMIN: 'Administrador',
      HUMANRESOURCES: 'Recursos Humanos',
      INFORMATIONSUPPORT: 'Soporte de Información',
      NURSES: 'Enfermera',
      DOCTORS: 'Doctor',
      PATIENT: 'Paciente',
    }
    return map[normalized] || role
  }

  const updateEmployeeField = (field, value) => {
    setEmployeeForm((currentForm) => ({
      ...currentForm,
      [field]: value,
    }))
  }

  const resetEmployeeForm = () => {
    setEmployeeForm({ ...emptyEmployeeForm })
  }

  const sections = [
    { id: 'patients', label: 'Pacientes' },
    { id: 'employees', label: 'Empleados' },
    { id: 'appointments', label: 'Citas' },
    { id: 'billing', label: 'Facturas' },
    { id: 'emergency', label: 'Contactos de Emergencia' },
  ]

  const ensureArray = (value) => {
    if (Array.isArray(value)) return value
    if (Array.isArray(value?.data)) return value.data
    if (Array.isArray(value?.patients)) return value.patients
    return []
  }

  const loadSectionData = async (section) => {
    try {
      setIsLoading(true)
      if (section === 'patients') {
        const response = await fetchPatients()
        setPatients(ensureArray(response.data).map(normalizePatient))
      } else if (section === 'employees') {
        const response = await fetchEmployees()
        setEmployees(ensureArray(response.data))
      } else if (section === 'appointments') {
        const response = await fetchAppointments()
        setAppointments(ensureArray(response.data))
      } else if (section === 'billing') {
        const response = await fetchBillings()
        setBillings(ensureArray(response.data))
      } else if (section === 'emergency') {
        const response = await fetchEmergencyContacts()
        setEmergencyContacts(ensureArray(response.data))
      }
    } catch (err) {
      showError(getApiErrorMessage(err, 'No se pudo cargar la información. Intenta nuevamente.'))
    } finally {
      setIsLoading(false)
    }
  }

  useEffect(() => {
    // eslint-disable-next-line react-hooks/set-state-in-effect
    loadSectionData(currentSection)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentSection])

  const handleCreatePatient = async () => {
    try {
      if (!colombianDocumentRegex.test(String(patientForm.document).trim())) {
        showError('El documento colombiano debe tener entre 6 y 10 dígitos y no puede iniciar en 0')
        return
      }

      if (!colombianPhoneRegex.test(String(patientForm.phoneNumber).trim())) {
        showError('El teléfono debe ser colombiano: celular de 10 dígitos que empiece por 3 o fijo nacional que empiece por 60')
        return
      }

      const payload = {
        document: Number(patientForm.document),
        fullName: patientForm.fullName,
        email: patientForm.email,
        birthdate: formatDateToDDMMYYYY(patientForm.birthdate),
        address: patientForm.address,
        phoneNumber: Number(patientForm.phoneNumber),
        gender: patientForm.gender,
        weigth: Number(patientForm.weigth),
        size: Number(patientForm.size),
        doctorDocument: patientForm.doctorDocument ? Number(patientForm.doctorDocument) : 0,
        policyNumber: Number(patientForm.policyNumber),
        insuranceCompanyName: patientForm.insuranceCompanyName,
        policyValidity: patientForm.policyValidity,
        policyEndDate: patientForm.policyEndDate,
        userName: patientForm.document ? String(patientForm.document) : undefined,
        password: patientForm.document ? String(patientForm.document) : undefined,
      }
      await createPatient(payload)
      showSuccess('Paciente creado correctamente')
      setPatientForm({
        document: '',
        fullName: '',
        email: '',
        birthdate: '',
        address: '',
        phoneNumber: '',
        gender: 'MALE',
        weigth: '',
        size: '',
        doctorDocument: '',
        policyNumber: '',
        insuranceCompanyName: '',
        policyValidity: '',
        policyEndDate: '',
      })
      setExpandedSection('')
      loadSectionData('patients')
    } catch (err) {
      showError(err.response?.data || err.message || 'Error al crear paciente')
    }
  }

  const handleEditPatient = (patient) => {
    setEditingPatientId(patient.id)
    setPatientForm({
      document: patient.document || '',
      fullName: patient.fullName || '',
      email: patient.email || '',
      birthdate: patient.birthdate ? patient.birthdate.split('/').reverse().join('-') : '',
      address: patient.address || '',
      phoneNumber: patient.phoneNumber || '',
      gender: patient.gender || 'MALE',
      weigth: patient.weigth || patient.weight || '',
      size: patient.size || '',
      doctorDocument: patient.doctorDocument?.document || patient.doctorDocument || '',
      policyNumber: patient.policyNumber || '',
      insuranceCompanyName: patient.insuranceCompanyName || '',
      policyValidity: patient.policyValidity || '',
      policyEndDate: patient.policyEndDate || '',
    })
    setExpandedSection('patients')
  }

  const handleUpdatePatient = async () => {
    try {
      if (!colombianDocumentRegex.test(String(patientForm.document).trim())) {
        showError('El documento colombiano debe tener entre 6 y 10 dígitos y no puede iniciar en 0')
        return
      }

      if (!colombianPhoneRegex.test(String(patientForm.phoneNumber).trim())) {
        showError('El teléfono debe ser colombiano: celular de 10 dígitos que empiece por 3 o fijo nacional que empiece por 60')
        return
      }

      const payload = {
        document: Number(patientForm.document),
        fullName: patientForm.fullName,
        email: patientForm.email,
        birthdate: formatDateToDDMMYYYY(patientForm.birthdate),
        address: patientForm.address,
        phoneNumber: Number(patientForm.phoneNumber),
        gender: patientForm.gender,
        weigth: Number(patientForm.weigth),
        size: Number(patientForm.size),
        doctorDocument: patientForm.doctorDocument ? Number(patientForm.doctorDocument) : 0,
        policyNumber: Number(patientForm.policyNumber),
        insuranceCompanyName: patientForm.insuranceCompanyName,
        policyValidity: patientForm.policyValidity,
        policyEndDate: patientForm.policyEndDate,
      }
      await updatePatient(editingPatientId, payload)
      showSuccess('Paciente actualizado correctamente')
      setEditingPatientId(null)
      setPatientForm({
        document: '',
        fullName: '',
        email: '',
        birthdate: '',
        address: '',
        phoneNumber: '',
        gender: 'MALE',
        weigth: '',
        size: '',
        doctorDocument: '',
        policyNumber: '',
        insuranceCompanyName: '',
        policyValidity: '',
        policyEndDate: '',
      })
      setExpandedSection('')
      loadSectionData('patients')
    } catch (err) {
      showError(err.response?.data || err.message || 'Error al actualizar paciente')
    }
  }

  const handleCreateEmployee = async () => {
    try {
      if (!colombianDocumentRegex.test(String(employeeForm.document).trim())) {
        showError('El documento colombiano debe tener entre 6 y 10 dígitos y no puede iniciar en 0')
        return
      }

      if (!colombianPhoneRegex.test(String(employeeForm.phoneNumber).trim())) {
        showError('El teléfono debe ser colombiano: celular de 10 dígitos que empiece por 3 o fijo nacional que empiece por 60')
        return
      }

      const payload = {
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
      }
      await createEmployee(payload)
      showSuccess('Empleado creado correctamente')
      resetEmployeeForm()
      setExpandedSection('')
      setEditingEmployeeId(null)
      loadSectionData('employees')
    } catch (err) {
      showError(err.response?.data || err.message || 'Error al crear empleado')
    }
  }

  const handleEditEmployee = (employee) => {
    setEditingEmployeeId(employee.id)
    setEmployeeForm({
      fullName: employee.fullName || '',
      document: employee.document || '',
      email: employee.email || '',
      birthdate: employee.birthdate ? employee.birthdate.split('/').reverse().join('-') : '',
      address: employee.address || '',
      phoneNumber: employee.phoneNumber || '',
      userName: employee.userName || '',
      password: employee.password || '',
      age: employee.age || '',
      roleType: employee.role?.name || employee.role || employee.roleType || 'DOCTORS',
    })
    setExpandedSection('employees')
  }

  const handleUpdateEmployee = async () => {
    try {
      if (!colombianDocumentRegex.test(String(employeeForm.document).trim())) {
        showError('El documento colombiano debe tener entre 6 y 10 dígitos y no puede iniciar en 0')
        return
      }

      if (!colombianPhoneRegex.test(String(employeeForm.phoneNumber).trim())) {
        showError('El teléfono debe ser colombiano: celular de 10 dígitos que empiece por 3 o fijo nacional que empiece por 60')
        return
      }

      const payload = {
        fullName: employeeForm.fullName,
        document: Number(employeeForm.document),
        email: employeeForm.email,
        birthdate: formatDateToDDMMYYYY(employeeForm.birthdate),
        address: employeeForm.address,
        phoneNumber: Number(employeeForm.phoneNumber),
        userName: employeeForm.userName,
        password: employeeForm.password || undefined,
        age: Number(employeeForm.age),
        roleType: employeeForm.roleType,
      }
      await updateEmployee(editingEmployeeId, payload)
      showSuccess('Empleado actualizado correctamente')
      resetEmployeeForm()
      setEditingEmployeeId(null)
      setExpandedSection('')
      loadSectionData('employees')
    } catch (err) {
      showError(err.response?.data || err.message || 'Error al actualizar empleado')
    }
  }

  const handleCreateAppointment = async () => {
    try {
      const payload = {
        date: appointmentForm.date,
        time: appointmentForm.time,
        doctor: appointmentForm.doctorDocument ? { document: Number(appointmentForm.doctorDocument) } : null,
        patient: appointmentForm.patientDocument ? { document: Number(appointmentForm.patientDocument) } : null,
      }
      await createAppointment(payload)
      showSuccess('Cita creada correctamente')
      setAppointmentForm({
        patientDocument: '',
        doctorDocument: '',
        date: '',
        time: '',
      })
      setExpandedSection('')
      loadSectionData('appointments')
    } catch (err) {
      showError(err.response?.data || err.message || 'Error al crear cita')
    }
  }

  const handleEditAppointment = (appointment) => {
    setEditingAppointmentId(appointment.id ?? appointment.appointmentId)
    setAppointmentForm({
      patientDocument: appointment.patientDocument || '',
      doctorDocument: appointment.doctorDocument || '',
      date: appointment.date ? String(appointment.date).split('T')[0] : '',
      time: appointment.time ? String(appointment.time).slice(0, 5) : formatAppointmentTime(appointment.date),
    })
    setExpandedSection('appointments')
  }

  const handleUpdateAppointment = async () => {
    try {
      const payload = {
        date: appointmentForm.date,
        time: appointmentForm.time,
        doctor: appointmentForm.doctorDocument ? { document: Number(appointmentForm.doctorDocument) } : null,
        patient: appointmentForm.patientDocument ? { document: Number(appointmentForm.patientDocument) } : null,
      }
      await updateAppointment(editingAppointmentId, payload)
      showSuccess('Cita actualizada correctamente')
      setAppointmentForm({
        patientDocument: '',
        doctorDocument: '',
        date: '',
        time: '',
      })
      setEditingAppointmentId(null)
      setExpandedSection('')
      loadSectionData('appointments')
    } catch (err) {
      showError(err.response?.data || err.message || 'Error al actualizar cita')
    }
  }

  const handleCreateBilling = async () => {
    try {
      const payload = {
        patientDocument: Number(billingForm.patientDocument),
        policyNumber: Number(billingForm.policyNumber),
        patientAge: Number(billingForm.patientAge),
        doctorName: billingForm.doctorDocument ? { document: Number(billingForm.doctorDocument) } : null,
        patientName: billingForm.patientNameDocument ? { document: Number(billingForm.patientNameDocument) } : null,
      }
      await createBilling(payload)
      showSuccess('Factura creada correctamente')
      setBillingForm({
        patientDocument: '',
        policyNumber: '',
        patientAge: '',
        doctorDocument: '',
        patientNameDocument: '',
        insuranceCompanyName: '',
        policyValidity: '',
        policyEndDate: '',
      })
      setExpandedSection('')
      loadSectionData('billing')
    } catch (err) {
      showError(err.response?.data || err.message || 'Error al crear factura')
    }
  }

  const handleEditBilling = (billing) => {
  setEditingBillingId(billing.id)

  setBillingForm({
    patientDocument: billing.patientDocument || '',
    policyNumber: billing.policyNumber || '',
    patientAge: billing.patientAge || '',
    doctorDocument: billing.doctorDocument || billing.doctorName?.document || '',
    patientNameDocument: billing.patientNameDocument || billing.patientName?.document || billing.patientDocument || '',
    insuranceCompanyName: billing.insuranceCompanyName || '',
    policyValidity: billing.policyValidity || '',
    policyEndDate: billing.policyEndDate || '',
  })

  setExpandedSection('billing')
}
  const handleUpdateBilling = async () => {
    try {
      const payload = {
        patientDocument: Number(billingForm.patientDocument),
        policyNumber: Number(billingForm.policyNumber),
        patientAge: Number(billingForm.patientAge),
        doctorName: billingForm.doctorDocument ? { document: Number(billingForm.doctorDocument) } : null,
        patientName: billingForm.patientNameDocument ? { document: Number(billingForm.patientNameDocument) } : null,
      }
      await updateBilling(editingBillingId, payload)
      showSuccess('Factura actualizada correctamente')
      setBillingForm({
        patientDocument: '',
        policyNumber: '',
        patientAge: '',
        doctorDocument: '',
        patientNameDocument: '',
        insuranceCompanyName: '',
        policyValidity: '',
        policyEndDate: '',
      })
      setEditingBillingId(null)
      setExpandedSection('')
      loadSectionData('billing')
    } catch (err) {
      showError(err.response?.data || err.message || 'Error al actualizar factura')
    }
  }

  const handleCreateEmergency = async () => {
    try {
      await createEmergencyContact({
        patient: { document: Number(emergencyForm.patientDocument) },
        name: emergencyForm.name,
        lastName: emergencyForm.lastName,
        phoneNumber: emergencyForm.phoneNumber,
      })
      showSuccess('Contacto de emergencia creado correctamente')
      setEmergencyForm({
        patientDocument: '',
        name: '',
        lastName: '',
        phoneNumber: '',
      })
      setExpandedSection('')
      loadSectionData('emergency')
    } catch (err) {
      showError(err.response?.data || err.message || 'Error al crear contacto')
    }
  }

  const handleEditEmergency = (emergency) => {
    setEditingEmergencyId(emergency.id)
    setEmergencyForm({
      patientDocument: emergency.patient?.document || emergency.patientDocument || '',
      name: emergency.name || '',
      lastName: emergency.lastName || '',
      phoneNumber: emergency.phoneNumber || '',
    })
    setExpandedSection('emergency')
  }

  const handleUpdateEmergency = async () => {
    try {
      const payload = {
        patient: { document: Number(emergencyForm.patientDocument) },
        name: emergencyForm.name,
        lastName: emergencyForm.lastName,
        phoneNumber: emergencyForm.phoneNumber,
      }
      await updateEmergencyContact(editingEmergencyId, payload)
      showSuccess('Contacto de emergencia actualizado correctamente')
      setEmergencyForm({
        patientDocument: '',
        name: '',
        lastName: '',
        phoneNumber: '',
      })
      setEditingEmergencyId(null)
      setExpandedSection('')
      loadSectionData('emergency')
    } catch (err) {
      showError(err.response?.data || err.message || 'Error al actualizar contacto')
    }
  }


const confirmDelete = async () => {
  try {
    if (confirmData?.type === 'patient') {
      await deletePatient(confirmData.id)
      showSuccess('Paciente eliminado correctamente')
      loadSectionData('patients')
    }

    if (confirmData?.type === 'employee') {
      try {
        await deleteEmployee(confirmData.id)
        showSuccess('Empleado eliminado correctamente')
        loadSectionData('employees')
      } catch (err) {
        showError(getApiErrorMessage(err, 'No se puede eliminar porque este empleado tiene registros asignados.'))
      }
    }

    if (confirmData?.type === 'appointment') {
      await deleteAppointment(confirmData.id)
      showSuccess('Cita eliminada correctamente')
      loadSectionData('appointments')
    }

    if (confirmData?.type === 'billing') {
      await deleteBilling(confirmData.id)
      showSuccess('Factura eliminada correctamente')
      loadSectionData('billing')
    }

    if (confirmData?.type === 'emergency') {
      await deleteEmergencyContact(confirmData.id)
      showSuccess('Contacto eliminado correctamente')
      loadSectionData('emergency')
    }

  } catch (err) {
    showError(err.response?.data || err.message || 'Error al eliminar')
  } finally {
    setShowConfirm(false)
    setConfirmData(null)
  }
}

  const renderSection = () => {
    switch (currentSection) {
      case 'home':
        return <HomeSection user={user} />
      case 'patients':
        return (
          <SectionPanel
            title="Pacientes"
            isLoading={isLoading}
            formOpen={expandedSection === 'patients'}
            onToggleForm={() => toggleForm('patients')}
            createLabel="Crear Paciente"
            itemCount={patients.length}
            emptyText="No hay pacientes registrados todavía."
            renderForm={() => renderPatientForm()}
            renderList={() => renderPatientList()}
          />
        )
      case 'employees':
        return (
          <SectionPanel
            title="Empleados"
            isLoading={isLoading}
            formOpen={expandedSection === 'employees'}
            onToggleForm={() => toggleForm('employees')}
            createLabel="Crear Empleado"
            itemCount={employees.length}
            emptyText="No hay empleados registrados todavía."
            renderForm={() => renderEmployeeForm()}
            renderList={() => renderEmployeeList()}
          />
        )
      case 'appointments':
        return (
          <SectionPanel
            title="Citas Médicas"
            isLoading={isLoading}
            formOpen={expandedSection === 'appointments'}
            onToggleForm={() => toggleForm('appointments')}
            createLabel="Crear Cita"
            itemCount={appointments.length}
            emptyText="No hay citas agendadas todavía."
            renderForm={() => renderAppointmentForm()}
            renderList={() => renderAppointmentList()}
          />
        )
      case 'billing':
        return (
          <SectionPanel
            title="Facturas"
            isLoading={isLoading}
            formOpen={expandedSection === 'billing'}
            onToggleForm={() => toggleForm('billing')}
            createLabel="Crear Factura"
            itemCount={billings.length}
            emptyText="No hay facturas registradas todavía."
            renderForm={() => renderBillingForm()}
            renderList={() => renderBillingList()}
          />
        )
      case 'emergency':
        return (
          <SectionPanel
            title="Contactos de Emergencia"
            isLoading={isLoading}
            formOpen={expandedSection === 'emergency'}
            onToggleForm={() => toggleForm('emergency')}
            createLabel="Registrar Contacto"
            itemCount={emergencyContacts.length}
            emptyText="No hay contactos de emergencia registrados todavía."
            renderForm={() => renderEmergencyForm()}
            renderList={() => renderEmergencyList()}
          />
        )
      default:
        return <HomeSection user={user} />
    }
  }

  const toggleForm = (section) => {
    setExpandedSection(expandedSection === section ? '' : section)
  }

  const renderPatientForm = () => (
    <div className="inline-form-card">
      <h3>Nuevo paciente</h3>
      <div className="form-card">
        <label>
          Documento
          <input type="text" value={patientForm.document} onChange={(e) => setPatientForm({ ...patientForm, document: e.target.value })} placeholder="Ej: 1234567890" inputMode="numeric" />
        </label>
        <label>
          Nombre Completo
          <input type="text" value={patientForm.fullName} onChange={(e) => setPatientForm({ ...patientForm, fullName: e.target.value })} placeholder="Ej: Juan Pérez" />
        </label>
        <label>
          Email
          <input type="email" value={patientForm.email} onChange={(e) => setPatientForm({ ...patientForm, email: e.target.value })} placeholder="Ej: juan@example.com" />
        </label>
        <label>
          Fecha de Nacimiento
          <input type="date" value={patientForm.birthdate} onChange={(e) => setPatientForm({ ...patientForm, birthdate: e.target.value })} placeholder="Ej: 1994-05-15" title="Ej: 1994-05-15" />
        </label>
        <label>
          Dirección
          <input type="text" value={patientForm.address} onChange={(e) => setPatientForm({ ...patientForm, address: e.target.value })} placeholder="Ej: Calle Principal 123" />
        </label>
        <label>
          Teléfono
          <input type="text" value={patientForm.phoneNumber} onChange={(e) => setPatientForm({ ...patientForm, phoneNumber: e.target.value })} placeholder="Ej: 3001234567" inputMode="numeric" />
        </label>
        <label>
          Género
          <select value={patientForm.gender} onChange={(e) => setPatientForm({ ...patientForm, gender: e.target.value })}>
            <option value="MALE">Masculino</option>
            <option value="FEMALE">Femenino</option>
          </select>
        </label>
        <label>
          Peso (kg)
          <input type="number" step="0.1" value={patientForm.weigth} onChange={(e) => setPatientForm({ ...patientForm, weigth: e.target.value })} placeholder="Ej: 75.5" />
        </label>
        <label>
          Talla (m)
          <input type="number" step="0.01" value={patientForm.size} onChange={(e) => setPatientForm({ ...patientForm, size: e.target.value })} placeholder="Ej: 1.80" />
        </label>
        <label>
          Documento Doctor
          <input type="text" value={patientForm.doctorDocument} onChange={(e) => setPatientForm({ ...patientForm, doctorDocument: e.target.value })} placeholder="Ej: 9876543210" />
        </label>
        <label>
          Número de Póliza
          <input type="text" value={patientForm.policyNumber} onChange={(e) => setPatientForm({ ...patientForm, policyNumber: e.target.value })} placeholder="Ej: 123456" />
        </label>
        <label>
          Compañía de Seguro
          <input type="text" value={patientForm.insuranceCompanyName} onChange={(e) => setPatientForm({ ...patientForm, insuranceCompanyName: e.target.value })} placeholder="Ej: Seguros XYZ" />
        </label>
        <label>
          Inicio de Póliza
          <input type="date" value={patientForm.policyValidity} onChange={(e) => setPatientForm({ ...patientForm, policyValidity: e.target.value })} placeholder="Ej: 2026-01-01" title="Ej: 2026-01-01" />
        </label>
        <label>
          Fin de Póliza
          <input type="date" value={patientForm.policyEndDate} onChange={(e) => setPatientForm({ ...patientForm, policyEndDate: e.target.value })} placeholder="Ej: 2026-12-31" title="Ej: 2026-12-31" />
        </label>
        <div className="form-actions">
          {editingPatientId ? (
            <>
              <button className="btn-create" onClick={handleUpdatePatient} type="button">Actualizar paciente</button>
              <button
                className="btn-secondary"
                onClick={() => {
                  setEditingPatientId(null)
                  setPatientForm({
                    document: '',
                    fullName: '',
                    email: '',
                    birthdate: '',
                    address: '',
                    phoneNumber: '',
                    gender: 'MALE',
                    weigth: '',
                    size: '',
                    doctorDocument: '',
                    policyNumber: '',
                    insuranceCompanyName: '',
                    policyValidity: '',
                    policyEndDate: '',
                  })
                  setExpandedSection('')
                }}
                type="button"
              >
                Cancelar
              </button>
            </>
          ) : (
            <>
              <button className="btn-create" onClick={handleCreatePatient} type="button">Guardar paciente</button>
              <button className="btn-secondary" onClick={() => setExpandedSection('')} type="button">Cancelar</button>
            </>
          )}
        </div>
      </div>
    </div>
  )

  const renderEmployeeForm = () => (
    <div className="inline-form-card">
      <h3>{editingEmployeeId ? 'Editar empleado' : 'Nuevo empleado'}</h3>
      <form className="form-card" onSubmit={(event) => event.preventDefault()}>
        <label>
          Nombre Completo
          <input type="text" value={employeeForm.fullName} onChange={(e) => updateEmployeeField('fullName', e.target.value)} placeholder="Ej: María García" />
        </label>
        <label>
          Documento
          <input type="text" value={employeeForm.document} onChange={(e) => updateEmployeeField('document', e.target.value)} placeholder="Ej: 1234567890" inputMode="numeric" />
        </label>
        <label>
          Usuario
          <input type="text" value={employeeForm.userName} onChange={(e) => updateEmployeeField('userName', e.target.value)} placeholder="Ej: maria.garcia" />
        </label>
        <label>
          Contraseña
          <input type="password" value={employeeForm.password} onChange={(e) => updateEmployeeField('password', e.target.value)} placeholder="••••••••" />
        </label>
        <label>
          Email
          <input type="email" value={employeeForm.email} onChange={(e) => updateEmployeeField('email', e.target.value)} placeholder="Ej: maria@example.com" />
        </label>
        <label>
          Fecha de Nacimiento
          <input type="date" value={employeeForm.birthdate} onChange={(e) => updateEmployeeField('birthdate', e.target.value)} placeholder="Ej: 1994-05-15" title="Ej: 1994-05-15" />
        </label>
        <label>
          Dirección
          <input type="text" value={employeeForm.address} onChange={(e) => updateEmployeeField('address', e.target.value)} placeholder="Ej: Calle Principal 123" />
        </label>
        <label>
          Teléfono
          <input type="text" value={employeeForm.phoneNumber} onChange={(e) => updateEmployeeField('phoneNumber', e.target.value)} placeholder="Ej: 3001234567" inputMode="numeric" />
        </label>
        <label>
          Edad
          <input type="number" value={employeeForm.age} onChange={(e) => updateEmployeeField('age', e.target.value)} placeholder="Ej: 30" />
        </label>
        <label>
          Rol
          <select value={employeeForm.roleType} onChange={(e) => updateEmployeeField('roleType', e.target.value)}>
            <option value="ADMINISTRATOR">Administrador</option>
            <option value="HUMANRESOURCES">Recursos Humanos</option>
            <option value="INFORMATIONSUPPORT">Soporte de Información</option>
            <option value="NURSES">Enfermera</option>
            <option value="DOCTORS">Doctor</option>
          </select>
        </label>
        <div className="form-actions">
          {editingEmployeeId ? (
            <>
              <button className="btn-create" onClick={handleUpdateEmployee} type="button">Actualizar empleado</button>
              <button
                className="btn-secondary"
                onClick={() => {
                  setEditingEmployeeId(null)
                  resetEmployeeForm()
                  setExpandedSection('')
                }}
                type="button"
              >
                Cancelar
              </button>
            </>
          ) : (
            <>
              <button className="btn-create" onClick={handleCreateEmployee} type="button">Guardar empleado</button>
              <button className="btn-secondary" onClick={() => setExpandedSection('')} type="button">Cancelar</button>
            </>
          )}
        </div>
      </form>
    </div>
  )

  const renderAppointmentForm = () => (
    <div className="inline-form-card">
      <h3>Nueva cita médica</h3>
      <div className="form-card">
        <label>
          Documento del Paciente
          <input type="text" value={appointmentForm.patientDocument} onChange={(e) => setAppointmentForm({ ...appointmentForm, patientDocument: e.target.value })} placeholder="Ej: 1234567890" />
        </label>
        <label>
          Documento del Doctor
          <input type="text" value={appointmentForm.doctorDocument} onChange={(e) => setAppointmentForm({ ...appointmentForm, doctorDocument: e.target.value })} placeholder="Ej: 9876543210" />
        </label>
        <label>
          Fecha de la Cita
          <input type="date" value={appointmentForm.date} onChange={(e) => setAppointmentForm({ ...appointmentForm, date: e.target.value })} placeholder="Ej: 2026-05-20" title="Ej: 2026-05-20" />
        </label>
        <label>
          Hora de la Cita
          <input type="time" value={appointmentForm.time} onChange={(e) => setAppointmentForm({ ...appointmentForm, time: e.target.value })} placeholder="Ej: 08:30" title="Ej: 08:30" />
        </label>
        <div className="form-actions">
          {editingAppointmentId ? (
            <>
              <button className="btn-create" onClick={handleUpdateAppointment} type="button">Actualizar cita</button>
              <button
                className="btn-secondary"
                onClick={() => {
                  setEditingAppointmentId(null)
                  setAppointmentForm({
                    patientDocument: '',
                    doctorDocument: '',
                    date: '',
                    time: '',
                  })
                  setExpandedSection('')
                }}
                type="button"
              >
                Cancelar
              </button>
            </>
          ) : (
            <>
              <button className="btn-create" onClick={handleCreateAppointment} type="button">Guardar cita</button>
              <button className="btn-secondary" onClick={() => setExpandedSection('')} type="button">Cancelar</button>
            </>
          )}
        </div>
      </div>
    </div>


  )

  const renderBillingForm = () => (
    <div className="inline-form-card">
      <h3>{editingBillingId ? 'Editar factura' : 'Nueva factura'}</h3>
      <div className="form-card">
        <label>
          Documento del Paciente
          <input type="text" value={billingForm.patientDocument} onChange={(e) => setBillingForm({ ...billingForm, patientDocument: e.target.value })} placeholder="Ej: 1234567890" />
        </label>
        <label>
          Número de Póliza
          <input type="text" value={billingForm.policyNumber} onChange={(e) => setBillingForm({ ...billingForm, policyNumber: e.target.value })} placeholder="Ej: POL123456" />
        </label>
        <label>
          Edad del Paciente
          <input type="number" value={billingForm.patientAge} onChange={(e) => setBillingForm({ ...billingForm, patientAge: e.target.value })} placeholder="Ej: 35" />
        </label>
        <label>
          Documento del Doctor
          <input type="text" value={billingForm.doctorDocument} onChange={(e) => setBillingForm({ ...billingForm, doctorDocument: e.target.value })} placeholder="Ej: 9876543210" />
        </label>
        <label>
          Documento del Paciente Visto
          <input type="text" value={billingForm.patientNameDocument} onChange={(e) => setBillingForm({ ...billingForm, patientNameDocument: e.target.value })} placeholder="Ej: 1234567890" />
        </label>
        <div className="form-actions">
          {editingBillingId ? (
            <>
              <button className="btn-create" onClick={handleUpdateBilling} type="button">Actualizar factura</button>
              <button
                className="btn-secondary"
                onClick={() => {
                  setEditingBillingId(null)
                  setBillingForm({
                    patientDocument: '',
                    policyNumber: '',
                    patientAge: '',
                    doctorDocument: '',
                    patientNameDocument: '',
                    insuranceCompanyName: '',
                    policyValidity: '',
                    policyEndDate: '',
                  })
                  setExpandedSection('')
                }}
                type="button"
              >
                Cancelar
              </button>
            </>
          ) : (
            <>
              <button className="btn-create" onClick={handleCreateBilling} type="button">Guardar factura</button>
              <button className="btn-secondary" onClick={() => setExpandedSection('')} type="button">Cancelar</button>
            </>
          )}
        </div>
      </div>
    </div>
  )

  const renderEmergencyForm = () => (
    <div className="inline-form-card">
      <h3>{editingEmergencyId ? 'Editar contacto de emergencia' : 'Nuevo contacto de emergencia'}</h3>
      <div className="form-card">
        <label>
          Documento del Paciente
          <input type="text" value={emergencyForm.patientDocument} onChange={(e) => setEmergencyForm({ ...emergencyForm, patientDocument: e.target.value })} placeholder="Ej: 1234567890" />
        </label>
        <label>
          Nombre
          <input type="text" value={emergencyForm.name} onChange={(e) => setEmergencyForm({ ...emergencyForm, name: e.target.value })} placeholder="Ej: Juan" />
        </label>
        <label>
          Apellido
          <input type="text" value={emergencyForm.lastName} onChange={(e) => setEmergencyForm({ ...emergencyForm, lastName: e.target.value })} placeholder="Ej: Pérez" />
        </label>
        <label>
          Teléfono
          <input type="text" value={emergencyForm.phoneNumber} onChange={(e) => setEmergencyForm({ ...emergencyForm, phoneNumber: e.target.value })} placeholder="Ej: 3001234567" />
        </label>
        <div className="form-actions">
          {editingEmergencyId ? (
            <>
              <button className="btn-create" onClick={handleUpdateEmergency} type="button">Actualizar contacto</button>
              <button
                className="btn-secondary"
                onClick={() => {
                  setEditingEmergencyId(null)
                  setEmergencyForm({
                    patientDocument: '',
                    name: '',
                    lastName: '',
                    phoneNumber: '',
                  })
                  setExpandedSection('')
                }}
                type="button"
              >
                Cancelar
              </button>
            </>
          ) : (
            <>
              <button className="btn-create" onClick={handleCreateEmergency} type="button">Guardar contacto</button>
              <button className="btn-secondary" onClick={() => setExpandedSection('')} type="button">Cancelar</button>
            </>
          )}
        </div>
      </div>
    </div>
  )

  const renderPatientList = () => (
    <div>
      <div className="list-actions">
        <input type="text" placeholder="Buscar por documento" value={patientSearch} onChange={(e) => setPatientSearch(e.target.value)} />
        <button className="btn-secondary" onClick={() => {
          if (!patientSearch) return loadSectionData('patients')
          const filtered = patients.filter(p => String(p.document) === String(patientSearch))
          setPatients(filtered)
        }}>Buscar</button>
        <button className="btn-secondary" onClick={() => { setPatientSearch(''); loadSectionData('patients') }}>Limpiar</button>
      </div>
      <TableList
        headers={['Documento', 'Nombre', 'Email', 'Teléfono', 'Nacimiento', 'Dirección', 'Género', 'Peso (kg)', 'Talla (m)', 'Doctor', 'Póliza', 'Aseguradora', 'Vigencia', 'Acciones']}
        rows={patients}
        renderRow={(patient, index) => {
          const normalizedPatient = normalizePatient(patient)

          return (
            <tr key={index}>
              <td>{normalizedPatient.document}</td>
              <td>{normalizedPatient.fullName}</td>
              <td>{normalizedPatient.email}</td>
              <td>{normalizedPatient.phoneNumber || '-'}</td>
              <td>{normalizedPatient.birthdate || '-'}</td>
              <td>{normalizedPatient.address || '-'}</td>
              <td>{normalizedPatient.gender || '-'}</td>
              <td>{normalizedPatient.weigth ?? normalizedPatient.weight ?? '-'}</td>
              <td>{normalizedPatient.size ?? '-'}</td>
              <td>{(normalizedPatient.doctorDocument?.document ?? normalizedPatient.doctorDocument?.fullName ?? normalizedPatient.doctorDocument) || '-'}</td>
              <td>{normalizedPatient.policyNumber || '-'}</td>
              <td>{normalizedPatient.insuranceCompanyName || '-'}</td>
              <td>{formatPolicyValidity(normalizedPatient)}</td>
              <td>
                <button className="btn-secondary" onClick={() => handleEditPatient(normalizedPatient)}>Editar</button>
                <button className="btn-danger" onClick={() => { setConfirmData({ type: 'patient', id: normalizedPatient.id }); setShowConfirm(true) }}>Eliminar</button>
              </td>
            </tr>
          )
        }}
      />
    </div>
  )

  const renderEmployeeList = () => (
    <div>
      <div className="list-actions">
        <input type="text" placeholder="Buscar por documento, nombre o usuario" value={employeeSearch} onChange={(e) => setEmployeeSearch(e.target.value)} />
        <button className="btn-secondary" onClick={() => {
          if (!employeeSearch) return loadSectionData('employees')
          const query = employeeSearch.toLowerCase()
          const filtered = employees.filter((employee) =>
            String(employee.document || '').includes(employeeSearch) ||
            String(employee.fullName || '').toLowerCase().includes(query) ||
            String(employee.userName || '').toLowerCase().includes(query)
          )
          setEmployees(filtered)
        }}>Buscar</button>
        <button className="btn-secondary" onClick={() => { setEmployeeSearch(''); loadSectionData('employees') }}>Limpiar</button>
      </div>
      <TableList
        headers={['Documento', 'Nombre', 'Email', 'Usuario', 'Rol', 'Edad', 'Nacimiento', 'Teléfono', 'Dirección', 'Acciones']}
        rows={employees}
        renderRow={(employee, index) => {
          const roleValue = employee.role?.name || employee.role || employee.roleType
          return (
            <tr key={index}>
              <td>{employee.document}</td>
              <td>{employee.fullName}</td>
              <td>{employee.email}</td>
              <td>{employee.userName || '-'}</td>
              <td>{translateRole(roleValue)}</td>
              <td>{employee.age ?? '-'}</td>
              <td>{employee.birthdate || '-'}</td>
              <td>{employee.phoneNumber || '-'}</td>
              <td>{employee.address || '-'}</td>
              <td>
                <button className="btn-secondary" onClick={() => handleEditEmployee(employee)}>Editar</button>
                <button className="btn-danger" onClick={() => { setConfirmData({ type: 'employee', id: employee.id }); setShowConfirm(true) }}>Eliminar</button>
              </td>
            </tr>
          )
        }}
      />
    </div>
  )

  const renderAppointmentList = () => (
    <div>
      <div className="list-actions">
        <input type="text" placeholder="Buscar por paciente, doctor o fecha" value={appointmentSearch} onChange={(e) => setAppointmentSearch(e.target.value)} />
        <button className="btn-secondary" onClick={() => {
          if (!appointmentSearch) return loadSectionData('appointments')
          const query = appointmentSearch.toLowerCase()
          const filtered = appointments.filter((appointment) =>
            String(appointment.patientDocument || '').includes(appointmentSearch) ||
            String(appointment.doctorDocument || '').includes(appointmentSearch) ||
            String(appointment.patientName || '').toLowerCase().includes(query) ||
            String(appointment.doctorName || '').toLowerCase().includes(query) ||
            String(appointment.date || '').includes(appointmentSearch)
          )
          setAppointments(filtered)
        }}>Buscar</button>
        <button className="btn-secondary" onClick={() => { setAppointmentSearch(''); loadSectionData('appointments') }}>Limpiar</button>
      </div>
      <TableList
        headers={[
          'Fecha',
          'Hora',
          'Paciente',
          'Doctor',
          'Acciones'
        ]}
        rows={appointments}
        renderRow={(appointment, index) => (
          <tr key={index}>

            <td>
              {formatAppointmentDate(appointment.date)}
            </td>

            <td>
              {appointment.time ||
                formatAppointmentTime(appointment.date)}
            </td>

            <td>
              {formatPerson(
                appointment.patientName,
                appointment.patientDocument,
                appointment.patient
              )}
            </td>

            <td>
              {formatPerson(
                appointment.doctorName,
                appointment.doctorDocument,
                appointment.doctor
              )}
            </td>

            <td>

              <button
                className="btn-secondary"
                onClick={() =>
                  handleEditAppointment(appointment)
                }
              >
                Editar
              </button>

              <button
                className="btn-danger"
                onClick={() => {

                  setConfirmData({
                    type: 'appointment',
                    id: appointment.id
                  })

                  setShowConfirm(true)

                }}
              >
                Eliminar
              </button>

            </td>

          </tr>
        )}
      />
    </div>
  )

  const renderBillingList = () => (
    <div>
      <div className="list-actions">
        <input type="text" placeholder="Buscar por paciente, póliza o aseguradora" value={billingSearch} onChange={(e) => setBillingSearch(e.target.value)} />
        <button className="btn-secondary" onClick={() => {
          if (!billingSearch) return loadSectionData('billing')
          const query = billingSearch.toLowerCase()
          const filtered = billings.filter((billing) =>
            String(billing.patientDocument || '').includes(billingSearch) ||
            String(billing.policyNumber || '').includes(billingSearch) ||
            String(billing.insuranceCompanyName || '').toLowerCase().includes(query)
          )
          setBillings(filtered)
        }}>Buscar</button>
        <button className="btn-secondary" onClick={() => { setBillingSearch(''); loadSectionData('billing') }}>Limpiar</button>
      </div>
      <TableList
        headers={[
          'Póliza',
          'Paciente',
          'Aseguradora',
          'Vigencia',
          'Acciones'
        ]}
        rows={billings}
        renderRow={(billing, index) => (
          <tr key={index}>

            <td>{billing.policyNumber}</td>

            <td>{billing.patientDocument}</td>

            <td>{billing.insuranceCompanyName}</td>

            <td>
              {billing.policyValidity}
              {' -> '}
              {billing.policyEndDate}
            </td>

            <td>

              <button
                className="btn-secondary"
                onClick={() =>
                  handleEditBilling(billing)
                }
              >
                Editar
              </button>

              <button
                className="btn-danger"
                onClick={() => {

                  setConfirmData({
                    type: 'billing',
                    id: billing.id
                  })

                  setShowConfirm(true)

                }}
              >
                Eliminar
              </button>

            </td>

          </tr>
        )}
      />
    </div>
  )

  const renderEmergencyList = () => (
    <div>
      <div className="list-actions">
        <input type="text" placeholder="Buscar por nombre, apellido o teléfono" value={emergencySearch} onChange={(e) => setEmergencySearch(e.target.value)} />
        <button className="btn-secondary" onClick={() => {
          if (!emergencySearch) return loadSectionData('emergency')
          const query = emergencySearch.toLowerCase()
          const filtered = emergencyContacts.filter((contact) =>
            String(contact.name || '').toLowerCase().includes(query) ||
            String(contact.lastName || '').toLowerCase().includes(query) ||
            String(contact.phoneNumber || '').includes(emergencySearch) ||
            String(contact.patient?.document || contact.patientDocument || '').includes(emergencySearch) ||
            String(contact.patient?.fullName || contact.patientName || '').toLowerCase().includes(query)
          )
          setEmergencyContacts(filtered)
        }}>Buscar</button>
        <button className="btn-secondary" onClick={() => { setEmergencySearch(''); loadSectionData('emergency') }}>Limpiar</button>
      </div>
      <TableList
        headers={['Paciente', 'Documento', 'Nombre', 'Apellido', 'Teléfono', 'Acciones']}
        rows={emergencyContacts}
        renderRow={(contact, index) => (
          <tr key={index}>
            <td>{contact.patient?.fullName || contact.patientName || '-'}</td>
            <td>{contact.patient?.document || contact.patientDocument || '-'}</td>
            <td>{contact.name}</td>
            <td>{contact.lastName}</td>
            <td>{contact.phoneNumber}</td>
            <td>
              <button className="btn-secondary" onClick={() => handleEditEmergency(contact)}>Editar</button>
              <button className="btn-danger" onClick={() => { setConfirmData({ type: 'emergency', id: contact.id }); setShowConfirm(true) }}>Eliminar</button>
            </td>
          </tr>
        )}
      />
    </div>
  )

  return (
    <div className="admin-dashboard">
      <Sidebar currentSection={currentSection} onSectionChange={setCurrentSection} sections={sections} />
      <main className="dashboard-content">
        {message && <div className="alert alert-success">{message}</div>}
        {error && <div className="alert alert-error">{error}</div>}
        {renderSection()}
        <ConfirmDialog
          isOpen={showConfirm}
          title="Eliminar Item"
          message="¿Estás seguro de que deseas eliminar este item? Esta acción no se puede deshacer."
          onConfirm={confirmDelete}
          onCancel={() => setShowConfirm(false)}
          confirmText="Sí, eliminar"
        />
      </main>
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
  const safeRows = Array.isArray(rows) ? rows : []

  return (
    <div className="table-list">
      <table>
        <thead>
          <tr>
            {headers.map((header) => (
              <th key={header}>{header}</th>
            ))}
          </tr>
        </thead>
        <tbody>{safeRows.map(renderRow)}</tbody>
      </table>
    </div>
  )
}

function HomeSection({ user }) {
  const displayName = user?.fullName || user?.username || 'Administrador'

  return (
    <div className="section-content">
      <div className="welcome-card">
        <h2>Bienvenido, {displayName}</h2>
        <p>
          Bienvenido al panel de administración de CliniCosta.
        </p>
        <p className="subtitle">
          Usa el menú lateral para navegar entre secciones.
        </p>
      </div>
    </div>
  )
}
export default AdminDashboard
