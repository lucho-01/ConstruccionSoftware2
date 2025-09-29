package app.adapter.out;

import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.model.Patient;
import app.domain.port.PatientPort;

@Service
public class PatientAdapter implements PatientPort {

	@Override
	public Patient findByDocument(Patient patient) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient findById(Patient patient) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> findByPatient(Patient patient) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient updatePatient(Patient patient) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Patient patient) throws Exception {
		System.out.println("Se guardo al paciente");
		
	}

}
