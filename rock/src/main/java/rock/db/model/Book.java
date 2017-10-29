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

import rock.JsonDateSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Book {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	@NotNull
	private String name;
	
	@Column(name="img_url")
	private String imgUrl;
	
	
	@Column(name="publisher")
	@NotNull
	private String publisher;
	
	@Column(name="price", columnDefinition="Decimal(10,2) default '0.00'")
	private float price;
	
	@Column(name="discounted_price",columnDefinition="Decimal(10,2) default '0.00'")
	private float discountedPrice;
	
	@Column(name="avg_rating",columnDefinition = "int default 0")
	private int avgRating;
	
	@Column(name="is_sample")
	private boolean isSample;
	
	@Column(name="rank")
	private int rank;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name="created_on")
	private Date createdOn;
	
	@Column(name="modified_on")
	private Date modifiedOn;
	
	@Transient
	@JsonInclude
	private String location;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private CategoryDetails categoryDetails;
	
	@ManyToOne
	@JoinColumn(name="product_type_id")
	private ProductType productTypeDetails;
	
	
	
	
	@Transient
	@JsonIgnore
	private int state;
	
	

	public Book() {
		
		
	}

	public Book(int id, String name, String imgUrl, String publisher,
			float price, float discountedPrice, int avgRating,
			boolean isSample, CategoryDetails categoryDetails, int rank,
			boolean isActive, Date createdOn, Date modifiedOn,
			ProductType productTypeDetails) {
		
		this.id = id;
		this.name = name;
		this.imgUrl = imgUrl;
		this.publisher = publisher;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.avgRating = avgRating;
		this.isSample = isSample;
		this.categoryDetails = categoryDetails;
		this.rank = rank;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.productTypeDetails = productTypeDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(float discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public int getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(int avgRating) {
		this.avgRating = avgRating;
	}

	public boolean getIsSample() {
		return isSample;
	}

	public void setIsSample(boolean isSample) {
		this.isSample = isSample;
	}

	public CategoryDetails getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(CategoryDetails categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
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

	public ProductType getProductTypeDetails() {
		return productTypeDetails;
	}

	public void setProductTypeDetails(ProductType productTypeDetails) {
		this.productTypeDetails = productTypeDetails;
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
