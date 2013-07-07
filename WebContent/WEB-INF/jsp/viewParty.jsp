<%@page import="dao.User"%>
<%@page import="dao.VoterId"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="dao.User"%>
<%@ page isELIgnored="false"%>
<c:if test="${not empty requestScope.partycard}">
	<div class="span12">
		<div class="row-fluid">
			<div class="span2">
				<img class="img-polaroid"
					src="./File?fileid=${requestScope.partycard.symbol }" />
			</div>
			<div class="span10">
				<div class="pull-right">
					<h2>${requestScope.partycard.partyname}</h2>
				</div>
			</div>
		</div>
		<hr />
		<div class="span12">
			<dl class="dl-horizontal">
				<dt>Head</dt>
				<dd>${requestScope.partycard.head}</dd>
				<dt>Status</dt>
				<dd>
					<c:if test="${requestScope.partycard.validity eq 1 }">
						<strong class="text-success">Accepted</strong>
					</c:if>
					<c:if test="${requestScope.partycard.validity eq 2 }">
						<strong class="text-error">Rejected</strong>
					</c:if>
					<c:if test="${requestScope.partycard.validity eq 0 }">
						<strong class="text-info">Not validated</strong>
					</c:if>
				</dd>
			</dl>
		</div>
		<c:if test="${requestScope.partycard.validity eq 2 or requestScope.partycard.validity eq 1	 }">
			<form action="updatePartyReg.action" method="post">
				<input type="hidden" id="voterid"
					value="${requestScope.partycard.partyname}" name="partyname" /><input
					type="submit" class="btn" value="edit">
			</form>
		</c:if>
	</div>
</c:if>