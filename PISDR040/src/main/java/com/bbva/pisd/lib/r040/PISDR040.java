package com.bbva.pisd.lib.r040;

import com.bbva.rbvd.dto.participant.dao.QuotationCustomerDAO;
import com.bbva.rbvd.dto.participant.dao.QuotationLifeDAO;
import com.bbva.rbvd.dto.participant.dao.RolDAO;

import java.math.BigDecimal;
import java.util.List;

/**
 * The  interface PISDR040 class...
 */
public interface PISDR040 {

	/**
	 * The execute method...
	 */
	QuotationCustomerDAO executeFindQuotationJoinByPolicyQuotaInternalId(String policyQuotaInternalId);

	List<RolDAO> executeListParticipantRolesByCompanyId(BigDecimal insuranceCompanyId);

	QuotationLifeDAO executeGetInsuredQuotationLife(String quotationId, String productId, String planId, String ducumentNumber,String documentType);

}
