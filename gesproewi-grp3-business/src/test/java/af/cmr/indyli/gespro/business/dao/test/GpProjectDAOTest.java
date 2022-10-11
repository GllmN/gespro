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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import af.cmr.indyli.gespro.business.config.GesproBusinessConfig;
import af.cmr.indyli.gespro.business.dao.IGpOrganizationRepository;
import af.cmr.indyli.gespro.business.dao.IGpProjectManagerRepository;
import af.cmr.indyli.gespro.business.dao.IGpProjectRepository;
import af.cmr.indyli.gespro.business.entity.GpOrganization;
import af.cmr.indyli.gespro.business.entity.GpProject;
import af.cmr.indyli.gespro.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GesproBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GpProjectDAOTest {
	@Resource(name = GesproConstantesDAO.GP_PROJECT_DAO)
	private IGpProjectRepository gpProjectRepository;
	
	@Resource(name = GesproConstantesDAO.GP_ORGANIZATION_DAO)
	private IGpOrganizationRepository orgDAO;
	@Resource(name = GesproConstantesDAO.GP_PROJECTMANAGER_DAO)
	private IGpProjectManagerRepository gpProjectManagerDAO;
	
	private Integer proIdForAllTest = null;
	private Integer createProId = null;
	
	private GpOrganization orgForAllTest ;
	private GpProjectManager proManagerForAllTest ;
	
	@Test
	public void createProj()  {
		
		GpProject project = new GpProject();
		project.setCode("123");
		project.setName("Chirac");
		project.setDescription("Manger des pommes");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(10000);
		project.setCreationDate(new Date());
		project.setUpdateDate(null);
		project.setOrganization(orgForAllTest);
		project.setProjectManager(proManagerForAllTest);
		project = gpProjectRepository.save(project);
		createProId = project.getId();
		System.out.println(createProId);
		Assert.assertNotNull(createProId);
		
	}

	@Test
	public void testFindAllProjectWithSuccess() {
		// Given
		// When
		List<GpProject> project = this.gpProjectRepository.findAll();
		// Then
		Assert.assertTrue(project.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() throws GesproBusinessException {
		// Given
		Integer projectId = this.proIdForAllTest;
		// When
		GpProject project = this.gpProjectRepository.findById(projectId).orElse(null);
		// Then
		Assert.assertNotNull(project);
	}

	@Test
	public void testUpdateProject() throws AccessDeniedException, GesproBusinessException {
		// Given
		GpProject project = this.gpProjectRepository.findById(this.proIdForAllTest).orElse(null);
		project.setCode("123");
		// When
		this.gpProjectRepository.saveAndFlush(project);
		GpProject projectUpdate = this.gpProjectRepository.findById(this.proIdForAllTest).orElse(null);
		// Then

		Assert.assertTrue(projectUpdate.getCode() == "123");
	}

	@Test
	public void testDelete() throws AccessDeniedException, Exception {
		// Given
		Integer projectId = this.proIdForAllTest;
		this.proIdForAllTest = null;
		// Whens
		this.gpProjectRepository.deleteById(projectId);
		GpProject project = this.gpProjectRepository.findById(projectId).orElse(null);

		// Then
		Assert.assertNull(project);

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpOrganization organization = new GpOrganization();
		organization.setAdrWeb("website.com");
		organization.setContactEmail("contact@mail.com");
		organization.setContactName("CONTACT_NANE");
		organization.setName("ORG");
		organization.setOrgCode("OMC");
		organization.setPhoneNumber(1024);
		// when
		organization = orgDAO.save(organization);
		//System.out.println(organization);
		orgForAllTest=organization;
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
		proManagerForAllTest=projectManager;
		//System.out.println(projectManager);
		GpProject project = new GpProject();
		project.setCode("456");
		project.setName("Jacque");
		project.setDescription("Frauder le metro");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(10000);
		project.setCreationDate(new Date());
		project.setUpdateDate(null);
		project.setOrganization(organization);
		project.setProjectManager(projectManager);
		project = gpProjectRepository.save(project);
		//createProId = project.getId();
		//System.out.println(createProId);
		proIdForAllTest=project.getId();
		Assert.assertNotNull(project.getId());
	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, GesproBusinessException {
		if (this.proIdForAllTest != null) {
			this.gpProjectRepository.deleteById(this.proIdForAllTest);
		}

		if (!Objects.isNull(this.createProId)) {
			this.gpProjectRepository.deleteById(this.createProId);
		}
	}
}


