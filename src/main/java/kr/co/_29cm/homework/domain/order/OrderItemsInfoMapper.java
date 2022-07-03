package kr.co._29cm.homework.domain.order;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderItemsInfoMapper {

    OrderItemsInfo.OrderItems of(OrderItems orderItems);

}
