package Business;
import java.util.*;
public class BaseProduct extends MenuItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5260234708415506546L;
	int id;
	String nume;
	int pret;
	public BaseProduct(int id,String nume,int pret){
		super();
		this.id = id;
		this.nume = nume;
		this.pret = pret;	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNume() {
		return nume;
	}
	public int getPret() {
		return pret;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public void setPret(int pret) {
		this.pret = pret;
	}
	public int computePrice() {
		return pret;
	}
	@Override
	public String toString() {
		return nume+" ";
	}
	@Override
	public List<BaseProduct> getProduseDeBaza() {
		ArrayList<BaseProduct> base = new ArrayList<BaseProduct>();
		base.add(this);
		return base;
	}
	@Override
	protected void setProduseDeBaza(List<BaseProduct> produse) {
	}
	@Override
	protected void addProduseDeBaza(BaseProduct produse) {
	}
	
}
