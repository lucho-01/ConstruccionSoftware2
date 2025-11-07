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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private EmployeeEntity doctor;

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

    public EmployeeEntity getDoctor() { return doctor; }
    public void setDoctor(EmployeeEntity doctor) { this.doctor = doctor; }
}
