package kr.co._29cm.homework.domain.order;

import java.util.List;

public interface OrderItemsFactory {

    List<OrderItems> store(Order order, OrderCommand.RegisterOrder registerOrder);

}
