package app.adapter.out;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public java.util.List<Billing> findAll() throws Exception {
		return billingRepository.findAll().stream()
			.map(billingMapper::toDomain)
			.collect(Collectors.toList());
	}

	@Override
	public void save(Billing billing) throws Exception {
		BillingEntity billingEntity = billingMapper.toEntity(billing);
		billingEntity.setId(null);
		
		billingRepository.save(billingEntity);
		System.out.println("Se ha guardado correctamente la factura!");

		
	}

	@Override
	public Billing findById(Billing billing) throws Exception {
		Optional<BillingEntity> billingEntityOpt = billingRepository.findById(billing.getId());
		return billingEntityOpt.map(billingMapper::toDomain).orElse(null);
	}

	@Override
	public void update(Billing billing) throws Exception {
		BillingEntity billingEntity = billingMapper.toEntity(billing);
		billingRepository.save(billingEntity);
		System.out.println("Factura actualizada correctamente!");
	}

	@Override
	public void deleteById(Billing billing) throws Exception {
		billingRepository.deleteById(billing.getId());
		System.out.println("Factura eliminada correctamente!");
	}

}
