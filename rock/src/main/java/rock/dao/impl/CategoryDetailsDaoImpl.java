package rock.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rock.dao.CategoryDetailsDao;
import rock.db.model.CategoryDetails;
import rock.db.model.ProductType;

@Repository
public class CategoryDetailsDaoImpl implements CategoryDetailsDao {

	@Autowired 
	SessionFactory sessionFactory;
	
	protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	
	@Override
	public CategoryDetails add(CategoryDetails cd) {

		/*Criteria criteria = getSession().createCriteria(CategoryDetails.class)
										.add(Restrictions.and(
												Restrictions.eq("category",cd.getCategory()),
												Restrictions.eq("prod_type_id", )
												))*/
		
		/*Criteria accountCriteria = getCurrentSession().createCriteria(Account.class,"acc");
		Criteria bookCriteria =  accountCriteria .createCriteria("book","b");
		Criteria orgCriteria =  bookCriteria.createCriteria("organization","org");
		orgCriteria.add(Restrictions.eq("name", "XYZ"));

		ProjectionList properties = Projections.projectionList();
		properties.add(Projections.property("name"));
		properties.add(Projections.property("id"));

		accountCriteria.setProjection(properties);
		accountCriteria.list();*/
		int prodId = cd.getProdTypeDetails().getProdTypeId();
		
		Criteria cr = getSession().createCriteria(ProductType.class)
								  .add(Restrictions.eq("id",prodId));
		
		ProductType pt = (ProductType) cr.uniqueResult();
		cd.setProdTypeDetails(pt);
	
		getSession().persist(cd);
		
		
		
		int catId = cd.getId();
		
		Criteria criteria1 = getSession().createCriteria(CategoryDetails.class)
										.add(Restrictions.eq("id", catId));
		
		CategoryDetails savedCategory = (CategoryDetails) criteria1.uniqueResult();
		
		return savedCategory;
	}


	@Override
	public CategoryDetails listCategoryDetails(int id) {
		
		/*Criteria criteria = getSession().createCriteria(CategoryDetails.class)
				 .add(Restrictions.eq("id", id));

		CategoryDetails pt = (CategoryDetails) criteria.uniqueResult();*/
		
		CategoryDetails pt = (CategoryDetails) getSession().get(CategoryDetails.class, id);
		
		//CategoryDetails pt = (CategoryDetails) getSession().load(CategoryDetails.class, id);
		
	
		
		System.out.println("after query");
		//Hibernate.initialize(pt.getProdTypeDetails());
		//System.out.println("after initialise");
		
	   // boolean status = 	pt.getIsActive();

		if(pt!=null)
		{
			System.out.println("inside if");
		return pt;
		
		}
		System.out.println("outside id");
		return null;

	}

}
