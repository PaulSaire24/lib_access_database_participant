package com.bbva.pisd.lib.r040.transform.map;

import com.bbva.rbvd.dto.participant.utils.InsuranceProperties;

import java.util.HashMap;
import java.util.Map;

public class QuotationLifeTransformMap {
    public static Map<String, Object> mapTransformParticipantQuotationEntity(String quotationId, String productId, String planId, String ducumentNumber,String documentType){
        Map<String, Object> mapParticipantQuotationLife = new HashMap<>();
        mapParticipantQuotationLife.put(InsuranceProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue(), quotationId);
        mapParticipantQuotationLife.put(InsuranceProperties.FIELD_INSURANCE_PRODUCT_ID.getValue(), productId);
        mapParticipantQuotationLife.put(InsuranceProperties.FIELD_INSURANCE_MODALITY_TYPE.getValue(), planId);
        mapParticipantQuotationLife.put(InsuranceProperties.FIELD_PERSONAL_ID.getValue(), ducumentNumber);
        mapParticipantQuotationLife.put(InsuranceProperties.FIELD_CUSTOMER_DOCUMENT_TYPE.getValue(), documentType);
        return mapParticipantQuotationLife;
    }
}
