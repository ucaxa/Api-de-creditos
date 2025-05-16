package aoi.com.creditos_api.factory;

import aoi.com.creditos_api.dto.CreditoDTO;
import aoi.com.creditos_api.model.Credito;
import org.springframework.beans.BeanUtils;

public class CreditoFactory {
    public static CreditoDTO toDto(Credito credito) {
        CreditoDTO dto = new CreditoDTO();
        BeanUtils.copyProperties(credito, dto);
        return dto;
    }
}
