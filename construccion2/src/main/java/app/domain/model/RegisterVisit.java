package app.domain.model;

public class RegisterVisit {
    private Patient patient;
    private double bloodPressure;
    private double temperature;
    private int pulse;
    private int oxygenLevel;
    private String medications;
    private String procedure;
    private String diagnosticAid;

    public double getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(double bloodPressure) { this.bloodPressure = bloodPressure; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public int getPulse() { return pulse; }
    public void setPulse(int pulse) { this.pulse = pulse; }

    public int getOxygenLevel() { return oxygenLevel; }
    public void setOxygenLevel(int oxygenLevel) { this.oxygenLevel = oxygenLevel; }

    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }

    public String getProcedure() { return procedure; }
    public void setProcedure(String procedure) { this.procedure = procedure; }

    public String getDiagnosticAid() { return diagnosticAid; }
    public void setDiagnosticAid(String diagnosticAid) { this.diagnosticAid = diagnosticAid; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
}
