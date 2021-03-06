package rock.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rock.dao.BookDao;
import rock.db.model.Book;
import rock.db.model.CategoryDetails;
import rock.db.model.ProductType;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	SessionFactory sessionFactory;
	
	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Book addBook(Book book) {
		
		//check if category id is valid
		CategoryDetails categoryDetails = (CategoryDetails) getSession().get(CategoryDetails.class, book.getCategoryDetails().getId());
		
		if(categoryDetails ==null)
		{
			//category id is invalid
			book.setState(-1);
			return book;
		}
		
		//check if record with name given by user already exists
		Criteria criteria1 = getSession().createCriteria(Book.class)
										 .add(Restrictions.eq("name", book.getName()));
		Book existingBook = (Book) criteria1.uniqueResult();
		//if such book exist, record will not be saved
		if(existingBook!=null)
		{
			book.setState(-2);
			return book;
		}
		
		//retrieve the product type corresponding to category id
		ProductType productTypeDetails = (ProductType) getSession().get(ProductType.class, categoryDetails.getProdTypeDetails().getProdTypeId());
		
		//set category and product type for the book
		book.setCategoryDetails(categoryDetails);
		book.setProductTypeDetails(productTypeDetails);
		
		//set state of book
		book.setState(1);
		getSession().persist(book);
		
		int bookId = book.getId();
		
		System.out.println("book id is "+bookId);
		
		Book savedBook = (Book) getSession().get(Book.class, bookId);
		
		return savedBook;
	}

	@Override
	public Book viewBookById(int id) {
	
		Book book = (Book) getSession().get(Book.class, id);
		
		return book;
	}

	@Override
	public List viewAllBooks(int start,int count) {
	
		List books = getSession().createCriteria(Book.class)
								 .add(Restrictions.eq("isActive", true))
								 .setFirstResult(start)
								 .setMaxResults(count)
								 .addOrder(Order.asc("rank"))
								 .addOrder(Order.desc("modifiedOn"))
								 .list();
		
		return books;
	}

	@Override
	public Book updateBookDetails(int id, Book book) 
	{
		Book existingBook = (Book) getSession().get(Book.class, id);
		
		if(existingBook==null)
		{
			book.setState(-1);
			return book;
		}
		
		if(book.getRank()!=0)
		{
			existingBook.setRank(book.getRank());
		}
		
		if(book.getPrice()!=0)
		{
			existingBook.setPrice(book.getPrice());
		}
		
		if(book.getDiscountedPrice()!=0)
		{
			existingBook.setDiscountedPrice(book.getDiscountedPrice());
		}
		
		if(book.getIsSample()==true)
		{
			existingBook.setIsSample(true);
		}
		
		existingBook.setIsActive(true);
		existingBook.setModifiedOn(new Date());
		existingBook.setState(1);
		return existingBook;
	}

	@Override
	public int deleteBook(int id) {
		
		Book book = (Book) getSession().get(Book.class, id);
		
		if(book==null)
		{
			return 0;
		}
		
		else
		{
			book.setModifiedOn(new Date());
			book.setIsActive(false);
			return 1;
		}
	}

	@Override
	public void aggregateFunctions() {
	
		//count no of records in table
		Long countRecords = (Long) getSession().createCriteria(Book.class)
													  .setProjection(Projections.rowCount())
													  .list().get(0);
		
		System.out.println("count of records is "+countRecords);
		
		//count no of records with restrictions
		Long countRestrictedRecords = (Long) getSession().createCriteria(Book.class)
															   .add(Restrictions.eq("categoryDetails.id",21))
															   .setProjection(Projections.count("name"))
															   .list().get(0);
		
		System.out.println("count of restricted records is "+countRestrictedRecords);
		
		//select max and min values
		Criteria criteria1 =  getSession().createCriteria(Book.class)
												 .add(Restrictions.eq("productTypeDetails.prodTypeId", 1));
		
		ProjectionList properties1 = Projections.projectionList();
		properties1.add(Projections.max("id"));
		properties1.add(Projections.min("id"));
		
		criteria1.setProjection(properties1);
		
		Object[] minMax = (Object[]) criteria1.list().get(0);
		
		System.out.println("max id is "+minMax[0]+" and min id is "+minMax[1]);
		
		
		//select avg and sum of values
		Criteria criteria2 =  getSession().createCriteria(Book.class)
				 						  .add(Restrictions.gt("price", new Float(99.0)));

		ProjectionList properties2 = Projections.projectionList();
		properties2.add(Projections.avg("price"));
		properties2.add(Projections.sum("price"));
		
		criteria2.setProjection(properties2);
		
		Object[] avgSum = (Object[]) criteria2.list().get(0);
		
		System.out.println("avg price is "+avgSum[0]+" and sum of price is "+avgSum[1]);	
		
		
		//groupby
				
		Criteria criteria3 =  getSession().createCriteria(Book.class);
				  

		ProjectionList properties3 = Projections.projectionList();
		properties3.add(Projections.groupProperty("categoryDetails.id"));
		properties3.add(Projections.sum("price"));
		properties3.add(Projections.rowCount());
		
		criteria3.setProjection(properties3);
		
		List  groupBy= (List) criteria3.list();
		
		Iterator it = groupBy.iterator();
		
		while(it.hasNext())
		{
			Object grpBy[] = (Object[])it.next();
			System.out.println("category id is "+grpBy[0]+" sum of price is "+grpBy[1]+" and row count is "+grpBy[2]);
		}


		
			

		
		
												 
	}

	

}
