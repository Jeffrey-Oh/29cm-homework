package kr.co._29cm.homework.infrastructure.order;

import kr.co._29cm.homework.domain.order.Order;
import kr.co._29cm.homework.domain.order.OrderItems;
import kr.co._29cm.homework.domain.order.OrderStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {

    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;

    @Override
    @Transactional
    public Order storeOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public OrderItems storeOrderItems(OrderItems orderItem) {
        return orderItemsRepository.save(orderItem);
    }

}
