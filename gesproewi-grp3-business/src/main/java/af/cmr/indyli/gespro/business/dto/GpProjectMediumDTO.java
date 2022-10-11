package af.cmr.indyli.gespro.business.dto;

import java.util.Date;

public class GpProjectMediumDTO extends GpProjectBasicDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GpProjectManagerBasicDTO projectmanager;

	private GpOrganizationBasicDTO organization;
	
	public GpProjectMediumDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GpProjectMediumDTO(int id, String code, String name, String description, Date startDate, Date endDate,
			double amount, Date creationDate, Date updateDate, GpProjectManagerBasicDTO projectmanager, GpOrganizationBasicDTO organization) {
		super(id, code, name, description, startDate, endDate, amount, creationDate, updateDate);
		// TODO Auto-generated constructor stub
		this.projectmanager = projectmanager;
		this.organization = organization;
	}

	public GpProjectMediumDTO(String code, String name, String description, Date startDate, Date endDate, double amount,
			Date creationDate, Date updateDate, GpProjectManagerBasicDTO projectmanager, GpOrganizationBasicDTO organization) {
		super(code, name, description, startDate, endDate, amount, creationDate, updateDate);
		// TODO Auto-generated constructor stub
		this.projectmanager = projectmanager;
		this.organization = organization;
	}

	public GpProjectManagerBasicDTO getProjectmanager() {
		return projectmanager;
	}

	public void setProjectmanager(GpProjectManagerBasicDTO projectmanager) {
		this.projectmanager = projectmanager;
	}

	public GpOrganizationBasicDTO getOrganization() {
		return organization;
	}

	public void setOrganization(GpOrganizationBasicDTO organization) {
		this.organization = organization;
	}
	
	

}
