package af.cmr.indyli.gespro.business.dto;

public class GpAddressMediumDTO extends GpAddressBasicDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GpEmployeeBasicDTO gpEmployee;

	private GpOrganizationBasicDTO gpOrganization;

	public GpAddressMediumDTO(int id, String country, byte isMain, String streetLabel, int streetNumber, int zipCode) {
		super(id, country, isMain, streetLabel, streetNumber, zipCode);

	}

	public GpAddressMediumDTO() {
		super();

	}

	public GpAddressMediumDTO(String country, byte isMain, String streetLabel, int streetNumber, int zipCode) {
		super(country, isMain, streetLabel, streetNumber, zipCode);

	}

	public GpAddressMediumDTO(String country, byte isMain, String streetLabel, int streetNumber, int zipCode,
			GpEmployeeBasicDTO gpEmployee, GpOrganizationBasicDTO gpOrganization) {
		super(country, isMain, streetLabel, streetNumber, zipCode);
		this.gpEmployee = gpEmployee;
		this.gpOrganization = gpOrganization;
	}

	public GpEmployeeBasicDTO getGpEmployee() {
		return gpEmployee;
	}

	public void setGpEmployee(GpEmployeeBasicDTO gpEmployee) {
		this.gpEmployee = gpEmployee;
	}

	public GpOrganizationBasicDTO getGpOrganization() {
		return gpOrganization;
	}

	public void setGpOrganization(GpOrganizationBasicDTO gpOrganization) {
		this.gpOrganization = gpOrganization;
	}

}
