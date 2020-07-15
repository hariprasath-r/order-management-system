package in.hp.java.orderservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@ApiModel("Order")
@Data
@NoArgsConstructor
public class OrderDto {

    @ApiModelProperty(name = "Customer Name", example = "Hari", required = true)
    @NotEmpty(message = "Customer name should not be empty or null.")
    private String customerName;

    @ApiModelProperty(name = "Order Date", example = "2020-12-31", required = true)
    @FutureOrPresent(message = "Order date should not be in past and should follow ISO Format.")
    private LocalDate orderDate;

    @ApiModelProperty(name = "Shipping Address", example = "14, OMR, Chennai - 96", required = true)
    @NotEmpty(message = "Shipping address should not be empty or null.")
    private String shippingAddress;

    @ApiModelProperty(name = "Order Items", required = true)
    @NotEmpty
    @Valid
    private List<OrderItemDto> orderItems;

    @ApiModelProperty(name = "Total", example = "100", required = true)
    @Range(min = 1, message = "Total should not be zero.")
    private Double total;
}
