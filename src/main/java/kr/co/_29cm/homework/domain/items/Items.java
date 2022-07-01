package kr.co._29cm.homework.domain.items;

import kr.co._29cm.homework.common.util.TokenGenerator;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "items")
@ToString
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemToken;
    private String itemNo;
    private String itemName;
    private Long price;
    private int stock;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ON_SALE("판매중"),
        END_OF_SALE("판매종료");

        private final String description;
    }

    @Builder
    public Items(String itemNo, String itemName, Long price, int stock) {
        if (StringUtils.isBlank(itemNo)) throw new IllegalArgumentException("empty itemNo");
        if (StringUtils.isBlank(itemName)) throw new IllegalArgumentException("empty itemName");
        if (price == null) throw new IllegalArgumentException("empty price");
        if (stock < 0) throw new IllegalArgumentException("empty stock");

        this.itemToken = TokenGenerator.randomCharacter(12);
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.price = price;
        this.stock = stock;
        this.status = Status.ON_SALE;
    }

    /**
     * 판매 후 재고 수량 수정
     */
    public void changeStockAfterSell(int changeStock) {
        // 판매중이고 수량 수정되는 값이 0 보다 큰 경우에만 수정가능
        if (this.status.equals(Status.ON_SALE)) {
            this.stock -= changeStock;

            // 완판한 경우에 바로 판매종료로 상태 변경
            if (this.stock == 0) changeEndOfSale();
        }
    }

    /**
     * 판매종료로 상태 변경
     */
    public void changeEndOfSale() {
        this.status = Status.END_OF_SALE;
    }

}
