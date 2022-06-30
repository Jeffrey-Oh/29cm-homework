package kr.co._29cm.homework.infrastructure.order;

import kr.co._29cm.homework.common.exception.NotFoundDataException;
import kr.co._29cm.homework.domain.order.Order;
import kr.co._29cm.homework.domain.order.OrderReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {

    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public Order getOrder(String orderToken) {
        return orderRepository.findByOrderToken(orderToken).orElseThrow(() -> new NotFoundDataException("Order"));
    }

}
