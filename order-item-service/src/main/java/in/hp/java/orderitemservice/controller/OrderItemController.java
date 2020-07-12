package in.hp.java.orderitemservice.controller;

import in.hp.java.orderitemservice.dto.OrderItemDto;
import in.hp.java.orderitemservice.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/orderitems")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/{orderId}")
    public List<OrderItemDto> getAllOrderItemsForOrderId(@PathVariable Integer orderId) {
        return orderItemService.getAllOrderItemsForOrderId(orderId);
    }

    @PostMapping("/{orderId}")
    public void createOrderItemsForOrderId(@PathVariable Integer orderId, @RequestBody List<OrderItemDto> orderItemDto) {
        orderItemService.createOrderItemsForOrderId(orderId, orderItemDto);
    }
}
