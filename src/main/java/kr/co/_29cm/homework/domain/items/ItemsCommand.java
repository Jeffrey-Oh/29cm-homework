package kr.co._29cm.homework.domain.items;

import lombok.Builder;
import lombok.Getter;

public class ItemsCommand {

    @Getter
    @Builder
    public static class ItemsChangeStockAfterSellRequest {
        private String itemToken;
        private int changeStock;
    }

}
