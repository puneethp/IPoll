package service;

import java.sql.Blob;

import dao.FileContent;
import dao.FileInfo;
import dao.Party;
import dao.PartyDAO;
import dao.RequestForPartyDAO;
import dao.User;
import dao.UserDAO;

public class PartyServ {
	PartyDAO dao;
	RequestForPartyDAO rfpdao;
	UserDAO udao;
		
	public UserDAO getUdao() {
		return udao;
	}

	public void setUdao(UserDAO udao) {
		this.udao = udao;
	}

	public PartyDAO getDao() {
		return dao;
	}

	public void setDao(PartyDAO dao) {
		this.dao = dao;
	}

	public RequestForPartyDAO getRfpdao() {
		return rfpdao;
	}

	public void setRfpdao(RequestForPartyDAO rfpdao) {
		this.rfpdao = rfpdao;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	Party party;
	
	public boolean writeParty(String partyname,String head,byte[] symbol,String fileName,String filetype)
	{
		party = new Party(partyname, head, null, Party.NOTVALIDATED);
		FileInfo file = new FileInfo(fileName,filetype,head);
		FileContent content = new FileContent(null,symbol);
		if(dao.writeParty(party, content, file))
			return true;
		return false;
	}
	
	public boolean updatePhoto(String partyname,String head,byte[] symbol,String fileName,String filetype)
	{
		FileInfo file = new FileInfo(fileName,filetype,head);
		FileContent content = new FileContent(null,symbol);
		if(dao.updatePartyPhoto(partyname, content, file))
			return true;
		return false;
	}
	
	public boolean acceptParty(String head,Long reqid){
		
		if(dao.acceptParty(reqid, head))
			return true;
		return false;
	}

	public boolean rejectParty(String head,Long reqid){
		
		if(dao.rejectParty(reqid, head))
			return true;
		return false;
	}
	
	public User getUser(String userid)
	{
		return udao.getUser(userid);
	}
	
	public boolean getPartyByUser(String head)
	{
		party = dao.getPartyByUser(head);
		if(party!=null)
		{
			return true;
		}
		return false;
	}
	
	public boolean getParty(String partyname)
	{
		party = dao.getParty(partyname);
		if(party!=null)
			return true;
		return false;
	}
	
	public boolean isRequestForParty(Long reqid)
	{
		if(rfpdao.getRequestForParty(reqid)!=null)
			return true;
		return false;
	}
	
	public Party getPartyObject()
	{
		return party;
	}
}
