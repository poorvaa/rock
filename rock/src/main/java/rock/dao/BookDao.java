package rock.dao;

import java.util.List;

import rock.db.model.Book;

public interface BookDao {
	
	
		//add book 
		public Book addBook(Book book);
		
		//view book by id
		public Book viewBookById(int id);
		
		//view all books
		public List viewAllBooks(int start,int count);
		
		//update book
		public Book updateBookDetails(int id,Book book);
		
		//to delete book
		public int deleteBook(int id);
		
		public void aggregateFunctions();

}
