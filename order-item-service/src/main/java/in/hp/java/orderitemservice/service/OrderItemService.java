package in.hp.java.orderitemservice.service;

import in.hp.java.orderitemservice.dto.OrderItemDto;
import in.hp.java.orderitemservice.entity.OrderItem;
import in.hp.java.orderitemservice.exception.OrderConflictException;
import in.hp.java.orderitemservice.exception.OrderItemException;
import in.hp.java.orderitemservice.exception.OrderItemNotFoundException;
import in.hp.java.orderitemservice.mapper.OrderItemMapper;
import in.hp.java.orderitemservice.repository.OrderItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemMapper mapper;

    /**
     * Retrieves Customer Order Items for a given Customer Name.
     *
     * @param orderId - Name of Customer
     * @return - Customer Order Items
     */
    public List<OrderItemDto> getAllOrderItemsForOrderId(String orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId)
                .orElseThrow(() -> new OrderItemNotFoundException("Order Items not found for ID: " + orderId));
        log.info("Order Items for ID: {}, {}", orderId, orderItems);
        return orderItems.stream()
                .map(item -> mapper.toDto(item))
                .collect(Collectors.toList());
    }

    /**
     * Creates Customer Order Items based for Customer.
     *
     * @param orderId      - Customer Name
     * @param orderItemDto - Order Detail
     */
    @Transactional
    public void createOrderItemsForOrderId(String orderId, List<OrderItemDto> orderItemDto) {
        Optional<List<OrderItem>> orderItemOptional = orderItemRepository.findByOrderId(orderId);
        if (orderItemOptional.isPresent()) {
            throw new OrderConflictException("Order Items already exists for ID: " + orderId);
        }
        List<OrderItem> orderItems = orderItemDto.stream()
                .map(item -> mapper.toEntity(orderId, item))
                .collect(Collectors.toList());
        try {
            orderItems.forEach(item -> orderItemRepository.save(item));
        } catch (Exception e) {
            log.error("createOrderItemsForOrderId: exception occurred.");
            throw new OrderItemException(e.getMessage());
        }
    }
}
