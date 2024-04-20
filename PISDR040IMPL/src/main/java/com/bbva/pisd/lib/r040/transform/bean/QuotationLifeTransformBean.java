package com.bbva.pisd.lib.r040.transform.bean;


import com.bbva.rbvd.dto.participant.dao.QuotationLifeDAO;
import com.bbva.rbvd.dto.participant.utils.InsuranceProperties;

import java.util.Map;

public class QuotationLifeTransformBean {
    public static QuotationLifeDAO mapTransformParticipantQuotationLifeEntity(Map<String,Object> mapQuotation){
        QuotationLifeDAO participantQuotationLife = new QuotationLifeDAO();
        participantQuotationLife.setClientLastName((String) mapQuotation.get(InsuranceProperties.FIELD_CLIENT_LAST_NAME.getValue()));
        participantQuotationLife.setCustomerBirthDate(String.valueOf(mapQuotation.get(InsuranceProperties.FIELD_CUSTOMER_BIRTH_DATE.getValue())));
        participantQuotationLife.setInsuredCustomerName((String) mapQuotation.get(InsuranceProperties.FIELD_INSURED_CUSTOMER_NAME.getValue()));
        participantQuotationLife.setCustomerDocumentType((String) mapQuotation.get(InsuranceProperties.FIELD_CUSTOMER_DOCUMENT_TYPE.getValue()));
        participantQuotationLife.setPersonalId((String) mapQuotation.get(InsuranceProperties.FIELD_PERSONAL_ID.getValue()));
        participantQuotationLife.setGenderId((String) mapQuotation.get(InsuranceProperties.FIELD_GENDER_ID.getValue()));
        participantQuotationLife.setUserEmailPersonalDesc((String) mapQuotation.get(InsuranceProperties.FIELD_USER_EMAIL_PERSONAL_DESC.getValue()));
        participantQuotationLife.setPhoneId((String) mapQuotation.get(InsuranceProperties.FIELD_PHONE_ID.getValue()));

        return participantQuotationLife;
    }
}
