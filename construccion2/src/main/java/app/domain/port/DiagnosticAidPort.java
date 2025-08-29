package app.domain.port;

import app.domain.model.diagnosticAid;

public interface DiagnosticAidPort {
	public diagnosticAid findByOrderNumber(diagnosticAid diagnosticAid) throws Exception;
	public void save(diagnosticAid diagnosticAid) throws Exception;
}

