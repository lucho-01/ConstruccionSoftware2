package app.domain.port;

import java.util.List;

import app.domain.model.Patient;
public interface PatientPort {
	public Patient findByDocument(Patient patient) throws Exception;
	public Patient findById(Patient patient) throws Exception;
	public List<Patient> findByPatient(Patient patient) throws Exception;
	public Patient updatePatient(Patient patient) throws Exception;
	public Patient save(Patient patient) throws Exception;
	public Patient deleteById(Patient patient) throws Exception;
	public List<Patient> findAll() throws Exception;
	public Patient findByUsername(String username) throws Exception;
	public Patient findByEmail(String email) throws Exception;
}
