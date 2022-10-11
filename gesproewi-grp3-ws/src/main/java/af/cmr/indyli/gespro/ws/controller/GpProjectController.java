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

import af.cmr.indyli.gespro.business.dto.GpProjectBasicDTO;
import af.cmr.indyli.gespro.business.dto.GpProjectFullDTO;
import af.cmr.indyli.gespro.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.business.service.IGpProjectService;
import af.cmr.indyli.gespro.business.utils.GesproConstantes.GesproConstantesService;
import af.cmr.indyli.gespro.ws.urlbase.GesproUrlBase;

@CrossOrigin(origins = GesproUrlBase.url, maxAge = GesproUrlBase.maxAge)
@RestController
@RequestMapping("/projects")
public class GpProjectController {
	@Resource(name = GesproConstantesService.GP_PROJECT_SERVICE_KEY)
	private IGpProjectService gpProjectService;

	/**
	 * Create GpProject in the database
	 * 
	 **/
	@PostMapping
	public ResponseEntity<?> createProject(@Valid @RequestBody GpProjectFullDTO gpProject)
			throws GesproBusinessException {

		return ResponseEntity.ok(gpProjectService.create(gpProject));
	}
	/**
	 * return the list of all address
	 * 
	 **/
	@GetMapping
	public List<GpProjectFullDTO> getAllAdress() {

		return gpProjectService.findAll();
}
	/**
	 * return Project by id
	 * 
	 * @throws GesproBusinessException
	 **/
	@GetMapping("/{id}")
	public GpProjectFullDTO getOneProject(@PathVariable(value = "id") int id) throws GesproBusinessException {
		return this.gpProjectService.findById(id);
	}
	/**
	 * modify a given Employee in the database
	 * 
	 * @throws GesproBusinessException
	 * @throws AccessDeniedException
	 */
	@PutMapping("/{id}")
	public ResponseEntity<GpProjectBasicDTO> getUpdateGpProject(@PathVariable(value = "id") int idGpProject,
			@Valid @RequestBody GpProjectFullDTO projectDetails)
			throws GesproBusinessException, AccessDeniedException {

		GpProjectFullDTO projectFull = gpProjectService.findById(idGpProject);
		if (projectFull == null) {
			return ResponseEntity.notFound().build();
		}

		GpProjectBasicDTO updateProject = gpProjectService.update(projectDetails);
		return ResponseEntity.ok().body(updateProject);
	}
	/**
	 * Delete a given address in the database
	 * 
	 * @throws GesproBusinessException
	 * @throws AccessDeniedException
	 **/
	@DeleteMapping("/{id}")
	public ResponseEntity<GpProjectBasicDTO> deleteProject(@PathVariable(value = "id") int idProject)
			throws AccessDeniedException, GesproBusinessException {
		gpProjectService.deleteById(idProject);
		return ResponseEntity.ok().build();

	}
	}

