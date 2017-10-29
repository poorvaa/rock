package rock.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import rock.db.model.Book;
import rock.error.Error;
import rock.response.BookResponse;
import rock.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService bookService;

	@RequestMapping(value="/add",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public ResponseEntity<?> addBook(@RequestBody Book book,UriComponentsBuilder ucb)
	{
		if(book.getName()==null)
		{
			Error error = new Error(400," book name can not be null");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		if(book.getPublisher()==null)
		{
			Error error = new Error(400," publisher name can not be null");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		if(book.getPrice()==0)
		{
			Error error = new Error(400," book price can not be 0");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		if(book.getDiscountedPrice()>book.getPrice())
		{
			Error error = new Error(400," discounted price can not be greater than book price");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		if(book.getCategoryDetails()==null)
		{
			Error error = new Error(400," book category id is required");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		if(book.getRank()==0)
		{
			Error error = new Error(400," book rank can not be 0");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		Book addedBook = bookService.addBook(book);
		
		if(addedBook == null)
		{
			Error error = new Error(500," something wen wrong, try again later");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		BookResponse bookResponse = new BookResponse();

		//category id is not valid
		if(addedBook.getState()==-1)
		{
			bookResponse.setMessage("category id is not valid");
			bookResponse.setSuccess(false);
			return new ResponseEntity<BookResponse>(bookResponse,HttpStatus.NOT_ACCEPTABLE);

		}
		
		else if(addedBook.getState()==-2)
		{
			bookResponse.setMessage("book with this name already exist, name has to be unique");
			bookResponse.setSuccess(false);
			return new ResponseEntity<BookResponse>(bookResponse,HttpStatus.NOT_ACCEPTABLE);
		}
		
		else
		{
			bookResponse.setMessage("book added");
			bookResponse.setSuccess(true);
			URI locationUri = ucb.path("/book/")
					 .path(String.valueOf(addedBook.getId()))
					 .build()
					 .toUri();
	
			String location = locationUri.toString();
			System.out.println("Location: "+location);
			addedBook.setLocation(location);
			System.out.println(" setLocation: "+addedBook.getLocation());
			bookResponse.setBook(addedBook);
			System.out.println(" getLocation: "+bookResponse.getBook().getLocation());
			return new ResponseEntity<BookResponse>(bookResponse,HttpStatus.OK);
		}
	}
	
	
	
		//view book by id
		@RequestMapping(value="/{bookId}",method=RequestMethod.GET,produces="application/json")
		public ResponseEntity<?> viewBookById(@PathVariable int bookId)
		{
			
			
			Book book = bookService.viewBookById(bookId);
			
			if(book== null)
			{
				
				Error error = new Error(204,"book with id "+bookId+" not found");
				return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Book>(book,HttpStatus.OK);
		}
		
		
		//view all books
		@RequestMapping(method=RequestMethod.GET,produces="application/json")
		public ResponseEntity<?> viewAllBooks(@RequestParam("start")int start,
											  @RequestParam("count") int count)
		{		
			 List  books = bookService.viewAllBooks(start,count);
					
			if(books.size()>0)
			{
				return new ResponseEntity<List>(books,HttpStatus.OK);
			}
			
						
				Error error = new Error(204,"no book found");
				return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
					
		}	
		
		
		
		//update category detail
		@RequestMapping(value="/{bookId}",method=RequestMethod.PUT,produces="application/json",consumes="application/json")
		@ResponseBody
		public ResponseEntity<?> updateCategoryDetails(@PathVariable int bookId, @RequestBody Book book)
		{
			System.out.println("inside controller");
			/*if(cd.getId()==0)
			{
				Error error = new Error(400,"id can not be 0");
				return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
			}*/
			
			if(book.getName()!=null)
			{
				Error error = new Error(400,"book name can not be modified");
				return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
			}
			
			if(book.getCategoryDetails()!=null)
			{
				Error error = new Error(400,"category id cant be modified");
				return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
			}
			
			
			Book updatedBook = bookService.updateBookDetails(bookId, book);
			
			if(updatedBook.getState()==-1)
			{
				Error error = new Error(404,"no book with id "+bookId);
				return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
			}
			
			
			
			return new ResponseEntity<Book>( updatedBook,HttpStatus.OK);
		}
		
		
		
		//delete book
		@RequestMapping(value="/remove/{bookId}",method=RequestMethod.GET)
		public ResponseEntity<?> deleteBook(@PathVariable int bookId)
		{
			int status = bookService.deleteBook(bookId);
			
			if(status== 0)
			{
				Error error = new Error(404,"no book with  id "+bookId);
				return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<String>("Done",HttpStatus.OK);
		}
		
	
}
