package af.cmr.indyli.gespro.business.service.test;

import static org.junit.Assert.assertNotNull;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import af.cmr.indyli.gespro.business.config.GesproBusinessConfig;
import af.cmr.indyli.gespro.business.dto.GpOrganizationBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpOrganizationFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GesproBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GpOrganizationServiceTest {

	@Resource(name = GesproConstantesService.GP_ORGANIZATION_KEY)
	private IGpOrganizationService orgService;

	private Integer orgIdForAllTest = null;
	private Integer createOrgId = null;

	@Test
	public void testCreateOrganizationWithSuccess() throws GesproBusinessException {
		// Given
		GpOrganizationFullDTO organization = new GpOrganizationFullDTO();
		organization.setAdrWeb("website.com");
		organization.setContactEmail("contact@mail.com");
		organization.setContactName("CONTACT_NANE");
		organization.setName("ORG");
		organization.setOrgCode("OMC");
		organization.setPhoneNumber(1024);
		// when
		organization = orgService.create(organization);
		// Then
		Assert.assertNotNull(organization.getId());
	}

	@Test
	public void testFindAllOrganizationWithSuccess() {
		// Given

		// When
		List<GpOrganizationBasicDTO> orgs = this.orgService.findAll();
		// Then
		Assert.assertTrue(orgs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() throws GesproBusinessException {
		// Given
		Integer orgId = this.orgIdForAllTest;

		// When
		GpOrganizationFullDTO org = this.orgService.findById(orgId);
		// Then
		Assert.assertNotNull(org);
	}

	@Test
	public void testDelete() throws GesproBusinessException, AccessDeniedException {
		// Given
		Integer orgId = this.orgIdForAllTest;
		//this.orgIdForAllTest = 0;
		// When
		this.orgService.deleteById(orgId);
		boolean ifEntityExist = this.orgService.ifEntityExistById(orgId);

		// Then
		Assert.assertFalse(ifEntityExist);
	}

	@Test
	public void testUpdateOrganization() throws GesproBusinessException, AccessDeniedException {
		// Given
		GpOrganizationFullDTO org = this.orgService.findById(this.orgIdForAllTest);
		int phone = 1000;
		org.setPhoneNumber(phone);
		// When

		GpOrganizationFullDTO orgUpdate = this.orgService.update(org);
		// Then

		Assert.assertTrue(orgUpdate.getPhoneNumber() == phone);

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpOrganizationFullDTO org = new GpOrganizationFullDTO();

		org.setAdrWeb("orgainternational.com");
		org.setContactEmail("contact@orgainternational.com");
		org.setContactName("OI-contact");
		org.setName("OI-name");
		org.setOrgCode("OM001");
		org.setPhoneNumber(1233);
		org = orgService.create(org);

		assertNotNull(org);
		orgIdForAllTest = org.getId();
	}

	@After
	public void deleteAllEntityAfter() throws GesproBusinessException, AccessDeniedException {
		if (this.orgIdForAllTest != 0) {
			boolean ifEntityExist = this.orgService.ifEntityExistById(orgIdForAllTest);
			if (ifEntityExist)
			this.orgService.deleteById(this.orgIdForAllTest);
		}
		if (!Objects.isNull(this.createOrgId)) {
			this.orgService.deleteById(this.createOrgId);
		}
	}
}
