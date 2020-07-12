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

    public List<OrderItemDto> getAllOrderItemsForOrderId(Integer orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId)
                .orElseThrow(() -> new OrderItemNotFoundException("Order Items not found for Order ID: " + orderId));

        log.info("Order Items for Order ID: {}, {}", orderId, orderItems);
        return orderItems.stream()
                .map(item -> mapper.toDto(item))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createOrderItemsForOrderId(Integer orderId, List<OrderItemDto> orderItemDto) {
        Optional<List<OrderItem>> orderItemOptional = orderItemRepository.findByOrderId(orderId);
        if (orderItemOptional.isPresent()) {
            throw new OrderConflictException("Order Items already exists for Order ID: " + orderId);
        }

        List<OrderItem> orderItems = orderItemDto.stream()
                .map(item -> mapper.toEntity(orderId, item))
                .collect(Collectors.toList());

        try {
            orderItems.stream().forEach(item -> orderItemRepository.save(item));
        } catch (Exception e) {
            throw new OrderItemException(e.getMessage());
        }
    }
}
