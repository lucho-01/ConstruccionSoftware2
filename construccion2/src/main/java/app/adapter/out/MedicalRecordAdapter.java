package app.adapter.out;

import org.springframework.stereotype.Service;

import app.domain.model.MedicalRecord;
import app.domain.port.MedicalRecordPort;


@Service
public class MedicalRecordAdapter implements MedicalRecordPort {

	@Override
	public void save(MedicalRecord medicalRecord) throws Exception {
		System.out.println("Se guardo el registro medico");
		
	}

	@Override
	public void update(MedicalRecord medicalRecord) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
