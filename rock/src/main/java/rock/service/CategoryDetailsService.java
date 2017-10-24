package rock.service;

import java.util.List;

import rock.bean.CategoryDetailsBean;
import rock.db.model.CategoryDetails;

public interface CategoryDetailsService {
	
	public CategoryDetails add(CategoryDetails cd);
	
	public CategoryDetails listCategoryDetails(int id);
	
	public List<CategoryDetailsBean> viewCategoryAndProdTypeId();
	
	public List<String> viewDistinctCategories();
	
	public List<CategoryDetails> joinCategoryAndProductType();

}
