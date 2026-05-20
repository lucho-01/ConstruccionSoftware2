import { Navigate } from 'react-router-dom'

function ProtectedRoute({ children, isAuthenticated, userRole, allowedRoles }) {
  if (!isAuthenticated) {
    return <Navigate to="/login" replace />
  }

  if (!allowedRoles.includes(userRole)) {
    return <Navigate to="/login" replace />
  }

  return children
}

export default ProtectedRoute
