import { useEffect, useState } from 'react'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { getUserProfileFromToken, loginRequest } from './services/api'
import Login from './pages/Login'
import Register from './pages/Register'
import AdminDashboard from './pages/AdminDashboard'
import DoctorDashboard from './pages/DoctorDashboard'
import NurseDashboard from './pages/NurseDashboard'
import PatientDashboard from './pages/PatientDashboard'
import UserDashboard from './pages/UserDashboard'
import ProtectedRoute from './components/ProtectedRoute'
import './App.css'

const getRouteForRole = (role) => {
  const normalized = String(role || '').trim().toUpperCase()
  if (normalized === 'ADMINISTRATOR' || normalized === 'ADMIN') return '/admin'
  if (normalized === 'DOCTORS') return '/doctor'
  if (normalized === 'NURSES') return '/nurse'
  if (normalized === 'HUMANRESOURCES') return '/humanResources'
  if (normalized === 'INFORMATIONSUPPORT') return '/informationSupport'
  if (normalized === 'PATIENT') return '/patient'
  return '/login'
}

const getDisplayName = (user) => user?.fullName || user?.username || 'Usuario'

function App() {
  const [auth, setAuth] = useState(() => {
    const token = localStorage.getItem('clinicToken')
    const user = token ? getUserProfileFromToken(token) : null
    return { token, user }
  })

  useEffect(() => {
    if (auth.token) {
      localStorage.setItem('clinicToken', auth.token)
    } else {
      localStorage.removeItem('clinicToken')
    }
  }, [auth.token])

  const login = async (username, password) => {
    try {
      const response = await loginRequest(username, password)
      const tokenUser = getUserProfileFromToken(response.token)
      const user = {
        ...tokenUser,
        username: response.username || tokenUser?.username,
        fullName: response.fullName || tokenUser?.fullName,
        role: response.role || tokenUser?.role,
      }
      if (!user) {
        throw new Error('No se pudo decodificar el token recibido', { cause: response })
      }
      setAuth({ token: response.token, user })
      return getRouteForRole(user.role)
    } catch (error) {
      const status = error.response?.status
      if (status === 401 || status === 404) {
        throw new Error('El usuario no existe. Verifica usuario y contraseña.', { cause: error })
      }
      if (status === 403) {
        throw new Error('No tienes permiso para acceder. Inicia sesión con un usuario válido.', { cause: error })
      }
      throw error
    }
  }

  const logout = () => {
    setAuth({ token: null, user: null })
  }

  const defaultRoute = auth.user ? getRouteForRole(auth.user.role) : '/login'

  return (
    <BrowserRouter>
      {!auth.user ? (
        <Routes>
          <Route path="/" element={<Login onLogin={login} />} />
          <Route path="/login" element={<Login onLogin={login} />} />
          <Route path="/register" element={<Register />} />
          <Route path="*" element={<Navigate to="/login" replace />} />
        </Routes>
      ) : (
        <div className="app-shell">
          <header className="app-header">
            <div className="app-brand">
              <span className="app-brand-mark" aria-hidden="true">
                <span className="app-brand-plus"></span>
              </span>
              <h1>CliniCosta</h1>
            </div>
            <div className="user-bar">
              <span>
                {getDisplayName(auth.user)} • {auth.user.role}
              </span>
              <button className="logout-button" onClick={logout}>
                Cerrar sesión
              </button>
            </div>
          </header>

          <main className="app-main">
            <Routes>
              <Route path="/" element={<Navigate to={defaultRoute} replace />} />
              <Route
                path="/login"
                element={<Navigate to={defaultRoute} replace />}
              />
              <Route
                path="/admin"
                element={
                  <ProtectedRoute
                    isAuthenticated={!!auth.user}
                    userRole={auth.user?.role}
                    allowedRoles={['ADMINISTRATOR', 'ADMIN']}
                  >
                    <AdminDashboard user={auth.user} />
                  </ProtectedRoute>
                }
              />
              <Route
                path="/doctor"
                element={
                  <ProtectedRoute
                    isAuthenticated={!!auth.user}
                    userRole={auth.user?.role}
                    allowedRoles={['DOCTORS']}
                  >
                    <DoctorDashboard user={auth.user} />
                  </ProtectedRoute>
                }
              />
              <Route
                path="/nurse"
                element={
                  <ProtectedRoute
                    isAuthenticated={!!auth.user}
                    userRole={auth.user?.role}
                    allowedRoles={['NURSES']}
                  >
                    <NurseDashboard user={auth.user} />
                  </ProtectedRoute>
                }
              />
              <Route
                path="/humanResources"
                element={
                  <ProtectedRoute
                    isAuthenticated={!!auth.user}
                    userRole={auth.user?.role}
                    allowedRoles={['HUMANRESOURCES']}
                  >
                    <UserDashboard user={auth.user} />
                  </ProtectedRoute>
                }
              />
              <Route
                path="/informationSupport"
                element={
                  <ProtectedRoute
                    isAuthenticated={!!auth.user}
                    userRole={auth.user?.role}
                    allowedRoles={['INFORMATIONSUPPORT']}
                  >
                    <UserDashboard user={auth.user} />
                  </ProtectedRoute>
                }
              />
              <Route
                path="/patient"
                element={
                  <ProtectedRoute
                    isAuthenticated={!!auth.user}
                    userRole={auth.user?.role}
                    allowedRoles={['PATIENT']}
                  >
                    <PatientDashboard user={auth.user} />
                  </ProtectedRoute>
                }
              />
              <Route path="/user" element={<Navigate to={defaultRoute} replace />} />
              <Route path="*" element={<Navigate to={defaultRoute} replace />} />
            </Routes>
          </main>
        </div>
      )}
    </BrowserRouter>
  )
}

export default App
