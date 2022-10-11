package af.cmr.indyli.gespro.business.dto;

import java.util.Date;

public class GpEmployeeMediumDTO extends GpEmployeeBasicDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GpEmployeeMediumDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GpEmployeeMediumDTO(Date creationDate, String email, String fileNumber, String firstname, String lastname,
			String login, String password, int phoneNumber, Date updateDate) {
		super(creationDate, email, fileNumber, firstname, lastname, login, password, phoneNumber, updateDate);
		// TODO Auto-generated constructor stub
	}

	public GpEmployeeMediumDTO(int id, Date creationDate, String email, String fileNumber, String firstname,
			String lastname, String login, String password, int phoneNumber, Date updateDate) {
		super(id, creationDate, email, fileNumber, firstname, lastname, login, password, phoneNumber, updateDate);
		// TODO Auto-generated constructor stub
	}

}
