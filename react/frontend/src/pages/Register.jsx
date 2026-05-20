import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { registerPatient } from '../services/api'
import './Register.css'

const emptyForm = {
  fullName: '',
  email: '',
  username: '',
  password: '',
  confirmPassword: '',
  document: '',
  phoneNumber: '',
  address: '',
  birthdate: '',
  gender: '',
  weight: '',
  size: '',
  policyNumber: '',
  insuranceCompanyName: '',
  policyValidity: '',
  policyEndDate: '',
}

function Register() {
  const [form, setForm] = useState(emptyForm)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)
  const navigate = useNavigate()

  const updateField = (field, value) => {
    setForm((current) => ({ ...current, [field]: value }))
  }

  const handleSubmit = async (event) => {
    event.preventDefault()

    const requiredFields = [
      'fullName',
      'email',
      'username',
      'password',
      'confirmPassword',
      'document',
      'policyNumber',
      'insuranceCompanyName',
      'policyValidity',
      'policyEndDate',
    ]

    if (requiredFields.some((field) => !String(form[field] || '').trim())) {
      setError('Por favor completa todos los campos requeridos')
      setMessage('')
      return
    }

    if (form.password !== form.confirmPassword) {
      setError('Las contraseñas no coinciden')
      setMessage('')
      return
    }

    if (form.password.length < 6) {
      setError('La contraseña debe tener al menos 6 caracteres')
      setMessage('')
      return
    }

    const emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/
    if (!emailRegex.test(form.email)) {
      setError('El email no tiene un formato válido')
      setMessage('')
      return
    }

    setLoading(true)
    setError('')
    setMessage('')

    try {
      await registerPatient({
        fullName: form.fullName,
        email: form.email,
        username: form.username,
        password: form.password,
        document: Number(form.document),
        phoneNumber: form.phoneNumber ? Number(form.phoneNumber) : null,
        address: form.address,
        birthdate: form.birthdate || null,
        gender: form.gender || null,
        weight: form.weight ? Number(form.weight) : 0,
        size: form.size ? Number(form.size) : 0,
        policyNumber: Number(form.policyNumber),
        insuranceCompanyName: form.insuranceCompanyName,
        policyValidity: form.policyValidity,
        policyEndDate: form.policyEndDate,
      })

      setMessage('Registro exitoso. Ahora puedes iniciar sesión.')
      window.setTimeout(() => navigate('/login'), 1500)
    } catch (err) {
      const data = err.response?.data
      const backendMessage = typeof data === 'string' ? data : data?.message || data?.error || err.message
      setError(backendMessage || 'Error en el registro. Intenta de nuevo.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="register-page">
      <section className="register-card" aria-label="Registro de paciente">
        <div className="register-brand">
          <span className="register-brand-mark" aria-hidden="true"><span></span></span>
          <div>
            <h1>CliniCosta</h1>
            <p>Registro de paciente</p>
          </div>
        </div>

        <form onSubmit={handleSubmit} className="register-form">
          <h2>Datos personales</h2>
          <label>Nombre completo*<input value={form.fullName} onChange={(event) => updateField('fullName', event.target.value)} placeholder="Juan Pérez" required /></label>
          <label>Email*<input type="email" value={form.email} onChange={(event) => updateField('email', event.target.value)} placeholder="correo@example.com" required /></label>
          <label>Documento de identidad*<input value={form.document} onChange={(event) => updateField('document', event.target.value)} placeholder="1234567890" required /></label>
          <label>Teléfono<input value={form.phoneNumber} onChange={(event) => updateField('phoneNumber', event.target.value)} placeholder="3001234567" /></label>
          <label>Dirección<input value={form.address} onChange={(event) => updateField('address', event.target.value)} placeholder="Calle Principal 123" /></label>
          <label>Fecha de nacimiento<input type="date" value={form.birthdate} onChange={(event) => updateField('birthdate', event.target.value)} /></label>
          <label>
            Género
            <select value={form.gender} onChange={(event) => updateField('gender', event.target.value)}>
              <option value="">Selecciona una opción</option>
              <option value="MALE">Masculino</option>
              <option value="FEMALE">Femenino</option>
            </select>
          </label>
          <label>Peso<input type="number" step="0.1" value={form.weight} onChange={(event) => updateField('weight', event.target.value)} placeholder="70" /></label>
          <label>Estatura<input type="number" step="0.01" value={form.size} onChange={(event) => updateField('size', event.target.value)} placeholder="1.70" /></label>

          <h2>Póliza</h2>
          <label>Número de póliza*<input type="number" value={form.policyNumber} onChange={(event) => updateField('policyNumber', event.target.value)} placeholder="100245" required /></label>
          <label>Aseguradora*<input value={form.insuranceCompanyName} onChange={(event) => updateField('insuranceCompanyName', event.target.value)} placeholder="Sura" required /></label>
          <label>Inicio de póliza*<input type="date" value={form.policyValidity} onChange={(event) => updateField('policyValidity', event.target.value)} required /></label>
          <label>Fin de póliza*<input type="date" value={form.policyEndDate} onChange={(event) => updateField('policyEndDate', event.target.value)} required /></label>

          <h2>Acceso</h2>
          <label>Usuario*<input value={form.username} onChange={(event) => updateField('username', event.target.value)} placeholder="tu_usuario" required /></label>
          <label>Contraseña*<input type="password" value={form.password} onChange={(event) => updateField('password', event.target.value)} placeholder="Contraseña segura" required /></label>
          <label>Confirmar contraseña*<input type="password" value={form.confirmPassword} onChange={(event) => updateField('confirmPassword', event.target.value)} placeholder="Repite la contraseña" required /></label>

          {error && <div className="register-error">{error}</div>}
          {message && <div className="register-success">{message}</div>}

          <div className="register-actions">
            <button className="register-submit" type="submit" disabled={loading}>
              {loading ? 'Registrando...' : 'Registrar paciente'}
            </button>
            <Link to="/login">Ya tengo cuenta</Link>
          </div>
        </form>
      </section>
    </div>
  )
}

export default Register
