package af.cmr.indyli.gespro.business.dto;

public class GpAddressBasicDTO implements IDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String country;

	private byte isMain;

	private String streetLabel;

	private int streetNumber;

	private int zipCode;

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

	public GpAddressBasicDTO(String country, byte isMain, String streetLabel, int streetNumber, int zipCode) {
		super();
		this.country = country;
		this.isMain = isMain;
		this.streetLabel = streetLabel;
		this.streetNumber = streetNumber;
		this.zipCode = zipCode;
	}

	public GpAddressBasicDTO(int id, String country, byte isMain, String streetLabel, int streetNumber, int zipCode) {
		super();
		this.id = id;
		this.country = country;
		this.isMain = isMain;
		this.streetLabel = streetLabel;
		this.streetNumber = streetNumber;
		this.zipCode = zipCode;
	}

	public GpAddressBasicDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public byte getIsMain() {
		return isMain;
	}

	public void setIsMain(byte isMain) {
		this.isMain = isMain;
	}

	public String getStreetLabel() {
		return streetLabel;
	}

	public void setStreetLabel(String streetLabel) {
		this.streetLabel = streetLabel;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

}
