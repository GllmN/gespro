package af.cmr.indyli.gespro.business.dao.test;

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
import af.cmr.indyli.gespro.business.dao.IGpProjectManagerRepository;
import af.cmr.indyli.gespro.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GesproBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
//@Rollback(false)
public class GpProjectManagerDAOTest {
	@Resource(name = GesproConstantesDAO.GP_PROJECTMANAGER_DAO)
	private IGpProjectManagerRepository gpProjectManagerDAO;

	private Integer ProManIdForAllTest = null;
	private Integer createProManId = null;

	@Test
	
	public void createProjectManager() {
		// Given
		GpProjectManager projectManager = new GpProjectManager();
		projectManager.setFileNumber("3100");
		projectManager.setLastname("Gestoa");
		projectManager.setFirstname("PRISZ");
		projectManager.setPhoneNumber(321);
		projectManager.setPassword("newPassword");
		projectManager.setEmail("gzstot766.prisz@gouv.fr");
		projectManager.setLogin("gzstot766.prisz");
		projectManager.setCreationDate(new Date());
		projectManager.setUpdateDate(new Date());
		// When
		projectManager = gpProjectManagerDAO.save(projectManager);
		this.createProManId = projectManager.getId();
		// Then
		Assert.assertNotNull(projectManager.getId());
	}

	@Test
	public void testFindAllProjectManagerWithSuccess() {
		// Given
		// When
		List<GpProjectManager> projectManagers = this.gpProjectManagerDAO.findAll();
		// Then
		Assert.assertTrue(projectManagers.size() > 0);
	}

	@Test
	
	public void testFindByIdWithSuccess() throws GesproBusinessException {
		// Given
		Integer ProjectManagerId = this.ProManIdForAllTest;
		// When
		GpProjectManager projectManager = this.gpProjectManagerDAO.findById(ProjectManagerId).orElse(null);
		// Then
		Assert.assertTrue(projectManager.getId() == ProjectManagerId);
		//Assert.assertNotNull(projectManager);
		}

	@Test
	
	public void testUpdateProject() throws AccessDeniedException, GesproBusinessException {
		// Given
		GpProjectManager projectManager = this.gpProjectManagerDAO.findById(this.ProManIdForAllTest).orElse(null);
		// When
		//this.gpProjectManagerDAO.saveAndFlush(projectManager);
		GpProjectManager ProjectManagerUpdate = this.gpProjectManagerDAO.findById(this.ProManIdForAllTest).orElse(null);
		// Then
		Assert.assertNotNull(projectManager);
	}

	@Test
	public void testDelete() throws AccessDeniedException, Exception {
		// Given
		Integer projectManagerId = this.ProManIdForAllTest;
		this.ProManIdForAllTest = null;
		// Whens
		this.gpProjectManagerDAO.deleteById(projectManagerId);
		GpProjectManager projectManager = this.gpProjectManagerDAO.findById(projectManagerId).orElse(null);
		// Then
		Assert.assertNull(gpProjectManagerDAO.findById(projectManagerId).orElse(null));
	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpProjectManager projectManager = new GpProjectManager();
		projectManager.setFileNumber("3001");
		projectManager.setLastname("Gesto");
		projectManager.setFirstname("PRISZ");
		projectManager.setPhoneNumber(321);
		projectManager.setPassword("newPassword");
		projectManager.setEmail("gzstot66.prisz@gouv.fr");
		projectManager.setLogin("gzstot66.prisz");
		projectManager.setCreationDate(new Date());
		projectManager.setUpdateDate(new Date());
		projectManager = gpProjectManagerDAO.save(projectManager);
		this.ProManIdForAllTest = projectManager.getId();
	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, GesproBusinessException {
		if (this.ProManIdForAllTest != null) {
			this.gpProjectManagerDAO.deleteById(this.ProManIdForAllTest);
		}

		if (!Objects.isNull(this.createProManId)) {
			this.gpProjectManagerDAO.deleteById(this.createProManId);
		}
	}
}



