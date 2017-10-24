package rock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rock.bean.CategoryDetailsBean;
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

		
		int prodId = cd.getProdTypeDetails().getProdTypeId();
		
		// find product with given id
		Criteria cr = getSession().createCriteria(ProductType.class)
								  .add(Restrictions.eq("id",prodId));
		
		ProductType pt = (ProductType) cr.uniqueResult();
		
		if(pt == null)
		{
			cd.setState(1);
			return cd;
		}
		else
		{
			cd.setProdTypeDetails(pt);
		}
		
		//check if a row with category and product id already exists in db
		Criteria cr2 = getSession().createCriteria(CategoryDetails.class)
									.add(Restrictions.eq("prodTypeDetails.prodTypeId", prodId))
									.add(Restrictions.eq("category", cd.getCategory()));
		
		CategoryDetails catDetails = (CategoryDetails) cr2.uniqueResult();
		
		if(catDetails!=null)
		{
			cd.setState(2);
			return cd;
		}
	
		else
		{
			
			getSession().persist(cd);
		}
		
		
		
		int catId = cd.getId();
		
		Criteria criteria1 = getSession().createCriteria(CategoryDetails.class)
										.add(Restrictions.eq("id", catId));
		
		CategoryDetails savedCategory = (CategoryDetails) criteria1.uniqueResult();
		
		return savedCategory;
	}


	@Override
	public CategoryDetails listCategoryDetails(int id) {
		
		CategoryDetails pt = (CategoryDetails) getSession().get(CategoryDetails.class, id);
		
		
		if(pt!=null)
		{
			return pt;
		}
		
		return null;

	}


	@Override
	public List<CategoryDetailsBean> viewCategoryAndProdTypeId() {
		
		Criteria criteria = getSession().createCriteria(CategoryDetails.class);
		
		ProjectionList properties = Projections.projectionList();
		properties.add(Projections.property("category"), "category");
		properties.add(Projections.property("prodTypeDetails.prodTypeId"), "prodId");
		
		criteria.setProjection(properties);
		criteria.setResultTransformer(Transformers.aliasToBean(CategoryDetailsBean.class));
		
		
		
		List<CategoryDetailsBean> cd =  criteria.list();
		
		return cd;
	}


	@Override
	public List<String> viewDistinctCategories() {
		
		Criteria criteria = getSession().createCriteria(CategoryDetails.class)
										.setProjection(Projections.distinct(Projections.property("category")));

		List categories = criteria.list();
		
		return categories;
	}


	@Override
	public List<CategoryDetails> joinCategoryAndProductType() {

		
		Criteria criteria = getSession().createCriteria(CategoryDetails.class,"cd")
										.createAlias("cd.prodTypeDetails", "prodTypeDetails");

		ProjectionList properties = Projections.projectionList();
		properties.add(Projections.property("cd.category"), "category");
		properties.add(Projections.property("prodTypeDetails.prodTypeName"), "productType");
		properties.add(Projections.property("cd.rank"), "rank");
		
		
		criteria.setProjection(properties);
		/*Criteria criteria = getSession().createCriteria(CategoryDetails.class);
		criteria.setFetchMode("ProductType", FetchMode.JOIN);
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		*/
		
		
		List<CategoryDetails> cd = criteria.list();
		
		
		return cd;
	}

}
