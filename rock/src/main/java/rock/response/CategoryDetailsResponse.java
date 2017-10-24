package rock.response;

import rock.db.model.CategoryDetails;

public class CategoryDetailsResponse extends AbstractAPIResponse {

	private CategoryDetails categoryDetails;
	

	public CategoryDetailsResponse() {
		
	}

	public CategoryDetailsResponse(CategoryDetails categoryDetails) {
		
		this.categoryDetails = categoryDetails;
	}

	public CategoryDetails getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(CategoryDetails categoryDetails) {
		this.categoryDetails = categoryDetails;
	}
	
	
}
