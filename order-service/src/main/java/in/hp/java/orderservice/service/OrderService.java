package in.hp.java.orderservice.service;

import in.hp.java.orderservice.delegate.OrderItemServiceDelegate;
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
    private OrderItemServiceDelegate orderItemServiceDelegate;

    @Autowired
    private OrderMapper mapper;

    /**
     * Retrieves Customer Order for a given Customer Name.
     *
     * @param customerName - Name of Customer
     * @return - Customer Order
     */
    public OrderDto getOrderForCustomerName(String customerName) {
        Optional<Order> orderOptional = orderRepository.findByCustomerName(customerName);
        if (!orderOptional.isPresent()) {
            throw new OrderNotFoundException("Order not found for Customer: " + customerName);
        }

        Order order = orderOptional.get();
        OrderDto orderDto = mapper.toDto(order);
        orderDto.setOrderItems(orderItemServiceDelegate.getOrderItems(order.getCustomerName()));

        log.info("Retrieved Order for {}.", customerName);
        return orderDto;
    }

    /**
     * Creates Customer Order based on the Order detail passed.
     *
     * @param orderDto - Order Detail
     */
    @Transactional
    public void createOrder(OrderDto orderDto) {
        Optional<Order> existingOrder = orderRepository.findByCustomerName(orderDto.getCustomerName());
        if (existingOrder.isPresent()) {
            throw new OrderConflictException("Customer Already has an Order.");
        }

        Order order = mapper.toEntity(orderDto);
        orderRepository.save(order).getId();
        orderItemServiceDelegate.createOrderItems(orderDto.getCustomerName(), orderDto.getOrderItems());
        log.info("Successfully created order for Customer {}", orderDto.getCustomerName());
    }
}
