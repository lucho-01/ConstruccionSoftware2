package app.adapter.out;

import org.springframework.stereotype.Service;

import app.domain.model.Procedure;
import app.domain.port.ProcedurePort;

@Service
public class ProcedureAdapter implements ProcedurePort {

	@Override
	public Procedure findByOrderNumber(Procedure procedure) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Procedure procedure) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
