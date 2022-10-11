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
import af.cmr.indyli.gespro.business.dto.GpProjectManagerBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpProjectManagerFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpProjectManagerService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GesproBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GpProjectManagerServiceTest {
	
	@Resource(name = GesproConstantesService.GP_PROJECTMANAGER_SERVICE_KEY)
	private IGpProjectManagerService proManService;

	private Integer proManIdForAllTest = null;
	private Integer createProManId = null;

	@Test
	public void createProManId() throws GesproBusinessException {
		GpProjectManagerFullDTO proMan = new GpProjectManagerFullDTO();
		proMan.setFileNumber("3001");
		proMan.setLastname("Gesto");
		proMan.setFirstname("PRISZ");
		proMan.setPhoneNumber(321);
		proMan.setPassword("newPassword");
		proMan.setEmail("gzstot.prisz@gouv.fr");
		proMan.setLogin("gzstot.prisz");
		proMan.setCreationDate(new Date());
		proMan.setUpdateDate(new Date());
		
		proMan = proManService.create(proMan);
		this.createProManId = proMan.getId();
		Assert.assertNotNull(createProManId);
	}

	@Test
	public void testFindAllProjectManagerWithSuccess() {
		// Given
		// When
		List<GpProjectManagerBasicDTO> proMans = this.proManService.findAll();
		// Then
		Assert.assertTrue(proMans.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() throws GesproBusinessException {
		// Given
		Integer proManId = this.proManIdForAllTest;
		// When
		GpProjectManagerFullDTO proMan = this.proManService.findById(proManId);
		// Then
		Assert.assertTrue(proMan.getId() == proManId);
	}

	@Test
	public void testUpdateProjectManager() throws AccessDeniedException, GesproBusinessException {
		// Given
		GpProjectManagerFullDTO proMan = this.proManService.findById(this.proManIdForAllTest);
		proMan.setPhoneNumber(3221);
		// When
		this.proManService.update(proMan);
		GpProjectManagerFullDTO proManUpdate = this.proManService.findById(this.proManIdForAllTest);
		// Then

		Assert.assertTrue(proManUpdate.getPhoneNumber() == 3221);
	}

	@Test
	public void testDelete() throws AccessDeniedException, Exception {
		// Given
		Integer proManId = this.proManIdForAllTest;
		this.proManIdForAllTest = null;
		// Whens
		this.proManService.deleteById(proManId);
		GpProjectManagerFullDTO proMan = this.proManService.findById(proManId);

		// Then
		Assert.assertNull(proMan);

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpProjectManagerFullDTO proMan = new GpProjectManagerFullDTO();
		//proMan.setFileNumber("2001");
		//proMan.setLastname("Laurent");
		//proMan.setFirstname("FABIUS");
		//proMan.setPhoneNumber(123);
		//proMan.setPassword("myThirdPassword");
		//proMan.setEmail("laurent.fabius@gouv.fr");
		//proMan.setLogin("laurent.fabius");
		//proMan.setCreationDate(new Date());
		proMan.setUpdateDate(new Date());
		proMan = proManService.create(proMan);
		//this.proManIdForAllTest = proMan.getId();
	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, GesproBusinessException {
		if (this.proManIdForAllTest != null) {
			this.proManService.deleteById(this.proManIdForAllTest);
		}

		if (!Objects.isNull(this.createProManId)) {
			this.proManService.deleteById(this.createProManId);
		}
	}
}

