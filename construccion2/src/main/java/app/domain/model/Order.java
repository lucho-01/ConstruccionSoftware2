package app.domain.model;

import java.util.List;

public class Order {
	
	    private String orderId;
	    private String patientId;
	    private List<Medications> medications;
	    private List<Procedure> procedures;
	    private List<DiagnosticAid> diagnosticAids;

	    // Getters y Setters
	    public String getOrderId() { return orderId; }
	    public void setOrderId(String orderId) { this.orderId = orderId; }

	    public String getPatientId() { return patientId; }
	    public void setPatientId(String patientId) { this.patientId = patientId; }

	    public List<Medications> getMedications() { return medications; }
	    public void setMedications(List<Medications> medications) { this.medications = medications; }

	    public List<Procedure> getProcedures() { return procedures; }
	    public void setProcedures(List<Procedure> procedures) { this.procedures = procedures; }

	    public List<DiagnosticAid> getDiagnosticAids() { return diagnosticAids; }
	    public void setDiagnosticAids(List<DiagnosticAid> diagnosticAids) { this.diagnosticAids = diagnosticAids; }
	}

