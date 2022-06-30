package kr.co._29cm.homework;

import kr.co._29cm.homework.application.items.ItemsFacade;
import kr.co._29cm.homework.application.order.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class OrderApplication implements CommandLineRunner {

    private final ItemsFacade itemsFacade;
    private final OrderFacade orderFacade;

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        OrderProgram orderProgram = new OrderProgram(itemsFacade, orderFacade);
        orderProgram.start();
    }
}
