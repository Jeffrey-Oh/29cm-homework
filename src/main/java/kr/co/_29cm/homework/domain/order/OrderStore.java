package kr.co._29cm.homework.domain.order;

public interface OrderStore {

    Order storeOrder(Order order);
    OrderItems storeOrderItems(OrderItems orderItem);

}
