package Variationslog;

import java.sql.Date;

public class VariationsBean
{
	String cname;
	Date date ;
	Float cowqty ;
	Float buffaloqty ;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getCowqty() {
		return cowqty;
	}
	public void setCowqty(Float cowqty) {
		this.cowqty = cowqty;
	}
	public Float getBuffaloqty() {
		return buffaloqty;
	}
	public void setBuffaloqty(Float buffaloqty) {
		this.buffaloqty = buffaloqty;
	}
	public VariationsBean(){}
	public VariationsBean(String cname, Date date, Float cowqty, Float buffaloqty) {
		super();
		this.cname = cname;
		this.date = date;
		this.cowqty = cowqty;
		this.buffaloqty = buffaloqty;
	}
	

}
