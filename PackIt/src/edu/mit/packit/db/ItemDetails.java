package edu.mit.packit.db;

public class ItemDetails {
	private long id;
	private long trip_id;
	private int item;
	private String category;
	private int packed;
	private int unpacked;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setTripId(long trip_id) {
		this.trip_id = trip_id;
	}
	
	public long getTripId() {
		return this.trip_id;
	}
	
	public void setItem(int item) {
		this.item = item;
	}
	
	public int getItem() {
		return this.item;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setPacked(int packed) {
		this.packed = packed;
	}
	
	public int getPacked() {
		return this.packed;
	}
	
	public void setUnpacked(int unpacked) {
		this.unpacked = unpacked;
	}
	
	public int getUnpacked() {
		return this.unpacked;
	}
	
	public void unpack() {
		this.unpacked ++;
		this.packed --;
	}
	
	public void pack() {
		this.packed ++;
		this.unpacked --;
	}
}
