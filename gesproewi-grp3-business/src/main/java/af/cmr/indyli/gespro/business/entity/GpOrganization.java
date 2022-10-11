package af.cmr.indyli.gespro.business.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the gp_organization database table.
 * 
 */
@Entity
@Table(name = "GP_ORGANIZATION")
public class GpOrganization implements IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORG_ID")
	private int id;

	@Column(name = "ADR_WEB")
	private String adrWeb;

	@Column(name = "CONTACT_EMAIL")
	private String contactEmail;

	@Column(name = "CONTACT_NAME")
	private String contactName;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ORG_CODE")
	private String orgCode;

	@Column(name = "PHONE_NUMBER")
	private int phoneNumber;

	// bi-directional many-to-one association to GpAddress
	@OneToMany(mappedBy = "gpOrganization")
	private List<GpAddress> gpAddresses;
	/*
	@OneToMany(mappedBy = "gpOrganization")
	private List<GpProject> gpProject;

	public List<GpProject> getGpProject() {
		return gpProject;
	}

	public void setGpProject(List<GpProject> gpProject) {
		this.gpProject = gpProject;
	}*/

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

	public String getAdrWeb() {
		return this.adrWeb;
	}

	public void setAdrWeb(String adrWeb) {
		this.adrWeb = adrWeb;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<GpAddress> getGpAddresses() {
		return this.gpAddresses;
	}

	public void setGpAddresses(List<GpAddress> gpAddresses) {
		this.gpAddresses = gpAddresses;
	}

	public GpAddress addGpAddress(GpAddress gpAddress) {
		getGpAddresses().add(gpAddress);
		gpAddress.setGpOrganization(this);

		return gpAddress;
	}

	public GpAddress removeGpAddress(GpAddress gpAddress) {
		getGpAddresses().remove(gpAddress);
		gpAddress.setGpOrganization(null);

		return gpAddress;
	}
	/*
	public GpProject addGpProject(GpProject gpProject) {
		getGpProject().add(gpProject);
		gpProject.setOrganization(this);

		return gpProject;
	}

	public GpProject removeGpProject(GpProject gpProject) {
		getGpProject().remove(gpProject);
		gpProject.setOrganization(null);

		return gpProject;
	}*/

}