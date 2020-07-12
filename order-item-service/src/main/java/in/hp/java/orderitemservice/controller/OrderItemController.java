package in.hp.java.orderitemservice.controller;

import in.hp.java.orderitemservice.dto.OrderItemDto;
import in.hp.java.orderitemservice.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/orderitems")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderItemDto>> getAllOrderItemsForOrderId(@PathVariable Integer orderId) {
        List<OrderItemDto> allOrderItemsForOrderId = orderItemService.getAllOrderItemsForOrderId(orderId);
        return ResponseEntity.ok(allOrderItemsForOrderId);
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<Object> createOrderItemsForOrderId(@PathVariable Integer orderId, @RequestBody List<OrderItemDto> orderItemDto) {
        orderItemService.createOrderItemsForOrderId(orderId, orderItemDto);
        return ResponseEntity.ok().build();
    }
}
