package org.healthcare.remedies.homeremediesonline.dao.impl;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.healthcare.remedies.homeremediesonline.dao.ProductDAO;
import org.healthcare.remedies.homeremediesonline.entity.Account;
import org.healthcare.remedies.homeremediesonline.entity.Disease;
import org.healthcare.remedies.homeremediesonline.entity.Product;
import org.healthcare.remedies.homeremediesonline.entity.SignUp;
import org.healthcare.remedies.homeremediesonline.model.DiseaseInfo;
import org.healthcare.remedies.homeremediesonline.model.PaginationResult;
import org.healthcare.remedies.homeremediesonline.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
 
// Transactional for Hibernate
@Transactional
public class ProductDAOImpl implements ProductDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    @Override
    public Product findProduct(String code) {
    	System.out.println("In findproduct code ="+code);
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("code", code));
        return (Product) crit.uniqueResult();
    }
    @Override
    public SignUp findUser(String email) {
    	System.out.println("In findUser code ="+email);
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(SignUp.class);
        crit.add(Restrictions.eq("email", email));
        return (SignUp) crit.uniqueResult();
    }
    
    @Override
    public List<Product> findAllProduct() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "Select distinct "+
        	    "p.code from " 
        	    + Product.class.getName() + " p ";
        Query query = session.createQuery(sql);
        return query.list();
        //return session.createQuery("SELECT a FROM Product a", Product.class).getResultList(); 
    }
    
    @Override
    public Disease findDisease(String code) {
    	System.out.println("In findDisease code ="+code);
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Disease.class);
        System.out.println("code in findDisease"+code);
        crit.add(Restrictions.eq("dcode", code));
        return (Disease) crit.uniqueResult();
    }
 
    @Override
    public ProductInfo findProductInfo(String code) {
        Product product = this.findProduct(code);
        if (product == null) {
            return null;
        }
        return new ProductInfo(product.getCode(), product.getName(), product.getPrice());
    }
    @Override
    public SignUp findUserDetails(String email) {
        SignUp signUp = this.findUser(email);
        if (signUp == null) {
            return null;
        }
        return new SignUp(signUp.getFname(), signUp.getLname(), signUp.getEmail(), signUp.getContact(), signUp.getPasswd(), signUp.getCpasswd(), signUp.getCreateDate());
    }
    @Override
    public DiseaseInfo findDiseaseInfo(String code) {
        Disease disease = this.findDisease(code);
        if (disease == null) {
            return null;
        }
        
        return new DiseaseInfo(disease.getDcode(),disease.getName(),disease.getRemedies(),disease.getPcode());
    }
    
    @Override
    public DiseaseInfo getAllProductCodes() {
        List<Product> productList = this.findAllProduct();
        System.out.println("productList"+productList);
        if (productList.size() ==0) {
            return null;
        }
        
        return new DiseaseInfo(productList);
    }
 
    @Override
    public void save(ProductInfo productInfo) {
        String code = productInfo.getCode();
 
        Product product = null;
 
        boolean isNew = false;
        if (code != null) {
            product = this.findProduct(code);
        }
        if (product == null) {
            isNew = true;
            product = new Product();
            product.setCreateDate(new Date());
        }
        product.setCode(code);
        product.setName(productInfo.getName());
        product.setPrice(productInfo.getPrice());
 
        if (productInfo.getFileData() != null) {
            byte[] image = productInfo.getFileData().getBytes();
            if (image != null && image.length > 0) {
                product.setImage(image);
            }
        }
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(product);
        }
        // If error in DB, Exceptions will be thrown out immediately
        // Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
        this.sessionFactory.getCurrentSession().flush();
       
    }
    @Override
    public void saveUserDetails(SignUp signUp) {
        
    		signUp.setCreateDate(new Date());
            this.sessionFactory.getCurrentSession().persist(signUp);
        
            Account account = new Account();
            account.setUserName(signUp.getEmail());
            account.setPassword(signUp.getPasswd());
            account.setActive(true);
            account.setUserRole("EMPLOYEE");
            this.sessionFactory.getCurrentSession().persist(account);
            
            
            
        // If error in DB, Exceptions will be thrown out immediately
        // Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
        this.sessionFactory.getCurrentSession().flush();
       
    }
    
    @Override
    public void updateUserDetails(SignUp signUp) {
        
    		signUp.setCreateDate(new Date());
            this.sessionFactory.getCurrentSession().update(signUp);
            
            Account account = new Account();
            account.setUserName(signUp.getEmail());
            account.setPassword(signUp.getPasswd());
            account.setActive(true);
            account.setUserRole("EMPLOYEE");
            this.sessionFactory.getCurrentSession().update(account);
       
        // If error in DB, Exceptions will be thrown out immediately
        // Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
        this.sessionFactory.getCurrentSession().flush();
       
    }
    
    @Override
    public void deleteUserDetails(String email) {
        
	    	 SignUp signUp = this.findUser(email);
	    	 System.out.println("signup:"+signUp);
	         if (signUp != null) {
	        
            this.sessionFactory.getCurrentSession().delete(signUp);
            
            Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Account.class);
            crit.add(Restrictions.eq("userName", email));
            Account uniqueAccountDetails = (Account)crit.uniqueResult();
            this.sessionFactory.getCurrentSession().delete(uniqueAccountDetails);
       
        // If error in DB, Exceptions will be thrown out immediately
        // Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
            this.sessionFactory.getCurrentSession().flush();

	         }
       
    }
    @Override
    public void saveDisease(DiseaseInfo diseaseInfo) {
        String code = diseaseInfo.getCode();
 
        Disease disease = null;
 
        boolean isNew = false;
        if (code != null) {
        	disease = this.findDisease(code);
        }
        if (disease == null) {
            isNew = true;
            disease = new Disease();
            disease.setCreateDate(new Date());
        }
        disease.setDcode(code);
        disease.setName(diseaseInfo.getName());
        disease.setRemedies(diseaseInfo.getRemedies());
        disease.setPcode(diseaseInfo.getPcode());
        System.out.println(disease);
        //if (isNew) {
            this.sessionFactory.getCurrentSession().saveOrUpdate(disease);
        //}
        // If error in DB, Exceptions will be thrown out immediately
        // Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
        this.sessionFactory.getCurrentSession().flush();
       
    }
    
    @Override
    public List<ProductInfo> queryDiseaseName(String diseasename) {
    	System.out.println("diseasename-->"+diseasename);
    	/*String sql = "Select distinct new " + ProductInfo.class.getName() 
                + "(d.name,p.name) from "
                + Product.class.getName() + " p ,"+Disease.class.getName() + " d Where p.code =d.pcode ";
        
        sql += " and lower(d.name) like :diseasename or lower(p.name) like :diseasename";*/
   
        String sql = "Select distinct "+
    "case "+ 
    "when lower(d.name) like :diseasename then d.name "+
    "when lower(p.name) like :diseasename then p.name "+
    "end from " 
    + Product.class.getName() + " p ,"+Disease.class.getName() + " d Where p.code =d.pcode ";
    sql += " and lower(d.name) like :diseasename or lower(p.name) like :diseasename";
        //System.out.println("sql-->"+sql);
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        if (diseasename != null && diseasename.length() > 0) {
            query.setParameter("diseasename", "%" + diseasename.toLowerCase()+"%");
        }
        List<ProductInfo> diseaseList = new ArrayList<ProductInfo>();
        
        for (Object o : query.list()) {
        	System.out.println("The value is =="+o);
        	ProductInfo pi = new ProductInfo();
        	pi.setDname(o.toString());
        	diseaseList.add(pi);
        }
        //diseaseList.forEach(System.out::println);
        return diseaseList;
    }
    
    @Override
    public PaginationResult<ProductInfo> queryProductsByDiseaseName(int page, int maxResult, int maxNavigationPage,
            String diseasename) {
    	System.out.println("page-->"+page);
    	System.out.println("maxResult-->"+maxResult);
    	System.out.println("maxNavigationPage-->"+maxNavigationPage);
    	System.out.println("diseasename-->"+diseasename);
    	
        /*String sql = "Select new " + ProductInfo.class.getName() 
                + "(p.code, p.name, p.price) " +"new " + DiseaseInfo.class.getName() 
                + "(d.name, d.remedies) "+ " from "//
                + Product.class.getName() + " p ,"+Disease.class.getName() + "d Where p.code =d.pcode ";*/
        
       /* String sql = "Select p.code, p.name, p.price ,d.name, d.remedies from "
                + Product.class.getName() + " p ,"+Disease.class.getName() + " d Where p.code = d.pcode ";*/
    	String sql = "Select distinct new " + ProductInfo.class.getName() 
                + "(p.code, p.name, p.price,d.name, d.remedies) from "
                + Product.class.getName() + " p ,"+Disease.class.getName() + " d Where p.code =d.pcode ";
        
        sql += " and (lower(d.name) = :diseasename or lower(p.name) = :diseasename)";
   
        
        //System.out.println("sql-->"+sql);
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        if (diseasename != null && diseasename.length() > 0) {
            query.setParameter("diseasename",  diseasename.toLowerCase());
        }
        //System.out.println(query.list());
        
       
        return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
            String likeName) {
    	System.out.println("page-->"+page);
    	System.out.println("maxResult-->"+maxResult);
    	System.out.println("maxNavigationPage-->"+maxNavigationPage);
    	System.out.println("likeName-->"+likeName);
    	
        String sql = "Select new " + ProductInfo.class.getName() //
                + "(p.code, p.name, p.price) " + " from "//
                + Product.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        sql += " order by p.createDate desc ";
        //
        
        System.out.println("sql-->"+sql);
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
    }
    
    @Override
    public PaginationResult<Product> getAllProductList(int page, int maxResult, int maxNavigationPage) {
    	System.out.println("page-->"+page);
    	System.out.println("maxResult-->"+maxResult);
    	System.out.println("maxNavigationPage-->"+maxNavigationPage);
    	
        String sql = "Select new " + Product.class.getName()//
                + "(ord.code, ord.name, ord.price, ord.image, "
                + " ord.createDate) " + " from "
                + Product.class.getName() + " ord "//
                + " order by ord.createDate desc";
        //
        
        System.out.println("sql-->"+sql);
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        
        
        return new PaginationResult<Product>(query, page, maxResult, maxNavigationPage);
    }
    
    @Override
    public PaginationResult<Disease> getAllDiseaseList(int page, int maxResult, int maxNavigationPage) {
    	System.out.println("page-->"+page);
    	System.out.println("maxResult-->"+maxResult);
    	System.out.println("maxNavigationPage-->"+maxNavigationPage);
    	
        String sql = "Select new " + Disease.class.getName()//
                + "(ord.dcode, ord.name, ord.remedies, ord.createDate, "
                + " ord.pcode) " + " from "
                + Disease.class.getName() + " ord "//
                + " order by ord.createDate desc";
        //
        
        System.out.println("sql-->"+sql);
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        
        
        return new PaginationResult<Disease>(query, page, maxResult, maxNavigationPage);
    }
 
    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        return queryProducts(page, maxResult, maxNavigationPage, null);
    }
    
}