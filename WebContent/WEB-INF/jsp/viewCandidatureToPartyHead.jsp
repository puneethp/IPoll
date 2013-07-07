<%@page import="dao.User"%>
<%@page import="dao.VoterId"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="dao.Candidate"%>
<%@ page isELIgnored="false"%>
<c:if test="${not empty requestScope.candidatecard}">
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
			<div class="row-fluid">
				<div class="span2">
					<img style="width: 100%; height: auto;" class="img-polaroid"
						src="./File?fileid=${requestScope.candidatecard.symbol }" />
				</div>
				<div class="span10">
					<div class="pull-right">
						<c:if
							test="${ not empty requestScope.candidatecard.representingParty }">
							<span class="muted">${requestScope.candidatecard.representingParty}</span>
						</c:if>

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
					<dt>Voter-Id Status</dt>
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
					<c:if test="${not empty requestScope.candidatecard.candidate2}">
						<dt>Candidate 2 :</dt>
						<dd>${requestScope.candidatecard.candidate2}</dd>
						<br />
					</c:if>

					<c:if test="${not empty requestScope.candidatecard.candidate3}">
						<dt class="votercard_property">Candidate 3 :</dt>
						<dd>${requestScope.candidatecard.candidate3}</dd>
						<br />
					</c:if>
					<c:if test="${not empty requestScope.candidatecard.candidate4}">
						<dt class="votercard_property">Candidate 4 :</dt>
						<dd>${requestScope.candidatecard.candidate4}</dd>
						<br />
					</c:if>
					<c:if test="${not empty requestScope.candidatecard.candidate5}">
						<dt class="votercard_property">Candidate 5 :</dt>
						<dd>${requestScope.candidatecard.candidate5}</dd>
						<br />
					</c:if>
					<c:if test="${not empty requestScope.candidatecard.candidate6}">
						<dt class="votercard_property">Candidate 6 :</dt>
						<dd>${requestScope.candidatecard.candidate6}</dd>
						<br />
					</c:if>
					<c:if test="${not empty requestScope.candidatecard.candidate7}">
						<dt>Candidate 7 :</dt>
						<dd>${requestScope.candidatecard.candidate7}</dd>
						<br />
					</c:if>
					<c:if test="${not empty requestScope.candidatecard.candidate8}">
						<dt>Candidate 8 :</dt>
						<dd>${requestScope.candidatecard.candidate8}</dd>
						<br />
					</c:if>
					<c:if test="${not empty requestScope.candidatecard.candidate9}">
						<dt>Candidate 9 :</dt>
						<dd>${requestScope.candidatecard.candidate9}</dd>
						<br />
					</c:if>
					<c:if test="${not empty requestScope.candidatecard.candidate10}">
						<dt>Candidate 10 :</dt>
						<dd>${requestScope.candidatecard.candidate10}</dd>
						<br />
					</c:if>
					<dt>Status</dt>
					<dd>
						<c:if test="${requestScope.candidatecard.validity eq 2 }">Accepted</c:if>
						<c:if test="${requestScope.candidatecard.validity eq 3 }">Rejected</c:if>
						<c:if test="${requestScope.candidatecard.validity eq 0 }">Not validated</c:if>
						<c:if test="${requestScope.candidatecard.validity eq 1 }">Partially Accepted</c:if>
					</dd>
				</dl>
			</div>
		</div>

		<c:if test="${requestScope.candidatecard.validity eq 3	 }">
			<form action="./UpdateCandidatureAction.action" method="post">
				<table class="verifyblock">
					<tbody>
						<tr>
							<td><input type="hidden" id="voterid"
								value="${requestScope.votercard.voterID}" name="voterid" /> <input
								type="hidden" id="candidateid"
								value="${requestScope.candidatecard.user}" name="candidateid" />
								<input type="submit" value="edit"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</c:if>
		<c:if test="${requestScope.candidatecard.validity eq 0}">
			<form action="./VerifyCandidatureStatusPartyHead.action"
				method="post">
				<input type="hidden" id="reqid" name="reqid" value="${param.reqid}" />
				<input type="hidden" id="candidateid" name="candidateid"
					value="${requestScope.candidatecard.user}" />
				<table class="verifyblock">
					<tr>
						<td><span class="accept">Accept</span></td>
						<td><input type="radio" value="accept" id="status"
							name="status" /></td>
						<td><span class="reject">Reject</span></td>
						<td><input type="radio" value="reject" id="status"
							name="status" /></td>
						<td><input type="submit" class="inbutton" value="OK" /></td>
					</tr>
				</table>
			</form>
		</c:if>

	</c:if>
</c:if>