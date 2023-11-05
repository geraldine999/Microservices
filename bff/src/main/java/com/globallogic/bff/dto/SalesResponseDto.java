package com.globallogic.bff.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesResponseDto {

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private MethodPaymentEnum methodPayment;
    private String totalAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CardResponseDto card;
    private List<SoldProductDTO> items;

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = buildTotalAmountStringWithCurrencySignAnd2Decimals(totalAmount);
    }

    public static class SalesResponseDtoBuilder {
        public SalesResponseDtoBuilder totalAmount(double totalAmount) {
            this.totalAmount = buildTotalAmountStringWithCurrencySignAnd2Decimals(totalAmount);
            return this;
        }
    }

    private static String buildTotalAmountStringWithCurrencySignAnd2Decimals(double totalAmount) {
        return "$" + String.format("%.2f", totalAmount);
    }
}
