package rock.service;

import rock.db.model.CategoryDetails;

public interface CategoryDetailsService {
	
	public CategoryDetails add(CategoryDetails cd);
	
	public CategoryDetails listCategoryDetails(int id);

}
