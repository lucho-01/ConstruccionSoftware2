package app.adapter.rest.response;

import app.domain.model.EmergencyContact;
import app.domain.model.Patient;

public class EmergencyContactResponse {
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private Long patientDocument;
    private String patientName;

    public EmergencyContactResponse(EmergencyContact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.lastName = contact.getLastName();
        this.phoneNumber = contact.getPhoneNumber();

        Patient patient = contact.getPatient();
        if (patient != null) {
            this.patientDocument = patient.getDocument();
            this.patientName = patient.getFullName();
        }
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public Long getPatientDocument() { return patientDocument; }
    public String getPatientName() { return patientName; }
}
