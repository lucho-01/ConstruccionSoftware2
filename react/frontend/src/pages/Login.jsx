import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import './Login.css'

function Login({ onLogin }) {

  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)
  const [showPassword, setShowPassword] = useState(false)

  const navigate = useNavigate()

  const handleSubmit = async (event) => {

    event.preventDefault()

    setError('')
    setLoading(true)

    try {

      const route = await onLogin(
        username.trim(),
        password
      )

      navigate(route)

    } catch (err) {

      const defaultMessage =
        'Error al iniciar sesión. Verifica usuario y contraseña.'

      const backendData = err.response?.data
      const backendMessage =
        typeof backendData === 'string'
          ? backendData
          : backendData?.message || backendData?.error

      const errorMessage =
        backendMessage ||
        err.message ||
        defaultMessage

      const alertMessage =
        errorMessage === 'Credenciales inválidas. Verifica usuario y contraseña.'
          ? 'El usuario no existe. Revisa tus credenciales.'
          : errorMessage

      setError(errorMessage)
      window.alert(alertMessage)
      setUsername('')
      setPassword('')

    } finally {

      setLoading(false)

    }
  }

  return (

    <div className="login-page">

      <div className="login-background" aria-hidden="true"></div>

      <div className="medical-cross cross-one" aria-hidden="true"></div>
      <div className="medical-cross cross-two" aria-hidden="true"></div>
      <div className="medical-cross cross-three" aria-hidden="true"></div>

      <section className="login-card" aria-label="Inicio de sesion">

        <div className="brand-block">
          <div className="brand-mark" aria-hidden="true">
            <span className="brand-plus">+</span>
            <span className="brand-pulse"></span>
          </div>

          <h1>Clinicosta</h1>

          <p>
            Sistema de Gestion Clinica
          </p>
        </div>

        <div className="login-heading">
          <h2>Bienvenido</h2>

          <p className="login-hint">
            Inicia sesion para continuar
          </p>
        </div>

          {error && (

            <div className="login-error">
              {error}
            </div>

          )}

          <form
            className="login-form"
            onSubmit={handleSubmit}
          >

            <label className="field-group">
              <span>Usuario</span>
              <span className="input-wrap">
                <span className="field-icon user-icon" aria-hidden="true"></span>
                <input
                  type="text"
                  placeholder="Ingrese su usuario"
                  value={username}
                  onChange={(e) =>
                    setUsername(e.target.value)
                  }
                />
              </span>
            </label>

            <label className="field-group">
              <span>Contrasena</span>
              <span className="input-wrap">
                <span className="field-icon lock-icon" aria-hidden="true"></span>
                <input
                  type={showPassword ? 'text' : 'password'}
                  placeholder="Ingrese su contrasena"
                  value={password}
                  onChange={(e) =>
                    setPassword(e.target.value)
                  }
                />
                <button
                  className="password-toggle"
                  type="button"
                  aria-label={showPassword ? 'Ocultar contrasena' : 'Mostrar contrasena'}
                  onClick={() => setShowPassword((value) => !value)}
                >
                  <span className={showPassword ? 'eye-icon is-visible' : 'eye-icon'} aria-hidden="true"></span>
                </button>
              </span>
            </label>

            <button
              className="login-submit"
              type="submit"
              disabled={loading}
            >
              <span className="login-arrow" aria-hidden="true"></span>

              {loading
                ? 'Ingresando...'
                : 'Iniciar sesión'}

            </button>

          </form>

        <div className="register-line">
          <span>¿Eres paciente nuevo?</span>
          <Link to="/register">Crear cuenta</Link>
        </div>

        <p className="secure-note">
          <span className="shield-icon" aria-hidden="true"></span>
          Acceso seguro y confidencial
        </p>

      </section>

    </div>

  )
}

export default Login
