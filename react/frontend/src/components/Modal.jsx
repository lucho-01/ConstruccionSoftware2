import './Modal.css'

function Modal({ isOpen, title, onClose, onSubmit, children, submitText = 'Guardar' }) {
  if (!isOpen) return null

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <div className="modal-header">
          <h2>{title}</h2>
          <button className="modal-close" onClick={onClose}>×</button>
        </div>
        <div className="modal-body">
          {children}
        </div>
        <div className="modal-footer">
          <button className="btn-secondary" onClick={onClose}>
            Cancelar
          </button>
          <button className="btn-primary" onClick={onSubmit}>
            {submitText}
          </button>
        </div>
      </div>
    </div>
  )
}

export default Modal
