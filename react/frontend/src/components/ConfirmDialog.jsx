import './ConfirmDialog.css'

function ConfirmDialog({ isOpen, title, message, onConfirm, onCancel, confirmText = 'Eliminar', danger = true }) {
  if (!isOpen) return null

  return (
    <div className="confirm-overlay" onClick={onCancel}>
      <div className="confirm-content" onClick={(e) => e.stopPropagation()}>
        <div className="confirm-header">
          <h2>{title}</h2>
        </div>
        <div className="confirm-body">
          <p>{message}</p>
        </div>
        <div className="confirm-footer">
          <button className="btn-cancel" onClick={onCancel}>
            Cancelar
          </button>
          <button 
            className={danger ? 'btn-danger' : 'btn-confirm'}
            onClick={onConfirm}
          >
            {confirmText}
          </button>
        </div>
      </div>
    </div>
  )
}

export default ConfirmDialog
