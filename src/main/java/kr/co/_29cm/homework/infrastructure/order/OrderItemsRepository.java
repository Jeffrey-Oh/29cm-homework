package kr.co._29cm.homework.infrastructure.order;

import kr.co._29cm.homework.domain.order.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
