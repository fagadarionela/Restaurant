package Business;
import java.util.ArrayList;

public interface IRestaurantProcessing {
	/*
	 * @pre m.getId()>0
	 */
	public static void createMenuItem(Restaurant r, MenuItem m){
		r.addMenuItem(m);
	}
	/*
	 * @pre m.getId()>0
	 */
	public static void deleteMenuItem(Restaurant r, int id){
		r.deleteMenuItem(id);
	}
	/*
	 * @pre m.getId()>0
	 */
	public static void editMenuItem(Restaurant r, MenuItem m){
		r.editMenuItem(m);
	}
	/*
	 * @pre o.getTable()>0
	 * @pre o.getTable()<=50
	 * @post gasit == true
	 */
	public static void createOrder(Restaurant r, Order o, ArrayList<MenuItem> mancare){
		r.addComanda(o,mancare);
	}
	/*
	 * @invariant price >=0
	 */
	public static void computePrice(Restaurant r, Order o){
		r.computePrice(o);
	}
	/*
	 * @pre o.getTable()>0
	 * @pre o.getTable()<=50
	 * @post price > 0
	 */
	public static void generateBill(Restaurant r, Order o){
		r.generateBill(o.getOrderID(),o.getDate(),o.getTable());
	}
}
