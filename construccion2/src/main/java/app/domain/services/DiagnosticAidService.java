package app.domain.services;

import app.domain.model.diagnosticAid;
import app.domain.port.DiagnosticAidPort;

public class DiagnosticAidService {
	private DiagnosticAidPort diagnosticAidPort;
	
	public void createDiagnosticAid(diagnosticAid diagnosticAid) throws Exception{
		if(diagnosticAidPort.findByOrderNumber(diagnosticAid)!=null) {
			throw new Exception("No pueden haber dos factoras con el mismo numero de orden");
		}
		
		diagnosticAidPort.save(diagnosticAid);
	}
}
