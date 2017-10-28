package rock.dao.impl;

import java.util.Date;
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

import rock.bean.CategoryDistinctBean;
import rock.bean.CategoryProdTypeBean;
import rock.bean.CategoryProdTypeIdBean;
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
	public CategoryDetails updateCategoryDetails(int id,
			CategoryDetails categoryDetails) {
		
		CategoryDetails updatedCategoryDetails = (CategoryDetails)getSession().get(CategoryDetails.class, id);
		
		//check if id is invalid
		if(updatedCategoryDetails==null)
		{
			categoryDetails.setState(0);
			return categoryDetails;
		}
		
		if(categoryDetails.getRank()!=0)
		{
			updatedCategoryDetails.setRank(categoryDetails.getRank());
		}
		updatedCategoryDetails.setModifiedOn(new Date());
		updatedCategoryDetails.setState(1);
		
		return updatedCategoryDetails ;
	}

	
	@Override
	public int deleteCategoryDetails(int categoryDetailsId) {
		
		CategoryDetails cd =(CategoryDetails) getSession().get(CategoryDetails.class, categoryDetailsId);
		
		if(cd==null)
		{
			return 0;
		}
		
		else
		{
			cd.setIsActive(false);
			cd.setModifiedOn(new Date());
			return 1;
		}
		
	}


	@Override
	public List<CategoryProdTypeIdBean> viewCategoryAndProdTypeId() {
		
		Criteria criteria = getSession().createCriteria(CategoryDetails.class);
		
		ProjectionList properties = Projections.projectionList();
		properties.add(Projections.property("category"), "category");
		properties.add(Projections.property("prodTypeDetails.prodTypeId"), "prodId");
		
		criteria.setProjection(properties);
		criteria.setResultTransformer(Transformers.aliasToBean(CategoryProdTypeIdBean.class));
		
		
		
		List<CategoryProdTypeIdBean> cd =  criteria.list();
		
		return cd;
	}


	@Override
	public List<CategoryDistinctBean> viewDistinctCategories() {
		
		/*Criteria criteria = getSession().createCriteria(CategoryDetails.class)
										.setProjection(Projections.distinct(Projections.property("category")));
		*/
		Criteria criteria = getSession().createCriteria(CategoryDetails.class)
										.setProjection(
										Projections.distinct(
										Projections.projectionList()
										.add(Projections.property("category"), "categoryName") ));
			 


		
		
		criteria.setResultTransformer(Transformers.aliasToBean(CategoryDistinctBean.class));

		List categories = criteria.list();
		
		return categories;
	}


	@Override
	public List<CategoryProdTypeBean> joinCategoryAndProductType() {

		
		Criteria criteria = getSession().createCriteria(CategoryDetails.class,"cd")
										.createAlias("cd.prodTypeDetails", "prodTypeDetails");

		ProjectionList properties = Projections.projectionList();
		properties.add(Projections.property("cd.category"), "category");
		properties.add(Projections.property("prodTypeDetails.prodTypeName"), "productType");
		properties.add(Projections.property("cd.rank"), "rank");
		
		
		criteria.setProjection(properties);
		criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
		
		
		List<CategoryProdTypeBean> cd = criteria.list();
		
		
		
		return cd;
	}


	

	

}
