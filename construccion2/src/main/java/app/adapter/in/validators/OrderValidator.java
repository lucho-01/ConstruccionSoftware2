package app.adapter.in.validators;

import app.domain.model.DiagnosticAid;
import app.domain.model.Medications;
import app.domain.model.Procedure;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator extends SimpleValidator{

	public List listDiagnosticAidValidator(String value, List<String> list) throws Exception {
            return listValidator("Ayuda diagnostica de la orden", list);
	}
        
        public List listProcedureValidator(String value, List<String> list) throws Exception {
            return listValidator("El procedimiento de la orden", list);
                }
        public List listMedicationsValidator(String value, List<String> list) throws Exception {
            return listMedicationsValidator("Medicina de la orden", list);
                }
	
}
