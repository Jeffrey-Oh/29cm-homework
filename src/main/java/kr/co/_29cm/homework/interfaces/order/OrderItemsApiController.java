package kr.co._29cm.homework.interfaces.order;

import kr.co._29cm.homework.application.order.OrderFacade;
import kr.co._29cm.homework.domain.order.OrderCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class OrderItemsApiController {

    private final OrderFacade orderFacade;

    @PostMapping(value = "/register", name = "주문 등록하기")
    public OrderItemsDto.RegisterOrderResponse register(@RequestBody @Valid OrderItemsDto.RegisterOrderRequest registerOrderRequest) {
        OrderCommand.RegisterOrder initRegisterOrder = registerOrderRequest.toCommand();
        String orderToken = orderFacade.register(initRegisterOrder);
        return new OrderItemsDto.RegisterOrderResponse(orderToken);
    }

    @PostMapping(value = "/payment", name = "결제 하기")
    public OrderItemsDto.PaymentResponse payment(@RequestBody @Valid OrderItemsDto.PaymentRequest paymentRequest) {
        OrderCommand.PaymentRequest initPaymentRequest = paymentRequest.toCommand();
        OrderCommand.PaymentResponse paymentResponse = orderFacade.payment(initPaymentRequest);
        return new OrderItemsDto.PaymentResponse(paymentResponse);
    }

}
