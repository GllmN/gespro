package af.cmr.indyli.gespro.business.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/* 
* Création de l'entité gpproject ayant pour attribut
* (project_id, project_code, name, description, start_date, end_date, amount, creation_date, update_date, org_id, emp_id)
*/
@Entity
@Table(name = "GP_PROJECT")
public class GpProject implements IEntity {
	private static final long serialVersionUID = 1L;

	/*
	 * on associe chaque attribut au variable déclarer
	 * project_id = id
	 * project_code=code
	 * name=name
	 * description=description
	 * start_date= startDate
	 * end_date=endDate
	 * amount=amount
	 * creation_date=creationDate
	 * update_date=updateDate
	 * org_id = gpOrganization
	 * emp_id = gpEmployee
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private int id;
	
	@Column(name = "PROJECT_CODE")
	private String code;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Column(name = "AMOUNT")
	private double amount;
	
	/*
	 * @ManytoOne nous permet de joindre l'entiter GpEmployee
	 * avec GpProject car emp_id 
	 */
	
	@ManyToOne
	@JoinColumn(name = "EMP_ID")
	private GpProjectManager projectmanager;
	
	@ManyToOne
	@JoinColumn(name = "ORG_ID")
	private GpOrganization organization;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	
	public GpProject() {
	}
	
	public GpProject(String code, String name, String description, Date startDate, Date endDate, double amount,
			GpProjectManager projectmanager, GpOrganization organization, Date creationDate, Date updateDate) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.projectmanager = projectmanager;
		this.organization = organization;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}

	public GpProject(int id, String code, String name, String description, Date startDate, Date endDate, double amount,
			GpProjectManager projectmanager, GpOrganization organisation, Date creationDate, Date updateDate) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.projectmanager = projectmanager;
		this.organization = organisation;
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

	public GpProjectManager getProjectManager() {
		return projectmanager;
	}

	public void setProjectManager(GpProjectManager projectmanager) {
		this.projectmanager = projectmanager;
	}

	public GpOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(GpOrganization organisation) {
		this.organization = organisation;
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
