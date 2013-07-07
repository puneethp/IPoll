<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %>
<c:if test="${not empty requestScope.candidatecard}">

<script>
function RemoveError()
{
	var node;
	node= documet.getElementById("error");
	node.innerHTML = "Re-Registerd. Awaiting Ward User's response.";
	return true;
}
var count=2;
function addCandidate()
{
	if(count<=10)
	{
		var newCandidate, parent, newLine;
		newCandidate= document.createElement("input");
		newLine= document.createElement("br");
		parent= document.getElementById("CandidateList");
		newCandidate.setAttribute('id', "candidate"+count);
		newCandidate.setAttribute('name', "candidate"+count++);
		newCandidate.setAttribute('type', "text");
		parent.appendChild(newCandidate);
		parent.appendChild(newLine);
	}
	else
	{
		alert("Cannot add more Candidates.");
	}
	return true;
}
function hasFilledfields()
{
	if(document.getElementById('constituency').value.length!=0&&
			document.getElementById('candidate1').value.length!=0)
	{
		var temp = document.getElementById("error");
		temp.innerHTML = "";
		return true;
	}
	var temp = document.getElementById("error");
	temp.innerHTML = "Error: Empty Fields.";
	return false;
}
function verifyForm()
{
	if(hasFilledfields())
	{
		var temp = document.getElementById("error");
		temp.innerHTML = "";
		return true;
	}
	var temp = document.getElementById("error");
	
	temp.innerHTML = "Error: Invalid Fields.";
	return false;
}
</script>
<div class="main">
	<h2> Candidature Verification </h2>
	
	<s:form action="UpdateCandidatureAction.action" enctype="multipart/form-data" method="post" onsubmit="verifyForm()">
		
		
			 <div class="error" id="error">${requestScope.error} </div>
		
		
			
				<div id="error" class="error">There seems to be some problem with your Candidature, please review.</div>
			
		
		<label>Constituency</label>
		<input type="text" name="constituency" id="constituency" value="${requestScope.candidatecard.constituency}"  />
		<label>Representing Party </label>
		<input type="text" name="representingParty" id="representingParty" value="${requestScope.candidatecard.representingParty}"  />
		<label>Symbol</label>
		<input type="file" name="userPhoto" id="userPhoto"/>	
		
			
				<label>List of Candidates : </label>
						
			
				<div id="CandidateList">
					<c:if test="${ not empty requestScope.candidatecard.candidate1}">
						<input type="text" name="candidate1" id="candidate1" value="${requestScope.candidatecard.candidate1}"/>
					</c:if>
					<br/>
					<c:if test="${ not empty requestScope.candidatecard.candidate2}">
						<input type="text" name="candidate2" id="candidate2" value="${requestScope.candidatecard.candidate2}"/>
						<br/>
					</c:if>
					<c:if test="${ not empty requestScope.candidatecard.candidate3}">
						<input type="text" name="candidate3" id="candidate3" value="${requestScope.candidatecard.candidate3}"/>
						<br/>
					</c:if>
					<c:if test="${ not empty requestScope.candidatecard.candidate4}">
						<input type="text" name="candidate4" id="candidate4" value="${requestScope.candidatecard.candidate4}"/>
						<br/>
					</c:if>
					<c:if test="${ not empty requestScope.candidatecard.candidate5}">
						<input type="text" name="candidate5" id="candidate5" value="${requestScope.candidatecard.candidate5}"/>
						<br/>
					</c:if>
					<c:if test="${ not empty requestScope.candidatecard.candidate6}">
						<input type="text" name="candidate6" id="candidate6" value="${requestScope.candidatecard.candidate6}"/>
						<br/>
					</c:if>
					<c:if test="${ not empty requestScope.candidatecard.candidate7}">
						<input type="text" name="candidate7" id="candidate7" value="${requestScope.candidatecard.candidate7}"/>
						<br/>
					</c:if>
					<c:if test="${ not empty requestScope.candidatecard.candidate8}">
						<input type="text" name="candidate8" id="candidate8" value="${requestScope.candidatecard.candidate8}"/>
						<br/>
					</c:if>
					<c:if test="${ not empty requestScope.candidatecard.candidate9}">
						<input type="text" name="candidate9" id="candidate9" value="${requestScope.candidatecard.candidate9}"/>
						<br/>
					</c:if>
					<c:if test="${ not empty requestScope.candidatecard.candidate10}">
						<input type="text" name="candidate10" id="candidate10" value="${requestScope.candidatecard.candidate10}"/>
						<br/>
					</c:if>
				</div>
			<input value="Add More Candidate(s)" type="button" onclick="return addCandidate()" />
		
		<input type="submit" class="btn btn-primary" value="Submit" onclick="return ChangeError()" />
	</s:form>
</div>	
</c:if>
