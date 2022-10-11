package af.cmr.indyli.gespro.business.service.impl;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import af.cmr.indyli.gespro.business.dao.IGpOrganizationRepository;
import af.cmr.indyli.gespro.business.dto.GpOrganizationBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpOrganizationFullDTO;
import af.cmr.indyli.gespro.business.entity.GpOrganization;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;

@Service(GesproConstantesService.GP_ORGANIZATION_KEY)
public class GpOrganizationServiceImpl implements IGpOrganizationService {
	@Resource(name = "gespro-modelmapper")
	private ModelMapper mapper;
	@Resource(name = GesproConstantesDAO.GP_ORGANIZATION_DAO)
	private IGpOrganizationRepository gpOrganizationRepository;

	public GpOrganizationServiceImpl() {
		super();

	}

	public ModelMapper getModelMapper() {
		return this.mapper;
	}

	public IGpOrganizationRepository getDAO() {
		return this.gpOrganizationRepository;
	}

	public GpOrganizationFullDTO create(GpOrganizationFullDTO gpOrganizationFullDTO) throws GesproBusinessException {
		GpOrganization gpOrganization = this.getModelMapper().map(gpOrganizationFullDTO, GpOrganization.class);
		this.getDAO().save(gpOrganization);
		gpOrganizationFullDTO.setId(gpOrganization.getId());
		return gpOrganizationFullDTO;
	}

	public GpOrganizationFullDTO update(GpOrganizationFullDTO gpOrganizationFullDTO)
			throws GesproBusinessException, AccessDeniedException {
		// On va chercher l'entité pour l'attacher au contexte de persistence
		GpOrganization gpOrganization = this.getDAO().findById(gpOrganizationFullDTO.getId()).orElse(null);
		if (gpOrganization != null) {
			BeanUtils.copyProperties(gpOrganizationFullDTO, gpOrganization);
			gpOrganization = this.getDAO().saveAndFlush(gpOrganization);
		} else {
			throw new GesproBusinessException("L'objet A modifier N'existe pas en Base...");
		}
		return gpOrganizationFullDTO;
	}

	public void deleteById(int id) throws GesproBusinessException, AccessDeniedException {
		this.getDAO().deleteById(id);

	}

	public List<GpOrganizationBasicDTO> findAll() {
		List<GpOrganization> listGpOrganization = this.getDAO().findAll();
		List<GpOrganizationBasicDTO> listBasicDTO = new ArrayList<GpOrganizationBasicDTO>();
		for (GpOrganization ent : listGpOrganization) {
			GpOrganizationBasicDTO view = this.getModelMapper().map(ent, GpOrganizationBasicDTO.class);
			listBasicDTO.add(view);
		}
		return listBasicDTO;
	}

	public GpOrganizationFullDTO findById(int id) throws GesproBusinessException {
		GpOrganization gpOrganization = this.getDAO().findById(id).orElse(null);
		if (gpOrganization != null) {

			GpOrganizationFullDTO gpOrganizationFullDTO = this.getModelMapper().map(gpOrganization,
					GpOrganizationFullDTO.class);
			return gpOrganizationFullDTO;
		} else {
			return null;
		}
	}

	/**
	 * Teste l'existence d'une Entité par son id
	 * 
	 * @param id
	 * @return
	 * @throws GesproBusinessException
	 */
	public boolean ifEntityExistById(int id) throws GesproBusinessException {
		return this.getDAO().existsById(id);
	}

}
