package app.domain.model;

import java.util.List;

public class Order {
	
	    private String orderId;
	    private String patientId;
	    private List<Medications> medications;
	    private List<Procedure> procedure;
	    private List<DiagnosticAid> diagnosticAid;
	    private String medication;
	    private String procedures;
	    private String diagnosticAids;

	    public String getOrderId() { return orderId; }
	    public void setOrderId(String orderId) { this.orderId = orderId; }

	    public String getPatientId() { return patientId; }
	    public void setPatientId(String patientId) { this.patientId = patientId; }

	    public List<Medications> getMedications() { return medications; }
	    public void setMedications(List<Medications> medications) { this.medications = medications; }

	    public List<Procedure> getProcedures() { return procedure; }
	    public void setProcedures(List<Procedure> procedures) { this.procedure = procedures; }

	    public List<DiagnosticAid> getDiagnosticAids() { return diagnosticAid; }
	    public void setDiagnosticAids(List<DiagnosticAid> diagnosticAids) { this.diagnosticAid = diagnosticAids; }
	    
		public List<Procedure> getProcedure() {
			return procedure;
		}
		public void setProcedure(List<Procedure> procedure) {
			this.procedure = procedure;
		}
		public List<DiagnosticAid> getDiagnosticAid() {
			return diagnosticAid;
		}
		public void setDiagnosticAid(List<DiagnosticAid> diagnosticAid) {
			this.diagnosticAid = diagnosticAid;
		}
		public String getMedication() {
			return medication;
		}
		public void setMedication(String medication) {
			this.medication = medication;
		}
		public void setProcedures(String procedures) {
			this.procedures = procedures;
		}
		public void setDiagnosticAids(String diagnosticAids) {
			this.diagnosticAids = diagnosticAids;
		}
	}

