package aoi.com.creditos_api.service;
import aoi.com.creditos_api.dto.CreditoDTO;
import java.util.List;


public interface CreditoService {
    List<CreditoDTO> buscarPorNumeroNfse(String numeroNfse);
    CreditoDTO buscarPorNumeroCredito(String numeroCredito);
}
