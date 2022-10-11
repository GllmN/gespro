package af.cmr.indyli.gespro.business.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the gp_employee database table.
 * 
 */
@Entity
@Table(name = "GP_EMPLOYEE")
@Inheritance(strategy = InheritanceType.JOINED)
public class GpEmployee implements IEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5244828030528523340L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATION_DATE")
	private Date creationDate;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "FILE_NUMBER")
	private String fileNumber;

	@Column(name = "FIRSTNAME")
	private String firstname;

	@Column(name = "LASTNAME")
	private String lastname;

	@Column(name = "LOGIN")
	private String login;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PHONE_NUMBER")
	private int phoneNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	// bi-directional many-to-one association to GpAddress
	@OneToMany(mappedBy = "gpEmployee")
	private List<GpAddress> gpAddresses;
	
	public GpEmployee() {
		this.creationDate = new Date();
	}

	public GpEmployee(int id, Date creationDate, String email, String fileNumber, String firstname, String lastname,
			String login, String password, int phoneNumber, Date updateDate, List<GpAddress> gpAddresses) {
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
		this.gpAddresses = gpAddresses;
	}

	public GpEmployee(Date creationDate, String email, String fileNumber, String firstname, String lastname,
			String login, String password, int phoneNumber, Date updateDate, List<GpAddress> gpAddresses) {
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
		this.gpAddresses = gpAddresses;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFileNumber() {
		return this.fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<GpAddress> getGpAddresses() {
		return this.gpAddresses;
	}

	public void setGpAddresses(List<GpAddress> gpAddresses) {
		this.gpAddresses = gpAddresses;
	}

	public GpAddress addGpAddress(GpAddress gpAddress) {
		getGpAddresses().add(gpAddress);
		gpAddress.setGpEmployee(this);

		return gpAddress;
	}

	public GpAddress removeGpAddress(GpAddress gpAddress) {
		getGpAddresses().remove(gpAddress);
		gpAddress.setGpEmployee(null);

		return gpAddress;
	}

}