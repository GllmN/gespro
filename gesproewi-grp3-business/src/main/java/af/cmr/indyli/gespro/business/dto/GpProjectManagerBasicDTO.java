package af.cmr.indyli.gespro.business.dto;

import java.util.Date;

public class GpProjectManagerBasicDTO extends GpEmployeeBasicDTO {

	private static final long serialVersionUID = 1L;

	public GpProjectManagerBasicDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GpProjectManagerBasicDTO(Date creationDate, String email, String fileNumber, String firstname,
			String lastname, String login, String password, int phoneNumber, Date updateDate) {
		super(creationDate, email, fileNumber, firstname, lastname, login, password, phoneNumber, updateDate);
		// TODO Auto-generated constructor stub
	}

	public GpProjectManagerBasicDTO(int id, Date creationDate, String email, String fileNumber, String firstname,
			String lastname, String login, String password, int phoneNumber, Date updateDate) {
		super(id, creationDate, email, fileNumber, firstname, lastname, login, password, phoneNumber, updateDate);
		// TODO Auto-generated constructor stub
	}
	

}
