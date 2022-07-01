package kr.co._29cm.homework.domain.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderStore orderStore;
    private final OrderReader orderReader;
    private final OrderItemsFactory orderItemsFactory;
    private final PaymentProcessor paymentProcessor;

    @Override
    public String register(OrderCommand.RegisterOrder registerOrder) {
        Order order = orderStore.storeOrder(new Order());
        orderItemsFactory.store(order, registerOrder);
        return order.getOrderToken();
    }

    @Override
    public List<OrderItems> getOrderItems(String orderToken) {
        Order order = orderReader.getOrder(orderToken);
        return order.getOrderItemsList();
    }

    @Override
    public OrderCommand.PaymentResponse payment(OrderCommand.PaymentRequest paymentRequest) {
        String orderToken = paymentRequest.getOrderToken();
        Order order = orderReader.getOrder(orderToken);
        return paymentProcessor.pay(order);
    }

}
