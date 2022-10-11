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
import af.cmr.indyli.gespro.business.dto.GpEmployeeBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpEmployeeFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GesproBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GpEmployeeServiceTest {

	@Resource(name = GesproConstantesService.GP_EMPLOYEE_SERVICE_KEY)
	private IGpEmployeeService empService;

	private Integer empIdForAllTest = null;
	private Integer createEmpId = null;

	@Test
	public void createEmpId() throws GesproBusinessException {
		GpEmployeeFullDTO emp = new GpEmployeeFullDTO();
		emp.setFileNumber("3001");
		emp.setLastname("Gesto");
		emp.setFirstname("PRISZ");
		emp.setPhoneNumber(321);
		emp.setPassword("newPassword");
		emp.setEmail("gzstot.prisz@gouv.fr");
		emp.setLogin("gzstot.prisz");
		emp.setCreationDate(new Date());
		emp.setUpdateDate(new Date());
		emp = empService.create(emp);
		this.createEmpId = emp.getId();
		Assert.assertNotNull(createEmpId);
	}

	@Test
	public void testFindAllEmployeeWithSuccess() {
		// Given
		// When
		List<GpEmployeeBasicDTO> emps = this.empService.findAll();
		// Then
		Assert.assertTrue(emps.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() throws GesproBusinessException {
		// Given
		Integer empId = this.empIdForAllTest;
		// When
		GpEmployeeFullDTO emp = this.empService.findById(empId);
		// Then
		Assert.assertTrue(emp.getId() == empId);
	}

	@Test
	public void testUpdateEmployee() throws AccessDeniedException, GesproBusinessException {
		// Given
		GpEmployeeFullDTO emp = this.empService.findById(this.empIdForAllTest);
		emp.setPhoneNumber(3221);
		// When
		this.empService.update(emp);
		GpEmployeeFullDTO empUpdate = this.empService.findById(this.empIdForAllTest);
		// Then
		Assert.assertTrue(empUpdate.getPhoneNumber() == 3221);
	}

	@Test
	public void testDelete() throws AccessDeniedException, Exception {
		// Given
		Integer empId = this.empIdForAllTest;
		this.empIdForAllTest = null;
		// Whens
		this.empService.deleteById(empId);
		GpEmployeeFullDTO emp = this.empService.findById(empId);

		// Then
		Assert.assertNull(emp);

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpEmployeeFullDTO emp = new GpEmployeeFullDTO();
		emp.setFileNumber("2001");
		emp.setLastname("Laurent");
		emp.setFirstname("FABIUS");
		emp.setPhoneNumber(123);
		emp.setPassword("myThirdPassword");
		emp.setEmail("laurent.fabius@gouv.fr");
		emp.setLogin("laurent.fabius");
		emp.setCreationDate(new Date());
		emp.setUpdateDate(new Date());
		emp = empService.create(emp);
		this.empIdForAllTest = emp.getId();
	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, GesproBusinessException {
		if (this.empIdForAllTest != null) {
			this.empService.deleteById(this.empIdForAllTest);
		}

		if (!Objects.isNull(this.createEmpId)) {
			this.empService.deleteById(this.createEmpId);
		}
	}
}

		
