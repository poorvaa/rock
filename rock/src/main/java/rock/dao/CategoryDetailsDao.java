package rock.dao;

import rock.db.model.CategoryDetails;


public interface CategoryDetailsDao {
	
	public CategoryDetails add(CategoryDetails cd);
	
	public CategoryDetails listCategoryDetails(int id);

}
