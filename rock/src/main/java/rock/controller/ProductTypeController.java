package rock.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import rock.bean.ProductTypeBean;
import rock.db.model.ProductType;
import rock.error.Error;
import rock.service.ProductTypeService;

@RestController
@RequestMapping("/producttype")
public class ProductTypeController {
	
	private ProductTypeService pts;
	
	@Autowired
	public ProductTypeController(ProductTypeService pts) {
		this.pts = pts;
	}

	//to add product type
	@RequestMapping(value="/add", method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<?> addProductType(@RequestBody ProductTypeBean ptb,UriComponentsBuilder ucb)
	{
		if(ptb.getRank()==0)
		{
			Error error = new Error(400,"rank can not be null");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
		}
		
		if(ptb.getProdTypeName()==null)
		{
			Error error = new Error(400,"product name can not be null");
			return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
		}
		
		ProductType pt = new ProductType(ptb.getProdTypeName(),ptb.getImgURL(),ptb.getProdTypeDesc(),ptb.getRank());
		ProductType prodType = pts.addProductType(pt);
		
		if(prodType!=null)
		{
			HttpHeaders headers = new HttpHeaders();
			URI locationUri = ucb.path("/producttype/")
								 .path(String.valueOf(prodType.getProdTypeId()))
								 .build()
								 .toUri();
			headers.setLocation(locationUri);
			
			ResponseEntity<ProductType> responseEntity = new ResponseEntity<ProductType>(prodType,headers,HttpStatus.CREATED);
			return responseEntity;
		}
		
		Error error = new Error(404,"Rank already exists, enter different one");
		return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
		
		
	}
	
	//to view all products
		@RequestMapping(method= RequestMethod.GET,produces="application/json")
		public ResponseEntity<?> viewAllProducts() throws ParseException
		{
			List<ProductType> pt = pts.listAllProductType();
			if(pt.size()>0)
			{
				return new ResponseEntity<List<ProductType>>(pt,HttpStatus.OK);
			}
			
			Error error = new Error(404,"no products exist");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
				
		}
	
	
	
	@RequestMapping(value="/{prodTypeId}",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<?> viewProductType(@PathVariable int prodTypeId)
	{
		ProductType pt = pts.listProductType(prodTypeId);
		if(pt == null)
		{
			Error error = new Error(404,"product type with id "+prodTypeId+" not found");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ProductType>(pt,HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value="/{prodTypeId}",method=RequestMethod.PUT)
	@ResponseBody
	public String updateProdType(@PathVariable int prodTypeId, @RequestBody ProductType pt)
	{
		
		pts.updateProductType(prodTypeId,pt);
		return "done!!!!";
	}
	
	@RequestMapping(value="/remove/{prodTypeId}",method=RequestMethod.GET)
	@ResponseBody
	public String removeProductType(@PathVariable int prodTypeId)
	{
		pts.deleteProductType(prodTypeId);
		
		return "done";
	}

}
