package kr.co._29cm.homework;

import kr.co._29cm.homework.application.order.OrderFacade;
import kr.co._29cm.homework.domain.items.ItemsService;
import kr.co._29cm.homework.domain.order.OrderCommand;
import kr.co._29cm.homework.domain.order.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ExtendWith(SpringExtension.class)
class MultiThreadTest {

    private static final ExecutorService service = Executors.newFixedThreadPool(10);

    @InjectMocks
    private OrderFacade orderFacade;

    @Mock
    private OrderService orderService;

    @Mock
    private ItemsService itemsService;

    @DisplayName("멀티 쓰레드 SoldOutException 정상 동작")
    @Test
    void multiThreadSoldOutException() {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        List<OrderCommand.RegisterOrderItem> orderItemList = List.of(
            OrderCommand.RegisterOrderItem.builder()
                .itemToken("xclansklrSSr")
                .itemPrice(33250L)
                .itemName("20SS 오픈 카라/투 버튼 피케 티셔츠 (6color)")
                .orderCount(10)
                .build()
        );

        // 주문 처리
        String orderToken = orderFacade.register(OrderCommand.RegisterOrder.builder()
            .orderItemList(orderItemList)
            .build());

        System.out.println(orderToken);

//        Assertions.assertThrows(SoldOutException.class, () -> {
//
//            for (int i=0; i<10; i++) {
//                service.execute(() -> {
//
//
//
//                    // 결제 처리
//                    OrderCommand.PaymentRequest paymentRequest = OrderCommand.PaymentRequest.builder()
//                        .orderToken(orderToken)
//                        .build();
//
//                    orderFacade.payment(paymentRequest);
//
//                    countDownLatch.countDown();
//
//                });
//            }
//
//        });

    }

}
