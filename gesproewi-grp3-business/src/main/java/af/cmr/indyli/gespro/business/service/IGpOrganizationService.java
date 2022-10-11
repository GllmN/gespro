package af.cmr.indyli.gespro.business.service;

import java.nio.file.AccessDeniedException;
import java.util.List;

import af.cmr.indyli.gespro.business.dto.GpOrganizationBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpOrganizationFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;

public interface IGpOrganizationService {

	public GpOrganizationFullDTO create(GpOrganizationFullDTO gpOrganizationFullDTO) throws GesproBusinessException;

	public GpOrganizationFullDTO update(GpOrganizationFullDTO gpOrganizationFullDTO) throws GesproBusinessException, AccessDeniedException;

	public void deleteById(int id) throws GesproBusinessException, AccessDeniedException;

	public List<GpOrganizationBasicDTO> findAll();

	public GpOrganizationFullDTO findById(int id) throws GesproBusinessException;

	public boolean ifEntityExistById(int id) throws GesproBusinessException;
}
