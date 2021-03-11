package billingconsole;

public class billingbean
{
	String name;
	String dos;
	String doe;
	
	Float cowqty;
	Float bfqty;
	Float amount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getDoe() {
		return doe;
	}
	public void setDoe(String doe) {
		this.doe = doe;
	}
	
	public Float getCowqty() {
		return cowqty;
	}
	public void setCowqty(Float cowqty) {
		this.cowqty = cowqty;
	}
	public Float getBfqty() {
		return bfqty;
	}
	public void setBfqty(Float bfqty) {
		this.bfqty = bfqty;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public billingbean(){}
	public billingbean(String name, String dos, String doe,  Float cowqty, Float bfqty,Float amount) {
		super();
		this.name = name;
		this.dos = dos;
		this.doe = doe;
		
		this.cowqty = cowqty;
		this.bfqty = bfqty;
		this.amount = amount;
	}
	

}
