package af.cmr.indyli.gespro.business.entity;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the gp_project_manager database table.
 * 
 */ 
@Entity
@Table(name = "GP_PROJECT_MANAGER")
@Inheritance(strategy = InheritanceType.JOINED)
public class GpProjectManager extends GpEmployee {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
* 
*/
//	private static final long serialVersionUID = 1L;
	// bi-directional one-to-many association to GpProject
	
	@OneToMany(mappedBy = "projectmanager")
	private List<GpProject> gpProject;

	public List<GpProject> getGpProject() {
		return gpProject;
	}

	public void setGpProject(List<GpProject> gpProject) {
		this.gpProject = gpProject;
	}

	public GpProjectManager() {
		super();
	}

		
		
}