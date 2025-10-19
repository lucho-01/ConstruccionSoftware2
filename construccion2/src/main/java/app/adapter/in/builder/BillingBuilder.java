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
	
	public Billing build(String namePatient, String doctorName, String patientDocument, String patientAge, String policyNumber, String insuranceCompanyName, String policyValidity, String policyEndDate) throws Exception{
		Billing billing= new Billing();
		Employee doctor = new Employee();
		Patient patient = new Patient();
		doctor.setFullName(employeeValidator.fullNameValidator(doctorName));
		billing.setInsuranceCompanyName(billingValidator.insuranceCompanyNameValidator(insuranceCompanyName));
		billing.setPatientAge(billingValidator.patientAgeValidator(patientAge));
		patient.setDocument(patientValidator.documentValidator(patientDocument));
		patient.setFullName(patientValidator.fullNameValidator(namePatient));
		billing.setPolicyEndDate(billingValidator.policyEndDateValidator(insuranceCompanyName));
		billing.setPolicyNumber(billingValidator.policyNumberValidator(policyNumber));
		billing.setPolicyValidity(billingValidator.policyValidityValidator(policyValidity));
		
		return billing;
		
	}
}
