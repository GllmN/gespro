package af.cmr.indyli.gespro.business.dto;

public class GpAddressFullDTO extends GpAddressMediumDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GpAddressFullDTO(int id, String country, byte isMain, String streetLabel, int streetNumber, int zipCode) {
		super(id, country, isMain, streetLabel, streetNumber, zipCode);
	}

	public GpAddressFullDTO() {
		super();
	}

	public GpAddressFullDTO(String country, byte isMain, String streetLabel, int streetNumber, int zipCode,
			GpEmployeeBasicDTO gpEmployee, GpOrganizationBasicDTO gpOrganization) {
		super(country, isMain, streetLabel, streetNumber, zipCode, gpEmployee, gpOrganization);
	}

}
