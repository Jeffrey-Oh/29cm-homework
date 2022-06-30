package kr.co._29cm.homework.domain.order;

import java.util.List;

public interface OrderService {

    String register(OrderCommand.RegisterOrder registerOrder);
    List<OrderItems> getOrderItems(String orderToken);
    OrderCommand.PaymentResponse payment(OrderCommand.PaymentRequest paymentRequest);

}
