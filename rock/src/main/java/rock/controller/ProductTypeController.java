package rock.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rock.bean.ProductTypeBean;
import rock.db.model.ProductType;
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
	@ResponseBody
	public String addProductType(@RequestBody ProductTypeBean ptb)
	{
		ProductType pt = new ProductType(ptb.getProdTypeName(),ptb.getImgURL(),ptb.getProdTypeDesc(),ptb.getRank());
		int status = pts.addProductType(pt);
		
		if(status == 0)
		{
			return "rank already exists, enter valid one";
		}
		
		return "done";
		
	}
	
	//to view all products
	@RequestMapping(method= RequestMethod.GET,produces="application/json")
	@ResponseBody
	public List<ProductType> viewAllProducts() throws ParseException
	{
		List<ProductType> pt = pts.listAllProductType();
		/*if(pt.size()>0)
		{
			return pt;
		}
		
		return "no data exists";*/
		
		return pt;
		
	}
	
	@RequestMapping(value="/{prodTypeId}",method=RequestMethod.GET,produces="application/json")
	public ProductType viewProductType(@PathVariable int prodTypeId)
	{
		ProductType pt = pts.listProductType(prodTypeId);
		return pt;
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
