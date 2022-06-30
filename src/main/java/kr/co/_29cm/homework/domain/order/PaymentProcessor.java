package kr.co._29cm.homework.domain.order;

public interface PaymentProcessor {

    OrderCommand.PaymentResponse pay(Order order);

}
