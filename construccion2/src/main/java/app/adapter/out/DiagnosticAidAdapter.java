package app.adapter.out;

import org.springframework.stereotype.Service;

import app.domain.model.DiagnosticAid;
import app.domain.port.DiagnosticAidPort;

@Service
public class DiagnosticAidAdapter implements DiagnosticAidPort{

	@Override
	public DiagnosticAid findByOrderNumber(DiagnosticAid diagnosticAid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(DiagnosticAid diagnosticAid) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
