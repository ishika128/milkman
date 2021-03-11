package tablejdbc;

import java.sql.Date;

//this is the class which is cointains getters and setters and parametereized constructors


public class StudentBean
{ 
	String cname ;
    String 	mobile;
    String address ;
	Float cqty;
	Float bqty ;
	Float cprice ;
	Float	bprice ;
	Date dos ;
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Float getCqty() {
		return cqty;
	}
	public void setCqty(Float cqty) {
		this.cqty = cqty;
	}
	public Float getBqty() {
		return bqty;
	}
	public void setBqty(Float bqty) {
		this.bqty = bqty;
	}
	public Float getCprice() {
		return cprice;
	}
	public void setCprice(Float cprice) {
		this.cprice = cprice;
	}
	public Float getBprice() {
		return bprice;
	}
	public void setBprice(Float bprice) {
		this.bprice = bprice;
	}
	public Date getDos() {
		return dos;
	}
	public void setDos(Date dos) {
		this.dos = dos;
	}
	public StudentBean(){}
	public StudentBean(String cname, String mobile, String address, Float cqty, Float bqty, Float cprice, Float bprice,
			Date dos) {
		super();
		this.cname = cname;
		this.mobile = mobile;
		this.address = address;
		this.cqty = cqty;
		this.bqty = bqty;
		this.cprice = cprice;
		this.bprice = bprice;
		this.dos = dos;
	}
	
	
	
	
	

}
