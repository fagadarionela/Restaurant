package Business;

import java.io.Serializable;

public class Order implements Serializable {
	private static final long serialVersionUID = -5107939696502691150L;
	public int orderID;
	public String date;
	public int table;
	public Order(int orderID, String date,int table){
		super();
		this.orderID = orderID;
		this.date = date;
		this.table = table;
	}
	public int getOrderID() {
		return orderID;
	}
	public String getDate() {
		return date;
	}
	public int getTable() {
		return table;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTable(int table) {
		this.table = table;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderID;
		result = prime * result + table;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (orderID != other.orderID)
			return false;
		if (table != other.table)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", date=" + date + ", table=" + table + "]";
	}
}
