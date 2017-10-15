package rock.dao;

import java.util.List;

import rock.db.model.ProductType;

public interface ProductTypeDao {
	
	//to add product type
	public ProductType addProductType(ProductType pt);
	
	//to view all product type
	public List<ProductType> listAllProductType();
	
	public ProductType listProductType(int id);
	
	//to update a given product type
	public int updateProductType(int prodTypeId,ProductType pt);
	
	//to delete given product type
	public int deleteProductType(int id);
	
	


}
