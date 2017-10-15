package rock.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rock.dao.ProductTypeDao;
import rock.db.model.ProductType;

@Repository
public class ProductTypeDaoImpl implements ProductTypeDao {

	//private EntityManager em;
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	
	@Override
	public ProductType addProductType(ProductType pt) {
	
		List prod = getSession().createCriteria(ProductType.class)
				 				.add(Restrictions.eq("rank", pt.getRank()))
				 				.list();
		
		if(prod.size()==0)
		{
			getSession().persist(pt);
			//getSession().flush();
			int prodId = pt.getProdTypeId();
			System.out.println("id is :"+prodId);
			Criteria criteria = getSession().createCriteria(ProductType.class)
					 .add(Restrictions.eq("id", prodId));

			ProductType prodType = (ProductType) criteria.uniqueResult();
			return prodType;
		}
		
		return null;
		
		
		
		//em.persist(pt);
		
	}

	
	
	
	@Override
	public List<ProductType> listAllProductType() {
		
		
		List prodTypes = getSession().createCriteria(ProductType.class)
										 .add(Restrictions.eq("isActive", true))
										 .addOrder(Order.asc("rank"))
										 .list();
		
		
			return prodTypes;
		
		
		
		
	}
	
	@Override
	public ProductType listProductType(int id)
	{
		Criteria criteria = getSession().createCriteria(ProductType.class)
									 .add(Restrictions.eq("id", id));
		
		ProductType pt = (ProductType) criteria.uniqueResult();
		
		if(pt!=null)
		{
			return pt;
		}
		
		return null;
									 
	}

	@Override
	public int updateProductType(int prodTypeId,ProductType pt) {
		
		ProductType oldPt = (ProductType)getSession().get(ProductType.class, prodTypeId);
		oldPt.setProdTypeName(pt.getProdTypeName());
		oldPt.setActive(true);
		oldPt.setModifiedOn(new Date());
		//getSession().update(oldPt);
		return 1;
		
	}

	@Override
	public void deleteProductType(int id) {
		
		ProductType pt = (ProductType)getSession().get(ProductType.class, id);
		pt.setModifiedOn(new Date());
		pt.setActive(false);


		
	}
	
	

}
