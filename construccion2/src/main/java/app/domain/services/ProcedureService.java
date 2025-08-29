package app.domain.services;

import app.domain.model.procedure;
import app.domain.port.ProcedurePort;

public class ProcedureService {
	
	private ProcedurePort procedurePort;
	
	public void createProcedure(procedure procedure) throws Exception{
		if(procedurePort.findByOrderNumber(procedure)!=null) {
			throw new Exception("Ya existe un procedimiento con este numero de orden");
		}
		
		procedurePort.save(procedure);
		
	}
}
