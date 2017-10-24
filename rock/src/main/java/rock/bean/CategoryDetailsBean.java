package rock.bean;

import org.springframework.stereotype.Component;

@Component
public class CategoryDetailsBean {
	
	private String category;
	private int prodId;
	private int xyz;
	
	
	
	
	public CategoryDetailsBean() {
		
	}


	public CategoryDetailsBean(String category, int prodId) {
		
		this.category = category;
		this.prodId = prodId;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getProdId() {
		return prodId;
	}


	public void setProdId(int prodId) {
		this.prodId = prodId;
	}


	public int getXyz() {
		return xyz;
	}


	public void setXyz(int xyz) {
		this.xyz = xyz;
	}
	
	
	
	
	
	
	

}
