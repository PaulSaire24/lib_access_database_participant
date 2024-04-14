package com.bbva.pisd.lib.r040.transform.map;

import com.bbva.rbvd.dto.participant.utils.InsuranceProperties;

import java.util.HashMap;
import java.util.Map;

public class QuotationTransformMap {
    private QuotationTransformMap(){}

    public static Map<String,Object> participantQuotaInternalIdTransforMap(String policyQuotaInternalId){
        Map<String,Object> mapQuerySearchByPolicyQuotaInternalId = new HashMap<>();
        mapQuerySearchByPolicyQuotaInternalId.put(InsuranceProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue(),policyQuotaInternalId);
        return mapQuerySearchByPolicyQuotaInternalId;
    }
}
