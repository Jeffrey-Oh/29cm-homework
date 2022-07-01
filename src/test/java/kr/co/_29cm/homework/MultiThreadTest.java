package kr.co._29cm.homework;

import kr.co._29cm.homework.application.items.ItemsFacade;
import kr.co._29cm.homework.application.order.OrderFacade;
import kr.co._29cm.homework.common.exception.SoldOutException;
import kr.co._29cm.homework.domain.items.ItemsInfo;
import kr.co._29cm.homework.domain.order.OrderCommand;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
class MultiThreadTest {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private ItemsFacade itemsFacade;

    class PaymentTest implements Runnable {

        @Override
        public void run() {

            // 중복으로 구매할 아이템 토큰 값
            String itemToken = "WelKARNudsif";

            List<OrderCommand.RegisterOrderItem> orderItemList = List.of(
                OrderCommand.RegisterOrderItem.builder()
                    .itemToken(itemToken)
                    .itemPrice(238000L)
                    .itemName("BS 02-2A DAYPACK 26 (BLACK)")
                    .orderCount(2)
                    .build()
            );

            // 주문 처리
            String orderToken = orderFacade.register(OrderCommand.RegisterOrder.builder()
                .orderItemList(orderItemList)
                .build());

            // 결제 처리
            OrderCommand.PaymentRequest paymentRequest = OrderCommand.PaymentRequest.builder()
                .orderToken(orderToken)
                .build();

            ItemsInfo.ItemsInfoAll itemsInfoAll = itemsFacade.getItems(itemToken);

            OrderCommand.PaymentResponse paymentResponse = orderFacade.payment(paymentRequest);

            System.out.println("현재 스레드 : " + Thread.currentThread());
            System.out.println("남은 수량 : " + itemsInfoAll.getStock());
            System.out.println("주문 내역 : ");
            paymentResponse.getOrderItemsInfo().forEach(System.out::println);
            System.out.println("------------------------------");


        }

    }

    @DisplayName("멀티 쓰레드 SoldOutException 정상 동작")
    @Test
    void multiThreadSoldOutException() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);

        Assertions.assertThrows(SoldOutException.class, () -> {

            for (int i=0; i<10; i++) {
                try {
                    executorService.submit(new PaymentTest()).get();
                    countDownLatch.countDown();
                } catch (Exception e) {
                    if (e.getCause() instanceof SoldOutException) {
                        log.error("Exception : {}", e.getCause().getClass().getName());
                        log.error("Message : {}", e.getCause().getMessage());
                        throw e.getCause();
                    }
                }
            }

            countDownLatch.await();

        });
    }

}
