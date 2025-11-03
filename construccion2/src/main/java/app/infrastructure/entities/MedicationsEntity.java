package app.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "medications")
public class MedicationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedications;

    @Column(nullable = false)
    private Long orderNumber;

    @Column(nullable = false)
    private int item;

    @Column(nullable = false)
    private String dose;

    @Column(nullable = false)
    private String treatmentDuration;

    @Column(nullable = false)
    private Long doctorId; // FK hacia Employee (sin relación directa todavía)

    // ===== Getters y Setters =====
    public Long getIdMedications() {
        return idMedications;
    }

    public void setIdMedications(Long idMedications) {
        this.idMedications = idMedications;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getTreatmentDuration() {
        return treatmentDuration;
    }

    public void setTreatmentDuration(String treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
