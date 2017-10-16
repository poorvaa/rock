package rock.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import rock.db.model.BookCategory;
import rock.error.Error;
import rock.service.BookCategoryService;

@RestController
@RequestMapping("/bookcategory")
public class BookCategoryController {
	
	private BookCategoryService bookCategoryService;
	
	
	@Autowired
	public BookCategoryController(BookCategoryService bookCategoryService) {
		
		this.bookCategoryService = bookCategoryService;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<?> addBookCategory(@RequestBody BookCategory bookCategory,UriComponentsBuilder ucb)
	{
		if(bookCategory.getRank()==0)
		{
			Error error = new Error(400,"rank can not be null");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		if(bookCategory.getBookCategory()==null)
		{
			Error error = new Error(400,"book category can not be null");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		BookCategory bc = bookCategoryService.add(bookCategory);
		
		if(bc!=null)
		{
			URI locationUri = ucb.path("/bookcategory/")
								 .path(String.valueOf(bc.getId()))
								 .build()
								 .toUri();
			String location = locationUri.toString();
			
			bc.setLocation(location);
			
			return new ResponseEntity<BookCategory>(bc,HttpStatus.OK);
						
		}
		
		Error error = new Error(500,"Category already exists, enter different one");
		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
