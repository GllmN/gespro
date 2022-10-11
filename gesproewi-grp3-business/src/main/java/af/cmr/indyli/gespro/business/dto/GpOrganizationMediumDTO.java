package af.cmr.indyli.gespro.business.dto;

public class GpOrganizationMediumDTO extends GpOrganizationBasicDTO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2152184753283868784L;

	public GpOrganizationMediumDTO(int id, String adrWeb, String contactEmail, String contactName, String name,
			String orgCode, int phoneNumber) {
		super(id, adrWeb, contactEmail, contactName, name, orgCode, phoneNumber);
	}

	public GpOrganizationMediumDTO(String adrWeb, String contactEmail, String contactName, String name, String orgCode,
			int phoneNumber) {
		super(adrWeb, contactEmail, contactName, name, orgCode, phoneNumber);
	}

	public GpOrganizationMediumDTO() {
		super();
	}

}
