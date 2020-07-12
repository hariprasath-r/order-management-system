package in.hp.java.orderservice.service;

import in.hp.java.orderservice.dto.OrderDto;
import in.hp.java.orderservice.entity.Order;
import in.hp.java.orderservice.exception.OrderConflictException;
import in.hp.java.orderservice.exception.OrderNotFoundException;
import in.hp.java.orderservice.mapper.OrderMapper;
import in.hp.java.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper mapper;

    public OrderDto getOrderForCustomerName(String customerName) {
        Optional<Order> order = orderRepository.findByCustomerName(customerName);
        if (!order.isPresent()) {
            throw new OrderNotFoundException("Order not found for Customer: " + customerName);
        }

        Integer id = order.get().getId();
        //TODO: call order item service
        return mapper.toDto(order.get());
    }

    @Transactional
    public Integer createOrder(OrderDto orderDto) {
        Optional<Order> existingOrder = orderRepository.findByCustomerName(orderDto.getCustomerName());
        if (existingOrder.isPresent()) {
            throw new OrderConflictException("Customer Already has an Order.");
        }

        Order order = mapper.toEntity(orderDto);
        Integer orderId = orderRepository.save(order).getId();
        log.info("Order ID for Customer {} is {}", orderDto.getCustomerName(), orderId);
        return orderId;
        // TODO: call order item service
    }
}
