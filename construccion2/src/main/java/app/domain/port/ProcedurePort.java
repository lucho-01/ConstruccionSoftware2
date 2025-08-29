package app.domain.port;

import app.domain.model.procedure;


public interface ProcedurePort {
	public procedure findByOrderNumber(procedure procedure) throws Exception;
	public void save(procedure procedure) throws Exception;
	
}
