package af.cmr.indyli.gespro.business.service.impl;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import af.cmr.indyli.gespro.business.dao.IGpAddressRepository;
import af.cmr.indyli.gespro.business.dto.GpAddressBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpAddressFullDTO;
import af.cmr.indyli.gespro.business.entity.GpAddress;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpAddressService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;

@Service(GesproConstantesService.GP_ADDRESS_SERVICE_KEY)
public class GpAddressServiceImpl implements IGpAddressService {
	@Resource(name = "gespro-modelmapper")
	private ModelMapper mapper;

	public GpAddressServiceImpl() {
		super();
	}

	@Resource(name = GesproConstantesDAO.GP_ADDRESS_DAO)
	private IGpAddressRepository gpAddressRepository;

	public IGpAddressRepository getDAO() {
		return this.gpAddressRepository;
	}

	public ModelMapper getModelMapper() {
		return this.mapper;
	}

	public GpAddressBasicDTO updateAddress(GpAddressFullDTO addressDetails) {
		GpAddress addr = this.getModelMapper().map(addressDetails, GpAddress.class);
		addr = this.gpAddressRepository.saveAndFlush(addr);
		return this.getModelMapper().map(addr, GpAddressBasicDTO.class);
	}

	public GpAddressFullDTO create(GpAddressFullDTO gpAddressFullDTO) throws GesproBusinessException {
		GpAddress gpAddress = this.getModelMapper().map(gpAddressFullDTO, GpAddress.class);
		this.getDAO().save(gpAddress);
		gpAddressFullDTO.setId(gpAddress.getId());
		return gpAddressFullDTO;
	}

	public GpAddressFullDTO update(GpAddressFullDTO gpAddressFullDTO)
			throws GesproBusinessException, AccessDeniedException {
		// On va chercher l'entité pour l'attacher au contexte de persistence
		GpAddress gpAddress = this.getDAO().findById(gpAddressFullDTO.getId()).orElse(null);
		if (gpAddress != null) {
			BeanUtils.copyProperties(gpAddressFullDTO, gpAddress);
			gpAddress = this.getDAO().saveAndFlush(gpAddress);
		} else {
			throw new GesproBusinessException("L'objet A modifier N'existe pas en Base...");
		}
		return gpAddressFullDTO;
	}

	public void deleteById(int id) throws GesproBusinessException, AccessDeniedException {
		this.getDAO().deleteById(id);

	}

	public List<GpAddressBasicDTO> findAll() {
		List<GpAddress> list = this.getDAO().findAll();
		List<GpAddressBasicDTO> viewList = new ArrayList<GpAddressBasicDTO>();
		for (GpAddress gpAddress : list) {
			GpAddressBasicDTO gpAddressBasicDTO = this.getModelMapper().map(gpAddress, GpAddressBasicDTO.class);
			viewList.add(gpAddressBasicDTO);
		}
		return viewList;
	}

	/**
	 * Found Entity By Id
	 * 
	 * @param id : Entity's Id to found
	 * @return
	 * @throws GesproBusinessException
	 */
	public GpAddressFullDTO findById(int id) throws GesproBusinessException {

		GpAddress gpAddress = this.getDAO().findById(id).orElse(null);
		if (gpAddress != null) {

			GpAddressFullDTO gpAddressFullDTO = this.getModelMapper().map(gpAddress, GpAddressFullDTO.class);
			return gpAddressFullDTO;
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
