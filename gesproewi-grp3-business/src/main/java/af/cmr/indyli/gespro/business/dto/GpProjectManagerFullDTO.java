package af.cmr.indyli.gespro.business.dto;

import java.util.Date;
import java.util.List;

public class GpProjectManagerFullDTO extends GpProjectManagerMediumDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<GpProjectBasicDTO> gpProject;
	
	public GpProjectManagerFullDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GpProjectManagerFullDTO(Date creationDate, String email, String fileNumber, String firstname,
			String lastname, String login, String password, int phoneNumber, Date updateDate) {
		super(creationDate, email, fileNumber, firstname, lastname, login, password, phoneNumber, updateDate);
		// TODO Auto-generated constructor stub
	}
	
	public GpProjectManagerFullDTO(int id, Date creationDate, String email, String fileNumber, String firstname,
			String lastname, String login, String password, int phoneNumber, Date updateDate) {
		super(id, creationDate, email, fileNumber, firstname, lastname, login, password, phoneNumber, updateDate);
		// TODO Auto-generated constructor stub
	}
	
	public List<GpProjectBasicDTO> getGpProject() {
		return gpProject;
	}

	public void setGpProject(List<GpProjectBasicDTO> gpProject) {
		this.gpProject = gpProject;
	}
	
	
}
