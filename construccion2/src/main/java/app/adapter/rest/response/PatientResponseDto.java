package app.adapter.rest.response;

import java.time.LocalDate;
import app.domain.model.enums.Gender;

public class PatientResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private Long phoneNumber;
    private String address;
    private LocalDate birthdate;
    private Gender gender;
    private Long document;
    private double weight;
    private double size;

    public PatientResponseDto() {}

    public PatientResponseDto(Long id, String fullName, String email, String username, 
                             Long phoneNumber, String address, LocalDate birthdate, 
                             Gender gender, Long document, double weight, double size) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthdate = birthdate;
        this.gender = gender;
        this.document = document;
        this.weight = weight;
        this.size = size;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Long getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(Long phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public Long getDocument() { return document; }
    public void setDocument(Long document) { this.document = document; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getSize() { return size; }
    public void setSize(double size) { this.size = size; }
}
