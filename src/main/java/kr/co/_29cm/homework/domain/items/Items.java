package kr.co._29cm.homework.domain.items;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "items")
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
