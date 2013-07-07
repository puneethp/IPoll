package dao;

import java.sql.Timestamp;

public class Election {
private String elecid;
private Timestamp start;
private Timestamp end;
private int validity;
private String title;
public static final int NOTDONE = 1;
public static final int DONE = 0;
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getElecid() {
	return elecid;
}
public void setElecid(String elecid) {
	this.elecid = elecid;
}
public Timestamp getStart() {
	return start;
}
public void setStart(Timestamp start) {
	this.start = start;
}
public Timestamp getEnd() {
	return end;
}
public void setEnd(Timestamp end) {
	this.end = end;
}
public int getValidity() {
	return validity;
}
public void setValidity(int validity) {
	this.validity = validity;
}
public Election(Timestamp start, Timestamp end, int validity) {
	super();
	this.start = start;
	this.end = end;
	this.validity = validity;
}
public Election() {
	super();
}

}
