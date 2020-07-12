package in.hp.java.orderitemservice.mapper;

import in.hp.java.orderitemservice.dto.OrderItemDto;
import in.hp.java.orderitemservice.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemDto toDto(OrderItem orderItem) {
        OrderItemDto dto = new OrderItemDto();
        dto.setProductCode(orderItem.getProductCode());
        dto.setProductName(orderItem.getProductName());
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }

    public OrderItem toEntity(Integer orderId, OrderItemDto orderItemDto) {
        OrderItem entity = new OrderItem();
        entity.setOrderId(orderId);
        entity.setProductCode(orderItemDto.getProductCode());
        entity.setProductName(orderItemDto.getProductName());
        entity.setQuantity(orderItemDto.getQuantity());
        return entity;
    }
}
