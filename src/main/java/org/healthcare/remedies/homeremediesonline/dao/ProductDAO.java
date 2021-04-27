package org.healthcare.remedies.homeremediesonline.dao;
 
import java.util.List;

import org.healthcare.remedies.homeremediesonline.entity.Disease;
import org.healthcare.remedies.homeremediesonline.entity.Product;
import org.healthcare.remedies.homeremediesonline.entity.SignUp;
import org.healthcare.remedies.homeremediesonline.model.DiseaseInfo;
import org.healthcare.remedies.homeremediesonline.model.PaginationResult;
import org.healthcare.remedies.homeremediesonline.model.ProductInfo;
 
public interface ProductDAO {
 
    
    
    public Product findProduct(String code);
    
    public ProductInfo findProductInfo(String code) ;
  
    
    public PaginationResult<ProductInfo> queryProducts(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
                       int maxNavigationPage, String likeName);
    
    public PaginationResult<Product> getAllProductList(int page,
            int maxResult, int maxNavigationPage  );
 
    public void save(ProductInfo productInfo);

	public PaginationResult<ProductInfo> queryProductsByDiseaseName(int page, int maxResult, int maxNavigationPage,
			String diseasename);

	public List<ProductInfo> queryDiseaseName(String diseasename);

	public void saveDisease(DiseaseInfo diseaseInfo);

	public Disease findDisease(String code);

	public DiseaseInfo findDiseaseInfo(String code);

	public List<Product> findAllProduct();

	public DiseaseInfo getAllProductCodes();

	public void saveUserDetails(SignUp signUp);

	public SignUp findUserDetails(String email);

	public SignUp findUser(String email);

	public void updateUserDetails(SignUp signUp);

	public void deleteUserDetails(String email);

	public PaginationResult<Disease> getAllDiseaseList(int page, int maxResult, int maxNavigationPage);
    
}