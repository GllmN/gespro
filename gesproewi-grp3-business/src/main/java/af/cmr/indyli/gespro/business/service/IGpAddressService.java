package af.cmr.indyli.gespro.business.service;

import java.nio.file.AccessDeniedException;
import java.util.List;

import af.cmr.indyli.gespro.business.dto.GpAddressBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpAddressFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;

public interface IGpAddressService {

	GpAddressBasicDTO updateAddress(GpAddressFullDTO addressDetails);

	GpAddressFullDTO create(GpAddressFullDTO gpAddressFullDTO) throws GesproBusinessException;

	public GpAddressFullDTO update(GpAddressFullDTO gpAddressFullDTO)
			throws GesproBusinessException, AccessDeniedException;

	void deleteById(int id) throws GesproBusinessException, AccessDeniedException;

	List<GpAddressBasicDTO> findAll();

	public GpAddressFullDTO findById(int id) throws GesproBusinessException;

	boolean ifEntityExistById(int id) throws GesproBusinessException;

}
