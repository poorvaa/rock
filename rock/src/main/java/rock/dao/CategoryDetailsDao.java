package rock.dao;

import java.util.List;

import rock.bean.CategoryDistinctBean;
import rock.bean.CategoryProdTypeBean;
import rock.bean.CategoryProdTypeIdBean;
import rock.db.model.CategoryDetails;


public interface CategoryDetailsDao {
	
	//add category
	public CategoryDetails add(CategoryDetails cd);
	
	//view category by id
	public CategoryDetails listCategoryDetails(int id);
	
	//update category
	public CategoryDetails updateCategoryDetails(int id,CategoryDetails categoryDetails);
	
	//to delete category
	public int deleteCategoryDetails(int categoryDetailsId);
	
	//view category and its product type id
	public List<CategoryProdTypeIdBean> viewCategoryAndProdTypeId();
	
	//view all distinct categories
	public List<CategoryDistinctBean> viewDistinctCategories();
	
	//view category and product type name
	public List<CategoryProdTypeBean> joinCategoryAndProductType();

}
