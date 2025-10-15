package app.adapter.out;

import org.springframework.stereotype.Service;

import app.domain.model.Medications;
import app.domain.port.MedicationsPort;
@Service
public class MedicationsAdapter implements MedicationsPort {

	@Override
	public Medications findByOrderNumber(Medications medications) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Medications mediactions) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
