package org.healthcare.remedies.homeremediesonline.model;

import java.util.List;

import org.healthcare.remedies.homeremediesonline.entity.Disease;
import org.healthcare.remedies.homeremediesonline.entity.Product;

public class DiseaseInfo {
	 private String code; 
	 

	private String name;
	 private String remedies;
	 private String pcode;
	 List<Product> product;
	 
	 private boolean newProduct=false;
	 
	 public DiseaseInfo() {
		 
	 }
	 
	 

	public DiseaseInfo(String code, String name, String remedies, String pcode) {
		super();
		this.code = code;
		this.name = name;
		this.remedies = remedies;
		this.pcode = pcode;
	}
	



	public DiseaseInfo(List<Product> product) {
		super();
		this.product = product;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemedies() {
		return remedies;
	}

	public void setRemedies(String remedies) {
		this.remedies = remedies;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	public boolean isNewProduct() {
		return newProduct;
	}



	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}



	public List<Product> getProduct() {
		return product;
	}



	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
	
}
