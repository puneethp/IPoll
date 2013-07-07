package dao;

import java.sql.Blob;

public class Party {
	public static final Integer ACCEPTED  = 1;
	public static final Integer REJECTED  = 2;
	public static final Integer NOTVALIDATED  = 0;
	
	private String partyname;
	private String head;
	private String symbol;
	private Integer validity;
	public String getPartyname() {
		return partyname;
	}
	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Integer getValidity() {
		return validity;
	}
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	public Party(String partyname, String head, String symbol, Integer validity) {
		super();
		this.partyname = partyname;
		this.head = head;
		this.symbol = symbol;
		this.validity = validity;
	}
	public Party() {
		super();
	}
	
}
