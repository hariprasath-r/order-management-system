package in.hp.java.orderservice.delegate;

import feign.FeignException;
import in.hp.java.orderservice.delegateproxies.OrderItemServiceProxy;
import in.hp.java.orderservice.dto.OrderItemDto;
import in.hp.java.orderservice.exception.FeignProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderItemServiceDelegate {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderItemServiceProxy orderItemService;


    public void createOrderItems(Integer orderId, List<OrderItemDto> orderItems) {
        try {
            orderItemService.createOrderItemsForOrderId(orderId, orderItems);
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.resolve(e.status());
            status = Objects.nonNull(status) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("Exception: createOrderItems Status: [{}]", status);
            throw new FeignProxyException(status, e.getMessage(),
                    e.contentUTF8());
        }
    }

    public void getOrderItems(Integer orderId) {
        try {
            orderItemService.getAllOrderItemsForOrderId(orderId);
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.resolve(e.status());
            status = Objects.nonNull(status) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("Exception: getOrderItems Status: [{}]", status);
            throw new FeignProxyException(status, e.getMessage(),
                    e.contentUTF8());
        }
    }
}
