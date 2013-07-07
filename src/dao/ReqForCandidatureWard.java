package dao;

public class ReqForCandidatureWard {

	private Long reqId;
	private String candidateId;
	
	public ReqForCandidatureWard(Long reqId, String candidateId) {
		super();
		this.reqId = reqId;
		this.candidateId = candidateId;
	}
	public ReqForCandidatureWard(){
		
	}
	public Long getReqId() {
		return reqId;
	}
	public void setReqId(Long reqId) {
		this.reqId = reqId;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
}
