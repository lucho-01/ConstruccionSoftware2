/* package app.infrastructure.entities;

import app.domain.model.enums.Role;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private Long document;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 15)
    private Long phoneNumber;

    @Column(length = 200)
    private String address;

    @Column
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;

    @Column(nullable = false, unique = true, length = 50)
    private String userName;

    @Column(nullable = false, length = 255)
    private String password;

    // ✅ Constructor vacío (requerido por JPA)
    public UserEntity() {}

    // ✅ Constructor útil sin el ID (lo genera la DB)
    public UserEntity(Long document, Long phoneNumber, String fullName, String email,
                      String address, LocalDate birthdate, Role role,
                      String userName, String password) {
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.birthdate = birthdate;
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    // ✅ Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDocument() { return document; }
    public void setDocument(Long document) { this.document = document; }

    public Long getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(Long phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

*/
