package rock.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rock.dao.ProductTypeDao;
import rock.db.model.ProductType;
import rock.service.ProductTypeService;


@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	ProductTypeDao ptDao;
	
	@Override
	@Transactional
	public ProductType addProductType(ProductType pt) {

		pt.setCreatedOn(new Date());
		pt.setModifiedOn(new Date());
		pt.setActive(true);
		ProductType prodType = ptDao.addProductType(pt);
		
		return prodType;
		
	}

	@Override
	@Transactional
	public List<ProductType> listAllProductType() throws ParseException {
		
		List<ProductType> prodTypeList =  ptDao.listAllProductType();
		
		
		return prodTypeList;
	}
	
	
	@Override
	@Transactional
	public ProductType listProductType(int id)
	{
		return ptDao.listProductType(id);
	}

	@Override
	@Transactional
	public int updateProductType(int prodTypeId,ProductType pt) {
		
		return ptDao.updateProductType(prodTypeId,pt);
	}

	@Override
	@Transactional
	public void deleteProductType(int id) {

		ptDao.deleteProductType(id);
		
	}

}
