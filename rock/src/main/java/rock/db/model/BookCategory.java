package rock.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import rock.JsonDateSerializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class BookCategory {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@NotNull
	@Column(name="book_category")
	private String bookCategory;
	
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

	public BookCategory() {
		
	}

	public BookCategory(String bookCategory, int rank, Date createdOn,
			Date modifiedOn, boolean isActive) {
		super();
		this.bookCategory = bookCategory;
		this.rank = rank;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	
}
