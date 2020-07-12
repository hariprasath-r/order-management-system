package in.hp.java.orderservice.mapper;

import in.hp.java.orderservice.dto.OrderDto;
import in.hp.java.orderservice.entity.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * Mapper class to map between entity and dto
 */
@Component
public class OrderMapper {

    /**
     * Returns a DTO for Order Entity
     * @param order
     * @return
     */
    public OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setCustomerName(order.getCustomerName());
        dto.setOrderDate(order.getOrderDate().toLocalDate());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setTotal(order.getTotal());
        return dto;
    }

    /**
     * Returns an Entity for Order DTO
     * @param orderDto
     * @return
     */
    public Order toEntity(OrderDto orderDto) {
        Order entity = new Order();
        entity.setCustomerName(orderDto.getCustomerName());
        entity.setOrderDate(Date.valueOf(orderDto.getOrderDate()));
        entity.setShippingAddress(orderDto.getShippingAddress());
        entity.setTotal(orderDto.getTotal());
        return entity;
    }
}
