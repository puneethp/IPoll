<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<%@page import="dao.Election"%>
<c:if test="${ not empty  requestScope.electionlist}">
	<table class="table">
	<tr>
		<th><label>Election ID</label></th>
		<th><label>Start</label></th>
		<th><label>End</label></th>
		<th><label>Actions</label></th>
	</tr>
	<c:forEach var="election" items="${requestScope.electionlist}">
			<tr>
			<td>${election.title}</td>
			<td>${election.start.date}/${election.start.month+1}/${election.start.year+1900} ${election.start.hours}:${election.start.minutes}:${election.start.seconds}</td>
			<td>${election.end.date}/${election.end.month+1}/${election.end.year+1900} ${election.end.hours}:${election.end.minutes}:${election.end.seconds}</td>
			<td>
			<s:form action="resultElection.action">
				<input  type="hidden" name="elecid" value="${election.elecid}" />
				<s:submit cssClass="btn btn-primary" value="Result" />
			</s:form>
			<s:form action="viewElection.action">
				<input type="hidden" name="elecid" value="${election.elecid}" />
				<s:submit cssClass="btn" value="View" />
			</s:form>
			</td>
			
			</tr>
	</c:forEach>
	</table>
</c:if>