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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import rock.bean.CategoryDetailsBean;
import rock.db.model.CategoryDetails;
import rock.error.Error;
import rock.response.CategoryDetailsResponse;
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
	public ResponseEntity<?> addCategory(@RequestBody CategoryDetails categoryDetails,UriComponentsBuilder ucb)
	{
		if(categoryDetails.getRank()==0)
		{
			Error error = new Error(400,"rank can not be null");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		if(categoryDetails.getCategory()==null)
		{
			Error error = new Error(400,"category can not be null");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		if(categoryDetails.getProdTypeDetails()==null)
		{
			Error error = new Error(400,"product type can not be null");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
	
		CategoryDetails catDetails = categoryDetailsService.add(categoryDetails);
		
		if(catDetails == null)
		{
			Error error = new Error(500,"Something went wrong, try again");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		CategoryDetailsResponse response = new CategoryDetailsResponse();
		
		
		
		if(catDetails.getState()==1)
		{
			response.setSuccess(false);
			response.setMessage("Product id is not valid.");
			return new ResponseEntity<CategoryDetailsResponse>(response,HttpStatus.NOT_ACCEPTABLE);
		}
		
		else if(catDetails.getState()==2)
		{
			response.setSuccess(false);
			response.setMessage("product id with given category already exists");
			return new ResponseEntity<CategoryDetailsResponse>(response,HttpStatus.NOT_ACCEPTABLE);
		}

		
		else
		{
			URI locationUri = ucb.path("/categorydetails/")
								 .path(String.valueOf(catDetails.getId()))
								 .build()
								 .toUri();
			String location = locationUri.toString();
			
			catDetails.setLocation(location);
			response.setCategoryDetails(catDetails);
			response.setSuccess(true);
			response.setMessage("Data successfully inserted");
			
			return new ResponseEntity<CategoryDetailsResponse>(response,HttpStatus.OK);
						
		}
		
		
		
			
			
		
		
	}
	
	@RequestMapping(value="/{categoryDetailsId}",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<?> viewCategoryById(@PathVariable int categoryDetailsId)
	{
		
		CategoryDetails cd = categoryDetailsService.listCategoryDetails(categoryDetailsId);
		System.out.println("after getting cd");
		if(cd == null)
		{
			
			Error error = new Error(204,"category with id "+categoryDetailsId+" not found");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<CategoryDetails>(cd,HttpStatus.OK);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/allcategories",method=RequestMethod.GET,produces="application/json")
	public List<CategoryDetailsBean> viewCategoryAndProductTypeId()
	{
		List<CategoryDetailsBean> cd = categoryDetailsService.viewCategoryAndProdTypeId();
		
		return cd;
	}
	
	@ResponseBody
	@RequestMapping(value="/distinctcategories",method=RequestMethod.GET,produces="application/json")
	public List<String> viewDistinctCategories()
	{
		List<String> cd = categoryDetailsService.viewDistinctCategories();
		
		return cd;
	}

	
	@ResponseBody
	@RequestMapping(value="/categoryandprodtype",method=RequestMethod.GET,produces="application/json")
	public List<CategoryDetails> joinCategoryAndProductType()
	{
		List<CategoryDetails> cd = categoryDetailsService.joinCategoryAndProductType();
		
		return cd;
	}

}
