package com.docutrace.shared.domain;

import com.docutrace.shared.security.TenantContext;
import jakarta.persistence.PrePersist;
import java.util.UUID;

public class TenantListener {

    @PrePersist
    public void setTenant(BaseTenantEntity entity){
        UUID currentTenant = TenantContext.getCurrentTenant();
        if(currentTenant == null){
            throw new IllegalStateException("CIBERSEGURIDAD FALLO CRITICO: No se puede persistir la entidad sin un organization_id valido en el contexto");
        }
        entity.setOrganizationId(currentTenant);
    }
}
