package app.domain.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Billing;
import app.domain.model.Employee;
import app.domain.model.Patient;
import app.domain.port.BillingPort;
import app.domain.port.EmployeePort;
import app.domain.port.PatientPort;
@Service
public class BillingService {
	@Autowired
	private BillingPort billingPort;
	@Autowired
	private PatientPort patientPort;
	@Autowired
	private EmployeePort employeePort;
	
    public void createBilling(Billing billing) throws Exception {
        if (billing == null) throw new Exception("La factura no puede ser nula");

        if (billing.getPatientDocument() <= 0) 
            throw new Exception("El documento del paciente debe ser un número positivo");

        if (billing.getPolicyNumber() <= 0) 
            throw new Exception("El número de póliza debe ser un número positivo");

        int age = billing.getPatientAge();
        if (age < 0 || age > 120) 
            throw new Exception("La edad del paciente debe estar entre 0 y 120");

        if (billing.getDoctorName() == null) 
            throw new Exception("El doctor no puede ser nulo");
        if (billing.getDoctorName().getDocument() == 0) 
            throw new Exception("El documento del doctor no puede ser 0");

        if (billing.getPatientName() == null) 
            throw new Exception("El paciente no puede ser nulo");
        if (billing.getPatientName().getDocument() == 0) 
            throw new Exception("El documento del paciente no puede ser 0");

        String companyName = billing.getInsuranceCompanyName();
        if (companyName == null || companyName.trim().isEmpty()) 
            throw new Exception("La compañía de seguros no puede estar vacía");

        Date validity = billing.getPolicyValidity();
        if (validity == null) 
            throw new Exception("La fecha de validez de la póliza no puede ser nula");

        Date endDate = billing.getPolicyEndDate();
        if (endDate == null) 
            throw new Exception("La fecha de finalización de la póliza no puede ser nula");

        if (endDate.before(validity)) 
            throw new Exception("La fecha de finalización debe ser posterior a la fecha de validez");

        Patient tempPatient = new Patient();
        tempPatient.setDocument(billing.getPatientDocument());
        
        Patient patient = patientPort.findByDocument(tempPatient);

        if (patient == null)
            throw new Exception("El paciente no existe");
        
        
        Employee doctor = employeePort.findByDocument(billing.getDoctorName());
        if (doctor == null)
            throw new Exception("El doc no existe");
        billing.setDoctorName(doctor);
        billing.setPatientName(patient);

        billingPort.save(billing);
    }
}
