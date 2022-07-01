package kr.co._29cm.homework;


import kr.co._29cm.homework.domain.order.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStore orderStore;

    @Autowired
    private OrderItemsFactory orderItemsFactory;

    @Test
    public void orderService() {

        Order order = orderStore.storeOrder(new Order());

        List<OrderCommand.RegisterOrderItem> orderItemList = List.of(
            OrderCommand.RegisterOrderItem.builder()
                .itemToken("xclansklrSSr")
                .itemPrice(33250L)
                .itemName("20SS 오픈 카라/투 버튼 피케 티셔츠 (6color)")
                .orderCount(10)
                .build()
        );

        OrderCommand.RegisterOrder registerOrder = OrderCommand.RegisterOrder.builder()
            .orderItemList(orderItemList)
            .build();

        List<OrderItems> orderItemsList = orderItemsFactory.store(order, registerOrder);

        System.out.println(orderItemsList.get(0).getItemToken());

        String orderToken = orderService.register(registerOrder);

        System.out.println("orderToken : " + orderToken);

    }

}
