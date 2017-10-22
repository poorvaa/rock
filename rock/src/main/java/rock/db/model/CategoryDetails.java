package rock.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class CategoryDetails {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	
	
	@NotNull
	@Column(name="category")
	private String category;
	
	@Column(name="rank")
	private int rank;
	
	@Column(name="created_on")
	private Date createdOn;
	
	@Column(name="modified_on")
	private Date modifiedOn;
	
	@Column(name="is_active")
	private boolean isActive;
	
	
	@ManyToOne
	@JoinColumn(name="prod_type_id")
	private ProductType prodTypeDetails;
	
	
	@Transient
	@JsonInclude
	private String location;


	
	
	public CategoryDetails() {
		
	}
	
	


	public CategoryDetails(int id, String category, int rank, Date createdOn,
			Date modifiedOn, boolean isActive, ProductType prodTypeDetails) {
		super();
		this.id = id;
		this.category = category;
		this.rank = rank;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.isActive = isActive;
		this.prodTypeDetails = prodTypeDetails;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public Date getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	public Date getModifiedOn() {
		return modifiedOn;
	}


	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}


	public boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}


	
	public ProductType getProdTypeDetails() {
		return prodTypeDetails;
	}


	public void setProdTypeDetails(ProductType prodTypeDetails) {
		this.prodTypeDetails = prodTypeDetails;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
