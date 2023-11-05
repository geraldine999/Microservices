package com.globallogic.bff.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesDTO {

    private Long id;
    @NotNull(message = "typeMethodPayment no puede estar vacío")
    private MethodPaymentEnum typeMethodPayment;
    @NotNull(message = "idCustomer no puede estar vacío")
    private Integer idCustomer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateSale;
    private String currency;
    private String idCard;
    private double totalAmount;
    @NotEmpty(message = "products no puede estar vacío")
    @Valid
    private List<SoldProductDTO> products;


}
