package app.domain.model;

public class RegisterVisit {
    private String patientId;
    private String bloodPressure;
    private double temperature;
    private int pulse;
    private int oxygenLevel;
    private Medications medications;
    private Procedure procedure;
    private DiagnosticAid diagnosticAid;
    
    
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public int getPulse() {
		return pulse;
	}
	public void setPulse(int pulse) {
		this.pulse = pulse;
	}
	public int getOxygenLevel() {
		return oxygenLevel;
	}
	public void setOxygenLevel(int oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}
	public Medications getMedications() {
		return medications;
	}
	public void setMedications(Medications medications) {
		this.medications = medications;
	}
	public Procedure getProcedure() {
		return procedure;
	}
	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}
	public DiagnosticAid getDiagnosticAid() {
		return diagnosticAid;
	}
	public void setDiagnosticAid(DiagnosticAid diagnosticAid) {
		this.diagnosticAid = diagnosticAid;
	}

}
