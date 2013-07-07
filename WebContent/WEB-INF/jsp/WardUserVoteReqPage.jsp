<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<%@page import="dao.Election"%>
<div id="info"><div id="left_uni"><span class="username_label">id: </span><span id="username_value" class="username_value">${requestScope.user.id}</span></div><div id="right_uni"><span class = "lastlogin_label">lastlogin: </span><span class = "lastlogin_value">${requestScope.user.lastLogin}</span></div></div>

<c:if test="${not empty requestScope.electionlist}">
	<div>
	<table>
	<tr>
		<th><label>Election ID</label></th>
		<th><label>Start</label></th>
		<th><label>End</label></th>
		<th><label>Actions</label></th>
	</tr>
	<c:forEach var="election" items="${requestScope.electionlist}">
			<tr>
			<td><span>${election.title}</span></td>
			<td><span>${election.start.date}/${election.start.month+1}/${election.start.year+1900} ${election.start.hours}:${election.start.minutes}:${election.start.seconds}</span></td>
			<td><span>${election.end.date}/${election.end.month+1}/${election.end.year+1900} ${election.end.hours}:${election.end.minutes}:${election.end.seconds}</span></td>
			<td><s:form action="WardUserVote.action">
				<input type="hidden" name="elecid" value="${election.elecid}" />\
				<input type="hidden" name="voterid" value="${voterid}" />\
				<s:submit cssClass="inbutton" value="Vote" />
			</s:form></td>
			</tr>
	</c:forEach>
	</table>
	</div>
</c:if>