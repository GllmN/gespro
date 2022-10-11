package af.cmr.indyli.gespro.business.dto;

import java.util.Date;


public class GpProjectFullDTO extends GpProjectMediumDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GpProjectFullDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GpProjectFullDTO(int id, String code, String name, String description, Date startDate, Date endDate,
			double amount, Date creationDate, Date updateDate, GpProjectManagerBasicDTO projectmanager,
			GpOrganizationBasicDTO organization) {
		super(id, code, name, description, startDate, endDate, amount, creationDate, updateDate, projectmanager, organization);
		// TODO Auto-generated constructor stub
	}

	public GpProjectFullDTO(String code, String name, String description, Date startDate, Date endDate, double amount,
			Date creationDate, Date updateDate, GpProjectManagerBasicDTO projectmanager,
			GpOrganizationBasicDTO organization) {
		super(code, name, description, startDate, endDate, amount, creationDate, updateDate, projectmanager, organization);
		// TODO Auto-generated constructor stub
	}

	

		
}
