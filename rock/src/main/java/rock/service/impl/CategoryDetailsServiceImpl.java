package rock.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	

}