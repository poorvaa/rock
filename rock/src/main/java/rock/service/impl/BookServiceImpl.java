package rock.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rock.dao.BookDao;
import rock.db.model.Book;
import rock.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDao;
	
	@Transactional
	@Override
	public Book addBook(Book book)
	{
		
		book.setCreatedOn(new Date());
		book.setModifiedOn(new Date());
		book.setIsActive(true);
		return bookDao.addBook(book);
	}

	@Transactional
	@Override
	public Book viewBookById(int id)
	{
		return bookDao.viewBookById(id);
	}

	@Transactional
	@Override
	public List viewAllBooks(int start,int count) {
		
		return bookDao.viewAllBooks(start,count);
	}

	@Transactional
	@Override
	public Book updateBookDetails(int id, Book book) 
	{
		return bookDao.updateBookDetails(id, book);
	}

	@Transactional
	@Override
	public int deleteBook(int id) 
	{
		return bookDao.deleteBook(id);
	}

}
