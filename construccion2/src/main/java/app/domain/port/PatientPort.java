package app.domain.port;

import app.domain.model.Patient;

public interface PatientPort {
	public Patient findByDocument(Patient patient) throws Exception;
	public void save(Patient patient) throws Exception;
}
