package app.domain.services;

import app.domain.model.DiagnosticAid;
import app.domain.port.DiagnosticAidPort;

public class DiagnosticAidService {
	private DiagnosticAidPort diagnosticAidPort;
	
	public void createDiagnosticAid(DiagnosticAid diagnosticAid) throws Exception{
		if(diagnosticAidPort.findByOrderNumber(diagnosticAid)!=null) {
			throw new Exception("No pueden haber dos facturas con el mismo numero de orden");
		}
		
		diagnosticAidPort.save(diagnosticAid);
	}
}
