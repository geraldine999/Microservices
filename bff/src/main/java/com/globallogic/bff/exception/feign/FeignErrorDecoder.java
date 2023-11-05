package com.globallogic.bff.exception.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.bff.exception.Exception400;
import com.globallogic.bff.exception.Exception404;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    private static final String GENERIC_ERROR_DETAILS = "Ocurrió un error con la petición.";

    @Override
    public Exception decode(String methodKey, Response response) {
        String exceptionDetails;
        if (methodKey.contains("ProductsClient")) {
            exceptionDetails = readProductsServiceExceptionDetails(response);
        } else {
            exceptionDetails = readServiceExceptionDetails(response);
        }

        switch (response.status()) {
            case 400:
                return new Exception400(exceptionDetails);
            case 404:
                return new Exception404(exceptionDetails);
            default:
                return new Exception();
        }
    }

    private String readProductsServiceExceptionDetails(Response response) {
        try {
            FeignProductsServiceError productsServiceError = readResponseBody(response,
                    FeignProductsServiceError.class);
            var stringBuilder = new StringBuilder();
            for (FeignServiceError error : productsServiceError.getErrors()) {
                stringBuilder.append(error.getDetails()).append(" - ");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return GENERIC_ERROR_DETAILS;
        }
    }

    private String readServiceExceptionDetails(Response response) {
        try {
            return readResponseBody(response, FeignServiceError.class).getDetails();
        } catch (Exception exception) {
            return GENERIC_ERROR_DETAILS;
        }
    }

    private <T> T readResponseBody(Response response, Class<T> someClass) throws IOException {
        var body = response.body().asInputStream();
        var mapper = new ObjectMapper();
        return mapper.readValue(body, someClass);
    }
}
