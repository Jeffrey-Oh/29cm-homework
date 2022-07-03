package kr.co._29cm.homework.domain.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderStore orderStore;
    private final OrderReader orderReader;
    private final OrderItemsFactory orderItemsFactory;
    private final OrderItemsInfoMapper orderItemsInfoMapper;
    private final PaymentProcessor paymentProcessor;

    @Override
    public String register(OrderCommand.RegisterOrder registerOrder) {
        Order order = orderStore.storeOrder(new Order());
        orderItemsFactory.store(order, registerOrder);
        return order.getOrderToken();
    }

    @Override
    public List<OrderItemsInfo.OrderItems> getOrderItems(String orderToken) {
        Order order = orderReader.getOrder(orderToken);
        List<OrderItemsInfo.OrderItems> orderItemsList = new ArrayList<>();
        order.getOrderItemsList().forEach(orderItems -> orderItemsList.add(orderItemsInfoMapper.of(orderItems)));
        return orderItemsList;
    }

    @Override
    public OrderCommand.PaymentResponse payment(String orderToken) {
        Order order = orderReader.getOrder(orderToken);
        return paymentProcessor.pay(order);
    }

}
