package kr.co._29cm.homework.application.order;

import kr.co._29cm.homework.domain.items.ItemsService;
import kr.co._29cm.homework.domain.order.OrderCommand;
import kr.co._29cm.homework.domain.order.OrderItems;
import kr.co._29cm.homework.domain.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final ItemsService itemsService;

    public String register(OrderCommand.RegisterOrder registerOrder) {
        return orderService.register(registerOrder);
    }

    @Transactional
    public synchronized OrderCommand.PaymentResponse payment(OrderCommand.PaymentRequest paymentRequest) {
        List<OrderItems> orderItemsList = orderService.getOrderItems(paymentRequest.getOrderToken());
        itemsService.checkAffordStock(orderItemsList);
        itemsService.changeStockAfterSell(orderItemsList);
        return orderService.payment(paymentRequest);
    }

}
