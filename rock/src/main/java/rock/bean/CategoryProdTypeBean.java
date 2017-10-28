package rock.bean;

public class CategoryProdTypeBean {
	
	private String category;
	
	private String productType;
	
	private int rank;

	public CategoryProdTypeBean() {
		
	}

	public CategoryProdTypeBean(String category, String productType, int rank) {
		
		this.category = category;
		this.productType = productType;
		this.rank = rank;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String prodType) {
		this.productType = productType;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	
	

}
