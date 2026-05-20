package app.adapter.rest.response;

import app.domain.model.Employee;

public class DoctorResponse {
    private Long id;
    private long document;
    private String fullName;
    private String email;
    private Long phoneNumber;

    public DoctorResponse(Employee doctor) {
        this.id = doctor.getId();
        this.document = doctor.getDocument();
        this.fullName = doctor.getFullName();
        this.email = doctor.getEmail();
        this.phoneNumber = doctor.getPhoneNumber();
    }

    public Long getId() { return id; }
    public long getDocument() { return document; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public Long getPhoneNumber() { return phoneNumber; }
}
