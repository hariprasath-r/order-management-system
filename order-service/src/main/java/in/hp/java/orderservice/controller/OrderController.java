package in.hp.java.orderservice.controller;

import in.hp.java.orderservice.dto.OrderDto;
import in.hp.java.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{customerName}")
    public ResponseEntity<OrderDto> getOrderByCustomerName(@PathVariable String customerName) {
        OrderDto orderForCustomerName = orderService.getOrderForCustomerName(customerName);
        return ResponseEntity.ok(orderForCustomerName);
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(
            @NotNull(message = "Order cannot be null.")
            @Valid @RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);
        return ResponseEntity.ok().build();
    }
}
