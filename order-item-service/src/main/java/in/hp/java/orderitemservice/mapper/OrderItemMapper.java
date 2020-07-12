package in.hp.java.orderitemservice.mapper;

import in.hp.java.orderitemservice.dto.OrderItemDto;
import in.hp.java.orderitemservice.entity.OrderItem;
import org.springframework.stereotype.Component;

/**
 * Mapper class to map between Order Items DTO and Entity
 */
@Component
public class OrderItemMapper {

    /**
     * Return an Order Item DTO from Entity.
     *
     * @param orderItem
     * @return
     */
    public OrderItemDto toDto(OrderItem orderItem) {
        OrderItemDto dto = new OrderItemDto();
        dto.setProductCode(orderItem.getProductCode());
        dto.setProductName(orderItem.getProductName());
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }

    /**
     * Returns an Order Item Entity from DTO.
     *
     * @param orderId
     * @param orderItemDto
     * @return
     */
    public OrderItem toEntity(String orderId, OrderItemDto orderItemDto) {
        OrderItem entity = new OrderItem();
        entity.setOrderId(orderId);
        entity.setProductCode(orderItemDto.getProductCode());
        entity.setProductName(orderItemDto.getProductName());
        entity.setQuantity(orderItemDto.getQuantity());
        return entity;
    }
}
