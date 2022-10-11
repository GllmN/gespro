package af.cmr.indyli.gespro.business.dto;

import java.util.Date;

public class GpEmployeeBasicDTO implements IDTO {
	private static final long serialVersionUID = 1L;

	private int id;

	private Date creationDate;

	private String email;

	private String fileNumber;

	private String firstname;

	private String lastname;

	private String login;

	private String password;

	private int phoneNumber;

	private Date updateDate;

	public int getId() {
		return id;
	}

	public GpEmployeeBasicDTO() {
		super();
	}

	public GpEmployeeBasicDTO(Date creationDate, String email, String fileNumber, String firstname, String lastname,
			String login, String password, int phoneNumber, Date updateDate) {
		super();
		this.creationDate = creationDate;
		this.email = email;
		this.fileNumber = fileNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.updateDate = updateDate;
	}

	public GpEmployeeBasicDTO(int id, Date creationDate, String email, String fileNumber, String firstname,
			String lastname, String login, String password, int phoneNumber, Date updateDate) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.email = email;
		this.fileNumber = fileNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.updateDate = updateDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
