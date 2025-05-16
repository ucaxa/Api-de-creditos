package aoi.com.creditos_api.controller;

import static org.junit.jupiter.api.Assertions.*;


import aoi.com.creditos_api.dto.CreditoDTO;
import aoi.com.creditos_api.service.CreditoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("Créditos")
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes unitários para CreditoController")
class CreditoControllerTest {

    @Mock
    private CreditoService creditoService;

    @InjectMocks
    private CreditoController creditoController;

    private CreditoDTO creditoDTO1;
    private CreditoDTO creditoDTO2;

    @BeforeEach
    void setUp() {
        creditoDTO1 = new CreditoDTO();
        creditoDTO1.setNumeroCredito("123456");
        creditoDTO1.setNumeroNfse("7891011");
        creditoDTO1.setDataConstituicao(LocalDate.of(2024, 2, 25));
        creditoDTO1.setValorIssqn(new BigDecimal("1500.75"));
        creditoDTO1.setTipoCredito("ISSQN");
        creditoDTO1.setSimplesNacional("Sim");
        creditoDTO1.setAliquota(new BigDecimal("5.00"));
        creditoDTO1.setValorFaturado(new BigDecimal("30000.00"));
        creditoDTO1.setValorDeducao(new BigDecimal("5000.00"));
        creditoDTO1.setBaseCalculo(new BigDecimal("25000.00"));

        creditoDTO2 = new CreditoDTO();
        creditoDTO2.setNumeroCredito("789012");
        creditoDTO2.setNumeroNfse("7891011");
        creditoDTO2.setDataConstituicao(LocalDate.of(2024, 2, 26));
        creditoDTO2.setValorIssqn(new BigDecimal("1200.50"));
        creditoDTO2.setTipoCredito("ISSQN");
        creditoDTO2.setSimplesNacional("Não");
        creditoDTO2.setAliquota(new BigDecimal("4.50"));
        creditoDTO2.setValorFaturado(new BigDecimal("25000.00"));
        creditoDTO2.setValorDeducao(new BigDecimal("4000.00"));
        creditoDTO2.setBaseCalculo(new BigDecimal("21000.00"));
    }

    @Test
    @DisplayName("Deve retornar lista de créditos quando buscar por NFS-e existente")
    void buscarPorNfse_QuandoExistir_DeveRetornarListaDeCreditos() {
        // Arrange
        String numeroNfse = "7891011";
        List<CreditoDTO> creditosEsperados = Arrays.asList(creditoDTO1, creditoDTO2);

        when(creditoService.buscarPorNumeroNfse(numeroNfse)).thenReturn(creditosEsperados);

        // Act
        ResponseEntity<List<CreditoDTO>> resposta = creditoController.buscarPorNfse(numeroNfse);

        // Assert
        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(creditosEsperados, resposta.getBody());
        verify(creditoService, times(1)).buscarPorNumeroNfse(numeroNfse);
    }

    @Test
    @DisplayName("Deve retornar crédito quando buscar por número de crédito existente")
    void buscarPorCredito_QuandoExistir_DeveRetornarCredito() {
        // Arrange
        String numeroCredito = "123456";

        when(creditoService.buscarPorNumeroCredito(numeroCredito)).thenReturn(creditoDTO1);

        // Act
        ResponseEntity<CreditoDTO> resposta = creditoController.buscarPorCredito(numeroCredito);

        // Assert
        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(creditoDTO1, resposta.getBody());
        verify(creditoService, times(1)).buscarPorNumeroCredito(numeroCredito);
    }

    @Test
    @DisplayName("Deve chamar o serviço corretamente ao buscar por NFS-e")
    void buscarPorNfse_DeveChamarServicoCorretamente() {
        // Arrange
        String numeroNfse = "7891011";
        List<CreditoDTO> creditosEsperados = Arrays.asList(creditoDTO1);

        when(creditoService.buscarPorNumeroNfse(numeroNfse)).thenReturn(creditosEsperados);

        // Act
        creditoController.buscarPorNfse(numeroNfse);

        // Assert
        verify(creditoService, times(1)).buscarPorNumeroNfse(numeroNfse);
        verifyNoMoreInteractions(creditoService);
    }

    @Test
    @DisplayName("Deve chamar o serviço corretamente ao buscar por número de crédito")
    void buscarPorCredito_DeveChamarServicoCorretamente() {
        // Arrange
        String numeroCredito = "123456";

        when(creditoService.buscarPorNumeroCredito(numeroCredito)).thenReturn(creditoDTO1);

        // Act
        creditoController.buscarPorCredito(numeroCredito);

        // Assert
        verify(creditoService, times(1)).buscarPorNumeroCredito(numeroCredito);
        verifyNoMoreInteractions(creditoService);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando NFS-e não tiver créditos associados")
    void buscarPorNfse_QuandoNaoExistirCreditos_DeveRetornarListaVazia() {
        // Arrange
        String numeroNfse = "000000";

        when(creditoService.buscarPorNumeroNfse(numeroNfse)).thenReturn(List.of());

        // Act
        ResponseEntity<List<CreditoDTO>> resposta = creditoController.buscarPorNfse(numeroNfse);

        // Assert
        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCodeValue());
        assertTrue(resposta.getBody().isEmpty());
        verify(creditoService, times(1)).buscarPorNumeroNfse(numeroNfse);
    }
}