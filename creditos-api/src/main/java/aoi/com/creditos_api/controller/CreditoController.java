package aoi.com.creditos_api.controller;

import aoi.com.creditos_api.dto.CreditoDTO;
import aoi.com.creditos_api.service.CreditoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Créditos", description = "API de Créditos Constituídos")
@RestController
@RequestMapping("/api/creditos")
@RequiredArgsConstructor
public class CreditoController {

    private final CreditoService service;

    @Operation(summary = "Buscar créditos por número da NFS-e")
    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDTO>> buscarPorNfse(
            @Parameter(description = "Número da NFS-e", required = true)
            @PathVariable String numeroNfse) {
        return ResponseEntity.ok(service.buscarPorNumeroNfse(numeroNfse));
    }

    @Operation(summary = "Buscar crédito por número de crédito")
    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDTO> buscarPorCredito(
            @Parameter(description = "Número do crédito", required = true)
            @PathVariable String numeroCredito) {
        return ResponseEntity.ok(service.buscarPorNumeroCredito(numeroCredito));
    }

}