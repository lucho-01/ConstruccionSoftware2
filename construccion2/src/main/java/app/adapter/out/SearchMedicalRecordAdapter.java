package app.adapter.out;

import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.model.MedicalRecord;
import app.domain.model.Patient;
import app.domain.port.SearchMedicalRecordPort;

@Service
public class SearchMedicalRecordAdapter implements SearchMedicalRecordPort{

	@Override
	public SearchMedicalRecordPort findById(Patient patient) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedicalRecord> findByPatient(Patient patient) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
