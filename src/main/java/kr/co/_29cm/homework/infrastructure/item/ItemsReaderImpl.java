package kr.co._29cm.homework.infrastructure.item;

import kr.co._29cm.homework.common.exception.BadRequestApiException;
import kr.co._29cm.homework.common.exception.NotFoundDataException;
import kr.co._29cm.homework.common.exception.message.ResponseCode;
import kr.co._29cm.homework.domain.items.Items;
import kr.co._29cm.homework.domain.items.ItemsReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemsReaderImpl implements ItemsReader {

    private final ItemsRepository itemsRepository;

    @Override
    public Items getItems(String itemToken) {
        if (StringUtils.isBlank(itemToken)) throw new BadRequestApiException("itemToken");

        return itemsRepository.findByItemToken(itemToken).orElseThrow(() -> new NotFoundDataException(ResponseCode.NOT_FOUND));
    }

    @Override
    public List<Items> getItemsAll() {
        return itemsRepository.findAll();
    }

}
