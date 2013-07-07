<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@page import="dao.Request"%>
<%@page import="dao.RequestForVoterId;"%>
<h2>Requests for VoterId</h2>
<c:if test="${empty requestScope.reqforvoterid}">
	Nothing left !!
</c:if>
<c:forEach var="item" items="${requestScope.reqforvoterid}">
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
			<h5 class="span10">${item.key.reqquestion} ${item.value.voterid}</h5>

			<c:if test="${item.key.reqtype eq \"voterid\"}">
				<form name="" action="./verifyVoterIdWardUser.action">
					<input type="hidden" value="${item.key.reqid}" name="reqid"
						id="reqid" /> <input type="hidden" value="${item.value.voterid}"
						name="voterid" id="voterid" /> <input type="submit"
						class="span2 btn pull-right" value="view" />

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