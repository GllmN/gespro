package af.cmr.indyli.gespro.business.service;

import java.nio.file.AccessDeniedException;
import java.util.List;

import af.cmr.indyli.gespro.business.dto.GpEmployeeBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpEmployeeFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;

public interface IGpEmployeeService {

	public GpEmployeeFullDTO create(GpEmployeeFullDTO gpEmployeeFullDTO) throws GesproBusinessException;

	public GpEmployeeFullDTO update(GpEmployeeFullDTO gpEmployeeFullDTO) throws GesproBusinessException;

	public void deleteById(int id) throws GesproBusinessException, AccessDeniedException;

	public List<GpEmployeeBasicDTO> findAll();

	public GpEmployeeFullDTO findById(int id) throws GesproBusinessException;

}
