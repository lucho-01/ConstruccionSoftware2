package app.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "register_visit")
public class RegisterVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double bloodPressure;
    private double temperature;
    private int pulse;
    private int oxygenLevel;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "medications_id")
    private MedicationsEntity medications;

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private ProcedureEntity procedure;

    @ManyToOne
    @JoinColumn(name = "diagnostic_aid_id")
    private DiagnosticAidEntity diagnosticAid;

    // Getters y Setters
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

    public PatientEntity getPatient() {
        return patient;
    }
    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public MedicationsEntity getMedications() {
        return medications;
    }
    public void setMedications(MedicationsEntity medications) {
        this.medications = medications;
    }

    public ProcedureEntity getProcedure() {
        return procedure;
    }
    public void setProcedure(ProcedureEntity procedure) {
        this.procedure = procedure;
    }

    public DiagnosticAidEntity getDiagnosticAid() {
        return diagnosticAid;
    }
    public void setDiagnosticAid(DiagnosticAidEntity diagnosticAid) {
        this.diagnosticAid = diagnosticAid;
    }
}
