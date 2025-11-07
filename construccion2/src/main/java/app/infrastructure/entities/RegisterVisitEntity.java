package app.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "register_visit")
public class RegisterVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visit")
    private Long id;

    @Column(name = "blood_pressure")
    private double bloodPressure;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "pulse")
    private int pulse;

    @Column(name = "oxygen_level")
    private int oxygenLevel;

    @Column(name = "medications")
    private String medications;

    // 🚨 Cambiamos el nombre de la columna "procedure" a "medical_procedure"
    @Column(name = "medical_procedure")
    private String procedure;

    @Column(name = "diagnostic_aid")
    private String diagnosticAid;

    // 🔧 Cambiamos referencedColumnName para que coincida con la propiedad real de PatientEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patient;

    // === Getters y Setters ===
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }

    public void setOxygenLevel(int oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getDiagnosticAid() {
        return diagnosticAid;
    }

    public void setDiagnosticAid(String diagnosticAid) {
        this.diagnosticAid = diagnosticAid;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }
}
