package kr.co._29cm.homework;

import kr.co._29cm.homework.domain.items.Items;
import kr.co._29cm.homework.domain.order.Order;
import kr.co._29cm.homework.infrastructure.item.ItemsRepository;
import kr.co._29cm.homework.infrastructure.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class JpaTest {

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void test() {

        List<Items> itemsList = itemsRepository.findAll();
        itemsList.forEach(items -> System.out.println(items.toString()));

    }

    @Test
    public void test2() {

        Order order = orderRepository.save(Order.builder().build());

        System.out.println("order : " + order.getOrderToken());

    }

}
