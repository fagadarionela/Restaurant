package Business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import Presentation.ChefGraphicalUserInterface;

public class Restaurant extends Observable implements Serializable,IRestaurantProcessing{
	private static final long serialVersionUID = -7985555054462712568L;
	ArrayList<MenuItem> mancaruri;
	ArrayList<Order> comenzi;
	HashMap<Order, ArrayList<MenuItem>> tabelComenzi;
	public Restaurant() {
		mancaruri = new ArrayList<MenuItem>();
		tabelComenzi = new HashMap<Order,ArrayList<MenuItem>>();
		comenzi = new ArrayList<Order>();
	}
	public boolean addMenuItem(MenuItem m) {
		assert m.getId() > 0 : "Precondition: Id > 0";
		boolean gasit = false;
		boolean eroare = false;
		for(MenuItem mancare:mancaruri) 
			if (mancare.getId() == m.getId()) eroare = true;
		if (eroare == false || m instanceof CompositeProduct) {
			for(MenuItem mancare2:mancaruri) {
				if (mancare2.getNume().equals(m.getNume())) {
					gasit = true;
					mancare2.addProduseDeBaza(m.getProduseDeBaza().get(0));
				}
			}
			if (gasit == false) mancaruri.add(m);
		}
		return eroare;
	}
	public int deleteMenuItem(int id) {
		assert id > 0 : "Precondition: Id > 0";
		int idSters = -1;
		for(int i=0;i< this.mancaruri.size();i++)
		{
			if (this.mancaruri.get(i).getId() == id) idSters = i;
		}
		if (idSters != -1) this.mancaruri.remove(idSters);
		return idSters;
	}
	public boolean editMenuItem(MenuItem m) {
		assert m.getId() > 0 : "Precondition: Id > 0";
		boolean gasit = false;
		System.out.println("aici");
		for(MenuItem mancare:mancaruri) {
			if (mancare.getId() == m.getId()) {
				gasit = true;
				mancare.setProduseDeBaza(m.getProduseDeBaza());
				if (m instanceof BaseProduct) {
					mancare.setNume(m.getNume());
					mancare.setPret(m.getPret());				
				}

			}
		}
		return gasit;
	}
	public void addComanda(Order o, ArrayList<MenuItem> mancare) {
		assert o.getTable() > 0 : "Precondition: Table > 0";
		assert o.getTable() <= 50 : "Precondition: Table <=50";
		boolean gasit = false;
		for(Order ord: comenzi) {
			if (ord.getOrderID() == o.getOrderID()) gasit = true;
		}
		if (gasit == false) {
			tabelComenzi.put(o, mancare);
			comenzi.add(o);
			this.setChanged();
			this.notifyObservers(o);
		}
		else assert gasit == true : "Postcondition: Nr comenzii exista";
		System.out.println(tabelComenzi);
	}
	public ArrayList<MenuItem> getMancaruri() {
		return mancaruri;
	}
	public HashMap<Order, ArrayList<MenuItem>> getTabelComenzi() {
		return tabelComenzi;
	}
	public void setMancaruri(ArrayList<MenuItem> mancaruri) {
		this.mancaruri = mancaruri;
	}
	public void setTabelComenzi(HashMap<Order, ArrayList<MenuItem>> tabelComenzi) {
		this.tabelComenzi = tabelComenzi;
	}
	public ArrayList<Order> getComenzi() {
		return comenzi;
	}
	public void setComenzi(ArrayList<Order> comenzi) {
		this.comenzi = comenzi;
	}
	public int computePrice(Order o) {
		int price = 0;
		for(MenuItem m: this.getTabelComenzi().get(o)) {
			price+=m.computePrice();
		}
		//assert price >= 0 : "Invariant: price >= 0";
		System.out.println(tabelComenzi);
		return price;
	}
	public void generateBill(int id,String data, int masa) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter("file.txt"));
			out.write("NrComenzii: "+id+"\n");
			out.write("Data: "+data+"\n");
			out.write("Masa: "+masa+"\n");
			out.write("COMANDA:\n");
			Order o = new Order(id,data,masa);
			ArrayList<MenuItem> mancaruri = this.getTabelComenzi().get(o);
			if (mancaruri!=null) {
		        for(MenuItem m: mancaruri) {
		        	out.write(m.toString()+"\n");
		        }
		        out.write("Total:"+computePrice(o)+"\n");
			}
			else  out.write("Total: 0 \n");
	        out.close();
	        System.out.println(tabelComenzi);
	        this.getTabelComenzi().remove(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	public String cautaUltimulId() {
		int id = 0;
		for(Order o:comenzi) {
			if (id <o.getOrderID()) id = o.getOrderID();
		}
		id++;
		return Integer.toString(id);
	}
	public String cautaUltimulIdMenu() {
		int id = 0;
		for(MenuItem m: this.getMancaruri()) {
			if (id <m.getId()) id = m.getId();
		}
		id++;
		return Integer.toString(id);
	}
	public void generateBill(int masa) {
		assert masa > 0 : "Precondition: Table > 0";
		assert masa <= 50 : "Precondition: Table <=50";
		BufferedWriter out;
			try {	
			out = new BufferedWriter(new FileWriter("file.txt"));
			boolean prima = true;
			int price = 0;
			int i=0;
			while(i<this.getComenzi().size()){
				if (this.getComenzi().get(i).getTable() == masa) {
					if (prima) {
						out.write("Data: "+this.getComenzi().get(i).getDate()+"\n");
						out.write("Masa: "+masa+"\n");
						out.write("COMANDA:\n");
					}
					ArrayList<MenuItem> mancaruri = this.getTabelComenzi().get(this.getComenzi().get(i));
					if (mancaruri!=null) {
						out.write("NrComenzii: "+this.getComenzi().get(i).getOrderID()+"\n");
				        for(MenuItem m: mancaruri) {
				        	out.write(m.toString()+"\n");
				        }
				        out.write("Pret:                 "+computePrice(this.getComenzi().get(i))+"\n");
				        price+=computePrice(this.getComenzi().get(i));
					}
					else  price+=0;
			        this.getTabelComenzi().remove(this.getComenzi().get(i));
			        this.getComenzi().remove(this.getComenzi().get(i));
			        prima = false;
				}
				else i++;
			}
			assert price > 0 : "PostCondition: price > 0";
			out.write("\nTotal:"+price+"\n");
			out.close();
		}
		catch (IOException e) {
		e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "Restaurant:"+ mancaruri + ", comenzi=" + tabelComenzi;
	}	
}
