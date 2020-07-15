package in.hp.java.orderitemservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@ApiModel("OrderItem")
@Data
@NoArgsConstructor
public class OrderItemDto {

    @ApiModelProperty(name = "Product Code", example = "ABC", required = true)
    @NotEmpty(message = "Product Code should not be empty or null.")
    private String productCode;

    @ApiModelProperty(name = "Product Name", example = "Laptop", required = true)
    @NotEmpty(message = "Product Name should not be empty or null.")
    private String productName;

    @ApiModelProperty(name = "Quantity", example = "2", required = true)
    @Range(min = 1, message = "Minimum Quantity Should be One.")
    private Integer quantity;

}
