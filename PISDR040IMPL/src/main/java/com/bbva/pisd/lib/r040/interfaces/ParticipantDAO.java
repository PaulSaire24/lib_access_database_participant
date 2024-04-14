package com.bbva.pisd.lib.r040.interfaces;

import com.bbva.rbvd.dto.participant.dao.QuotationCustomerDAO;
import com.bbva.rbvd.dto.participant.dao.QuotationLifeDAO;
import com.bbva.rbvd.dto.participant.dao.RolDAO;

import java.math.BigDecimal;
import java.util.List;

public interface ParticipantDAO {
    QuotationCustomerDAO findQuotationByPolicyQuotaInternalId(String policyQuotaInternalId);
    List<RolDAO> listParticipantRolesByCompanyId(BigDecimal insuranceCompanyId);
    QuotationLifeDAO getInsuredQuotationLife(String quotationId, String productId, String planId, String documentNumber, String documentType);
}
