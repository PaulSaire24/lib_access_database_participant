package com.bbva.pisd.lib.r040.transform.map;

import com.bbva.rbvd.dto.participant.utils.InsuranceProperties;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RoleTransformMap {
    public static Map<String,Object> participantRoleByCompanyIdTransforMap(BigDecimal insuranceCompanyId){
        Map<String,Object> mapParticipantRoleByCompanyId = new HashMap<>();
        mapParticipantRoleByCompanyId.put(InsuranceProperties.FIELD_INSURANCE_COMPANY_ID.getValue(),insuranceCompanyId);
        return mapParticipantRoleByCompanyId;
    }
}
