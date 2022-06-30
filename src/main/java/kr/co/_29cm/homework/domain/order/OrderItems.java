package kr.co._29cm.homework.domain.order;

import kr.co._29cm.homework.common.exception.BadRequestApiException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Integer orderCount;
    private String itemName;
    private String itemToken;
    private Long itemPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public OrderItems(Order order, Integer orderCount, String itemName, String itemToken, Long itemPrice) {
        if (order == null) throw new BadRequestApiException("order");
        if (orderCount == null) throw new BadRequestApiException("orderCount");
        if (itemName == null) throw new BadRequestApiException("itemName");
        if (itemToken == null) throw new BadRequestApiException("itemToken");
        if (itemPrice == null) throw new BadRequestApiException("itemPrice");

        this.order = order;
        this.orderCount = orderCount;
        this.itemName = itemName;
        this.itemToken = itemToken;
        this.itemPrice = itemPrice;
    }

}
