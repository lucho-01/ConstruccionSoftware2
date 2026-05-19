package app.application.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Billing;
import app.domain.model.EmergencyContact;
import app.domain.model.Employee;
import app.domain.model.MedicalAppointment;
import app.domain.model.Patient;
import app.domain.services.BillingService;
import app.domain.services.EmergencyContactService;
import app.domain.services.EmployeeService;
import app.domain.services.MedicalAppointmentService;
import app.domain.services.PatientServices;

@Service
public class AdministratorUseCase {

    @Autowired
    private PatientServices patientServices;
    @Autowired
    private MedicalAppointmentService medicalAppointmentService;
    @Autowired
    private EmergencyContactService emergencyContactService;
    @Autowired
    private BillingService billingService;
    @Autowired
    private EmployeeService employeeService;

    public void createPatient(Patient patient) throws Exception {
        patientServices.createPatient(patient);
    }

    public void updatePatient(Patient patient) throws Exception {
        patientServices.updatePatient(patient);
    }

    public void createAppointment(MedicalAppointment appointment) throws Exception {
        medicalAppointmentService.createMedicalAppointment(appointment);
    }

    public void updateAppointment(MedicalAppointment appointment) throws Exception {
        medicalAppointmentService.updateMedicalAppointment(appointment);
    }

    public void deleteAppointment(MedicalAppointment appointment) throws Exception {
        medicalAppointmentService.deleteMedicalAppointment(appointment);
    }

    public void createBilling(Billing billing) throws Exception {
        billingService.createBilling(billing);
    }

    public void updateBilling(Billing billing) throws Exception {
        billingService.updateBilling(billing);
    }

    public void deleteBilling(Billing billing) throws Exception {
        billingService.deleteBilling(billing);
    }

    public void createEmployee(Employee employee) throws Exception {
        employeeService.createEmployee(employee);
    }

    public Employee findEmployeeByDocument(Employee employee) throws Exception {
        return employeeService.findByDocument(employee);
    }

    public Patient findPatientByDocument(Patient patient) throws Exception {
        return patientServices.findPatientByDocument(patient);
    }

    public void updateEmployee(Employee employee) throws Exception {
        employeeService.updateEmployee(employee);
    }

    public void deleteEmployee(Employee employee) throws Exception {
        employeeService.deleteEmployee(employee);
    }

    public void createEmergencyContact(EmergencyContact emergencyContact) throws Exception {
        emergencyContactService.createEmergencyContact(emergencyContact);
    }

    public void updateEmergencyContact(EmergencyContact emergencyContact) throws Exception {
        emergencyContactService.updateEmergencyContact(emergencyContact);
    }

    public void deleteEmergencyContact(EmergencyContact emergencyContact) throws Exception {
        emergencyContactService.deleteEmergencyContact(emergencyContact);
    }

    public java.util.List<Patient> getAllPatients() throws Exception {
        return patientServices.getAllPatients();
    }

    public java.util.List<Employee> getAllEmployees() throws Exception {
        return employeeService.getAllEmployees();
    }

    public java.util.List<MedicalAppointment> getAllAppointments() throws Exception {
        return medicalAppointmentService.getAllAppointments();
    }

    public java.util.List<Billing> getAllBillings() throws Exception {
        return billingService.getAllBillings();
    }

    public void deletePatient(Patient patient) throws Exception {
        patientServices.deletePatient(patient);
    }

    public java.util.List<EmergencyContact> getAllEmergencyContacts() throws Exception {
        return emergencyContactService.getAllEmergencyContacts();
    }
}
