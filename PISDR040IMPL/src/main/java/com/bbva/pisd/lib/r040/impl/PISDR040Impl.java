package com.bbva.pisd.lib.r040.impl;

import com.bbva.pisd.lib.r040.interfaces.ParticipantDAO;
import com.bbva.pisd.lib.r040.util.JsonHelper;
import com.bbva.rbvd.dto.participant.dao.QuotationCustomerDAO;
import com.bbva.rbvd.dto.participant.dao.QuotationLifeDAO;
import com.bbva.rbvd.dto.participant.dao.RolDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * The PISDR040Impl class...
 */
public class PISDR040Impl extends PISDR040Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR040Impl.class);
	private ParticipantDAO participantDAO;

	@Override
	public QuotationCustomerDAO executeFindQuotationJoinByPolicyQuotaInternalId(String policyQuotaInternalId) {
		LOGGER.info(" :: executeFindQuotationByPolicyQuotaInternalId [ policyQuotaInternalId :: {} ]", policyQuotaInternalId);
		QuotationCustomerDAO result = this.participantDAO.findQuotationByPolicyQuotaInternalId(policyQuotaInternalId);
		LOGGER.info(" :: executeFindQuotationByPolicyQuotaInternalId [ QuotationEntity :: {} ]", JsonHelper.getInstance().toJsonString(result));
		return result;
	}

	@Override
	public List<RolDAO> executeListParticipantRolesByCompanyId(BigDecimal insuranceCompanyId) {
		LOGGER.info(" :: executeListParticipantRolesByCompanyId [ CompanyId :: {} ]", insuranceCompanyId);
		List<RolDAO> result = this.participantDAO.listParticipantRolesByCompanyId(insuranceCompanyId);
		LOGGER.info(" :: executeListParticipantRolesByCompanyId [ RoleList :: {} ]", JsonHelper.getInstance().toJsonString(result));
		return result;
	}

	@Override
	public QuotationLifeDAO executeGetInsuredQuotationLife(String quotationId, String productId, String planId, String documentNumber, String documentType) {
		LOGGER.info(":: executeGetInsuredQuotationLife [ QuotationId :: {} ]", quotationId);
		LOGGER.info(":: executeGetInsuredQuotationLife [ ProductId :: {} ]", productId);
		LOGGER.info(":: executeGetInsuredQuotationLife [ PlanId :: {} ]", planId);
		LOGGER.info(":: executeGetInsuredQuotationLife [ DocumentNumber :: {} ]", documentNumber);
		LOGGER.info(":: executeGetInsuredQuotationLife [ DocumentType :: {} ]", documentType);

		QuotationLifeDAO result = this.participantDAO.getInsuredQuotationLife(quotationId, productId, planId, documentNumber, documentType);
		LOGGER.info(" :: executeListParticipantRolesByCompanyId [ RoleList :: {} ]", JsonHelper.getInstance().toJsonString(result));
		return result;
	}

	public void setParticipantDAO(ParticipantDAO participantDAO) {
		this.participantDAO = participantDAO;
	}
}
