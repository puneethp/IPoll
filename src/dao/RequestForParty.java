package dao;

public class RequestForParty {
	String head;
	Long reqid;
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Long getReqid() {
		return reqid;
	}
	public void setReqid(Long reqid) {
		this.reqid = reqid;
	}
	public RequestForParty(String head, Long reqid) {
		super();
		this.head = head;
		this.reqid = reqid;
	}
	public RequestForParty() {
		super();
	}
	
}
