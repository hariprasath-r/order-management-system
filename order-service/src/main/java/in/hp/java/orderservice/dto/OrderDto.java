package in.hp.java.orderservice.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@ApiModel("Order")
public class OrderDto {

    @NotEmpty(message = "Customer name should not be empty or null.")
    private String customerName;

    @FutureOrPresent(message = "Order date should not be in past.")
    private LocalDate orderDate;

    @NotEmpty(message = "Shipping address should not be empty or null.")
    private String shippingAddress;

    private List<OrderItemDto> orderItems;

    @NotEmpty(message = "total should not be empty or null.")
    private Double total;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderDto{");
        sb.append("customerName='").append(customerName).append('\'');
        sb.append(", orderDate=").append(orderDate);
        sb.append(", shippingAddress='").append(shippingAddress).append('\'');
        sb.append(", orderItems=").append(orderItems);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}
