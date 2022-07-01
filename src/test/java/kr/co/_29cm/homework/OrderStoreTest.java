package kr.co._29cm.homework;

import kr.co._29cm.homework.domain.order.Order;
import kr.co._29cm.homework.domain.order.OrderItems;
import kr.co._29cm.homework.domain.order.OrderStore;
import kr.co._29cm.homework.infrastructure.order.OrderItemsRepository;
import kr.co._29cm.homework.infrastructure.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class OrderStoreTest {

    @Autowired
    private OrderStore orderStore;

    @Test
    public void orderStore() {

        Order order = orderStore.storeOrder(new Order());

        System.out.println("order : " + order.getOrderToken());

    }

    @Test
    public void storeOrderItems() {

        Order order = orderStore.storeOrder(new Order());

        OrderItems initOrderItems = OrderItems.builder()
            .itemName("test")
            .itemPrice(100L)
            .itemToken("testtest")
            .orderCount(1)
            .order(order)
            .build();

        OrderItems orderItems = orderStore.storeOrderItems(initOrderItems);

        System.out.println("orderItems : " + orderItems.getItemToken());

    }

}
