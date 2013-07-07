<%@page import="dao.User"%>
<%@page import="dao.VoterId"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dao.Candidate"%>
<%@ page isELIgnored="false" %>
<div id="info"><div id="left_uni"><span class="username_label">id: </span><span id="username_value" class="username_value">${requestScope.user.id}</span></div><div id="right_uni"><span class = "lastlogin_label">lastlogin: </span><span class = "lastlogin_value">${requestScope.user.lastLogin}</span></div></div>
<c:if test="${not empty requestScope.candidatecard}">
	<c:if test="${not empty requestScope.votercard}">
	
		<div class="votercard_header">
			
			<div id="left_uni">
				<span class="votercard_name_value">&nbsp;&nbsp; Constituency : </span>
			<span class="votercard_name_value" >${requestScope.candidatecard.constituency}</span> 
			<br/>
				<span class="votercard_name_value"> &nbsp;&nbsp; Name : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
				<span class="votercard_name_value">${requestScope.votercard.name} ${requestScope.votercard.surName}</span>
		</div>
		<div id="right_uni">
			<img  class="votercard_image" src="./File?fileid=${requestScope.votercard.imageFileId }">
			<span> &nbsp;</span>
			<img  class="votercard_image" src="./File?fileid=${requestScope.candidatecard.symbol }">
		</div>
	</div>
	<div class="votercard_content">
		<span class="votercard_heading">Personal</span>
		<div class="votercard_personal">
			<div class = "votercard_property">
				<span class="votercard_label">Date of birth: </span>
				<span class="votercard_value">${requestScope.votercard.dateOfBirth.date} / ${requestScope.votercard.dateOfBirth.month+1} / ${requestScope.votercard.dateOfBirth.year+1900}</span>
			</div>
			<div class = "votercard_property">
				<span class="votercard_label">Sex: </span>
				<span class="votercard_value">${requestScope.votercard.sex}</span>
			</div>
			<div class = "votercard_property">
				<span class="votercard_label">Place of birth: </span>
				<span class="votercard_value">${requestScope.votercard.placeOfBirth}</span>
			</div>
					
			<span class="votercard_property">Current address</span>
			<div class="votecard_sub_property">
				<span class="votercard_label">House : </span>
				<span class="votercard_value">${requestScope.votercard.currentAddress}</span>
			</div>
			<div class="votecard_sub_property">
				<span class="votercard_label">Ward n.o: </span>
				<span class="votercard_value">${requestScope.votercard.currentAddressWardNo}</span>
			</div>
			<div class="votecard_sub_property">
				<span class="votercard_label">City: </span>
				<span class="votercard_value">${requestScope.votercard.currentAddressCityTown}</span>
			</div>
			<div class="votecard_sub_property">
				<span class="votercard_label">State: </span>
				<span class="votercard_value">${requestScope.votercard.currentAddressState}</span>
			</div>
			<c:if test="${ not empty requestScope.candidatecard.representingParty }" >
			<div class="votecard_sub_property">
				<span class="votercard_label">Representing Party: </span>
				<span class="votercard_value">${requestScope.candidatecard.representingParty}</span>
			</div>
			</c:if>
		</div>
		
		<span class="votercard_heading">Other</span>
		<div class="votercard_personal">
			<div class = "votercard_property">
				<span class="votercard_label">Father's name: </span><span class="votercard_value">${requestScope.votercard.fathersName}</span>
			</div>
			<div class = "votercard_property">
				<span class="votercard_label">Relative's Id: </span><span class="votercard_value">${requestScope.votercard.relID}</span>
			</div>			
		</div>
		<span class="votercard_heading">Status: </span>
		<span class="votercard_value">
			<c:if test="${requestScope.candidatecard.validity eq 2 }">Accepted</c:if>
			<c:if test="${requestScope.candidatecard.validity eq 3 }">Rejected</c:if>
			<c:if test="${requestScope.candidatecard.validity eq 0 }">Not validated</c:if>
			<c:if test="${requestScope.candidatecard.validity eq 1 }">Partially Accepted</c:if>
			</span>
		
			<br>
			<br>	
			
		<span class="votercard_heading">List of Candidates to approve</span>
		<div class = "votercard_personal">
				<c:if test="${not empty requestScope.candidatecard.candidate1}">
					<span class="votercard_property">Candidate 1 :</span>
					<span class="votercard_value">${requestScope.candidatecard.candidate1}</span>
					<br/>
				</c:if>
				
				<c:if test="${not empty requestScope.candidatecard.candidate2}">
					<span class="votercard_property">Candidate 2 :</span>
					<span class="votercard_value">${requestScope.candidatecard.candidate2}</span>
					<br/>
				</c:if>
				
				<c:if test="${not empty requestScope.candidatecard.candidate3}">
					<span class="votercard_property">Candidate 3 :</span>
					<span class="votercard_value">${requestScope.candidatecard.candidate3}</span>
					<br/>
				</c:if>
				<c:if test="${not empty requestScope.candidatecard.candidate4}">
					<span class="votercard_property">Candidate 4 :</span>
					<span class="votercard_value">${requestScope.candidatecard.candidate4}</span>
					<br/>
				</c:if>
				<c:if test="${not empty requestScope.candidatecard.candidate5}">
					<span class="votercard_property">Candidate 5 :</span>
					<span class="votercard_value">${requestScope.candidatecard.candidate5}</span>
					<br/>
				</c:if>
				<c:if test="${not empty requestScope.candidatecard.candidate6}">
					<span class="votercard_property">Candidate 6 :</span>
					<span class="votercard_value">${requestScope.candidatecard.candidate6}</span>
					<br/>
				</c:if>
				<c:if test="${not empty requestScope.candidatecard.candidate7}">
					<span class="votercard_label">Candidate 7 :</span>
					<span class="votercard_value">${requestScope.candidatecard.candidate7}</span>
					<br/>
				</c:if>
				<c:if test="${not empty requestScope.candidatecard.candidate8}">
					<span class="votercard_label">Candidate 8 :</span>
					<span class="votercard_value">${requestScope.candidatecard.candidate8}</span>
					<br/>
				</c:if>
				<c:if test="${not empty requestScope.candidatecard.candidate9}">
					<span class="votercard_label">Candidate 9 :</span>
					<span class="votercard_value">${requestScope.candidatecard.candidate9}</span>
					<br/>
				</c:if>
				<c:if test="${not empty requestScope.candidatecard.candidate10}">
					<span class="votercard_label">Candidate 10 :</span>
					<span class="votercard_value">${requestScope.candidatecard.candidate10}</span>
					<br/>
				</c:if>
				</div>
	</div>
			<c:if test="${requestScope.candidatecard.validity eq 0}">
			<form action="./VerifyCandidatureStatusCandidate.action" method="post">
				<input type="hidden" id="reqid" name="reqid" value="${param.reqid}" />
				<input type="hidden" id="candidateid" name="candidateid" value="${requestScope.candidatecard.user}" />
				<table class="verifyblock">
				<tr>
				<td><span class="accept">Accept</span></td>
				<td><input type="radio" value="accept" id="status" name="status" /></td>
				<td><span class="reject">Reject</span></td>
				<td><input type="radio" value="reject" id="status" name="status" /></td>
				<td><input type="submit" class="inbutton" value="OK" /></td>
				</tr>
				</table>
			</form>
			</c:if>
</c:if>
</c:if>