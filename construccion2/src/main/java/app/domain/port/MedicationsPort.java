package app.domain.port;
import app.domain.model.Medications;
public interface MedicationsPort {
	
	public Medications findByOrderNumber(Medications medications) throws Exception;
	public void save(Medications mediactions) throws Exception;

}
