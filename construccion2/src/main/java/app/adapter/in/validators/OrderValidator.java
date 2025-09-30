package app.adapter.in.validators;

import app.domain.model.DiagnosticAid;
import app.domain.model.Medications;
import app.domain.model.Procedure;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator extends SimpleValidator{

	public List listDiagnosticAidValidator(String value, List list) throws Exception {
            return listValidator("Ayuda diagnostica de la orden", list);
	}
        
        public List listProcedureValidator(String value, List list) throws Exception {
            return listValidator("El procedimiento de la orden", list);
                }
        public List listMedicationsvalidator(String value, List list) throws Exception {
            return listValidator("Medicina de la orden", list);
                }
	
}
