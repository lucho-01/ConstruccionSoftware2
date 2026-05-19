package app.adapter.rest.response;

import java.time.LocalDateTime;

import app.domain.model.Employee;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;

public class MedicalAppointmentResponse {

    private long appointmentId;
    private LocalDateTime date;
    private String time;
    private Long doctorId;
    private long doctorDocument;
    private String doctorName;
    private Long patientId;
    private long patientDocument;
    private String patientName;

    public MedicalAppointmentResponse(MedicalAppointment appointment) {
        this.appointmentId = appointment.getAppointmentId();
        this.date = appointment.getDate();
        if (appointment.getDate() != null) {
            this.time = appointment.getDate().toLocalTime().toString();
        }

        Employee doctor = appointment.getDoctor();
        if (doctor != null) {
            this.doctorId = doctor.getId();
            this.doctorDocument = doctor.getDocument();
            this.doctorName = doctor.getFullName();
        }

        Patient patient = appointment.getPatient();
        if (patient != null) {
            this.patientId = patient.getId();
            this.patientDocument = patient.getDocument();
            this.patientName = patient.getFullName();
        }
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public long getId() {
        return appointmentId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public long getDoctorDocument() {
        return doctorDocument;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public long getPatientDocument() {
        return patientDocument;
    }

    public String getPatientName() {
        return patientName;
    }
}
