package com.bbva.pisd.lib.r040.transform.bean;

import com.bbva.rbvd.dto.participant.dao.RolDAO;
import com.bbva.rbvd.dto.participant.utils.InsuranceProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoleTransformBean {
    public static List<RolDAO> mapTransformParticipantQuotationEntity(List<Map<String,Object>> mapPartQuotation){
        List<RolDAO> rolList = new ArrayList<>();
        mapPartQuotation.forEach(list -> rolList.add(createRolObject(list)));
        return rolList;
    }

    private static RolDAO createRolObject(Map<String, Object> map){
        RolDAO rolDTO = new RolDAO();
        rolDTO.setParticipantRoleId(((BigDecimal) map.get(InsuranceProperties.FIELD_PARTICIPANT_ROLE_ID.getValue())).intValue());
        rolDTO.setInsuranceCompanyRoleId((String) map.get(InsuranceProperties.FIELD_INSURANCE_COMPANY_ROLE_ID.getValue()));
        return rolDTO;
    }
}
