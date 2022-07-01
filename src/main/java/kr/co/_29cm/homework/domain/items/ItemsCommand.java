package kr.co._29cm.homework.domain.items;

import lombok.Builder;
import lombok.Getter;

public class ItemsCommand {

    @Getter
    @Builder
    public static class ItemsChangeStockAfterSellRequest {
        private final String itemToken;
        private final int changeStock;
    }

}
