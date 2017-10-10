package rock.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductType {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private int prodTypeId;
	
	@Column(name="prod_type_name")
	private String prodTypeName;
	
	@Column(name="prod_type_img")
	private String prodTypeImg;
	
	
	@Column(name="prod_type_desc")
	private String prodTypeDesc;
	
	@Column(name="rank")
	private int rank;
	
	@Column(name="created_on")
	private Date createdOn;
	
	
	@Column(name="modified_on")
	private Date modifiedOn;
	
	@Column(name="is_active")
	private boolean isActive;

	
	
	public ProductType()
	{
		
	}



	public ProductType(int prodTypeId, String prodTypeName, String prodTypeImg,
			String prodTypeDesc, int rank, Date createdOn, Date modifiedOn,
			boolean isActive) {
		super();
		this.prodTypeId = prodTypeId;
		this.prodTypeName = prodTypeName;
		this.prodTypeImg = prodTypeImg;
		this.prodTypeDesc = prodTypeDesc;
		this.rank = rank;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.isActive = isActive;
	}


	
	

	public ProductType(String prodTypeName, String prodTypeImg,
			String prodTypeDesc, int rank, Date createdOn, Date modifiedOn,
			boolean isActive) {
		super();
		this.prodTypeName = prodTypeName;
		this.prodTypeImg = prodTypeImg;
		this.prodTypeDesc = prodTypeDesc;
		this.rank = rank;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.isActive = isActive;
	}

	
	


	public ProductType(String prodTypeName, String prodTypeImg,
			String prodTypeDesc, int rank) {
		super();
		this.prodTypeName = prodTypeName;
		this.prodTypeImg = prodTypeImg;
		this.prodTypeDesc = prodTypeDesc;
		this.rank = rank;
	}



	public int getProdTypeId() {
		return prodTypeId;
	}



	public String getProdTypeName() {
		return prodTypeName;
	}



	public String getProdTypeImg() {
		return prodTypeImg;
	}



	public String getProdTypeDesc() {
		return prodTypeDesc;
	}



	public int getRank() {
		return rank;
	}



	public Date getCreatedOn() {
		return createdOn;
	}



	public Date getModifiedOn() {
		return modifiedOn;
	}



	public boolean isActive() {
		return isActive;
	}



	public void setProdTypeId(int prodTypeId) {
		this.prodTypeId = prodTypeId;
	}



	public void setProdTypeName(String prodTypeName) {
		this.prodTypeName = prodTypeName;
	}



	public void setProdTypeImg(String prodTypeImg) {
		this.prodTypeImg = prodTypeImg;
	}



	public void setProdTypeDesc(String prodTypeDesc) {
		this.prodTypeDesc = prodTypeDesc;
	}



	public void setRank(int rank) {
		this.rank = rank;
	}



	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}



	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}



	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	
		
	

}