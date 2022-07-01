package kr.co._29cm.homework.config;

import kr.co._29cm.homework.OrderProgram;
import kr.co._29cm.homework.application.items.ItemsFacade;
import kr.co._29cm.homework.application.order.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
@RequiredArgsConstructor
public class CommandLineTaskExecutor implements CommandLineRunner {

    private final ItemsFacade itemsFacade;
    private final OrderFacade orderFacade;

    @Override
    public void run(String... args) throws Exception {
        OrderProgram orderProgram = new OrderProgram(itemsFacade, orderFacade);
        orderProgram.start();
    }

}
