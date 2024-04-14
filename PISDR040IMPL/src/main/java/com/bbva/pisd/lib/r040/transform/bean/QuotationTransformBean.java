package com.bbva.pisd.lib.r040.transform.bean;

import com.bbva.rbvd.dto.participant.dao.QuotationCustomerDAO;
import com.bbva.rbvd.dto.participant.dao.QuotationDAO;
import com.bbva.rbvd.dto.participant.dao.QuotationModDAO;
import com.bbva.rbvd.dto.participant.dao.InsuranceProductDAO;
import com.bbva.rbvd.dto.participant.utils.InsuranceProperties;
import com.bbva.rbvd.dto.participant.dao.InsuranceBusinessDAO;

import java.math.BigDecimal;
import java.util.Map;

public class QuotationTransformBean {
    public static QuotationCustomerDAO mapTransformParticipantQuotationEntity(Map<String,Object> mapQuotation){
        QuotationCustomerDAO quotationJoinInformation = new QuotationCustomerDAO();

        QuotationDAO quotationEntity = new QuotationDAO();
        QuotationModDAO quotationModEntity = new QuotationModDAO();
        InsuranceProductDAO insuranceProductEntity = new InsuranceProductDAO();
        InsuranceBusinessDAO insuranceBusinessEntity = new InsuranceBusinessDAO();

        quotationEntity.setInsuredCustomerName((String) mapQuotation.get(InsuranceProperties.FIELD_INSURED_CUSTOMER_NAME.getValue()));
        quotationEntity.setClientLasName((String) mapQuotation.get(InsuranceProperties.FIELD_CLIENT_LAST_NAME.getValue()));
        quotationEntity.setInsuranceCompanyQuotaId((String) mapQuotation.get(InsuranceProperties.FIELD_INSURANCE_COMPANY_QUOTA_ID.getValue()));
        quotationModEntity.setContactEmailDesc((String) mapQuotation.get(InsuranceProperties.FIELD_CONTACT_EMAIL_DESC.getValue()));
        quotationModEntity.setCustomerPhoneDesc((String) mapQuotation.get(InsuranceProperties.FIELD_CUSTOMER_PHONE_DESC.getValue()));
        quotationModEntity.setInsuranceProductId((BigDecimal) mapQuotation.get(InsuranceProperties.FIELD_INSURANCE_PRODUCT_ID.getValue()));
        quotationModEntity.setInsuranceModalityType((String) mapQuotation.get(InsuranceProperties.FIELD_INSURANCE_MODALITY_TYPE.getValue()));

        insuranceProductEntity.setInsuranceProductType((String) mapQuotation.get(InsuranceProperties.FIELD_INSURANCE_PRODUCT_TYPE.getValue()));

        insuranceBusinessEntity.setInsuranceBusinessName((String) mapQuotation.get(InsuranceProperties.FIELD_INSURANCE_BUSINESS_NAME.getValue()));

        quotationJoinInformation.setQuotation(quotationEntity);
        quotationJoinInformation.setQuotationMod(quotationModEntity);
        quotationJoinInformation.setInsuranceProduct(insuranceProductEntity);
        quotationJoinInformation.setInsuranceBusiness(insuranceBusinessEntity);
        return quotationJoinInformation;
    }
}
