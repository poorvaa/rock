package rock.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rock.dao.BookCategoryDao;
import rock.db.model.BookCategory;
import rock.service.BookCategoryService;

@Service
public class BookCategoryServiceImpl implements BookCategoryService{
	
	@Autowired
	BookCategoryDao bookCategoryDao;

	@Override
	@Transactional
	public BookCategory add(BookCategory bookCategory) {
		
		bookCategory.setCreatedOn(new Date());
		bookCategory.setModifiedOn(new Date());
		bookCategory.setIsActive(true);
		BookCategory bookCat = bookCategoryDao.add(bookCategory);
		return bookCat;
	}

}
