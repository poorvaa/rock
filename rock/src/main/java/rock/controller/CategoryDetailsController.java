package rock.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import rock.bean.CategoryDistinctBean;
import rock.bean.CategoryProdTypeIdBean;
import rock.db.model.CategoryDetails;
import rock.error.Error;
import rock.response.CategoryDetailsResponse;
import rock.service.CategoryDetailsService;

@RestController
@RequestMapping("/categorydetails")
public class CategoryDetailsController {
	
private CategoryDetailsService categoryDetailsService;
private Logger logger;
	
	
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
	
	
	//view category by id
	@RequestMapping(value="/{categoryDetailsId}",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<?> viewCategoryById(@PathVariable int categoryDetailsId)
	{
		logger =LoggerFactory.getLogger(CategoryDetails.class);
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is an warn message");
		
		CategoryDetails cd = categoryDetailsService.listCategoryDetails(categoryDetailsId);
		System.out.println("after getting cd");
		if(cd == null)
		{
			
			Error error = new Error(204,"category with id "+categoryDetailsId+" not found");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<CategoryDetails>(cd,HttpStatus.OK);
	}
	
	
	//update category detail
	@RequestMapping(value="/{categoryDetailsId}",method=RequestMethod.PUT,produces="application/json",consumes="application/json")
	@ResponseBody
	public ResponseEntity<?> updateCategoryDetails(@PathVariable int categoryDetailsId, @RequestBody CategoryDetails cd)
	{
		/*if(cd.getId()==0)
		{
			Error error = new Error(400,"id can not be 0");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}*/
		
		if(cd.getCategory()!=null)
		{
			Error error = new Error(400,"category name can not be modified");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		if(cd.getProdTypeDetails()!=null)
		{
			Error error = new Error(400,"prod id cant be modified");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		
		CategoryDetails updatedCategory = categoryDetailsService.updateCategoryDetails(categoryDetailsId,cd);
		
		if(updatedCategory.getState()==0)
		{
			Error error = new Error(404,"no category with id "+categoryDetailsId);
			return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
		}
		
		
		
		return new ResponseEntity<CategoryDetails>(updatedCategory,HttpStatus.OK);
	}
	
	
	//to remove category details
	@RequestMapping(value="/remove/{categoryDetailsId}",method=RequestMethod.GET)
	public ResponseEntity<?> removeCategory(@PathVariable int categoryDetailsId)
	{
		int status = categoryDetailsService.deleteCategoryDetails(categoryDetailsId);
		
		if(status== 0)
		{
			Error error = new Error(404,"no category with  id "+categoryDetailsId);
			return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("Done",HttpStatus.OK);
	}
	
	// to view category and its product type id
	@ResponseBody
	@RequestMapping(value="/categoryandprodtypeid",method=RequestMethod.GET,produces="application/json")
	public List<CategoryProdTypeIdBean> viewCategoryAndProductTypeId()
	{
		List<CategoryProdTypeIdBean> cd = categoryDetailsService.viewCategoryAndProdTypeId();
		
		return cd;
	}
	
	@ResponseBody
	@RequestMapping(value="/distinctcategories",method=RequestMethod.GET,produces="application/json")
	public List<CategoryDistinctBean> viewDistinctCategories()
	{
		List<CategoryDistinctBean> cd = categoryDetailsService.viewDistinctCategories();
		
		return cd;
	}

	
	@ResponseBody
	@RequestMapping(value="/categoryandprodtype",method=RequestMethod.GET,produces="application/json")
	public List joinCategoryAndProductType()
	{
		
		List cd = categoryDetailsService.joinCategoryAndProductType();
		
		return cd;
	}
	
	/*@ResponseBody
	@RequestMapping(value="/categoryandprodtype",method=RequestMethod.GET,produces="application/json")
	public void deleteCategory()
	{
		categoryDetailsService.deleteCategory();
	}*/
	

}
