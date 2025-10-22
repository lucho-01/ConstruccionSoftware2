package app.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // puedes dejarlo como orderId si prefieres

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @Column(length = 255)
    private String medications;

    @Column(name = "medical_procedure", length = 255)
    private String procedure; // ✅ evita conflicto SQL

    @Column(length = 255)
    private String diagnosticAid;

    // 🔹 Constructor explícito
    public OrderEntity() {}

    // === Getters y Setters ===

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PatientEntity getPatient() { return patient; }
    public void setPatient(PatientEntity patient) { this.patient = patient; }

    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }

    public String getProcedure() { return procedure; }
    public void setProcedure(String procedure) { this.procedure = procedure; }

    public String getDiagnosticAid() { return diagnosticAid; }
    public void setDiagnosticAid(String diagnosticAid) { this.diagnosticAid = diagnosticAid; }
}
