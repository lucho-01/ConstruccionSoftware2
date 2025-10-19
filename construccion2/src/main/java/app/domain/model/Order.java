package app.domain.model;

public class Order {
	
	    private long orderId;
	    private Patient patient;
	    private String medications;
	    private String procedure;
	    private String diagnosticAid;
	    
	    
		public long getOrderId() {
			return orderId;
		}
		public void setOrderId(long orderId) {
			this.orderId = orderId;
		}
		public String getMedications() {
			return medications;
		}
		public void setMedications(String medications) {
			this.medications = medications;
		}
		public String getProcedure() {
			return procedure;
		}
		public void setProcedure(String procedure) {
			this.procedure = procedure;
		}
		public String getDiagnosticAid() {
			return diagnosticAid;
		}
		public void setDiagnosticAid(String diagnosticAid) {
			this.diagnosticAid = diagnosticAid;
		}
		public Patient getPatient() {
			return patient;
		}
		public void setPatient(Patient patient) {
			this.patient = patient;
		}

}
