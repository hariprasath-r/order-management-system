package in.hp.java.orderservice.controller;

import in.hp.java.orderservice.dto.OrderDto;
import in.hp.java.orderservice.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@Validated
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Retrieves Customer Order.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved."),
            @ApiResponse(code = 404, message = "Order / Order Items not found.")
    })
    @GetMapping("/{customerName}")
    public ResponseEntity<OrderDto> getOrderByCustomerName(@PathVariable String customerName) {
        OrderDto orderForCustomerName = orderService.getOrderForCustomerName(customerName);
        return ResponseEntity.ok(orderForCustomerName);
    }

    @ApiOperation(value = "Creates Customer Order.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created Order."),
            @ApiResponse(code = 400, message = "Invalid Input."),
            @ApiResponse(code = 500, message = "Error in Creating Order.")
    })
    @PostMapping
    public ResponseEntity<Object> createOrder(
            @NotNull(message = "Order cannot be null.")
            @Valid @RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
