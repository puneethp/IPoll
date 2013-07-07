<%@page import="dao.User"%>
<%@page import="dao.VoterId"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="dao.User"%>
<%@ page isELIgnored="false"%>
<c:if test="${not empty requestScope.votercard}">
	<div class="span12">
		<div class="row-fluid">
			<div class="span2">
				<img style="width: 100%; height: auto;" class="img-polaroid"
					src="./File?fileid=${requestScope.votercard.imageFileId }" />
			</div>
			<div class="span10">
				<div class="pull-right">
					<h2>${requestScope.votercard.name}
						${requestScope.votercard.surName}</h2>
					<span class="muted">S/o </span>${requestScope.votercard.fathersName}
					<address>
						${requestScope.votercard.currentAddress}<br />
						${requestScope.votercard.currentAddressCityTown}
					</address>
				</div>
			</div>
		</div>
		<hr />
		<div class="span12">
			<dl class="dl-horizontal">
				<dt>Date of Birth</dt>
				<dd>${requestScope.votercard.dateOfBirth.date} /
					${requestScope.votercard.dateOfBirth.month+1} /
					${requestScope.votercard.dateOfBirth.year+1900}</dd>
				<dt>Sex</dt>
				<dd>${requestScope.votercard.sex}</dd>
				<dt>Place of Birth</dt>
				<dd>${requestScope.votercard.placeOfBirth}</dd>
				<dt>Ward</dt>
				<dd>${requestScope.votercard.currentAddressWardNo},
					${requestScope.votercard.currentAddressCityTown},
					${requestScope.votercard.currentAddressState}</dd>
				<dt>Relation</dt>
				<dd>
					<c:if test="${requestScope.votercard.relID eq \"\"}">-</c:if>
					${requestScope.votercard.relID}
				</dd>
				<dt>Status</dt>
				<dd>
					<c:if test="${requestScope.votercard.validity eq 2 }">
						<strong class="text-success">Accepted</strong>
					</c:if>
					<c:if test="${requestScope.votercard.validity eq 3 }">
						<strong class="text-error">Rejected</strong>
					</c:if>
					<c:if test="${requestScope.votercard.validity eq 0 }">
						<strong class="text-info">Not validated</strong>
					</c:if>
				</dd>
				<dt>Voter Id</dt>
				<dd>${requestScope.votercard.voterID}</dd>
			</dl>
		</div>
		<c:if test="${requestScope.votercard.validity eq 0}">
			<form action="verifyVoterIdStatus.action" method="post">
				<fieldset>
				<legend>Verify</legend> 
				<input type="hidden" id="reqid" name="reqid" value="${param.reqid}" />
				<input type="hidden" id="voterid" name="voterid"
					value="${param.voterid}" />
					<label class="radio"> <input
					type="radio" value="accept" id="status" name="status" />
					<span class="text-success">Accept</span>
					</label>
					<label class="radio">  <input type="radio" value="reject"
					id="status" name="status" /><span
					class="text-error">Reject</span></label>
					<input class="btn btn-primary"
					type="submit" value="OK" />
					</fieldset>
			</form>
		</c:if>
		</div>
</c:if>