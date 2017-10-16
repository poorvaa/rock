package rock.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rock.dao.BookCategoryDao;
import rock.db.model.BookCategory;

@Repository
public class BookCategoryDaoImpl implements BookCategoryDao {
	
	@Autowired 
	SessionFactory sessionFactory;
	
	protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	public BookCategory add(BookCategory bookCategory) {
		
		
		
		Criteria criteria = getSession().createCriteria(BookCategory.class)
										 .add(Restrictions.eq("bookCategory", bookCategory.getBookCategory()));
		
		BookCategory bc = (BookCategory) criteria.uniqueResult();
		
		if(bc == null)
		{
			getSession().persist(bookCategory);
			
			int bookId = bookCategory.getId();
			
			Criteria criteria1 = getSession().createCriteria(BookCategory.class)
											.add(Restrictions.eq("id", bookId));
			
			BookCategory savedBookCategory = (BookCategory) criteria.uniqueResult();
			
			return savedBookCategory;
		}
		return null;
	}
	
	
	

}
