package rock.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rock.bean.CategoryDistinctBean;
import rock.bean.CategoryProdTypeBean;
import rock.bean.CategoryProdTypeIdBean;
import rock.dao.CategoryDetailsDao;
import rock.db.model.CategoryDetails;
import rock.service.CategoryDetailsService;

@Service
public class CategoryDetailsServiceImpl implements CategoryDetailsService {
	
	@Autowired
	private CategoryDetailsDao cdDao;

	@Transactional
	@Override
	public CategoryDetails add(CategoryDetails cd) {
		
		cd.setCreatedOn(new Date());
		cd.setModifiedOn(new Date());
		cd.setIsActive(true);
		return cdDao.add(cd);
		
				

	}

	@Transactional
	@Override
	public CategoryDetails listCategoryDetails(int id) {
		
		return cdDao.listCategoryDetails(id);
	}
	
	
	@Transactional
	@Override
	public CategoryDetails updateCategoryDetails(int id,
			CategoryDetails categoryDetails) {


		return cdDao.updateCategoryDetails(id, categoryDetails);
		
	}

	@Transactional
	@Override
	public int deleteCategoryDetails(int categoryDetailsId) {
		
		 return cdDao.deleteCategoryDetails(categoryDetailsId);
		
	}
	
	@Transactional
	@Override
	public List<CategoryProdTypeIdBean> viewCategoryAndProdTypeId() {
		
		return cdDao.viewCategoryAndProdTypeId();
	}

	@Transactional
	@Override
	public List<CategoryDistinctBean> viewDistinctCategories() {
		
		return cdDao.viewDistinctCategories();
	}

	@Transactional
	@Override
	public List<CategoryProdTypeBean> joinCategoryAndProductType() {
		
		return cdDao.joinCategoryAndProductType();
	}

	

	
	
	

}
