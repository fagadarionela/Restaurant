package Business;

import java.io.Serializable;
import java.util.*;

public abstract class  MenuItem implements Serializable {
	private static final long serialVersionUID = 3695638750466746605L;
	public abstract int computePrice() ;
	public abstract int getId();
	public abstract List<BaseProduct> getProduseDeBaza();
	protected abstract void setProduseDeBaza(List<BaseProduct> produse);
	public abstract void setNume(String nume);
	public abstract void setPret(int pret);
	protected abstract void addProduseDeBaza(BaseProduct produse);
	public abstract String getNume();
	public abstract int getPret();
}
