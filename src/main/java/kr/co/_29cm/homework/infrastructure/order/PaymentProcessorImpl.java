package kr.co._29cm.homework.infrastructure.order;

import kr.co._29cm.homework.domain.order.Order;
import kr.co._29cm.homework.domain.order.OrderCommand;
import kr.co._29cm.homework.domain.order.OrderItems;
import kr.co._29cm.homework.domain.order.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProcessorImpl implements PaymentProcessor {

    @Override
    @Transactional(readOnly = true)
    public OrderCommand.PaymentResponse pay(Order order) {

        List<OrderItems> orderItemsList = order.getOrderItemsList();

        // 주문내역
        // 주문금액
        List<String> orderItemsInfo = new ArrayList<>();
        long orderItemsAmount = 0L;
        for (OrderItems orderItems : orderItemsList) {
            String itemsInfoFormat = String.format("%s - %d개", orderItems.getItemName(), orderItems.getOrderCount());
            orderItemsInfo.add(itemsInfoFormat);

            orderItemsAmount += (orderItems.getItemPrice() * orderItems.getOrderCount());
        }

        // 배송비
        long deliveryFee = 0L;
        if (orderItemsAmount < 50000) deliveryFee = 2500L;

        // 지불금액
        long totalAmount = orderItemsAmount + deliveryFee;

        log.info("orderToken : {} 결제 완료", order.getOrderToken());

        return OrderCommand.PaymentResponse.builder()
            .orderItemsInfo(orderItemsInfo)
            .orderItemsAmount(orderItemsAmount)
            .deliveryFee(deliveryFee)
            .totalAmount(totalAmount)
            .build();
    }

}
