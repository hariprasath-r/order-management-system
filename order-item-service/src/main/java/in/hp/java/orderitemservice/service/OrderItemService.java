package in.hp.java.orderitemservice.service;

import in.hp.java.orderitemservice.dto.OrderItemDto;
import in.hp.java.orderitemservice.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    public List<OrderItemDto> getAllOrderItemsForOrderId(Integer orderId) {
        return Collections.emptyList();
    }

    public void createOrderItemsForOrderId(Integer orderId, List<OrderItemDto> orderItemDto) {
    }
}
