package app.infrastructure.entities;

import java.time.LocalDate;
import app.domain.model.enums.Gender;
import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long document;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(length = 15)
    private Long phoneNumber;

    private String address;

    @Column(unique = true)
    private String email;

    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private double weight;

    private double size;

    @Column(unique = true)
    private Long policyNumber;

    @Column(length = 100)
    private String insuranceCompanyName;

    private LocalDate policyValidity;

    private LocalDate policyEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private EmployeeEntity doctor;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // === Getters y Setters ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDocument() { return document; }
    public void setDocument(Long document) { this.document = document; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Long getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(Long phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getSize() { return size; }
    public void setSize(double size) { this.size = size; }

    public Long getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(Long policyNumber) { this.policyNumber = policyNumber; }

    public String getInsuranceCompanyName() { return insuranceCompanyName; }
    public void setInsuranceCompanyName(String insuranceCompanyName) { this.insuranceCompanyName = insuranceCompanyName; }

    public LocalDate getPolicyValidity() { return policyValidity; }
    public void setPolicyValidity(LocalDate policyValidity) { this.policyValidity = policyValidity; }

    public LocalDate getPolicyEndDate() { return policyEndDate; }
    public void setPolicyEndDate(LocalDate policyEndDate) { this.policyEndDate = policyEndDate; }

    public EmployeeEntity getDoctor() { return doctor; }
    public void setDoctor(EmployeeEntity doctor) { this.doctor = doctor; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
