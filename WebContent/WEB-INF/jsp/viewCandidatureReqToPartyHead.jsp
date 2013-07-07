<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<%@page import="dao.Request"%>
<%@page import ="dao.RequestForPartyHead" %>
<c:forEach var="item" items="${requestScope.r4ph}">
	
	
	<div class="span12 well well-small">
		<div class="row-fluid">
			<div class="span6">
				<p class="muted">${item.key.reqid}</p>
			</div>
			<div class="span6">
				<p class="muted pull-right">${item.key.reqTime.date}/${item.key.reqTime.month+1}/${item.key.reqTime.year+1900}
					${item.key.reqTime.hours}:${item.key.reqTime.minutes}:${item.key.reqTime.seconds}</p>
			</div>
		</div>
		<div class="span12">
			<div class="row-fluid">
			<h5 class="span10">${item.key.reqquestion} ${item.value.candidateid}</h5>
				<c:if test="${item.key.reqtype eq \"candidatureparty\" }">
					<form name="" method="post" action="./ViewCandidatePartyHead.action" >
							<input type="hidden" value="${item.key.reqid}" name="reqid" id="reqid" />
							<input type="hidden" value="${item.value.candidateid}" name="candidateid" id="candidateid" />
							<input type="submit" class="btn" value="view" />
					</form>
				</c:if>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span6">
				<p class="text-info">${item.key.reqtype}</p>
			</div>
			<div class="span6">
				<p class="text-info pull-right">${item.key.reqstatus}</p>
			</div>
		</div>
	</div>
</c:forEach>