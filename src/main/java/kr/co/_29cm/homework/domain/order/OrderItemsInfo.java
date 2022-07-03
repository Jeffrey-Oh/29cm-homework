package kr.co._29cm.homework.domain.order;

import lombok.Builder;
import lombok.Getter;

public class OrderItemsInfo {

    @Getter
    @Builder
    public static class OrderItems {
        private final Integer orderCount;
        private final String itemName;
        private final String itemToken;
        private final Long itemPrice;
        private final Order order;
    }

}
