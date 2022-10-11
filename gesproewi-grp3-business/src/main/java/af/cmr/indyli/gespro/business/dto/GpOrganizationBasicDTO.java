package af.cmr.indyli.gespro.business.dto;

public class GpOrganizationBasicDTO implements IDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3068660566356615665L;

	private int id;

	private String adrWeb;

	private String contactEmail;

	private String contactName;

	private String name;

	private String orgCode;

	private int phoneNumber;

	public GpOrganizationBasicDTO(String adrWeb, String contactEmail, String contactName, String name, String orgCode,
			int phoneNumber) {
		super();
		this.adrWeb = adrWeb;
		this.contactEmail = contactEmail;
		this.contactName = contactName;
		this.name = name;
		this.orgCode = orgCode;
		this.phoneNumber = phoneNumber;
	}

	public GpOrganizationBasicDTO(int id, String adrWeb, String contactEmail, String contactName, String name,
			String orgCode, int phoneNumber) {
		super();
		this.id = id;
		this.adrWeb = adrWeb;
		this.contactEmail = contactEmail;
		this.contactName = contactName;
		this.name = name;
		this.orgCode = orgCode;
		this.phoneNumber = phoneNumber;
	}

	public GpOrganizationBasicDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdrWeb() {
		return adrWeb;
	}

	public void setAdrWeb(String adrWeb) {
		this.adrWeb = adrWeb;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
