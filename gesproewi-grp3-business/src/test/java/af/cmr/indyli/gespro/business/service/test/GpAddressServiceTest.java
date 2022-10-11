package af.cmr.indyli.gespro.business.service.test;

import java.nio.file.AccessDeniedException;
import java.util.Date;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import af.cmr.indyli.gespro.business.config.GesproBusinessConfig;
import af.cmr.indyli.gespro.business.dto.GpAddressBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpAddressFullDTO;
import af.cmr.indyli.gespro.business.dto.GpEmployeeFullDTO;
import af.cmr.indyli.gespro.business.dto.GpOrganizationBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpOrganizationFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpAddressService;
import af.cmr.indyli.gespro.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GesproBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GpAddressServiceTest {

	@Resource(name = GesproConstantesService.GP_ADDRESS_SERVICE_KEY)
	private IGpAddressService addressService;

	@Resource(name = GesproConstantesService.GP_EMPLOYEE_SERVICE_KEY)
	private IGpEmployeeService empService;

	@Resource(name = GesproConstantesService.GP_ORGANIZATION_KEY)
	private IGpOrganizationService organizationService;

	private Integer addrIdForAllTest = null;
	private Integer createAddrId = null;

	private GpOrganizationFullDTO orgForAllTest = null;
	private GpEmployeeFullDTO empForAllTest = null;

	@Test
	public void createAddressTest() throws GesproBusinessException {

		GpAddressFullDTO addr = new GpAddressFullDTO();
		addr.setStreetNumber(2050);
		addr.setStreetLabel("ROYAL");
		addr.setZipCode(44);
		addr.setCountry("France");
		byte isMain = 2;
		addr.setIsMain(isMain);

		addr.setGpOrganization(orgForAllTest);
		addr.setGpEmployee(empForAllTest);
		addr = addressService.create(addr);

		createAddrId = addr.getId();
		Assert.assertNotNull(createAddrId);

	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpAddressBasicDTO> addrList = this.addressService.findAll();
		// Then
		Assert.assertTrue(addrList.size() > 0);
	}

	@Test
	public void testFindAddressById() throws GesproBusinessException {
		// Given
		Integer id = this.addrIdForAllTest;
		// When
		GpAddressFullDTO addr = this.addressService.findById(id);
		// Then
		Assert.assertNotNull(addr.getId());
	}

	@Test
	public void testUpdateAddress() throws GesproBusinessException, AccessDeniedException {
		// Given
		GpAddressFullDTO addr = this.addressService.findById(this.addrIdForAllTest);
		GpOrganizationFullDTO org = new GpOrganizationFullDTO();
		org.setAdrWeb("nnew.com");
		org.setContactEmail("scontact@orgnew.com");
		org.setContactName("Osrgnew-contact");
		org.setName("Osrgnew-name");
		org.setOrgCode("OsMN00691");
		org.setPhoneNumber(1240);
		org = organizationService.create(org);
		GpOrganizationBasicDTO organisationBasic = new GpOrganizationBasicDTO();

		organisationBasic.setId(org.getId());
		organisationBasic.setAdrWeb(org.getAdrWeb());
		organisationBasic.setContactEmail(org.getContactEmail());
		organisationBasic.setName(org.getName());
		organisationBasic.setOrgCode(org.getOrgCode());
		organisationBasic.setPhoneNumber(org.getPhoneNumber());

		addr.setGpOrganization(organisationBasic);
		// When
		this.addressService.updateAddress(addr);
		GpAddressFullDTO addrUpdated = this.addressService.findById(addr.getId());
		// Then

		Assert.assertTrue(addrUpdated.getGpOrganization().getPhoneNumber() == 1240);
	}

	@Test
	public void testDelete() throws AccessDeniedException, GesproBusinessException {
		// Given
		Integer id = addrIdForAllTest;

		// When
		this.addressService.deleteById(id);
		boolean ifAddrExist = this.addressService.ifEntityExistById(id);

		// Then
		Assert.assertFalse(ifAddrExist);
	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpOrganizationFullDTO org = new GpOrganizationFullDTO();
		org.setAdrWeb("orgnew.com");
		org.setContactEmail("contact@orgnew.com");
		org.setContactName("Orgnew-contact");
		org.setName("Orgnew-name");
		org.setOrgCode("OMN00691");
		org.setPhoneNumber(1230);
		org = organizationService.create(org);
		this.orgForAllTest = org;

		GpEmployeeFullDTO emp = new GpEmployeeFullDTO();
		emp.setFileNumber("3001");
		emp.setLastname("Brice");
		emp.setFirstname("Joan");
		emp.setPhoneNumber(678558);
		emp.setPassword("orgNewPassword");
		emp.setEmail("Brice.Joan@gouv.fr");
		emp.setLogin("Brice.Joan");
		emp.setCreationDate(new Date());
		emp = empService.create(emp);
		this.empForAllTest = emp;

		GpAddressFullDTO addr = new GpAddressFullDTO();
		addr.setStreetNumber(2050);
		addr.setStreetLabel("ROYAL");
		addr.setZipCode(44);
		addr.setCountry("France");
		byte isMain = 2;
		addr.setIsMain(isMain);

		addr.setGpOrganization(org);
		addr.setGpEmployee(emp);
		addr = addressService.create(addr);
		Assert.assertNotNull(addr.getId());

		addrIdForAllTest = addr.getId();

	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, GesproBusinessException {

		if (!Objects.isNull(this.addrIdForAllTest)) {
			boolean ifAddrExist = this.addressService.ifEntityExistById(addrIdForAllTest);
			if (ifAddrExist)
				this.addressService.deleteById(this.addrIdForAllTest);
		}
		if (!Objects.isNull(this.createAddrId)) {
			this.addressService.deleteById(this.createAddrId);
		}
		if (!Objects.isNull(this.orgForAllTest)) {
			this.organizationService.deleteById(this.orgForAllTest.getId());
		}
		if (!Objects.isNull(this.empForAllTest)) {
			this.empService.deleteById(this.empForAllTest.getId());
		}

	}
}
