package com.globallogic.products.dtos;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockMovementDto implements Serializable {
    private static final long serialVersionUID = -3382071309699095825L;

    @NotNull(message = "productQuantity no debe ser null")
    private Integer productQuantity;
}
