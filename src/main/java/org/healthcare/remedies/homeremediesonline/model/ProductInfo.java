package org.healthcare.remedies.homeremediesonline.model;
 
import org.healthcare.remedies.homeremediesonline.entity.Disease;
import org.healthcare.remedies.homeremediesonline.entity.Product;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
 
public class ProductInfo {
    private String code;
    private String name;
    private double price;
    private String dname;
    private String dremedies;
   
 
   

	private boolean newProduct=false;
 
    // Upload file.
    private CommonsMultipartFile fileData;
 
    public ProductInfo() {
    }
    
    public ProductInfo(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
    }
 
    public ProductInfo(Product product,Disease disease) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
        this.dname = disease.getName();
        this.dremedies = disease.getRemedies();
    }
    
    
 
   

	// Không thay đổi Constructor này,
    // nó được sử dụng trong Hibernate query.
    
 
	public String getCode() {
        return code;
    }
	public ProductInfo(String dname,String name) {
		super();
		this.dname = dname;
		this.name = name;
	}
	

	public ProductInfo(String code, String name, double price, String dname, String dremedies) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.dname = dname;
		this.dremedies = dremedies;
	}

	public ProductInfo(String code, String name, double price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDremedies() {
		return dremedies;
	}

	public void setDremedies(String dremedies) {
		this.dremedies = dremedies;
	}

	public void setCode(String code) {
        this.code = code;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public CommonsMultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
 
    public boolean isNewProduct() {
        return newProduct;
    }
 
    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }
 
}