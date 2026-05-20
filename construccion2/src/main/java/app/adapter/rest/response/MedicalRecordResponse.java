package app.adapter.rest.response;

import java.sql.Date;

import app.domain.model.Employee;
import app.domain.model.MedicalRecord;
import app.domain.model.Patient;

public class MedicalRecordResponse {
    private long id;
    private Date date;
    private String symptomatology;
    private String reasonConsultation;
    private String diagnosis;
    private long doctorDocument;
    private String doctorName;
    private long patientDocument;
    private String patientName;

    public MedicalRecordResponse(MedicalRecord record) {
        this.id = record.getId();
        this.date = record.getDate();
        this.symptomatology = record.getSymptomatology();
        this.reasonConsultation = record.getReasonConsultation();
        this.diagnosis = record.getDiagnosis();
        this.doctorDocument = record.getDoctorDocument();

        Employee doctor = record.getDoctor();
        if (doctor != null) {
            this.doctorDocument = doctor.getDocument();
            this.doctorName = doctor.getFullName();
        }

        Patient patient = record.getPatient();
        if (patient != null) {
            this.patientDocument = patient.getDocument();
            this.patientName = patient.getFullName();
        }
    }

    public long getId() { return id; }
    public Date getDate() { return date; }
    public String getSymptomatology() { return symptomatology; }
    public String getReasonConsultation() { return reasonConsultation; }
    public String getDiagnosis() { return diagnosis; }
    public long getDoctorDocument() { return doctorDocument; }
    public String getDoctorName() { return doctorName; }
    public long getPatientDocument() { return patientDocument; }
    public String getPatientName() { return patientName; }
}
