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

import af.cmr.indyli.gespro.business.dto.GpProjectManagerBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpProjectManagerFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpProjectManagerService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;
import af.cmr.indyli.gespro.ws.urlbase.GesproUrlBase;

@CrossOrigin(origins = GesproUrlBase.url, maxAge = GesproUrlBase.maxAge)
@RestController
@RequestMapping("/project-managers")
public class GpProjectManagerController {

	@Resource(name = GesproConstantesService.GP_PROJECTMANAGER_SERVICE_KEY)
	private IGpProjectManagerService gpProjectManagerService;

	/**
	 * Create GpProjectManager in the database
	 * 
	 **/
	@PostMapping
	public ResponseEntity<?> createProjectManager(@Valid @RequestBody GpProjectManagerFullDTO gpProjectManager)
			throws GesproBusinessException {

		return ResponseEntity.ok(gpProjectManagerService.create(gpProjectManager));
	}

	/**
	 * return the list of all address
	 * 
	 **/
	@GetMapping
	public List<GpProjectManagerBasicDTO> getAllAdress() {

		return gpProjectManagerService.findAll();
	}

	/**
	 * return ProjectManager by id
	 * 
	 * @throws GesproBusinessException
	 **/
	@GetMapping("/{id}")
	public GpProjectManagerFullDTO getOneProjectManager(@PathVariable(value = "id") int id) throws GesproBusinessException {
		return this.gpProjectManagerService.findById(id);
	}
	/**
	 * modify a given ProjectManager in the database
	 * 
	 * @throws GesproBusinessException
	 * @throws AccessDeniedException
	 */
	@PutMapping("/{id}")
	public ResponseEntity<GpProjectManagerBasicDTO> getUpdateGpProjectManager(@PathVariable(value = "id") int idGpProjectManager,
			@Valid @RequestBody GpProjectManagerFullDTO projectManagerDetails)
				throws GesproBusinessException, AccessDeniedException {

		GpProjectManagerFullDTO empFull = gpProjectManagerService.findById(idGpProjectManager);
		if (empFull == null) {
			return ResponseEntity.notFound().build();
		}

		GpProjectManagerBasicDTO updateProMan = gpProjectManagerService.update(projectManagerDetails);
		return ResponseEntity.ok().body(updateProMan);
	
	}
	/**
	 * Delete a given address in the database
	 * 
	 * @throws GesproBusinessException
	 * @throws AccessDeniedException
	 **/
	@DeleteMapping("/{id}")
	public ResponseEntity<GpProjectManagerBasicDTO> deleteProjectManager(@PathVariable(value = "id") int idProjectManager)
			throws AccessDeniedException, GesproBusinessException {
		gpProjectManagerService.deleteById(idProjectManager);
		return ResponseEntity.ok().build();

	}
}

