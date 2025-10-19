package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.DiagnosticAid;
import app.domain.port.DiagnosticAidPort;
@Service
public class DiagnosticAidService {
	@Autowired
	private DiagnosticAidPort diagnosticAidPort;
	
	public void createDiagnosticAid(DiagnosticAid diagnosticAid) throws Exception{
		if(diagnosticAidPort.findByOrderNumber(diagnosticAid)!=null) {
			throw new Exception("No pueden haber dos ayudas diagnosticas con el mismo numero de orden");
		}
		
		diagnosticAidPort.save(diagnosticAid);
	}
}
