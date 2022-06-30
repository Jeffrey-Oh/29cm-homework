package kr.co._29cm.homework.interfaces.order;

import kr.co._29cm.homework.domain.order.OrderCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderItemsDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterOrderRequest {
        private List<OrderCommand.RegisterOrderItem> orderItemList;

        public OrderCommand.RegisterOrder toCommand() {
            return OrderCommand.RegisterOrder.builder()
                .orderItemList(orderItemList)
                .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterOrderItem {
        @NotNull
        private Integer orderCount;

        @NotBlank
        private String itemToken;

        @NotBlank
        private String itemName;

        @NotNull
        private Long itemPrice;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentRequest {
        @NotBlank
        private String orderToken;

        public OrderCommand.PaymentRequest toCommand() {
            return OrderCommand.PaymentRequest.builder()
                .orderToken(orderToken)
                .build();
        }
    }

    @Getter
    public static class RegisterOrderResponse {
        private final String orderToken;

        public RegisterOrderResponse(String orderToken) {
            this.orderToken = orderToken;
        }
    }

    @Getter
    public static class PaymentResponse {
        private final OrderCommand.PaymentResponse paymentResponse;

        public PaymentResponse(OrderCommand.PaymentResponse paymentResponse) {
            this.paymentResponse = paymentResponse;
        }
    }

}
