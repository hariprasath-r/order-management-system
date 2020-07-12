package in.hp.java.orderitemservice.repository;

import in.hp.java.orderitemservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    Optional<List<OrderItem>> findByOrderId(Integer orderId);
}
