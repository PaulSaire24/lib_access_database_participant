package com.bbva.pisd.lib.r040.dao;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.pisd.lib.r040.interfaces.ParticipantDAO;
import com.bbva.pisd.lib.r040.transform.bean.QuotationLifeTransformBean;
import com.bbva.pisd.lib.r040.transform.bean.QuotationTransformBean;
import com.bbva.pisd.lib.r040.transform.bean.RoleTransformBean;
import com.bbva.pisd.lib.r040.transform.map.QuotationLifeTransformMap;
import com.bbva.pisd.lib.r040.transform.map.QuotationTransformMap;
import com.bbva.pisd.lib.r040.transform.map.RoleTransformMap;
import com.bbva.pisd.lib.r040.util.FunctionUtils;
import com.bbva.rbvd.dto.participant.dao.QuotationCustomerDAO;
import com.bbva.rbvd.dto.participant.dao.QuotationLifeDAO;
import com.bbva.rbvd.dto.participant.dao.RolDAO;
import com.bbva.rbvd.dto.participant.dboperation.Operation;
import com.bbva.rbvd.dto.participant.dboperation.OperationConstants;
import com.bbva.rbvd.dto.participant.utils.DatabaseParticipantErrors;
import com.bbva.rbvd.dto.participant.utils.InsuranceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OracleParticipantDAO implements ParticipantDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleParticipantDAO.class);

    private BaseDAO baseDAO;
    @Override
    public QuotationCustomerDAO findQuotationByPolicyQuotaInternalId(String policyQuotaInternalId) {
    Map<String,Object> mapQuerySearchByPolicyQuotaInternalId = QuotationTransformMap.participantQuotaInternalIdTransforMap(policyQuotaInternalId);
        if (FunctionUtils.parametersIsValid(mapQuerySearchByPolicyQuotaInternalId,InsuranceProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue())) {
        Operation operationDTO = Operation.Builder.an().withTypeOperation(OperationConstants.Operation.SELECT)
                .withNameProp(InsuranceProperties.QUERY_SELECT_FIND_QUOTATION_BY_POLICY_QUOTA_INTERNAL_ID.getValue())
                .withParams(mapQuerySearchByPolicyQuotaInternalId)
                .withIsContainsParameters(true)
                .withIsForListQuery(false).build();
        Map<String,Object> mapQuotationEntity = (Map<String,Object>) this.baseDAO.executeQuery(operationDTO);
        mapQuotationEntity.forEach((key, value) -> LOGGER.info(" :: PISD.SELECT_FIND_QUOTATION_BY_POLICY_QUOTA_INTERNAL_ID :: [ Key {} with value: {} ]", key, value));
        LOGGER.info(" :: mapQuotationEntity : {}",mapQuotationEntity);
        return QuotationTransformBean.mapTransformParticipantQuotationEntity(mapQuotationEntity);
    }
        LOGGER.info(" :: Parameters invalidate [ policyQuotaInternalId : {} ]",policyQuotaInternalId);
        throw new BusinessException(DatabaseParticipantErrors.PARAMETERS_INVALIDATE.getAdviceCode(), false, DatabaseParticipantErrors.QUERY_EMPTY_RESULT.getMessage());
    }

    @Override
    public List<RolDAO> listParticipantRolesByCompanyId(BigDecimal insuranceCompanyId) {
        Map<String,Object> mapQuerySearchRolesByCompanyId = RoleTransformMap.participantRoleByCompanyIdTransforMap(insuranceCompanyId);
        if (FunctionUtils.parametersIsValid(mapQuerySearchRolesByCompanyId, InsuranceProperties.FIELD_INSURANCE_COMPANY_ID.getValue())) {
            Operation operationDTO = Operation.Builder.an().withTypeOperation(OperationConstants.Operation.SELECT)
                    .withNameProp(InsuranceProperties.QUERY_SELECT_PARTICIPANT_COMPANY_RELATED_ROLES.getValue())
                    .withParams(mapQuerySearchRolesByCompanyId)
                    .withIsContainsParameters(true)
                    .withIsForListQuery(true).build();
            List<Map<String,Object>> mapParticipantRolesEntityList = (List<Map<String,Object>>) this.baseDAO.executeQuery(operationDTO);
            mapParticipantRolesEntityList.forEach(list-> list.forEach((key, value) -> LOGGER.info(" :: PISD.SELECT_PARTICIPANT_COMPANY_RELATED_ROLES:: [ Key {} with value: {} ]", key, value)));
            LOGGER.info(" :: mapQuotationEntity : {}",mapParticipantRolesEntityList);
            return RoleTransformBean.mapTransformParticipantQuotationEntity(mapParticipantRolesEntityList);
        }
        LOGGER.info(" :: Parameters invalidate [ insuranceCompanyId : {} ]",insuranceCompanyId);
        throw new BusinessException(DatabaseParticipantErrors.PARAMETERS_INVALIDATE.getAdviceCode(), false, DatabaseParticipantErrors.QUERY_EMPTY_RESULT.getMessage());
    }

    @Override
    public QuotationLifeDAO getInsuredQuotationLife(String quotationId, String productId, String planId, String documentNumber, String documentType) {
        Map<String,Object> mapQueryGetInsuredQuotationLife = QuotationLifeTransformMap.mapTransformParticipantQuotationEntity(quotationId,productId,planId,documentNumber,documentType);
        if (FunctionUtils.parametersIsValid(mapQueryGetInsuredQuotationLife,InsuranceProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue(),InsuranceProperties.FIELD_INSURANCE_PRODUCT_ID.getValue(),
                InsuranceProperties.FIELD_INSURANCE_MODALITY_TYPE.getValue(),InsuranceProperties.FIELD_PERSONAL_ID.getValue(),InsuranceProperties.FIELD_CUSTOMER_DOCUMENT_TYPE.getValue())) {
            Operation operationDTO = Operation.Builder.an().withTypeOperation(OperationConstants.Operation.SELECT)
                    .withNameProp(InsuranceProperties.QUERY_GET_INSURED_DATA_FROM_QUOTATION.getValue())
                    .withParams(mapQueryGetInsuredQuotationLife)
                    .withIsContainsParameters(true)
                    .withIsForListQuery(false).build();
            Map<String,Object> mapInsuredQuotationLifeEntity = (Map<String,Object>) this.baseDAO.executeQuery(operationDTO);
            mapInsuredQuotationLifeEntity.forEach((key, value) -> LOGGER.info(" :: PISD.GET_INSURED_DATA_FROM_QUOTATION:: [ Key {} with value: {} ]", key, value));
            LOGGER.info(" :: mapInsuredQuotationLifeEntity : {}",mapInsuredQuotationLifeEntity);
            return QuotationLifeTransformBean.mapTransformParticipantQuotationLifeEntity(mapInsuredQuotationLifeEntity);
        }
        LOGGER.info(" :: Parameters invalidate");
        throw new BusinessException(DatabaseParticipantErrors.PARAMETERS_INVALIDATE.getAdviceCode(), false, DatabaseParticipantErrors.QUERY_EMPTY_RESULT.getMessage());
    }

    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }
}

