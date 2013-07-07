<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<%@page import="dao.Request"%>
<%@page import="dao.ReqForCandidatureWard"%>
<%@page import ="dao.ReqForCandidature" %>
<div id="info">
	<div id="left_uni">
		<span class="username_label">id: </span>	
		<span id="username_value" class="username_value">${requestScope.user.id}</span>
	</div>
	<div id="right_uni">
		<span class = "lastlogin_label">lastlogin: </span>
		<span class = "lastlogin_value">${requestScope.user.lastLogin}</span>
	</div>
</div>
<c:forEach var="item" items="${requestScope.reqForCandidature}">
	<c:if test="${item.key.reqtype eq \"candidature\" }">
	<span class="req_heading">Request: </span>
	<div class="requestHolder">
	<div class="request">
				<div id="left_uni_req"><span class="req_label">Id: </span><span class="req_value">${item.key.reqid}</span></div>
				<div id ="right_uni_req"><span class="req_label">Dated: </span><span class="req_value">${item.key.reqTime.date}/${item.key.reqTime.month+1}/${item.key.reqTime.year+1900} ${item.key.reqTime.hours}:${item.key.reqTime.minutes}:${item.key.reqTime.seconds}</span></div>
				<div class="req_content">${item.key.reqquestion}
				<br />
						<u>Actions: </u>
						<form name="" method="post" action="./viewCandidateAction.action" >
							<input type="hidden" value="${item.key.reqid}" name="reqid" id="reqid" />
							<input type="hidden" value="${item.value.candidateId}" name="candidateid" id="candidateid" />
							<input type="submit" cssClass="inbutton" value="view" />
						</form>
				
				</div>
				<div id="left_uni_req"><span class="req_label">Type: </span><span class="req_value">${item.key.reqtype}</span></div>
				<div id ="right_uni_req"><span class="req_label">RequestStatus: </span><span class="req_value">${item.key.reqstatus}</span></div>	
	</div>
	</div>
	</c:if>
	</c:forEach>