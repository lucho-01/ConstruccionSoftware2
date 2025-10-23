package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.BillingValidator;
import app.adapter.in.validators.EmployeeValidator;
import app.adapter.in.validators.PatientValidator;
import app.domain.model.Billing;
import app.domain.model.Employee;
import app.domain.model.Patient;
@Component
public class BillingBuilder {
    
    @Autowired
    private BillingValidator billingValidator;
    @Autowired
    private EmployeeValidator employeeValidator;
    @Autowired
    private PatientValidator patientValidator;
    
    public Billing build(String namePatient, String doctorName, String patientDocument,String doctorDocument, String patientAge, String policyNumber, String insuranceCompanyName, String policyValidity, String policyEndDate) throws Exception {
        Billing billing = new Billing();
        Employee doctor = new Employee();
        Patient patient = new Patient();
        doctor.setFullName(employeeValidator.fullNameValidator(doctorName));
        doctor.setDocument(employeeValidator.documentValidator(doctorDocument));
        patient.setDocument(patientValidator.documentValidator(patientDocument));
        billing.setPatientDocument(patient.getDocument());
        patient.setFullName(patientValidator.fullNameValidator(namePatient));
        billing.setDoctorName(doctor);
        billing.setPatientName(patient);
        billing.setInsuranceCompanyName(billingValidator.insuranceCompanyNameValidator(insuranceCompanyName));
        billing.setPatientAge(billingValidator.patientAgeValidator(patientAge));
        billing.setPolicyNumber(billingValidator.policyNumberValidator(policyNumber));
        billing.setPolicyValidity(billingValidator.policyValidityValidator(policyValidity));
        billing.setPolicyEndDate(billingValidator.policyEndDateValidator(policyEndDate)); 

        return billing;
    }
}
