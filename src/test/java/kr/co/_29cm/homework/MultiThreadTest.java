package kr.co._29cm.homework;

import kr.co._29cm.homework.application.items.ItemsFacade;
import kr.co._29cm.homework.application.order.OrderFacade;
import kr.co._29cm.homework.common.exception.SoldOutException;
import kr.co._29cm.homework.domain.items.Items;
import kr.co._29cm.homework.domain.items.ItemsInfo;
import kr.co._29cm.homework.domain.order.OrderCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@ActiveProfiles("test")
@SpringBootTest
class MultiThreadTest {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private ItemsFacade itemsFacade;

    @DisplayName("멀티 쓰레드 SoldOutException 정상 동작")
    @Test
    void multiThreadSoldOutException() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        AtomicReference<SoldOutException> failure = new AtomicReference<>();

        // 중복으로 구매할 아이템 토큰 값
        String itemToken = "xclansklrSSr";

        List<OrderCommand.RegisterOrderItem> orderItemList = List.of(
            OrderCommand.RegisterOrderItem.builder()
                .itemToken(itemToken)
                .itemPrice(33250L)
                .itemName("20SS 오픈 카라/투 버튼 피케 티셔츠 (6color)")
                .orderCount(10)
                .build()
        );

        for (int i=0; i<10; i++) {
            new Thread(() -> {

                // 주문 처리
                String orderToken = orderFacade.register(OrderCommand.RegisterOrder.builder()
                    .orderItemList(orderItemList)
                    .build());

                System.out.println("------------------------------");
                System.out.println("orderToken : " + orderToken);
                System.out.println("------------------------------");

                // 결제 처리
                OrderCommand.PaymentRequest paymentRequest = OrderCommand.PaymentRequest.builder()
                    .orderToken(orderToken)
                    .build();

                ItemsInfo.ItemsInfoAll itemsInfoAll = itemsFacade.getItems(itemToken);

                try {
                    OrderCommand.PaymentResponse paymentResponse = orderFacade.payment(paymentRequest);

                    System.out.println("------------------------------");
                    System.out.println(System.currentTimeMillis());
                    System.out.println("남은 수량 : " + itemsInfoAll.getStock());
                    System.out.println("------------------------------");

                    paymentResponse.getOrderItemsInfo().forEach(System.out::println);
                } catch (SoldOutException e) {
                    failure.set(e);
                }

                System.out.println("Current Thread name : " + Thread.currentThread().getName());

                countDownLatch.countDown();

            }).start();
        }

        countDownLatch.await();

        Assertions.assertThrows(SoldOutException.class, () -> {
            if (failure.get() != null) {
                throw failure.get();
            }
        });

    }

}
