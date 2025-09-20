package app.adapter.in.builder;

import app.adapter.in.validators.BillingValidator;
import app.domain.model.Billing;
import app.domain.model.Patient;

public class BillingBuilder {

	private BillingValidator billingValidator;;
	
	public Billing build(String namePatient, String doctorName, String patientDocument, String patientAge, String policyNumber, String insuranceCompanyName, String policyValidity, String policyEndDate) throws Exception{
		Billing billing= new Billing();
		billing.setDoctorName(billingValidator.doctorNameValidator(doctorName));
		billing.setInsuranceCompanyName(billingValidator.insuranceCompanyNameValidator(insuranceCompanyName));
		billing.setPatientAge(billingValidator.patientAgeValidator(patientAge));
		billing.setPatientDocument(billingValidator.PatientDocumentValidator(patientDocument));
		billing.setPatientName(billingValidator.namePatientValidator(namePatient));
		billing.setPolicyEndDate(billingValidator.policyEndDateValidator(insuranceCompanyName));
		billing.setPolicyNumber(billingValidator.policyNumberValidator(policyNumber));
		billing.setPolicyValidity(billingValidator.policyValidityValidator(policyValidity));
		
		return billing;
		
	}
}
