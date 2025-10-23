package app.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import app.domain.model.Billing;
import app.domain.port.BillingPort;
import app.infrastructure.entities.BillingEntity;
import app.infrastructure.mapper.BillingMapper;
import app.infrastructure.repository.BillingRepository;

@Service
public class BillingAdapter implements BillingPort {
	
	@Autowired
	private BillingRepository billingRepository;
	@Autowired
	private BillingMapper billingMapper;
	
	
	@Override
	public Billing findByPatientDocument(Billing billing) throws Exception {
		BillingEntity billingEntity = billingRepository.findByPatientDocument(billing.getPatientDocument());	
		return billingMapper.toDomain(billingEntity);	
		
	}

	@Override
	public void save(Billing billing) throws Exception {
		BillingEntity billingEntity = billingMapper.toEntity(billing);
		billingEntity.setId(null);
		
		billingRepository.save(billingEntity);
		System.out.println("Se ha guardado correctamente la factura!");

		
	}

}
