import axios from 'axios'

const api = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json',
  },
})

const decodeJwt = (token) => {
  try {
    const payload = token.split('.')[1]
    const pad = payload.length % 4 === 0 ? '' : '='.repeat(4 - (payload.length % 4))
    const decoded = atob(payload.replace(/-/g, '+').replace(/_/g, '/') + pad)
    return JSON.parse(decoded)
  } catch {
    return null
  }
}

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('clinicToken')
  if (!config.headers) {
    config.headers = {}
  }
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

export const loginRequest = async (username, password) => {
  const response = await api.post('/auth/login', { username, password })
  return response.data
}

export const registerPatient = async (patient) => {
  const response = await api.post('/auth/register', patient)
  return response.data
}

export const createPatient = async (patient) => api.post('/administrator/patients', patient)
export const updatePatient = async (id, patient) => api.put(`/administrator/patients/${id}`, patient)
export const deletePatient = async (id) => api.delete(`/administrator/patients/${id}`)
export const fetchPatients = async () => api.get('/administrator/patients')
export const createAppointment = async (appointment) => api.post('/administrator/appointments', appointment)
export const updateAppointment = async (id, appointment) => api.put(`/administrator/appointments/${id}`, appointment)
export const deleteAppointment = async (id) => api.delete(`/administrator/appointments/${id}`)
export const fetchAppointments = async () => api.get('/administrator/appointments')
export const createBilling = async (billing) => api.post('/administrator/billings', billing)
export const updateBilling = async (id, billing) => api.put(`/administrator/billings/${id}`, billing)
export const deleteBilling = async (id) => api.delete(`/administrator/billings/${id}`)
export const fetchBillings = async () => api.get('/administrator/billings')
export const createEmergencyContact = async (contact) => api.post('/administrator/emergency-contacts', contact)
export const updateEmergencyContact = async (id, contact) => api.put(`/administrator/emergency-contacts/${id}`, contact)
export const deleteEmergencyContact = async (id) => api.delete(`/administrator/emergency-contacts/${id}`)
export const fetchEmergencyContacts = async () => api.get('/administrator/emergency-contacts')
export const createEmployee = async (employee) => api.post('/administrator/employees', employee)
export const updateEmployee = async (id, employee) => api.put(`/administrator/employees/${id}`, employee)
export const deleteEmployee = async (id) => api.delete(`/administrator/employees/${id}`)
export const fetchEmployees = async () => api.get('/administrator/employees')

export const createOrder = async (order) => api.post('/doctors/orders', order)
export const fetchOrders = async () => api.get('/doctors/orders')
export const fetchOrdersByPatient = async (patientDocument) => api.get(`/doctors/orders/patient/${patientDocument}`)
export const updateOrder = async (id, order) => api.put(`/doctors/orders/${id}`, order)
export const deleteOrder = async (id) => api.delete(`/doctors/orders/${id}`)
export const createMedicalRecord = async (medicalRecord) => api.post('/doctors/medical-records', medicalRecord)
export const fetchMedicalRecords = async () => api.get('/doctors/medical-records')
export const updateMedicalRecord = async (id, medicalRecord) => api.put(`/doctors/medical-records/${id}`, medicalRecord)
export const deleteMedicalRecord = async (id) => api.delete(`/doctors/medical-records/${id}`)
export const fetchMedicalRecord = async (patientDocument) => api.get(`/doctors/medical-records/${patientDocument}`)

export const searchOrder = async (order) => api.post('/nurses/orders/search', order)
export const registerVisit = async (visit) => api.post('/nurses/register-visit', visit)
export const fetchVisits = async () => api.get('/nurses/visits')
export const fetchVisitByPatient = async (patientDocument) => api.get(`/nurses/visits/patient/${patientDocument}`)
export const updateVisit = async (id, visit) => api.put(`/nurses/visits/${id}`, visit)
export const deleteVisit = async (id) => api.delete(`/nurses/visits/${id}`)
export const createNurseEmergencyContact = async (contact) => api.post('/nurses/emergency-contacts', contact)
export const fetchNurseEmergencyContacts = async () => api.get('/nurses/emergency-contacts')
export const updateNurseEmergencyContact = async (id, contact) => api.put(`/nurses/emergency-contacts/${id}`, contact)
export const deleteNurseEmergencyContact = async (id) => api.delete(`/nurses/emergency-contacts/${id}`)

export const fetchHumanResourceEmployees = async () => api.get('/humanResource/employees')
export const createHumanResourceEmployee = async (employee) => api.post('/humanResource/employees', employee)
export const updateHumanResourceEmployee = async (id, employee) => api.put(`/humanResource/employees/${id}`, employee)
export const deleteHumanResourceEmployee = async (id) => api.delete(`/humanResource/employees/${id}`)

export const fetchSupportPatients = async () => api.get('/informationSupport/patients')
export const fetchSupportEmployees = async () => api.get('/informationSupport/employees')
export const fetchSupportAppointments = async () => api.get('/informationSupport/appointments')
export const fetchSupportBillings = async () => api.get('/informationSupport/billings')
export const fetchSupportEmergencyContacts = async () => api.get('/informationSupport/emergency-contacts')

export const fetchPatientProfile = async () => api.get('/patient/profile')
export const fetchPatientDoctors = async () => api.get('/patient/doctors')
export const fetchPatientAppointments = async () => api.get('/patient/appointments')
export const createPatientAppointment = async (appointment) => api.post('/patient/appointments', appointment)
export const deletePatientAppointment = async (id) => api.delete(`/patient/appointments/${id}`)
export const fetchPatientMedicalRecords = async () => api.get('/patient/medical-records')

export const getUserProfileFromToken = (token) => {
  const decoded = decodeJwt(token)
  if (!decoded) {
    return null
  }
  return {
    username: decoded.sub || decoded.username,
    fullName: decoded.fullName || decoded.name || decoded.sub || decoded.username,
    role: decoded.role,
  }
}
