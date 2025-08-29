package app.domain.port;

import app.domain.model.DiagnosticAid;

public interface DiagnosticAidPort {
	public DiagnosticAid findByOrderNumber(DiagnosticAid diagnosticAid) throws Exception;
	public void save(DiagnosticAid diagnosticAid) throws Exception;
}

