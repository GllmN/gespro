package af.cmr.indyli.gespro.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import af.cmr.indyli.gespro.business.entity.GpProject;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;

@Repository(GesproConstantesDAO.GP_PROJECT_DAO)
public interface IGpProjectRepository extends JpaRepository<GpProject, Integer> {
}
