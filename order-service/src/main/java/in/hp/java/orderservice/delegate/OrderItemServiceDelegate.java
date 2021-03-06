package in.hp.java.orderservice.delegate;

import feign.FeignException;
import feign.RetryableException;
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

    /**
     * Communicates with order-item-service to create Order Items for a Customer.
     *
     * @param id         = Customer Name
     * @param orderItems - Order Items
     */
    public void createOrderItems(String id, List<OrderItemDto> orderItems) {
        try {
            log.info("Communicating with order-item-service to create Order Items.");

            orderItemService.createOrderItemsForOrderId(id, orderItems);
            log.info("Order Items created successfully for {}.", id);

        } catch (RetryableException e) {
            log.error("createOrderItems: Order Item Service unavailable");
            throw new FeignProxyException(HttpStatus.NOT_FOUND, "Order Item Service unavailable", "");

        } catch (FeignException e) {
            HttpStatus status = HttpStatus.resolve(e.status());
            status = Objects.nonNull(status) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("createOrderItems Status: [{}]", status);
            throw new FeignProxyException(status, e.getMessage(), e.contentUTF8());

        } catch (Exception e) {
            log.error("createOrderItems: exception occurred.");
            throw new FeignProxyException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to communicate to Order Items Service", e.getMessage());
        }
    }

    /**
     * Retrieves Order Items of a Customer from order-item-service.
     *
     * @param id - Customer Name
     * @return - Order Items
     */
    public List<OrderItemDto> getOrderItems(String id) {
        try {
            log.info("Communicating with order-item-service to retrieve Order Items.");
            return orderItemService.getAllOrderItemsForOrderId(id).getBody();

        } catch (RetryableException e) {
            log.error("getOrderItems: Order Item Service unavailable");
            throw new FeignProxyException(HttpStatus.NOT_FOUND, "Order Item Service unavailable", "");

        } catch (FeignException e) {
            HttpStatus status = HttpStatus.resolve(e.status());
            status = Objects.nonNull(status) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("Exception: createOrderItems Status: [{}]", status);
            throw new FeignProxyException(status, e.getMessage(), e.contentUTF8());

        } catch (Exception e) {
            log.error("getOrderItems: exception occurred.");
            throw new FeignProxyException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to communicate to Order Items Service", e.getMessage());
        }
    }
}
