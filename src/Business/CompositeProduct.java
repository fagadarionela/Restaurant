package Business;

import java.util.*;


public class CompositeProduct extends MenuItem{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1181729871835166739L;
	int id;
	String nume;
	List<BaseProduct> produseDeBaza = new ArrayList<BaseProduct>();
	public CompositeProduct() {
		nume="";
		produseDeBaza = null;
	}
	public CompositeProduct(String nume) {
		this.nume = nume;
	}
	public CompositeProduct(int id,String nume,BaseProduct produseDeBaza) {
		this.id = id;
		this.nume = nume;
		this.produseDeBaza.add(produseDeBaza);
	}
	public CompositeProduct(int id,String nume,List<BaseProduct> produseDeBaza) {
		this.id = id;
		this.nume = nume;
		this.produseDeBaza = produseDeBaza;
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
	public void setNume(String nume) {
		this.nume = nume;
	}
	public List<BaseProduct> getProduseDeBaza() {
		return produseDeBaza;
	}
	public void setProduseDeBaza(List<BaseProduct> produseDeBaza) {
		this.produseDeBaza = produseDeBaza;
	}
	public int computePrice() {
		int pret = 0;
		for(BaseProduct b:produseDeBaza) {
			pret+=b.getPret();
		}
		return pret;
	}
	@Override
	public String toString() {
		return nume + ": produseDeBaza=" + produseDeBaza;
	}
	@Override
	protected void addProduseDeBaza(BaseProduct produse) {
		this.produseDeBaza.add(produse);
	}
	@Override
	public void setPret(int pret) {
	}
	@Override
	public int getPret() {
		return 0;
	}
	
}
