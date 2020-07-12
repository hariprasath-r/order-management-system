package in.hp.java.orderitemservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    private Integer id;
    private Integer orderId;
    private String productCode;
    private String productName;
    private String quantity;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getOrderId() { return orderId; }

    public void setOrderId(Integer orderId) { this.orderId = orderId; }

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderItem{");
        sb.append("id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", productCode=").append(productCode);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", quantity='").append(quantity).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
