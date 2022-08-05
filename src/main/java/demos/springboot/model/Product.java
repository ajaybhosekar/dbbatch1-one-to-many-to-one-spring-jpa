package demos.springboot.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="product_details")
public class Product {
	
	@Id
	@Column(name = "productid", scale = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "product_prodid_seq")
	private int prodId;
	
	@Column(name = "productname", length = 30)
	private String prodName;
	
	@Column(name = "brand", length = 30)
	private String brand;
	
	@Column(name = "price", scale = 10, precision = 2)
	private double price;
	
	@Column(name = "dateOfManufacture")
	@Temporal(TemporalType.DATE)
	private Date dateOfManufacture;
	
	@Column(name = "timeOfManufacture")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeOfManufacture;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	@JsonIgnoreProperties("products")
	private Category category;
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int prodId, String prodName, String brand, double price, Date dateOfManufacture) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.brand = brand;
		this.price = price;
		this.dateOfManufacture = dateOfManufacture;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}
	public void setDateOfManufacture(String dateOfManufacture) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.dateOfManufacture = sdf.parse(dateOfManufacture);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public Date getTimeOfManufacture() {
		return timeOfManufacture;
	}
	public void setTimeOfManufacture(String timeOfManufacture) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				
		try {
			Date manufactureTime = sdf.parse(timeOfManufacture);
			
			long totalMillis = this.dateOfManufacture.getTime() + manufactureTime.getTime();
			
			totalMillis = totalMillis +((1000*60*60*5)+(1000*60*30));
			
			this.timeOfManufacture = new Date(totalMillis);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodName=" + prodName + ", brand=" + brand + ", price=" + price + "]";
	}
	
	

}
