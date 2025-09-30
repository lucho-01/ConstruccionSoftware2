package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.BillingValidator;
import app.domain.model.Billing;
import app.domain.model.Patient;
@Component
public class BillingBuilder {
	@Autowired
	private BillingValidator billingValidator;;
	
	public Billing build(String namePatient, String doctorName, String patientDocument, String patientAge, String policyNumber, String insuranceCompanyName, String policyValidity, String policyEndDate) throws Exception{
		Billing billing= new Billing();
		billing.setDoctorName(billingValidator.doctorNameValidator(doctorName, null));
		billing.setInsuranceCompanyName(billingValidator.insuranceCompanyNameValidator(insuranceCompanyName));
		billing.setPatientAge(billingValidator.patientAgeValidator(patientAge));
		billing.setPatientDocument(billingValidator.PatientDocumentValidator(patientDocument));
		billing.setPatientName(billingValidator.patientNameValidator(namePatient, null));
		billing.setPolicyEndDate(billingValidator.policyEndDateValidator(insuranceCompanyName));
		billing.setPolicyNumber(billingValidator.policyNumberValidator(policyNumber));
		billing.setPolicyValidity(billingValidator.policyValidityValidator(policyValidity));
		
		return billing;
		
	}
}
