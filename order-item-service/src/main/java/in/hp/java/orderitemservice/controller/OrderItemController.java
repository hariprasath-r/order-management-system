package in.hp.java.orderitemservice.controller;

import in.hp.java.orderitemservice.dto.OrderItemDto;
import in.hp.java.orderitemservice.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
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
    public ResponseEntity<Object> createOrderItemsForOrderId(
            @PathVariable Integer orderId,
            @NotEmpty(message = "Order Items cannot be empty or null.")
            @RequestBody List<@Valid OrderItemDto> orderItemDto) {
        orderItemService.createOrderItemsForOrderId(orderId, orderItemDto);
        return ResponseEntity.ok().build();
    }
}
