package rock.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class ProductTypeBean implements Serializable {
	
	private String prodTypeName;
	private String imgURL;
	private String prodTypeDesc;
	private int rank;
	
	
	
	public ProductTypeBean() {
		super();
	}
	public ProductTypeBean(String prodTypeName, String imgURL,
			String prodTypeDesc, int rank) {
		
		this.prodTypeName = prodTypeName;
		this.imgURL = imgURL;
		this.prodTypeDesc = prodTypeDesc;
		this.rank = rank;
	}
	public String getProdTypeName() {
		return this.prodTypeName;
	}
	public String getImgURL() {
		return this.imgURL;
	}
	public String getProdTypeDesc() {
		return this.prodTypeDesc;
	}
	public int getRank() {
		return this.rank;
	}
	public void setProdTypeName(String prodTypeName) {
		this.prodTypeName = prodTypeName;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public void setProdTypeDesc(String prodTypeDesc) {
		this.prodTypeDesc = prodTypeDesc;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	

}
