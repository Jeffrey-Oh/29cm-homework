package kr.co._29cm.homework;

import kr.co._29cm.homework.application.items.ItemsFacade;
import kr.co._29cm.homework.application.order.OrderFacade;
import kr.co._29cm.homework.common.util.StringUtil;
import kr.co._29cm.homework.domain.items.ItemsInfo;
import kr.co._29cm.homework.domain.order.OrderCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProgram {

    private static final String START_MESSAGE = "입력(o[order]: 주문, q[quit]: 종료) : ";
    private static final String HEADER = "상품번호\t상품명\t\t\t\t\t\t\t\t판매가격\t재고수량";
    private static final String ITEM_NO_MESSAGE = "상품번호 : ";
    private static final String STOCK_MESSAGE = "수량 : ";
    private static final String HORIZONTAL_HYPHEN = "---------------------------------------------";
    private static final String ORDER_LIST_MESSAGE = "주문 내역 : ";
    private static final String ORDER_AMOUNT_MESSAGE = "주문금액 : ";
    private static final String DELIVERY_FEE_MESSAGE = "배송비 : ";
    private static final String PAYMENT_AMOUNT_MESSAGE = "지불금액 : ";
    private static final String FINAL_MESSAGE = "고객님의 주문 감사합니다.";

    private static final String SOLD_OUT_MESSAGE = "SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.";
    private static final String EMPTY_ORDER_MESSAGE = "주문하신 상품이 없습니다.";

    private final ItemsFacade itemsFacade;
    private final OrderFacade orderFacade;

    void start() {

        // 프로그램 시작
        while (true) {

            boolean checkSoldOut = false;

            Scanner scanner = new Scanner(System.in);
            String operation = "";

            // 명령어가 올바르지 않은 경우
            while (!operation.equals("o") && !operation.equals("q")) {
                System.out.print(START_MESSAGE);
                operation = scanner.nextLine().toLowerCase();

                switch (operation) {
                    case "o":
                    case "order":
                        operation = "o";
                        break;

                    case "q":
                    case "quit":
                        operation = "q";
                        break;
                }
            }

            // 프로그램 종료
            if (operation.equals("q")) {
                System.out.println(FINAL_MESSAGE);
                break;
            }

            // 주문 시작을 위한 상품 리스트 표시
            System.out.println(HEADER);
            List<ItemsInfo.ItemsInfoAll> itemsInfoList = itemsFacade.getItemsAll();
            StringBuilder itemsInfoStringBuilder = new StringBuilder();

            itemsInfoList.forEach(itemsInfoAll -> {
                itemsInfoStringBuilder.append(itemsInfoAll.getItemNo());
                itemsInfoStringBuilder.append("\t");
                itemsInfoStringBuilder.append(itemsInfoAll.getItemName());
                itemsInfoStringBuilder.append("\t");
                itemsInfoStringBuilder.append(itemsInfoAll.getPrice());
                itemsInfoStringBuilder.append("\t");
                itemsInfoStringBuilder.append(itemsInfoAll.getStock());
                itemsInfoStringBuilder.append("\n");
            });

            // 상품목록 출력
            System.out.println(itemsInfoStringBuilder);

            // 주문받기
            List<OrderCommand.RegisterOrderItem> orderItemList = new ArrayList<>();

            while (true) {

                // 상품번호 입력 메시지 출력
                System.out.print(ITEM_NO_MESSAGE);
                String itemNo = scanner.nextLine();
                while (!StringUtil.isNumberValid(itemNo) && !itemNo.equals(" ")) {
                    // 상품번호가 숫자이므로 숫자체크
                    // SPACE + ENTER 경우 결제를 위한 프로세스 이기때문에 패스
                    itemNo = scanner.nextLine();
                }

                // 수량 입력 메시지 출력
                System.out.print(STOCK_MESSAGE);
                String orderCount = scanner.nextLine();
                while (!StringUtil.isNumberValid(orderCount) && !orderCount.equals(" ")) {
                    // 수량 값이 숫자이므로 숫자체크
                    // SPACE + ENTER 경우 결제를 위한 프로세스 이기때문에 패스
                    orderCount = scanner.nextLine();
                }
                
                // 결제를 위해 중지
                if (itemNo.equals(" ") && orderCount.equals(" ")) break;

                // 주문상품 담기
                String finalItemNo = itemNo;
                ItemsInfo.ItemsInfoAll itemsInfoAll = itemsInfoList.stream().filter(v -> v.getItemNo().equals(finalItemNo)).findFirst().orElse(null);

                // 존재하는 상품 체크
                if (itemsInfoAll == null) {
                    System.out.println("존재하지 않는 상품입니다.");
                    System.out.println(HORIZONTAL_HYPHEN);
                } else {

                    // 재고 파악
                    if (itemsFacade.getItems(itemsInfoAll.getItemToken()).getStock() < Integer.parseInt(orderCount)) {
                        System.out.println(SOLD_OUT_MESSAGE);
                        checkSoldOut = true;
                        break;
                    } else {
                        // 이미 담은 상품인지 체크
                        OrderCommand.RegisterOrderItem checkOrderItem = orderItemList.stream().filter(v -> v.getItemToken().equals(itemsInfoAll.getItemToken())).findFirst().orElse(null);
                        if (checkOrderItem == null) {
                            orderItemList.add(OrderCommand.RegisterOrderItem.builder()
                                .orderCount(Integer.parseInt(orderCount))
                                .itemName(itemsInfoAll.getItemName())
                                .itemPrice(itemsInfoAll.getPrice())
                                .itemToken(itemsInfoAll.getItemToken())
                                .build());
                        } else {
                            // 기존에 담은 수량과 합치기
                            for (int i=0; i<orderItemList.size(); i++) {
                                OrderCommand.RegisterOrderItem registerOrderItem = orderItemList.get(i);
                                if (registerOrderItem.getItemToken().equals(itemsInfoAll.getItemToken())) {
                                    orderItemList.set(i, OrderCommand.RegisterOrderItem.builder()
                                        .orderCount(Integer.parseInt(orderCount) + registerOrderItem.getOrderCount())
                                        .itemName(itemsInfoAll.getItemName())
                                        .itemPrice(itemsInfoAll.getPrice())
                                        .itemToken(itemsInfoAll.getItemToken())
                                        .build());
                                    break;
                                }
                            }
                        }
                    }

                }
                
            }

            if (orderItemList.size() == 0) {
                System.out.println(EMPTY_ORDER_MESSAGE);
                System.out.println(HORIZONTAL_HYPHEN);
                System.out.println();
            } else if (!checkSoldOut) {
                // 주문상품 등록
                OrderCommand.RegisterOrder registerOrder = OrderCommand.RegisterOrder.builder()
                    .orderItemList(orderItemList)
                    .build();
                String orderToken = orderFacade.register(registerOrder);

                // 결제하기
                OrderCommand.PaymentRequest paymentRequest = OrderCommand.PaymentRequest.builder()
                    .orderToken(orderToken)
                    .build();

                OrderCommand.PaymentResponse paymentResponse = orderFacade.payment(paymentRequest);

                // 주문 내역 출력
                System.out.println(ORDER_LIST_MESSAGE);
                System.out.println(HORIZONTAL_HYPHEN);
                paymentResponse.getOrderItemsInfo().forEach(System.out::println);
                System.out.println(HORIZONTAL_HYPHEN);
                System.out.print(ORDER_AMOUNT_MESSAGE);
                System.out.println(StringUtil.convertCommaByPrice(paymentResponse.getOrderItemsAmount(), true));
                if (paymentResponse.getDeliveryFee() > 0) {
                    System.out.print(DELIVERY_FEE_MESSAGE);
                    System.out.println(StringUtil.convertCommaByPrice(paymentResponse.getDeliveryFee(), true));
                }
                System.out.println(HORIZONTAL_HYPHEN);
                System.out.print(PAYMENT_AMOUNT_MESSAGE);
                System.out.println(StringUtil.convertCommaByPrice(paymentResponse.getTotalAmount(), true));
                System.out.println(HORIZONTAL_HYPHEN);
                System.out.println();
            }
            
        }

        System.exit(0);
    }

}
