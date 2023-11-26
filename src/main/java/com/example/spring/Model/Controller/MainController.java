package com.example.spring.Model.Controller;

import com.example.spring.Model.Model.RequestDTO;
import com.example.spring.Model.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class MainController {
    private Logger log = LoggerFactory.getLogger(MainController.class);
    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        try {
            String currency = requestDTO.getClientId().charAt(0) == '8' ? "US"
                    : requestDTO.getClientId().charAt(0) == '9' ? "EU" : "RU";

            BigDecimal maxLimit = new BigDecimal(requestDTO.getClientId().charAt(0) == '8' ? 2000
                    : requestDTO.getClientId().charAt(0) == '9' ? 1000 : 1000000);

            ResponseDTO responseDTO =
                    new ResponseDTO(requestDTO.getRqUID(), requestDTO.getClientId(),
                            requestDTO.getAccount(), currency, new BigDecimal(10), maxLimit);
            log.debug("**************RequestDTO****************" +
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.debug("**************ResponseDTO****************" +
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));
            return responseDTO;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
