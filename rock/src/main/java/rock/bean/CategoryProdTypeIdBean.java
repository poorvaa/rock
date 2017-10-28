package rock.bean;

import org.springframework.stereotype.Component;

@Component
public class CategoryProdTypeIdBean {
	
	private String category;
	private int prodId;
	
	
	
	
	
	public CategoryProdTypeIdBean() {
		
	}


	public CategoryProdTypeIdBean(String category, int prodId) {
		
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


	
	
	
	
	
	

}
