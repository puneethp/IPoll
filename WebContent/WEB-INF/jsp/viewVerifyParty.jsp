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
					<c:if test="${requestScope.partycard.validity eq 2 }">
						<strong class="text-success">Accepted</strong>
					</c:if>
					<c:if test="${requestScope.partycard.validity eq 3 }">
						<strong class="text-error">Rejected</strong>
					</c:if>
					<c:if test="${requestScope.partycard.validity eq 0 }">
						<strong class="text-info">Not validated</strong>
					</c:if>
				</dd>
			</dl>
		</div>
		<c:if test="${requestScope.partycard.validity eq 0	 }">
			<form action="verifyPartyReg.action" method="post">
				<input type="hidden" id="reqid" name="reqid" value="${param.reqid}" />
				<input type="hidden" id="head" name="head" value="${param.head}" />
						<label class="radio">Accept <input type="radio" value="accept" id="status"
							name="status" /></label>
						<label class="radio">Reject
						<input type="radio" value="reject" id="status"
							name="status" /></label>
						<input type="submit" class="btn btn-primary" value="OK" />
			</form>
		</c:if>
	</div>
</c:if>