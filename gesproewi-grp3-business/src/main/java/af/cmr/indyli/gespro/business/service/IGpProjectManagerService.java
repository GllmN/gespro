package af.cmr.indyli.gespro.business.service;

import java.nio.file.AccessDeniedException;
import java.util.List;

import af.cmr.indyli.gespro.business.dto.GpProjectManagerBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpProjectManagerFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;

public interface IGpProjectManagerService {
	
	public GpProjectManagerFullDTO create(GpProjectManagerFullDTO gpProjectFullDTO) throws GesproBusinessException;

	public GpProjectManagerFullDTO update(GpProjectManagerFullDTO gpEmployeeFullDTO) throws GesproBusinessException;

	public void deleteById(int id) throws GesproBusinessException, AccessDeniedException;

	public List<GpProjectManagerBasicDTO> findAll();

	public GpProjectManagerFullDTO findById(int id) throws GesproBusinessException;
	
}
