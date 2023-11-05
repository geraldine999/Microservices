package com.globallogic.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardResponseDto {
    private String number;
    private String issuer;

    public static class CardResponseDtoBuilder {
        public CardResponseDtoBuilder number(String number) {
            this.number = buildNumberString(number);
            return this;
        }
    }

    public void setNumber(String number) {
        this.number = buildNumberString(number);
    }


    private static String buildNumberString(String number) {
        return "**** **** **** " + number.substring(12, 16);
    }
}
