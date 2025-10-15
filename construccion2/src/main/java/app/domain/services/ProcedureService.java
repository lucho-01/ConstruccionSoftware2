package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.Procedure;
import app.domain.port.ProcedurePort;
@Service
public class ProcedureService {
	@Autowired
	private ProcedurePort procedurePort;
	
	public void createProcedure(Procedure procedure) throws Exception{
		if(procedurePort.findByOrderNumber(procedure)!=null) {
			throw new Exception("Ya existe un procedimiento con este numero de orden");
		}
		
		procedurePort.save(procedure);
		
	}
}
