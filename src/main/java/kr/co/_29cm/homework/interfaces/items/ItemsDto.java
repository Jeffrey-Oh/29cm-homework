package kr.co._29cm.homework.interfaces.items;

import kr.co._29cm.homework.common.exception.response.SuccessResponse;
import kr.co._29cm.homework.domain.items.ItemsInfo;
import lombok.Getter;

import java.util.List;

public class ItemsDto {

    @Getter
    public static class ItemsInfoAllResponse extends SuccessResponse {
        private ItemsInfo.ItemsInfoAll itemsInfoAll;

        public ItemsInfoAllResponse(ItemsInfo.ItemsInfoAll itemsInfoAll) {
            this.itemsInfoAll = itemsInfoAll;
        }
    }

    @Getter
    public static class ItemsInfoAllListResponse extends SuccessResponse {
        private List<ItemsInfo.ItemsInfoAll> itemsInfoAllList;

        public ItemsInfoAllListResponse(List<ItemsInfo.ItemsInfoAll> itemsInfoAllList) {
            this.itemsInfoAllList = itemsInfoAllList;
        }
    }

}
