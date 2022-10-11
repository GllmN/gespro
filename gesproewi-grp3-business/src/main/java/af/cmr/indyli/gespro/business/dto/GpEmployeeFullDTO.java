package af.cmr.indyli.gespro.business.dto;

import java.util.Date;
import java.util.List;

public class GpEmployeeFullDTO extends GpEmployeeMediumDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<GpAddressBasicDTO> gpAddresses;

	public GpEmployeeFullDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GpEmployeeFullDTO(Date creationDate, String email, String fileNumber, String firstname, String lastname,
			String login, String password, int phoneNumber, Date updateDate) {
		super(creationDate, email, fileNumber, firstname, lastname, login, password, phoneNumber, updateDate);
		// TODO Auto-generated constructor stub
	}

	public GpEmployeeFullDTO(int id, Date creationDate, String email, String fileNumber, String firstname,
			String lastname, String login, String password, int phoneNumber, Date updateDate) {
		super(id, creationDate, email, fileNumber, firstname, lastname, login, password, phoneNumber, updateDate);
		// TODO Auto-generated constructor stub
	}

	public List<GpAddressBasicDTO> getGpAddresses() {
		return gpAddresses;
	}

	public void setGpAddresses(List<GpAddressBasicDTO> gpAddresses) {
		this.gpAddresses = gpAddresses;
	}

}
