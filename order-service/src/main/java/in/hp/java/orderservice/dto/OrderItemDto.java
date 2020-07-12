package in.hp.java.orderservice.dto;

import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@ApiModel("OrderItem")
public class OrderItemDto {

    @NotEmpty(message = "Product Code should not be empty or null.")
    private String productCode;

    @NotEmpty(message = "Product Name should not be empty or null.")
    private String productName;

    @Range(min = 1, message = "Minimum Quantity Should be One.")
    private Integer quantity;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderDto{");
        sb.append("productCode='").append(productCode).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", quantity='").append(quantity).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
