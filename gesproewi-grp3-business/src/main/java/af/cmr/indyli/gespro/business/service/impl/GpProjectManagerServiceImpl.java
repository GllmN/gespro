package af.cmr.indyli.gespro.business.service.impl;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import af.cmr.indyli.gespro.business.dao.IGpProjectManagerRepository;
import af.cmr.indyli.gespro.business.dto.GpProjectManagerBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpProjectManagerFullDTO;
import af.cmr.indyli.gespro.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;

import af.cmr.indyli.gespro.business.service.IGpProjectManagerService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;

@Service(GesproConstantesService.GP_PROJECTMANAGER_SERVICE_KEY)
public class GpProjectManagerServiceImpl implements IGpProjectManagerService{
	
	@Resource(name = "gespro-modelmapper")
	private ModelMapper mapper;

	public GpProjectManagerServiceImpl() {
		super();
	}
	
	@Resource(name = GesproConstantesDAO.GP_PROJECTMANAGER_DAO)
	private IGpProjectManagerRepository gpProjectManagerRepository;

	public ModelMapper getModelMapper() {
		return this.mapper;
	}
	
	public IGpProjectManagerRepository getDAO() {
        return this.gpProjectManagerRepository;
    }
	//create
	public GpProjectManagerFullDTO create(GpProjectManagerFullDTO gpProjectManagerFullDTO) throws GesproBusinessException {
		//RG_GP_01  
				if(StringUtils.isBlank(gpProjectManagerFullDTO.getEmail()) || StringUtils.isBlank(gpProjectManagerFullDTO.getLogin()) 
						|| StringUtils.isBlank(gpProjectManagerFullDTO.getLastname()) || StringUtils.isBlank(gpProjectManagerFullDTO.getFileNumber()))
					throw new GesproBusinessException("RG_GP_01 : Email ou Matricule ou Nom ou Login non renseigne");
		//RG_GP_02
				if(ifEntityExistByLogin(gpProjectManagerFullDTO.getLogin()) || ifEntityExistByPassword(gpProjectManagerFullDTO.getPassword()) 
						|| ifEntityExistByFileNumber(gpProjectManagerFullDTO.getFileNumber()))
					throw new GesproBusinessException("RG_GP_02 : Ce Login ou ce Mot de passe ou ce Matricule est déjà existant");
		GpProjectManager gpProjectManager = this.getModelMapper().map(gpProjectManagerFullDTO, GpProjectManager.class);
		this.gpProjectManagerRepository.save(gpProjectManager);
		gpProjectManagerFullDTO.setId(gpProjectManager.getId());
		return gpProjectManagerFullDTO;
	}
	//update
	public GpProjectManagerFullDTO update(GpProjectManagerFullDTO gpProjectManagerFullDTO) throws GesproBusinessException {
		// On va chercher l'entité pour l'attacher au contexte de persistence
		GpProjectManager gpProjectManager = this.getDAO().findById(gpProjectManagerFullDTO.getId()).orElse(null);
		if (gpProjectManager != null) {
			BeanUtils.copyProperties(gpProjectManagerFullDTO, gpProjectManager);
			gpProjectManager = this.getDAO().saveAndFlush(gpProjectManager);
		} else {
			throw new GesproBusinessException("L'objet A modifier N'existe pas en Base...");
		}
		return gpProjectManagerFullDTO;
	}

	//deleteById
	public void deleteById(int id) throws GesproBusinessException, AccessDeniedException {
		this.getDAO().deleteById(id);
	}
	
	//findAll
	public List<GpProjectManagerBasicDTO> findAll() {
		List<GpProjectManager> list = this.getDAO().findAll();
		List<GpProjectManagerBasicDTO> listBasicDTO = new ArrayList<GpProjectManagerBasicDTO>();
		for (GpProjectManager gpProjectManager : list) {
			GpProjectManagerBasicDTO gpProjectManagerBasicDTO = this.getModelMapper().map(gpProjectManager, GpProjectManagerBasicDTO.class);
			listBasicDTO.add(gpProjectManagerBasicDTO);
		}
		return listBasicDTO;
	}
	
	//findById
   public GpProjectManagerFullDTO findById(int id) throws GesproBusinessException {
		GpProjectManager gpProjectManager = this.getDAO().findById(id).orElse(null);
		if (gpProjectManager != null) {

			GpProjectManagerFullDTO view = this.getModelMapper().map(gpProjectManager, GpProjectManagerFullDTO.class);
			return view;
		} else {
			return null;
		}
	}
	
	//test sur existence entité par son id
   public boolean ifEntityExistById(int id) throws GesproBusinessException {
		return this.getDAO().existsById(id);
	}
   
   public boolean ifEntityExistByLogin(String login) throws GesproBusinessException {
	   List<GpProjectManager> list = this.getDAO().findAll();
	   for (GpProjectManager i : list) {
		if (i.getLogin() == login) {
			return true;
		}	
	}
	   return false;
	}
   public boolean ifEntityExistByPassword(String pwd) throws GesproBusinessException {
	   List<GpProjectManager> list = this.getDAO().findAll();
	   for (GpProjectManager i : list) {
		if (i.getPassword() == pwd) {
			return true;
		}	
	}
	   return false;
	}
   public boolean ifEntityExistByFileNumber(String FileNumber) throws GesproBusinessException {
	   List<GpProjectManager> list = this.getDAO().findAll();
	   for (GpProjectManager i : list) {
		if (i.getFileNumber() == FileNumber) {
			return true;
		}	
	}
	   return false;
	}
}
