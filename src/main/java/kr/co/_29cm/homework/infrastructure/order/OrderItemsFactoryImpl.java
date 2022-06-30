package kr.co._29cm.homework.infrastructure.order;

import kr.co._29cm.homework.domain.order.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderItemsFactoryImpl implements OrderItemsFactory {

    private final OrderStore orderStore;

    @Override
    @Transactional
    public List<OrderItems> store(Order order, OrderCommand.RegisterOrder registerOrder) {
        return registerOrder.getOrderItemList().stream().map(registerOrderItem -> {
            OrderItems initOrderItems = registerOrderItem.toEntity(order);
            return orderStore.storeOrderItems(initOrderItems);
        }).collect(Collectors.toList());
    }

}
