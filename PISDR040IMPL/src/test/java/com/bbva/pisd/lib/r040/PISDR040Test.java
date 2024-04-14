package com.bbva.pisd.lib.r040;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.rbvd.dto.participant.dao.QuotationCustomerDAO;
import com.bbva.rbvd.dto.participant.dao.QuotationLifeDAO;
import com.bbva.rbvd.dto.participant.dao.RolDAO;
import com.bbva.rbvd.dto.participant.utils.InsuranceProperties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/PISDR040-app.xml",
		"classpath:/META-INF/spring/PISDR040-app-test.xml",
		"classpath:/META-INF/spring/PISDR040-arc.xml",
		"classpath:/META-INF/spring/PISDR040-arc-test.xml" })
public class PISDR040Test {

	@Spy
	private Context context;

	@Resource(name = "pisdR040")
	private PISDR040 pisdR040;

	@Resource(name = "applicationConfigurationService")
	private ApplicationConfigurationService applicationConfigurationService;

	@Resource(name = "jdbcUtils")
	private JdbcUtils jdbcUtils;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",new Locale("es", "PE"));

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}

	private Object getObjectIntrospection() throws Exception{
		Object result = this.pisdR040;
		if(this.pisdR040 instanceof Advised){
			Advised advised = (Advised) this.pisdR040;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}

	/**
	 *  Casos para ejecutar en la creación de un proceso biometrico .
	 *
	 *  1. Ejecución exitosa para obtener los datos de cotización
	 *  2. Parametros invalidos al obtener la cotización .
	 * */

	@Test
	public void execute_find_quotation_by_quotationid_test() throws ParseException {
		/**
		 *  Data de prueba
		 * */
		Map<String,Object> mapResponse = getMockMapQuotationEntity();
		/**
		 *  Context
		 * */
		Mockito.when(this.jdbcUtils.queryForMap(Mockito.eq("PISD.SELECT_FIND_QUOTATION_BY_POLICY_QUOTA_INTERNAL_ID"),Mockito.anyMap())).thenReturn(mapResponse);
		/**
		 * Ejecución
		 * */
		QuotationCustomerDAO result = this.pisdR040.executeFindQuotationJoinByPolicyQuotaInternalId("0814000000691");
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getQuotation());
		Assert.assertEquals("customer name",result.getQuotation().getInsuredCustomerName());
		Assert.assertEquals("client last name",result.getQuotation().getClientLasName());
		Assert.assertNotNull(result.getQuotationMod());
		Assert.assertEquals("email@bbva.com",result.getQuotationMod().getContactEmailDesc());
		Assert.assertEquals("923453849",result.getQuotationMod().getCustomerPhoneDesc());
		Assert.assertNotNull(result.getInsuranceProduct());
		Assert.assertEquals("834",result.getInsuranceProduct().getInsuranceProductType());
		Assert.assertNotNull(result.getInsuranceBusiness());
		Assert.assertEquals("PROTECCION_TARJETAS",result.getInsuranceBusiness().getInsuranceBusinessName());
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test
	public void execute_list_Participant_Roles_By_Company_Id_test() throws ParseException {
		/**
		 *  Data de prueba
		 * */
		List<Map<String,Object>> ListMapResponse = getMockMapParticipantRolesEntity();
		/**
		 *  Context
		 * */
		Mockito.when(this.jdbcUtils.queryForList(Mockito.eq("PISD.SELECT_PARTICIPANT_COMPANY_RELATED_ROLES"),Mockito.anyMap())).thenReturn(ListMapResponse);
		/**
		 * Ejecución
		 * */
		List<RolDAO> result = this.pisdR040.executeListParticipantRolesByCompanyId(new BigDecimal(1));
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.get(0));
		Assert.assertNotNull(result.get(1));
		Assert.assertNotNull(result.get(2));
		Assert.assertEquals("8",result.get(0).getInsuranceCompanyRoleId());
		Assert.assertEquals("9",result.get(1).getInsuranceCompanyRoleId());
		Assert.assertEquals("23",result.get(2).getInsuranceCompanyRoleId());
		Assert.assertEquals(new Integer(1),result.get(0).getParticipantRoleId());
		Assert.assertEquals(new Integer(2),result.get(1).getParticipantRoleId());
		Assert.assertEquals(new Integer(7),result.get(2).getParticipantRoleId());
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test
	public void execute_Get_Insured_Quotation_Life_test() throws ParseException {
		/**
		 *  Data de prueba
		 * */
		Map<String,Object> mapResponse = getMockMapQuotationLifeEntity();
		/**
		 *  Context
		 * */
		Mockito.when(this.jdbcUtils.queryForMap(Mockito.eq("PISD.GET_INSURED_DATA_FROM_QUOTATION"),Mockito.anyMap())).thenReturn(mapResponse);
		/**
		 * Ejecución
		 * */
		QuotationLifeDAO result = this.pisdR040.executeGetInsuredQuotationLife("quotationId","productId","planId","documentNumber","documentType");
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getClientLastName());
		Assert.assertEquals("client_last_name",result.getClientLastName());
		Assert.assertNotNull(result.getCustomerBirthDate());
		Assert.assertEquals("customer_birth_date",result.getCustomerBirthDate());
		Assert.assertNotNull(result.getInsuredCustomerName());
		Assert.assertEquals("insured_customer_name",result.getInsuredCustomerName());
		Assert.assertNotNull(result.getCustomerDocumentType());
		Assert.assertEquals("customer_document_type",result.getCustomerDocumentType());
		Assert.assertNotNull(result.getPersonalId());
		Assert.assertEquals("personal_id",result.getPersonalId());
		Assert.assertNotNull(result.getGenderId());
		Assert.assertEquals("gender_id",result.getGenderId());
		Assert.assertNotNull(result.getUserEmailPersonalDesc());
		Assert.assertEquals("user_email_personal_desc",result.getUserEmailPersonalDesc());
		Assert.assertNotNull(result.getPhoneId());
		Assert.assertEquals("phone_id",result.getPhoneId());

		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test(expected = BusinessException.class)
	public void executeFindByInsrncCompanySimulationIdNullTest(){
		/**
		 * Execution
		 * */
		QuotationCustomerDAO result = this.pisdR040.executeFindQuotationJoinByPolicyQuotaInternalId(null);
		Assert.assertNull(result);
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test(expected = BusinessException.class)
	public void executelistParticipantRolesByCompanyIdNullTest(){
		/**
		 * Execution
		 * */
		List<RolDAO> result = this.pisdR040.executeListParticipantRolesByCompanyId(null);
		Assert.assertNull(result);
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test(expected = BusinessException.class)
	public void executeGetInsuredQuotationLifeNullTest(){
		/**
		 * Execution
		 * */
		QuotationLifeDAO result = this.pisdR040.executeGetInsuredQuotationLife(null,null,null,null,null);
		Assert.assertNull(result);
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	private Map<String, Object> getMockMapQuotationEntity() throws ParseException {

		Map<String,Object> mapMockQuotationEntity = new HashMap<>();
		mapMockQuotationEntity.put("INSURED_CUSTOMER_NAME","customer name");
		mapMockQuotationEntity.put("CLIENT_LAST_NAME","client last name");
		mapMockQuotationEntity.put("CONTACT_EMAIL_DESC","email@bbva.com");
		mapMockQuotationEntity.put("CUSTOMER_PHONE_DESC","923453849");
		mapMockQuotationEntity.put("INSURANCE_PRODUCT_TYPE","834");
		mapMockQuotationEntity.put("INSURANCE_BUSINESS_NAME","PROTECCION_TARJETAS");

		return mapMockQuotationEntity;
	}

	private Map<String, Object> getMockMapQuotationLifeEntity() throws ParseException {

		Map<String,Object> mapMockQuotationEntity = new HashMap<>();
		mapMockQuotationEntity.put(InsuranceProperties.FIELD_CLIENT_LAST_NAME.getValue(),"client_last_name");
		mapMockQuotationEntity.put(InsuranceProperties.FIELD_CUSTOMER_BIRTH_DATE.getValue(),"customer_birth_date");
		mapMockQuotationEntity.put(InsuranceProperties.FIELD_INSURED_CUSTOMER_NAME.getValue(),"insured_customer_name");
		mapMockQuotationEntity.put(InsuranceProperties.FIELD_CUSTOMER_DOCUMENT_TYPE.getValue(),"customer_document_type");
		mapMockQuotationEntity.put(InsuranceProperties.FIELD_PERSONAL_ID.getValue(),"personal_id");
		mapMockQuotationEntity.put(InsuranceProperties.FIELD_GENDER_ID.getValue(),"gender_id");
		mapMockQuotationEntity.put(InsuranceProperties.FIELD_USER_EMAIL_PERSONAL_DESC.getValue(),"user_email_personal_desc");
		mapMockQuotationEntity.put(InsuranceProperties.FIELD_PHONE_ID.getValue(),"phone_id");

		return mapMockQuotationEntity;
	}

	private List<Map<String,Object>> getMockMapParticipantRolesEntity() throws ParseException {
		List<Map<String,Object>> mapListMockParticipantRolesEntity = new ArrayList<>();
		Map<String,Object> mapMockParticipantRole1 = new HashMap<>();
		mapMockParticipantRole1.put("PARTICIPANT_ROLE_ID",new BigDecimal(1));
		mapMockParticipantRole1.put("INSURANCE_COMPANY_ROLE_ID","8");
		mapListMockParticipantRolesEntity.add(mapMockParticipantRole1);

		Map<String,Object> mapMockParticipantRole2 = new HashMap<>();
		mapMockParticipantRole2.put("PARTICIPANT_ROLE_ID",new BigDecimal(2));
		mapMockParticipantRole2.put("INSURANCE_COMPANY_ROLE_ID","9");
		mapListMockParticipantRolesEntity.add(mapMockParticipantRole2);

		Map<String,Object> mapMockParticipantRole3 = new HashMap<>();
		mapMockParticipantRole3.put("PARTICIPANT_ROLE_ID",new BigDecimal(7));
		mapMockParticipantRole3.put("INSURANCE_COMPANY_ROLE_ID","23");
		mapListMockParticipantRolesEntity.add(mapMockParticipantRole3);

		return mapListMockParticipantRolesEntity;
	}

	private Map<String, Object> getQuotationMap() {
		Map<String,Object> mapQuotationResponse = new HashMap<>();

		mapQuotationResponse.put("QUOTE_DATE","2023-01-17");
		mapQuotationResponse.put("INSURANCE_MODALITY_TYPE","02");
		mapQuotationResponse.put("INSURANCE_PRODUCT_TYPE","842");
		mapQuotationResponse.put("USER_AUDIT_ID","ZG13001");
		mapQuotationResponse.put("CUSTOMER_ID","97171889");
		mapQuotationResponse.put("POLICY_QUOTA_STATUS_TYPE","COT");
		mapQuotationResponse.put("PERSONAL_DOC_TYPE","R");
		mapQuotationResponse.put("PARTICIPANT_PERSONAL_ID","20763156118");
		mapQuotationResponse.put("CONTACT_EMAIL_DESC","cristian.segovia@bbva.com");
		mapQuotationResponse.put("CUSTOMER_PHONE_DESC","973834312");
		mapQuotationResponse.put("RFQ_INTERNAL_ID",null);
		mapQuotationResponse.put("INSURANCE_MODALITY_NAME","PLAN PLATA");
		mapQuotationResponse.put("INSUR_MODALITY_DESC","PLAN 02 VIDA LEY");
		mapQuotationResponse.put("INSURANCE_COMPANY_MODALITY_ID","545612");

		return mapQuotationResponse;
	}
	
}
