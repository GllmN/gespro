package af.cmr.indyli.gespro.business.dto;

import java.util.List;

public class GpOrganizationFullDTO extends GpOrganizationMediumDTO {

	private List<GpAddressBasicDTO> gpAddresses;

	public GpOrganizationFullDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GpOrganizationFullDTO(int id, String adrWeb, String contactEmail, String contactName, String name,
			String orgCode, int phoneNumber) {
		super(id, adrWeb, contactEmail, contactName, name, orgCode, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	public GpOrganizationFullDTO(String adrWeb, String contactEmail, String contactName, String name, String orgCode,
			int phoneNumber) {
		super(adrWeb, contactEmail, contactName, name, orgCode, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	public List<GpAddressBasicDTO> getGpAddresses() {
		return gpAddresses;
	}

	public void setGpAddresses(List<GpAddressBasicDTO> gpAddresses) {
		this.gpAddresses = gpAddresses;
	}

}
