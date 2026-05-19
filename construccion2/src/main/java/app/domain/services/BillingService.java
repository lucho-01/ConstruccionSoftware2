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

        Patient tempPatient = new Patient();
        tempPatient.setDocument(billing.getPatientDocument());
        
        Patient patient = patientPort.findByDocument(tempPatient);

        if (patient == null)
            throw new Exception("El paciente no existe");

        if (patient.getPolicyNumber() <= 0)
            throw new Exception("El paciente no tiene una póliza registrada");

        if (billing.getPolicyNumber() != patient.getPolicyNumber())
            throw new Exception("La póliza ingresada no corresponde al paciente seleccionado");

        if (patient.getInsuranceCompanyName() == null || patient.getInsuranceCompanyName().trim().isEmpty())
            throw new Exception("El paciente no tiene compañía de seguros registrada");

        if (patient.getPolicyValidity() == null || patient.getPolicyEndDate() == null)
            throw new Exception("El paciente no tiene fechas de póliza registradas");

        Date validity = Date.valueOf(patient.getPolicyValidity());
        Date endDate = Date.valueOf(patient.getPolicyEndDate());

        if (endDate.before(validity)) 
            throw new Exception("La fecha de finalización debe ser posterior a la fecha de validez");

        billing.setInsuranceCompanyName(patient.getInsuranceCompanyName());
        billing.setPolicyValidity(validity);
        billing.setPolicyEndDate(endDate);
        
        
        Employee doctor = employeePort.findByDocument(billing.getDoctorName());
        if (doctor == null)
            throw new Exception("El doc no existe");
        billing.setDoctorName(doctor);
        billing.setPatientName(patient);

        billingPort.save(billing);
    }

    public void updateBilling(Billing billing) throws Exception {
        if (billing == null || billing.getId() <= 0) {
            throw new Exception("La factura debe incluir un id válido");
        }

        Billing existing = billingPort.findById(billing);
        if (existing == null) {
            throw new Exception("La factura no existe");
        }

        validateAndCompleteBilling(billing);
        billingPort.update(billing);
    }

    public void deleteBilling(Billing billing) throws Exception {
        if (billing == null || billing.getId() <= 0) {
            throw new Exception("La factura debe incluir un id válido");
        }

        Billing existing = billingPort.findById(billing);
        if (existing == null) {
            throw new Exception("La factura no existe");
        }

        billingPort.deleteById(billing);
    }

    public java.util.List<Billing> getAllBillings() throws Exception {
        return billingPort.findAll();
    }

    private void validateAndCompleteBilling(Billing billing) throws Exception {
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

        Patient tempPatient = new Patient();
        tempPatient.setDocument(billing.getPatientDocument());

        Patient patient = patientPort.findByDocument(tempPatient);

        if (patient == null)
            throw new Exception("El paciente no existe");

        if (patient.getPolicyNumber() <= 0)
            throw new Exception("El paciente no tiene una póliza registrada");

        if (billing.getPolicyNumber() != patient.getPolicyNumber())
            throw new Exception("La póliza ingresada no corresponde al paciente seleccionado");

        if (patient.getInsuranceCompanyName() == null || patient.getInsuranceCompanyName().trim().isEmpty())
            throw new Exception("El paciente no tiene compañía de seguros registrada");

        if (patient.getPolicyValidity() == null || patient.getPolicyEndDate() == null)
            throw new Exception("El paciente no tiene fechas de póliza registradas");

        Date validity = Date.valueOf(patient.getPolicyValidity());
        Date endDate = Date.valueOf(patient.getPolicyEndDate());

        if (endDate.before(validity))
            throw new Exception("La fecha de finalización debe ser posterior a la fecha de validez");

        billing.setInsuranceCompanyName(patient.getInsuranceCompanyName());
        billing.setPolicyValidity(validity);
        billing.setPolicyEndDate(endDate);

        Employee doctor = employeePort.findByDocument(billing.getDoctorName());
        if (doctor == null)
            throw new Exception("El doc no existe");
        billing.setDoctorName(doctor);
        billing.setPatientName(patient);
    }
}
