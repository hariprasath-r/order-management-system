package in.hp.java.orderitemservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEM")
@Data
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue
    private Integer id;
    private String orderId;
    private String productCode;
    private String productName;
    private Integer quantity;

}
