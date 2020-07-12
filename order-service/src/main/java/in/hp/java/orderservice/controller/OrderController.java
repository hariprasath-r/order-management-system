package in.hp.java.orderservice.controller;

import in.hp.java.orderservice.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @GetMapping("/id/{orderId}")
    public List<OrderDto> getOrderForOrderId(@PathVariable Integer orderId) {
        return Collections.emptyList();
    }

    @GetMapping("/{customerName}")
    public List<OrderDto> getOrderByCustomerName(@PathVariable String customerName) {
        return Collections.emptyList();
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok().build();
    }
}
