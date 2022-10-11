package af.cmr.indyli.gespro.business.dto;

import java.util.Date;

public class GpProjectBasicDTO implements IDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String code;
	
	private String name;
	
	private String description;
	
	private Date startDate;
	
	private Date endDate;
	
	private double amount;
	
	private Date creationDate;
	
	private Date updateDate;

	public GpProjectBasicDTO() {
		super();
	}

	public GpProjectBasicDTO(String code, String name, String description, Date startDate, Date endDate, double amount,
			Date creationDate, Date updateDate) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}

	public GpProjectBasicDTO(int id, String code, String name, String description, Date startDate, Date endDate,
			double amount, Date creationDate, Date updateDate) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
