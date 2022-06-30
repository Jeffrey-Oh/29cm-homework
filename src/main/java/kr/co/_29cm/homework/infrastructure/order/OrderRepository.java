package kr.co._29cm.homework.infrastructure.order;

import kr.co._29cm.homework.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderToken(String orderToken);

}
