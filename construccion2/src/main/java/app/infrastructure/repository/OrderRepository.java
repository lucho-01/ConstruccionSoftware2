package app.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import app.infrastructure.entities.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	OrderEntity findById(long OrderId);
	List<OrderEntity> findByPatient_Document(long document);

}
