package app.domain.port;

import app.domain.model.RegisterVisit;

public interface RegisterVisitPort {
    void save(RegisterVisit registerVisit) throws Exception;
    RegisterVisit findById(RegisterVisit registerVisit) throws Exception;
    java.util.List<RegisterVisit> findAll() throws Exception;
    RegisterVisit findByPatientDocument(long document) throws Exception;
    RegisterVisit update(RegisterVisit registerVisit) throws Exception;
    RegisterVisit deleteById(RegisterVisit registerVisit) throws Exception;
}
