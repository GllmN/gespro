package af.cmr.indyli.gespro.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import af.cmr.indyli.gespro.business.entity.GpEmployee;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;

@Repository(GesproConstantesDAO.GP_EMPLOYEE_DAO)
public interface IGpEmployeeRepository extends JpaRepository<GpEmployee, Integer> {
}
