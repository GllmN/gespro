package af.cmr.indyli.gespro.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the gp_address database table.
 * 
 */
@Entity
@Table(name = "GP_ADDRESS")
public class GpAddress implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESS_ID")
	private int id;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "IS_MAIN")
	private byte isMain;

	@Column(name = "STREET_LABEL")
	private String streetLabel;

	@Column(name = "STREET_NUMBER")
	private int streetNumber;

	@Column(name = "ZIP_CODE")
	private int zipCode;

	// bi-directional many-to-one association to GpEmployee
	@ManyToOne
	@JoinColumn(name = "EMP_ID")
	private GpEmployee gpEmployee;

	// bi-directional many-to-one association to GpOrganization
	@ManyToOne
	@JoinColumn(name = "ORG_ID")
	private GpOrganization gpOrganization;

	public GpAddress() {
	}

	public GpAddress(int id, String country, byte isMain, String streetLabel, int streetNumber, int zipCode,
			GpEmployee gpEmployee, GpOrganization gpOrganization) {
		super();
		this.id = id;
		this.country = country;
		this.isMain = isMain;
		this.streetLabel = streetLabel;
		this.streetNumber = streetNumber;
		this.zipCode = zipCode;
		this.gpEmployee = gpEmployee;
		this.gpOrganization = gpOrganization;
	}

	public GpAddress(String country, byte isMain, String streetLabel, int streetNumber, int zipCode,
			GpEmployee gpEmployee, GpOrganization gpOrganization) {
		super();
		this.country = country;
		this.isMain = isMain;
		this.streetLabel = streetLabel;
		this.streetNumber = streetNumber;
		this.zipCode = zipCode;
		this.gpEmployee = gpEmployee;
		this.gpOrganization = gpOrganization;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public byte getIsMain() {
		return this.isMain;
	}

	public void setIsMain(byte isMain) {
		this.isMain = isMain;
	}

	public String getStreetLabel() {
		return this.streetLabel;
	}

	public void setStreetLabel(String streetLabel) {
		this.streetLabel = streetLabel;
	}

	public int getStreetNumber() {
		return this.streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public int getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public GpEmployee getGpEmployee() {
		return this.gpEmployee;
	}

	public void setGpEmployee(GpEmployee gpEmployee) {
		this.gpEmployee = gpEmployee;
	}

	public GpOrganization getGpOrganization() {
		return this.gpOrganization;
	}

	public void setGpOrganization(GpOrganization gpOrganization) {
		this.gpOrganization = gpOrganization;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

}