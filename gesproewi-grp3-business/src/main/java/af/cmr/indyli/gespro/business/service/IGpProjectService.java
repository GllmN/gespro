package af.cmr.indyli.gespro.business.service;

import java.nio.file.AccessDeniedException;
import java.util.List;

import af.cmr.indyli.gespro.business.dto.GpProjectBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpProjectFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;

public interface IGpProjectService {
	
	public GpProjectFullDTO create(GpProjectFullDTO gpProjectFullDTO) throws GesproBusinessException;

	public GpProjectFullDTO update(GpProjectFullDTO gpProjectFullDTO) throws GesproBusinessException;

	public void deleteById(int id) throws GesproBusinessException, AccessDeniedException;

	public List<GpProjectFullDTO> findAll();

	public GpProjectFullDTO findById(int id) throws GesproBusinessException;
}
