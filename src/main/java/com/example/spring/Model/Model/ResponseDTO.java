package com.example.spring.Model.Model;

import lombok.*;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class ResponseDTO {
    private String rqUID;//"58dgtf565j8547f64ke7",
    private String clientId;//"3050000000000000000",
    private String account;//"30500000000000000001",
    private String currency;//"RU",
    private BigDecimal balance; //"16000.00",
    private BigDecimal maxLimit; //"50000.00"
}

