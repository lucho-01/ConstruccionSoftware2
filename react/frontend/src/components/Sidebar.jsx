import './Sidebar.css'

function Sidebar({ currentSection, onSectionChange, sections }) {
  return (
    <aside className="sidebar">
      <div className="sidebar-header">
        <h3>Menú</h3>
      </div>
      <nav className="sidebar-nav">
        <button
          className={`sidebar-item ${currentSection === 'home' ? 'active' : ''}`}
          onClick={() => onSectionChange('home')}
        >
          Inicio
        </button>
        {sections.map((section) => (
          <button
            key={section.id}
            className={`sidebar-item ${currentSection === section.id ? 'active' : ''}`}
            onClick={() => onSectionChange(section.id)}
          >
            {section.label}
          </button>
        ))}
      </nav>
    </aside>
  )
}

export default Sidebar
