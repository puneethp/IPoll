<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@page import="dao.Election"%>
<%@page import="dao.Locations"%>
<%@page import="dao.User"%>
<c:if test="${ not empty requestScope.election  }">
	<c:if test="${not empty requestScope.locations}">
		<div class="span12">
			<h1>
				<span>${election.title}</span>
			</h1>
			<h3>
				<span class="muted">${election.elecid}</span>
			</h3>
			<br />
			<div class="span12">
				<div class="span6">
					<div>
						<strong>Start: </strong> <span class="text-info">${election.start.date}/${election.start.month+1}/${election.start.year+1900}
							${election.start.hours}:${election.start.minutes}:${election.start.seconds}</span>
					</div>
				</div>
				<div class="span6">
					<div class="pull-right">
						<strong>End: </strong> <span class="text-important">${election.end.date}/${election.end.month+1}/${election.end.year+1900}
							${election.end.hours}:${election.end.minutes}:${election.end.seconds}</span>
					</div>
				</div>
			</div>
			<table class="table span12">
				<tr>
					<th><span>Location ID</span></th>
					<th><span>Constituency</span></th>
				</tr>
				<c:forEach var="location" items="${requestScope.locations }">
					<tr>
						<td><span>${location.locid}</span></td>
						<td><span>${location.constituency}</span></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</c:if>
</c:if>