package af.cmr.indyli.gespro.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import af.cmr.indyli.gespro.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;

@Repository(GesproConstantesDAO.GP_PROJECTMANAGER_DAO)
public interface IGpProjectManagerRepository extends JpaRepository<GpProjectManager, Integer> {
	//boolean existsByFileNumberOrEmailOrLogin(String fileNumber, String email, String login);
}
	


