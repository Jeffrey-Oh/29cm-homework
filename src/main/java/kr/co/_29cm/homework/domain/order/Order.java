package kr.co._29cm.homework.domain.order;

import kr.co._29cm.homework.common.util.TokenGenerator;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItems> orderItemsList;

    @Builder
    public Order() {
        this.orderToken = TokenGenerator.randomCharacter(12);
    }

}
