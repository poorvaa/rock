package rock.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import rock.db.model.CategoryDetails;
import rock.error.Error;
import rock.service.CategoryDetailsService;

@RestController
@RequestMapping("/categorydetails")
public class CategoryDetailsController {
	
private CategoryDetailsService categoryDetailsService;
	
	
	@Autowired
	public CategoryDetailsController(CategoryDetailsService categoryDetailsService) {
		
		this.categoryDetailsService = categoryDetailsService;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<?> addBookCategory(@RequestBody CategoryDetails categoryDetails,UriComponentsBuilder ucb)
	{
		/*if(bookCategory.getRank()==0)
		{
			Error error = new Error(400,"rank can not be null");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		if(bookCategory.getBookCategory()==null)
		{
			Error error = new Error(400,"book category can not be null");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		*/
	
		System.out.println("rank is: " + categoryDetails.getRank());
		
		//System.out.println("id is " + categoryDetails.getProdTypeDetails().getProdTypeId());
		CategoryDetails cd = categoryDetailsService.add(categoryDetails);
		
		if(cd!=null)
		{
			URI locationUri = ucb.path("/categorydetails/")
								 .path(String.valueOf(cd.getId()))
								 .build()
								 .toUri();
			String location = locationUri.toString();
			
			cd.setLocation(location);
			
			return new ResponseEntity<CategoryDetails>(cd,HttpStatus.OK);
						
		}
		
		Error error = new Error(500,"Category already exists, enter different one");
		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/{categoryDetailsId}",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<?> viewProductType(@PathVariable int categoryDetailsId)
	{
		System.out.println("Inside controller");
		CategoryDetails cd = categoryDetailsService.listCategoryDetails(categoryDetailsId);
		System.out.println("after getting cd");
		if(cd == null)
		{
			System.out.println("inside");
			Error error = new Error(204,"product type with id "+categoryDetailsId+" not found");
			return new ResponseEntity<Error>(error,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<CategoryDetails>(cd,HttpStatus.OK);
	}


}
