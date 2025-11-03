package app.infrastructure.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "billings")
public class BillingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long patientDocument;

    @Column(nullable = false, unique = true)
    private Long policyNumber;

    private int patientAge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private EmployeeEntity doctorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientEntity patientName;

    @Column(length = 100)
    private String insuranceCompanyName;

    private LocalDate policyValidity;

    private LocalDate policyEndDate;

    // === Getters y Setters ===

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPatientDocument() { return patientDocument; }
    public void setPatientDocument(Long patientDocument) { this.patientDocument = patientDocument; }

    public Long getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(Long policyNumber) { this.policyNumber = policyNumber; }

    public int getPatientAge() { return patientAge; }
    public void setPatientAge(int patientAge) { this.patientAge = patientAge; }

    public EmployeeEntity getDoctorName() { return doctorName; }
    public void setDoctorName(EmployeeEntity doctorName) { this.doctorName = doctorName; }

    public PatientEntity getPatientName() { return patientName; }
    public void setPatientName(PatientEntity patientName) { this.patientName = patientName; }

    public String getInsuranceCompanyName() { return insuranceCompanyName; }
    public void setInsuranceCompanyName(String insuranceCompanyName) { this.insuranceCompanyName = insuranceCompanyName; }

    public LocalDate getPolicyValidity() { return policyValidity; }
    public void setPolicyValidity(LocalDate policyValidity) { this.policyValidity = policyValidity; }

    public LocalDate getPolicyEndDate() { return policyEndDate; }
    public void setPolicyEndDate(LocalDate policyEndDate) { this.policyEndDate = policyEndDate; }
}