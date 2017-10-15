package rock.service;

import java.text.ParseException;
import java.util.List;

import rock.db.model.ProductType;

public interface ProductTypeService {
	
	
	//to add product type
		public ProductType addProductType(ProductType pt);
		
		//to view all product type
		public List<ProductType> listAllProductType() throws ParseException;
		
		public ProductType listProductType(int id);
		
		//to update a given product type
		public int updateProductType(int prodTypeId,ProductType pt);
		
		//to delete given product type
		public int deleteProductType(int id);

}
