package af.cmr.indyli.gespro.business.dao.test;

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
import af.cmr.indyli.gespro.business.dao.IGpOrganizationRepository;
import af.cmr.indyli.gespro.business.entity.GpOrganization;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GesproBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GpOrganizationDAOTest {

	@Resource(name = GesproConstantesDAO.GP_ORGANIZATION_DAO)
	private IGpOrganizationRepository orgDAO;

	private Integer orgIdForAllTest = null;
	private Integer createOrgId = null;

	@Test
	public void testCreateOrganizationWithSuccess() throws GesproBusinessException {
		// Given
		GpOrganization organization = new GpOrganization();
		organization.setAdrWeb("website.com");
		organization.setContactEmail("contact@mail.com");
		organization.setContactName("CONTACT_NANE");
		organization.setName("ORG");
		organization.setOrgCode("OMC");
		organization.setPhoneNumber(1024);
		// when
		organization = orgDAO.save(organization);
		// Then
		Assert.assertNotNull(organization.getId());
	}

	@Test
	public void testFindAllOrganizationWithSuccess() {
		// Given

		// When
		List<GpOrganization> orgs = this.orgDAO.findAll();
		// Then
		Assert.assertTrue(orgs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() throws GesproBusinessException {
		// Given
		Integer orgId = this.orgIdForAllTest;

		// When
		GpOrganization org = this.orgDAO.findById(orgId).orElse(null);
		// Then
		Assert.assertNotNull(org);
	}

	@Test
	public void testDelete() throws GesproBusinessException, AccessDeniedException {
		// Given
		Integer orgId = this.orgIdForAllTest;
		this.orgIdForAllTest = null;
		// When
		this.orgDAO.deleteById(orgId);

		// Then
		Assert.assertNull(orgDAO.findById(orgId).orElse(null));
	}

	@Test
	public void testUpdateOrganization() throws GesproBusinessException, AccessDeniedException {
		// Given
		GpOrganization org = this.orgDAO.findById(this.orgIdForAllTest).orElse(null);
		int phone = 1000;
		org.setPhoneNumber(phone);
		// When

		GpOrganization orgUpdate = this.orgDAO.save(org);
		// Then

		Assert.assertTrue(orgUpdate.getPhoneNumber() == phone);

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpOrganization org = new GpOrganization();

		org.setAdrWeb("orgainternational.com");
		org.setContactEmail("contact@orgainternational.com");
		org.setContactName("OI-contact");
		org.setName("OI-name");
		org.setOrgCode("OM001");
		org.setPhoneNumber(1233);
		org = orgDAO.save(org);

		assertNotNull(org);
		orgIdForAllTest = org.getId();
	}

	@After
	public void deleteAllEntityAfter() throws GesproBusinessException, AccessDeniedException {
		if (this.orgIdForAllTest != null) {
			this.orgDAO.deleteById(this.orgIdForAllTest);
		}
		if (!Objects.isNull(this.createOrgId)) {
			this.orgDAO.deleteById(this.createOrgId);
		}
	}
}
