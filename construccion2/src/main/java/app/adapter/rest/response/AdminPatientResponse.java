package app.adapter.rest.response;

import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.model.enums.Gender;

public class AdminPatientResponse {

    private Long id;
    private long document;
    private long phoneNumber;
    private String fullName;
    private String email;
    private String birthdate;
    private String address;
    private Gender gender;
    private double weigth;
    private double weight;
    private double size;
    private Employee doctorDocument;
    private long policyNumber;
    private String insuranceCompanyName;
    private String policyValidity;
    private String policyEndDate;

    public AdminPatientResponse(Patient patient) {
        this.id = patient.getId();
        this.document = patient.getDocument();
        this.phoneNumber = patient.getPhoneNumber();
        this.fullName = patient.getFullName();
        this.email = patient.getEmail();
        this.birthdate = patient.getBirthdate();
        this.address = patient.getAddress();
        this.gender = patient.getGender();
        this.weigth = patient.getWeigth();
        this.weight = patient.getWeigth();
        this.size = patient.getSize();
        this.doctorDocument = patient.getDoctorDocument();
        this.policyNumber = patient.getPolicyNumber();
        this.insuranceCompanyName = patient.getInsuranceCompanyName();
        this.policyValidity = patient.getPolicyValidity();
        this.policyEndDate = patient.getPolicyEndDate();
    }

    public Long getId() { return id; }
    public long getDocument() { return document; }
    public long getPhoneNumber() { return phoneNumber; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getBirthdate() { return birthdate; }
    public String getAddress() { return address; }
    public Gender getGender() { return gender; }
    public double getWeigth() { return weigth; }
    public double getWeight() { return weight; }
    public double getSize() { return size; }
    public Employee getDoctorDocument() { return doctorDocument; }
    public long getPolicyNumber() { return policyNumber; }
    public String getInsuranceCompanyName() { return insuranceCompanyName; }
    public String getPolicyValidity() { return policyValidity; }
    public String getPolicyEndDate() { return policyEndDate; }
}
