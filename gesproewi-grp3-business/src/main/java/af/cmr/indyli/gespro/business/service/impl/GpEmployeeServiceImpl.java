package af.cmr.indyli.gespro.business.service.impl;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import af.cmr.indyli.gespro.business.dao.IGpEmployeeRepository;
import af.cmr.indyli.gespro.business.dto.GpEmployeeBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpEmployeeFullDTO;
import af.cmr.indyli.gespro.business.entity.GpEmployee;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesDAO;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;

@Service(GesproConstantesService.GP_EMPLOYEE_SERVICE_KEY)
public class GpEmployeeServiceImpl implements IGpEmployeeService {

	@Resource(name = "gespro-modelmapper")
	private ModelMapper mapper;

	public GpEmployeeServiceImpl() {
		super();
	}

	@Resource(name = GesproConstantesDAO.GP_EMPLOYEE_DAO)
	private IGpEmployeeRepository gpEmployeeRepository;

	public ModelMapper getModelMapper() {
		return this.mapper;
	}

	public GpEmployeeFullDTO create(GpEmployeeFullDTO emp) throws GesproBusinessException {
		//RG_GP_01  
				if(StringUtils.isBlank(emp.getEmail()) || StringUtils.isBlank(emp.getLogin()) 
						|| StringUtils.isBlank(emp.getLastname()) || StringUtils.isBlank(emp.getFileNumber()))
					throw new GesproBusinessException("RG_GP_01 : Email ou Matricule ou Nom ou Login non renseigne");
		GpEmployee gpEmployee = this.getModelMapper().map(emp, GpEmployee.class);
		this.gpEmployeeRepository.save(gpEmployee);
		emp.setId(gpEmployee.getId());
		return emp;
	}

	public GpEmployeeFullDTO update(GpEmployeeFullDTO gpEmployeeFullDTO) throws GesproBusinessException {
		// On va chercher l'entité pour l'attacher au contexte de persistence
		GpEmployee gpEmployee = this.gpEmployeeRepository.findById(gpEmployeeFullDTO.getId()).orElse(null);
		if (gpEmployee != null) {
			BeanUtils.copyProperties(gpEmployeeFullDTO, gpEmployee);
			gpEmployee = this.gpEmployeeRepository.saveAndFlush(gpEmployee);
		} else {
			throw new GesproBusinessException("L'objet A modifier N'existe pas en Base...");
		}
		return gpEmployeeFullDTO;
	}

	public void deleteById(int id) throws GesproBusinessException, AccessDeniedException {
		this.gpEmployeeRepository.deleteById(id);
	}

	/**
	 * Find All Entity
	 * 
	 * @return
	 */
	public List<GpEmployeeBasicDTO> findAll() {
		List<GpEmployee> list = this.gpEmployeeRepository.findAll();
		List<GpEmployeeBasicDTO> listBasicDTO = new ArrayList<GpEmployeeBasicDTO>();
		for (GpEmployee gpEmployee : list) {
			GpEmployeeBasicDTO gpEmployeeBasicDTO = this.getModelMapper().map(gpEmployee, GpEmployeeBasicDTO.class);
			listBasicDTO.add(gpEmployeeBasicDTO);
		}
		return listBasicDTO;
	}

	public GpEmployeeFullDTO findById(int id) throws GesproBusinessException {
		GpEmployee gpEmployee = this.gpEmployeeRepository.findById(id).orElse(null);
		if (gpEmployee != null) {

			GpEmployeeFullDTO view = this.getModelMapper().map(gpEmployee, GpEmployeeFullDTO.class);
			return view;
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
		return this.gpEmployeeRepository.existsById(id);
	}

}
