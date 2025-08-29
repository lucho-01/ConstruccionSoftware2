package app.domain.services;

import app.domain.model.Procedure;
import app.domain.port.ProcedurePort;

public class ProcedureService {
	
	private ProcedurePort procedurePort;
	
	public void createProcedure(Procedure procedure) throws Exception{
		if(procedurePort.findByOrderNumber(procedure)!=null) {
			throw new Exception("Ya existe un procedimiento con este numero de orden");
		}
		
		procedurePort.save(procedure);
		
	}
}
