package kr.co._29cm.homework.domain.order;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class OrderCommand {

    @Getter
    @Builder
    public static class RegisterOrder {
        private final List<RegisterOrderItem> orderItemList;
    }

    @Getter
    @Builder
    public static class RegisterOrderItem {
        private final Integer orderCount;
        private final String itemName;
        private final String itemToken;
        private final Long itemPrice;

        public OrderItems toEntity(Order order) {
            return OrderItems.builder()
                .order(order)
                .orderCount(orderCount)
                .itemToken(itemToken)
                .itemName(itemName)
                .itemPrice(itemPrice)
                .build();
        }
    }

    @Getter
    @Builder
    public static class PaymentRequest {
        private final String orderToken;
    }

    @Getter
    @Builder
    public static class PaymentResponse {
        private final List<String> orderItemsInfo;
        private final long orderItemsAmount;
        private final long deliveryFee;
        private final long totalAmount;
    }

}
