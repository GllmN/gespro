package af.cmr.indyli.gespro.business.dao.test;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import af.cmr.indyli.gespro.business.config.GesproBusinessConfig;
import af.cmr.indyli.gespro.business.dao.IGpAddressRepository;
import af.cmr.indyli.gespro.business.dao.IGpEmployeeRepository;
import af.cmr.indyli.gespro.business.dao.IGpOrganizationRepository;
import af.cmr.indyli.gespro.business.entity.GpAddress;
import af.cmr.indyli.gespro.business.entity.GpEmployee;
import af.cmr.indyli.gespro.business.entity.GpOrganization;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GesproBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GpAddressDAOTest {

	@Resource(name = GesproConstantesDAO.GP_ADDRESS_DAO)
	private IGpAddressRepository addressDAO;

	@Resource(name = GesproConstantesDAO.GP_EMPLOYEE_DAO)
	private IGpEmployeeRepository empDAO;

	@Resource(name = GesproConstantesDAO.GP_ORGANIZATION_DAO)
	private IGpOrganizationRepository organizationDAO;

	private Integer addrIdForAllTest = null;
	private Integer createAddrId = null;

	private GpOrganization orgForAllTest = null;
	private GpEmployee empForAllTest = null;

	@Test
	public void createAddressTest() throws GesproBusinessException {

		GpAddress addr = new GpAddress();
		addr.setStreetNumber(2050);
		addr.setStreetLabel("ROYAL");
		addr.setZipCode(44);
		addr.setCountry("France");
		byte isMain = 2;
		addr.setIsMain(isMain);

		addr.setGpOrganization(orgForAllTest);
		addr.setGpEmployee(empForAllTest);
		addr = addressDAO.save(addr);

		createAddrId = addr.getId();
		Assert.assertNotNull(createAddrId);

	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpAddress> addrList = this.addressDAO.findAll();
		// Then
		Assert.assertTrue(addrList.size() > 0);
	}

	@Test
	public void testFindAddressById() throws GesproBusinessException {
		// Given
		Integer id = this.addrIdForAllTest;
		// When
		Optional<GpAddress> addr = this.addressDAO.findById(id);
		// Then
		Assert.assertNotNull(addr.get().getId());
	}

	@Test
	public void testUpdateAddress() throws GesproBusinessException, AccessDeniedException {
		// Given
		Optional<GpAddress> addr = this.addressDAO.findById(this.addrIdForAllTest);

		String str = "ZANGA";
		addr.get().setStreetLabel(str);
		// When
		this.addressDAO.save(addr.get());
		Optional<GpAddress> addrUpdated = this.addressDAO.findById(addr.get().getId());
		// Then

		Assert.assertTrue(addrUpdated.get().getStreetLabel().equals(str));
	}

	@Test
	public void testDelete() throws AccessDeniedException, GesproBusinessException {
		// Given
		Integer id = addrIdForAllTest;
		addrIdForAllTest = null;
		// When
		this.addressDAO.deleteById(id);

		GpAddress adr = addressDAO.findById(id).orElse(null);
		// Then
		Assert.assertNull(adr);
	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpOrganization org = new GpOrganization();
		org.setAdrWeb("orgnew.com");
		org.setContactEmail("contact@orgnew.com");
		org.setContactName("Orgnew-contact");
		org.setName("Orgnew-name");
		org.setOrgCode("OMN00691");
		org.setPhoneNumber(1230);
		org = organizationDAO.save(org);
		this.orgForAllTest = org;

		GpEmployee emp = new GpEmployee();
		emp.setFileNumber("3001");
		emp.setLastname("Brice");
		emp.setFirstname("Joan");
		emp.setPhoneNumber(678558);
		emp.setPassword("orgNewPassword");
		emp.setEmail("Brice.Joan@gouv.fr");
		emp.setLogin("Brice.Joan");
		emp.setCreationDate(new Date());
		emp = empDAO.save(emp);
		this.empForAllTest = emp;

		GpAddress addr = new GpAddress();
		addr.setStreetNumber(2050);
		addr.setStreetLabel("ROYAL");
		addr.setZipCode(44);
		addr.setCountry("France");
		byte isMain = 2;
		addr.setIsMain(isMain);

		addr.setGpOrganization(org);
		addr.setGpEmployee(emp);
		addr = addressDAO.save(addr);
		Assert.assertNotNull(addr.getId());

		addrIdForAllTest = addr.getId();

	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, GesproBusinessException {

		if (!Objects.isNull(this.addrIdForAllTest)) {

			this.addressDAO.deleteById(this.addrIdForAllTest);
		}
		if (!Objects.isNull(this.createAddrId)) {
			this.addressDAO.deleteById(this.createAddrId);
		}
		if (!Objects.isNull(this.orgForAllTest)) {
			this.organizationDAO.deleteById(this.orgForAllTest.getId());
		}
		if (!Objects.isNull(this.empForAllTest)) {
			this.empDAO.deleteById(this.empForAllTest.getId());
		}

	}
}
