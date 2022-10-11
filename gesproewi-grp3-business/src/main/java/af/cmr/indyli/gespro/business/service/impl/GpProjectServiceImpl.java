package af.cmr.indyli.gespro.business.service.impl;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import af.cmr.indyli.gespro.business.dao.IGpProjectRepository;
import af.cmr.indyli.gespro.business.dto.GpProjectBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpProjectFullDTO;
import af.cmr.indyli.gespro.business.entity.GpProject;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpProjectService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;

@Service(GesproConstantesService.GP_PROJECT_SERVICE_KEY)
public class GpProjectServiceImpl implements IGpProjectService{

	@Resource(name = "gespro-modelmapper")
	private ModelMapper mapper;

	public GpProjectServiceImpl() {
		super();
	}
	
	@Resource(name = GesproConstantesDAO.GP_PROJECT_DAO)
	private IGpProjectRepository gpProjectRepository;

	public ModelMapper getModelMapper() {
		return this.mapper;
	}
	
	public IGpProjectRepository getDAO() {
        return this.gpProjectRepository;
    }

	//create
   public GpProjectFullDTO create(GpProjectFullDTO gpProjectFullDTO) throws GesproBusinessException {
		//RG_GP_03  
				if(StringUtils.isBlank(gpProjectFullDTO.getProjectmanager().getEmail()) || StringUtils.isBlank(gpProjectFullDTO.getProjectmanager().getLogin()) 
						|| StringUtils.isBlank(gpProjectFullDTO.getProjectmanager().getLastname()) || StringUtils.isBlank(gpProjectFullDTO.getProjectmanager().getFileNumber()))
					throw new GesproBusinessException("RG_GP_03 : Email ou Matricule ou Nom ou Login du Chef de projet est non renseigne");
		//RG_GP_04
				if(StringUtils.isBlank(gpProjectFullDTO.getCode()))
					throw new GesproBusinessException("RG_GP_04: Un code est obligatoire") ;
				if( ifEntityExistByCode(gpProjectFullDTO.getCode()))
					throw new GesproBusinessException("RG_GP_04: Ce Code est déjà existant") ;
		//RG_GP_05
				if(gpProjectFullDTO.getStartDate().after(gpProjectFullDTO.getEndDate()))
					throw new GesproBusinessException("RG_GP_05: La date de fin ne peut être anterieur à la date du début");
		GpProject gpProject = this.getModelMapper().map(gpProjectFullDTO, GpProject.class);
		this.gpProjectRepository.save(gpProject);
		gpProjectFullDTO.setId(gpProject.getId());
		return gpProjectFullDTO;
	}
	//update
   public GpProjectFullDTO update(GpProjectFullDTO gpProjectFullDTO) throws GesproBusinessException {
		// On va chercher l'entité pour l'attacher au contexte de persistence
		GpProject gpProject = this.getDAO().findById(gpProjectFullDTO.getId()).orElse(null);
		if (gpProject != null) {
			BeanUtils.copyProperties(gpProjectFullDTO, gpProject);
			gpProject = this.getDAO().saveAndFlush(gpProject);
		} else {
			throw new GesproBusinessException("L'objet A modifier N'existe pas en Base...");
		}
		return gpProjectFullDTO;
	}

	
	//deleteById
   public void deleteById(int id) throws GesproBusinessException, AccessDeniedException {
		this.getDAO().deleteById(id);
	}
	
	//findAll
   public List<GpProjectFullDTO> findAll() {
		List<GpProject> list = this.getDAO().findAll();
		List<GpProjectFullDTO> listBasicDTO = new ArrayList<GpProjectFullDTO>();
		for (GpProject gpProject : list) {
			GpProjectFullDTO gpProjectBasicDTO = this.getModelMapper().map(gpProject, GpProjectFullDTO.class);
			listBasicDTO.add(gpProjectBasicDTO);
		}
		return listBasicDTO;
	}
	
	//findById
   public GpProjectFullDTO findById(int id) throws GesproBusinessException {
		GpProject gpProject = this.getDAO().findById(id).orElse(null);
		if (gpProject != null) {

			GpProjectFullDTO view = this.getModelMapper().map(gpProject, GpProjectFullDTO.class);
			return view;
		} else {
			return null;
		}
	}
	
	//test sur existence entité par son id
   public boolean ifEntityExistById(int id) throws GesproBusinessException {
		return this.getDAO().existsById(id);
	}
   
   public boolean ifEntityExistByCode(String Code) throws GesproBusinessException {
	   List<GpProject> list = this.getDAO().findAll();
	   for (GpProject i : list) {
		if (i.getCode() == Code) {
			return true;
		}	
	}
	   return false;
	}
}
