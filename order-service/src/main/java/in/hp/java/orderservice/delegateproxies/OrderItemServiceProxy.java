package in.hp.java.orderservice.delegateproxies;

import in.hp.java.orderservice.dto.OrderItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "order-item-service", url = "http://localhost:8080", path = "/orderitems")
public interface OrderItemServiceProxy {

    @GetMapping("/{id}")
    ResponseEntity<List<OrderItemDto>> getAllOrderItemsForOrderId(@PathVariable String id);

    @PostMapping("/{id}")
    ResponseEntity<Object> createOrderItemsForOrderId(
            @PathVariable String id,
            List<OrderItemDto> orderItemDto);
}
