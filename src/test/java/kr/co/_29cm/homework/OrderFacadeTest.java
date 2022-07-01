package kr.co._29cm.homework;

import kr.co._29cm.homework.application.order.OrderFacade;
import kr.co._29cm.homework.domain.order.OrderCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderFacadeTest {

    @Autowired
    private OrderFacade orderFacade;

    @Test
    public void register() {

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

        String orderToken = orderFacade.register(registerOrder);

        System.out.println("orderToken : " + orderToken);

    }

}
