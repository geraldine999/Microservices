package com.globallogic.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSalesReportDto {

    private Integer id;
    private Long dni;
    private String fullName;
    private String address;

    private List<SalesResponseDto> sales;

}

