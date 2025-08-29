package app.domain.port;

import app.domain.model.patient;

public interface PatientPort {
	public patient findByDocument(patient patient) throws Exception;
	public void save(patient patient) throws Exception;
}
