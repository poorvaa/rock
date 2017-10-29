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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import rock.JsonDateSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	
	@Transient
	@JsonInclude
	private String location;
	
	
	//@ManyToOne(cascade=CascadeType.REMOVE)
	@ManyToOne
	@JoinColumn(name="prod_type_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ProductType prodTypeDetails;
	
	
	
	
	
	/*1- if product if prod_id is not valid
	2- if data already exists in db*/
	@Transient
	@JsonIgnore
	private int state;


	
	
	public CategoryDetails() {
		
		System.out.println("inside constructor");
		
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


	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	@JsonSerialize(using=JsonDateSerializer.class)
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




	public int getState() {
		return state;
	}




	public void setState(int state) {
		this.state = state;
	}
	
	
	

}
