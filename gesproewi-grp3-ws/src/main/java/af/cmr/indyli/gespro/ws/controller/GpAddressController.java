package af.cmr.indyli.gespro.ws.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.gespro.business.dto.GpAddressBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpAddressFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpAddressService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;
import af.cmr.indyli.gespro.ws.urlbase.GesproUrlBase;

@CrossOrigin(origins = GesproUrlBase.url, maxAge = GesproUrlBase.maxAge)
@RestController
@RequestMapping("/address")
public class GpAddressController {

	@Resource(name = GesproConstantesService.GP_ADDRESS_SERVICE_KEY)
	private IGpAddressService gpAddressService;

	/**
	 * Create GpAddress in the database
	 * 
	 **/
	@PostMapping
	public ResponseEntity<?> createAddress(@Valid @RequestBody GpAddressFullDTO gpAddress)
			throws GesproBusinessException {

		return ResponseEntity.ok(gpAddressService.create(gpAddress));
	}

	/***
	 * return the list of all address
	 * 
	 **/
	@GetMapping
	public List<GpAddressBasicDTO> getAllAdress() {

		return gpAddressService.findAll();
	}

	/**
	 * return Address by id
	 * 
	 * @throws GesproBusinessException
	 **/
	@GetMapping("/{id}")
	public GpAddressFullDTO getOneAddress(@PathVariable(value = "id") int id) throws GesproBusinessException {
		return this.gpAddressService.findById(id);
	}

	/**
	 * modify a given Address in the database
	 * 
	 * @throws GesproBusinessException
	 * @throws AccessDeniedException
	 */

	@PutMapping("/{id}")
	public ResponseEntity<GpAddressBasicDTO> getUpdateGpAddress(@PathVariable(value = "id") int idGpAddress,
			@Valid @RequestBody GpAddressFullDTO addressDetails) throws GesproBusinessException, AccessDeniedException {

		GpAddressFullDTO addrFull = gpAddressService.findById(idGpAddress);
		if (addrFull == null) {
			return ResponseEntity.notFound().build();
		}

		GpAddressBasicDTO updateAddr = gpAddressService.updateAddress(addressDetails);
		return ResponseEntity.ok().body(updateAddr);
	}

	/**
	 * Delete a given address in the database
	 * 
	 * @throws GesproBusinessException
	 * @throws AccessDeniedException
	 **/
	@DeleteMapping("/{id}")
	public ResponseEntity<GpAddressBasicDTO> deleteAddress(@PathVariable(value = "id") int idAddress)
			throws AccessDeniedException, GesproBusinessException {
		gpAddressService.deleteById(idAddress);
		return ResponseEntity.ok().build();

	}
}
