package app.domain.model;

public class RegisterVisit {
    private String patientId;
    private String bloodPressure;
    private double temperature;
    private int pulse;
    private int oxygenLevel;
    private String medication;
    private String procedure;
    private String testPerformed;
    private String observations;

    // Getters y Setters
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public int getPulse() { return pulse; }
    public void setPulse(int pulse) { this.pulse = pulse; }

    public int getOxygenLevel() { return oxygenLevel; }
    public void setOxygenLevel(int oxygenLevel) { this.oxygenLevel = oxygenLevel; }

    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }

    public String getProcedure() { return procedure; }
    public void setProcedure(String procedure) { this.procedure = procedure; }

    public String getTestPerformed() { return testPerformed; }
    public void setTestPerformed(String testPerformed) { this.testPerformed = testPerformed; }

    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
}
