package aoi.com.creditos_api.service.impl;

import aoi.com.creditos_api.dto.CreditoDTO;
import aoi.com.creditos_api.exception.RecursoNaoEncontradoException;
import aoi.com.creditos_api.factory.CreditoFactory;
import aoi.com.creditos_api.model.Credito;
import aoi.com.creditos_api.repository.CreditoRepository;
import aoi.com.creditos_api.service.CreditoService;
import aoi.com.creditos_api.singleton.AuditoriaSingleton;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository repository;
    private final AuditoriaSingleton auditoria;
    private KafkaTemplate<String, String> kafkaTemplate;
    private final Random random = new Random();

    @Override
    public List<CreditoDTO> buscarPorNumeroNfse(String numeroNfse) {
        List<Credito> lista = repository.findByNumeroNfse(numeroNfse);
        if (lista.isEmpty()) {
            throw new RecursoNaoEncontradoException("Créditos", "NFS-e", numeroNfse);
        }

        kafkaTemplate.send("creditos-consultados", "Consulta por NFS-e: " + numeroNfse);
        auditoria.registrarConsulta("NFS-e", numeroNfse);
        return lista.stream().map(CreditoFactory::toDto).toList();
    }

    @Override
    public CreditoDTO buscarPorNumeroCredito(String numeroCredito) {
        Credito credito = repository.findByNumeroCredito(numeroCredito)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Créditos", "NFS-e", numeroCredito));
        kafkaTemplate.send("creditos-consultados","Consulta por Crédito: " + numeroCredito);
        auditoria.registrarConsulta("Crédito", numeroCredito);
        return CreditoFactory.toDto(credito);
    }
}
