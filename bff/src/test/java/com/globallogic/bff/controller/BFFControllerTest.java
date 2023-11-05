package com.globallogic.bff.controller;

import com.globallogic.bff.dto.CardsResponseDTO;
import com.globallogic.bff.dto.ClientDTO;
import com.globallogic.bff.dto.ClientSalesReportDto;
import com.globallogic.bff.dto.ProductDTO;
import com.globallogic.bff.dto.SalesDTO;
import com.globallogic.bff.dto.SoldProductsReportDTO;
import com.globallogic.bff.service.CardService;
import com.globallogic.bff.service.ClientService;
import com.globallogic.bff.service.ProductService;
import com.globallogic.bff.service.SalesService;
import com.globallogic.bff.service.ShoppingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BFFControllerTest {

    @InjectMocks
    private BFFController controller;

    @Mock
    private ProductService productService;
    @Mock
    private CardService cardService;
    @Mock
    private ClientService clientService;
    @Mock
    private SalesService salesService;
    @Mock
    private ShoppingService shoppingService;

    @Test
    @DisplayName("/products debe retornar los productos")
    void shouldReturnProducts() {

        String description = "un producto";
        ProductDTO productDTO = ProductDTO.builder().description(description).build();

        Mockito.when(this.productService.getAllProducts()).thenReturn(List.of(productDTO));

        assertEquals(description, controller.getAllProducts().getBody().get(0).getDescription());

    }

    @Test
    @DisplayName("/products/{id} debe retornar un producto por id")
    void shouldReturnProductById() {

        String id = "123";
        ProductDTO productDTO = ProductDTO.builder().id(id).build();

        Mockito.when(this.productService.getProductById(id)).thenReturn(productDTO);

        assertEquals(id, controller.getProductById(id).getBody().getId());

    }

    @Test
    @DisplayName("/cards/client/{clientId}/card/{cardNumber} debe retornar una tarjeta por "
            + "id de cliente y número de tarjeta")
    void shouldReturnCardByClientIdAndCardNumber() {

        String clientId = "123";
        String cardNumber = "1234567812345678";

        CardsResponseDTO cardDTO = new CardsResponseDTO(cardNumber, clientId, "VISA");

        Mockito.when(this.cardService.getCards(clientId, cardNumber)).thenReturn(cardDTO);

        assertEquals(cardDTO, controller.getCards(clientId, cardNumber).getBody());

    }

    @Test
    @DisplayName("/clients/{id} debe retornar cliente por id")
    void shouldReturnClientById() {

        ClientDTO clientDTO = ClientDTO.builder().dni(30234567L).build();

        Mockito.when(this.clientService.clientById(any())).thenReturn(clientDTO);

        assertEquals(clientDTO, controller.getClientById((Integer) any()).getBody());

    }

    @Test
    @DisplayName("/clients/dni/{dni} debe retornar cliente por dni")
    void shouldReturnClientByDni() {

        ClientDTO clientDTO = ClientDTO.builder().dni(30234567L).build();

        Mockito.when(this.clientService.clientByDni(any())).thenReturn(clientDTO);

        assertEquals(clientDTO, controller.getClientByDni((Long) any()).getBody());

    }

    @Test
    @DisplayName("/products-report debe retornar el reporte de productos")
    void shouldReturnProductsReport() {

        SoldProductsReportDTO reportDTO = new SoldProductsReportDTO();

        Mockito.when(this.salesService.generateSoldProductsReport()).thenReturn(reportDTO);

        assertEquals(reportDTO, controller.generateSoldProductsReport());

    }

    @Test
    @DisplayName("/sales debe retornar las ventas")
    void shouldReturnSales() {

        SalesDTO salesDTO = new SalesDTO();

        Mockito.when(this.salesService.getSales()).thenReturn(List.of(salesDTO));

        assertEquals(salesDTO, controller.getSalesDto().get(0));

    }

    @Test
    @DisplayName("/shop debe retornar la venta realizada")
    void shouldReturnSale() {

        SalesDTO salesDTO = new SalesDTO();

        Mockito.when(this.shoppingService.createShop(salesDTO)).thenReturn(salesDTO);

        assertEquals(salesDTO, controller.createShop(salesDTO).getBody());

    }

    @Test
    @DisplayName("/client-sales-report/client/{clientId} debe retornar el reporte de ventas del"
            + " cliente según el id")
    void shouldReturnClientSalesReportByClientId() {

        ClientSalesReportDto clientSalesReportDto = new ClientSalesReportDto();

        Mockito.when(this.clientService.generateClientSalesReport(any())).thenReturn(clientSalesReportDto);

        assertEquals(clientSalesReportDto, controller.generateClientSalesReportDto((Integer) any()));

    }

    @Test
    @DisplayName("/client-sales-report/client/dni/{clientDni} debe retornar el reporte de ventas del"
            + " cliente según el dni")
    void shouldReturnClientSalesReportByClientDni() {

        ClientSalesReportDto clientSalesReportDto = new ClientSalesReportDto();

        Mockito.when(this.clientService.generateClientSalesReport(any())).thenReturn(clientSalesReportDto);

        assertEquals(clientSalesReportDto, controller.generateClientSalesReportDto((Long) any()));

    }
}
