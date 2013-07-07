package dao;

public class Locations {
	private String locid;
	private String elecid;
	private long constituency;
	public String getLocid() {
		return locid;
	}
	public void setLocid(String locid) {
		this.locid = locid;
	}
	public String getElecid() {
		return elecid;
	}
	public void setElecid(String elecid) {
		this.elecid = elecid;
	}
	public long getConstituency() {
		return constituency;
	}
	public void setConstituency(long constituency) {
		this.constituency = constituency;
	}
	public Locations() {
		super();
		// TODO Auto-generated constructor stub
	}

}
