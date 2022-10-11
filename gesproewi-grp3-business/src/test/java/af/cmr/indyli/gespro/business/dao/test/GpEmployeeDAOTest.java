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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import af.cmr.indyli.gespro.business.config.GesproBusinessConfig;
import af.cmr.indyli.gespro.business.dao.IGpEmployeeRepository;
import af.cmr.indyli.gespro.business.entity.GpEmployee;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GesproBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GpEmployeeDAOTest {

	@Resource(name = GesproConstantesDAO.GP_EMPLOYEE_DAO)
	private IGpEmployeeRepository gpEmployeeRepository;

	private Integer empIdForAllTest = 25;
	private Integer createEmpId = null;

	@Test
	@Rollback()
	public void createEmp() {
		GpEmployee emp = new GpEmployee();
		emp.setFileNumber("3001");
		emp.setLastname("Gesto");
		emp.setFirstname("PRISZ");
		emp.setPhoneNumber(321);
		emp.setPassword("newPassword");
		emp.setEmail("gzstot66.prisz@gouv.fr");
		emp.setLogin("gzstot66.prisz");
		emp.setCreationDate(new Date());
		emp.setUpdateDate(new Date());
		emp = gpEmployeeRepository.save(emp);
		this.createEmpId = emp.getId();
	}

	@Test
	public void testFindAllEmployeeWithSuccess() {
		// Given
		// When
		List<GpEmployee> emps = this.gpEmployeeRepository.findAll();
		// Then
		Assert.assertTrue(emps.size() > 0);
	}

	@Test
	@Rollback(false)
	public void testFindByIdWithSuccess() throws GesproBusinessException {
		// Given
		Integer empId = this.empIdForAllTest;
		// When
		GpEmployee emp = this.gpEmployeeRepository.findById(empId).orElse(null);
		// Then
		Assert.assertTrue(emp.getId() == empId);
	}

	@Test
	@Rollback(false)
	public void testUpdateEmployee() throws AccessDeniedException, GesproBusinessException {
		// Given
		GpEmployee emp = this.gpEmployeeRepository.findById(this.empIdForAllTest).orElse(null);
		emp.setPhoneNumber(3221);
		// When
		this.gpEmployeeRepository.saveAndFlush(emp);
		GpEmployee empUpdate = this.gpEmployeeRepository.findById(this.empIdForAllTest).orElse(null);
		// Then

		Assert.assertTrue(empUpdate.getPhoneNumber() == 3221);
	}

	@Test
	public void testDelete() throws AccessDeniedException, Exception {
		// Given
		Integer empId = this.empIdForAllTest;
		this.empIdForAllTest = null;
		// Whens
		this.gpEmployeeRepository.deleteById(empId);
		GpEmployee emp = this.gpEmployeeRepository.findById(empId).orElse(null);

		// Then
		Assert.assertNull(emp);

	}

	@Before
	@Rollback(false)
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpEmployee emp = new GpEmployee();
		emp.setFileNumber("2001");
		emp.setLastname("Laurent");
		emp.setFirstname("FABIUS");
		emp.setPhoneNumber(123);
		emp.setPassword("myThirdPassword");
		emp.setEmail("laurent.fabius@gouv.fr");
		emp.setLogin("laurent.fabius");
		emp.setCreationDate(new Date());
		emp.setUpdateDate(new Date());
		emp = gpEmployeeRepository.save(emp);
		this.empIdForAllTest = emp.getId();
	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, GesproBusinessException {
		if (this.empIdForAllTest != null) {
			this.gpEmployeeRepository.deleteById(this.empIdForAllTest);
		}

		if (!Objects.isNull(this.createEmpId)) {
			this.gpEmployeeRepository.deleteById(this.createEmpId);
		}
	}
}
