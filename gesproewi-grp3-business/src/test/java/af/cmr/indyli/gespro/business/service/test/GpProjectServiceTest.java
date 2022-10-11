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
import af.cmr.indyli.gespro.business.dto.GpOrganizationBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpOrganizationFullDTO;
import af.cmr.indyli.gespro.business.dto.GpProjectBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpProjectFullDTO;
import af.cmr.indyli.gespro.business.dto.GpProjectManagerBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpProjectManagerFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.business.service.IGpProjectManagerService;
import af.cmr.indyli.gespro.business.service.IGpProjectService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GesproBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GpProjectServiceTest {
	@Resource(name = GesproConstantesService.GP_PROJECT_SERVICE_KEY)
	private IGpProjectService projectService;
	
	@Resource(name = GesproConstantesService.GP_ORGANIZATION_KEY)
	private IGpOrganizationService orgService;
	
	@Resource(name = GesproConstantesService.GP_PROJECTMANAGER_SERVICE_KEY)
	private IGpProjectManagerService proManService;

	private Integer projectIdForAllTest = null;
	private Integer createProjectId = null;
	
	private GpOrganizationBasicDTO orgForAllTest;
	private GpProjectManagerBasicDTO proManagerForAllTest;

	@Test
	public void createProjectId() throws GesproBusinessException {
		GpProjectFullDTO project = new GpProjectFullDTO();
		
		project.setCode("456");
		project.setName("Chirac");
		project.setDescription("Manger des pommes");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(10000);
		project.setCreationDate(new Date());
		project.setUpdateDate(null);
		project.setOrganization(orgForAllTest);
		project.setProjectmanager(proManagerForAllTest);
		
		project = projectService.create(project);
		System.out.println(project.getId());
		createProjectId = project.getId();
		System.out.println(createProjectId);
		Assert.assertNotNull(createProjectId);
	}

	@Test
	public void testFindAllProjectWithSuccess() {
		// Given
		// When
		List<GpProjectFullDTO> project = this.projectService.findAll();
		// Then
		Assert.assertTrue(project.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() throws GesproBusinessException {
		// Given
		Integer projectId = this.projectIdForAllTest;
		// When
		GpProjectFullDTO pro = this.projectService.findById(projectId);
		// Then
		Assert.assertTrue(pro.getId() == projectId);
	}

	@Test
	public void testUpdateProject() throws AccessDeniedException, GesproBusinessException {
		// Given
		GpProjectFullDTO project = this.projectService.findById(this.projectIdForAllTest);
		project.setCode("124");
		// When
		this.projectService.update(project);
		GpProjectFullDTO projectUpdate = this.projectService.findById(this.projectIdForAllTest);
		// Then

		Assert.assertTrue(projectUpdate.getCode() == "124");
	}

	@Test
	public void testDelete() throws AccessDeniedException, Exception {
		// Given
		Integer projectId = this.projectIdForAllTest;
		this.projectIdForAllTest = null;
		// Whens
		this.projectService.deleteById(projectId);
		GpProjectFullDTO project = this.projectService.findById(projectId);

		// Then
		Assert.assertNull(project);

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		
		GpOrganizationFullDTO organization = new GpOrganizationFullDTO();
		organization.setAdrWeb("website.com");
		organization.setContactEmail("contact@mail.com");
		organization.setContactName("CONTACT_NANE");
		organization.setName("ORG");
		organization.setOrgCode("OMC");
		organization.setPhoneNumber(1024);
		// when
		System.out.println(organization);

		organization = orgService.create(organization);
		//
		orgForAllTest=organization;
		GpProjectManagerFullDTO projectManager = new GpProjectManagerFullDTO();
		projectManager.setFileNumber("3100");
		projectManager.setLastname("Gestoa");
		projectManager.setFirstname("PRISZ");
		projectManager.setPhoneNumber(321);
		projectManager.setPassword("newPassword");
		projectManager.setEmail("gzstot766.prisz@gouv.fr");
		projectManager.setLogin("gzstot766.prisz");
		projectManager.setCreationDate(new Date());
		projectManager.setUpdateDate(new Date());

		projectManager = proManService.create(projectManager);
		//System.out.println(projectManager);
		proManagerForAllTest=projectManager;

		GpProjectFullDTO project = new GpProjectFullDTO();
		project.setCode("123");
		project.setName("Chirac");
		project.setDescription("Manger des pommes");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(10000);
		project.setCreationDate(new Date());
		project.setUpdateDate(null);
		project.setOrganization(orgForAllTest);
		project.setProjectmanager(proManagerForAllTest);
		project = projectService.create(project);
		projectIdForAllTest = project.getId();
		Assert.assertNotNull(project.getId());
	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, GesproBusinessException {
		if (this.projectIdForAllTest != null) {
			this.projectService.deleteById(this.projectIdForAllTest);
		}

		if (!Objects.isNull(this.createProjectId)) {
			this.projectService.deleteById(this.createProjectId);
		}
	}
}

