package in.hp.java.orderitemservice.controller;

import in.hp.java.orderitemservice.dto.OrderItemDto;
import in.hp.java.orderitemservice.service.OrderItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.List;

@Validated
@RestController()
@RequestMapping("/orderitems")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @ApiOperation(value = "Retrieves Order Items for a Customer.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved."),
            @ApiResponse(code = 404, message = "Order Items not found.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<OrderItemDto>> getAllOrderItemsForCustomer(@PathVariable String id) {

        List<OrderItemDto> allOrderItemsForOrderId = orderItemService.getAllOrderItemsForOrderId(id);
        return ResponseEntity.ok(allOrderItemsForOrderId);
    }

    @ApiOperation(value = "Creates Order Items for a Customer.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created Order Items."),
            @ApiResponse(code = 400, message = "Invalid Input."),
            @ApiResponse(code = 500, message = "Error in creating Order Items.")
    })
    @PostMapping("/{id}")
    public ResponseEntity<Object> createOrderItemsForCustomer(
            @PathVariable String id,
            @NotEmpty(message = "Order Items cannot be empty or null.")
            @RequestBody List<@Valid OrderItemDto> orderItemDto) {

        orderItemService.createOrderItemsForOrderId(id, orderItemDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
