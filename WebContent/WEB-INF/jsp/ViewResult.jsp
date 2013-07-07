<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<%@page import="dao.Result"%>

<script type="text/javascript" src="./bootstrap/js/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="./bootstrap/plugin	/jqplot.pieRenderer.min.js"></script>
<script type="text/javascript" src="./bootstrap/plugin	/jqplot.meterGaugeRenderer.min.js"></script>
<link rel="stylesheet" type="text/css" href="./bootstrap/css/jquery.jqplot.min.css" />


<c:if test="${ not empty  requestScope.results}">
	<div class="main">
	<h2>${election.title }</h2>
	<table class="table">
	<tr>
		<th><label>Result ID</label></th>
		<th><label>Candidate</label></th>
		<th><label>Constituency</label></th>
		<c:if test="${sessionScope.user.type eq \"Admin\"}">
			<th><label>Actions</label></th>
		</c:if>
		<th><label>Stats</label></th>
	</tr>
	<c:forEach var="result" items="${requestScope.results}">
			<tr>
			<td><span>${result.resultid}</span></td>
			<td><span>${result.candidate}</span></td>
			<td><span>${result.constituency}</span></td>
			<c:if test="${sessionScope.user.type eq \"Admin\"}">
				<c:if test="${empty result.candidate}">
					<td>
					<a href="<s:url action='createElection.action' />?title=Re-${election.title}-${result.constituency}&locations=${result.constituency}">CreateElection</a>
					</td>
				</c:if>
			</c:if>
			<td>
				<div class="">${result.turnout} / ${result.total} </div>
<!-- 				<div width="100">
				<div id="${result.constituency}">
				</div>	
				<script type="text/javascript">
						$(document).ready(function(){
						   	s1 = [${result.turnout}];
						 
						   	plot3 = $.jqplot("${result.constituency}",[s1],{
						       seriesDefaults: {
						           renderer: $.jqplot.MeterGaugeRenderer,
						           rendererOptions: {
						        	   showTickLabels: false,
						               intervals:[${result.mid},${result.total}],
						               intervalColors:['#cc6666','#66cc66']
						           }
						       }
						   });
						});
					</script>
					</div> -->
			</td>
			</tr>
			<tr>
				<td colspan="4">
				<div id="${result.resultid}">
					<script type="text/javascript">
						$(document).ready(function(){  
    						var plot6 = $.jqplot("${result.resultid}", [${result.voteratio}], {
        						seriesDefaults:{ renderer: $.jqplot.PieRenderer },
        						legend:{ show:true }     
    						});   
						});
					</script>
				</div>
				</td>
			</tr>
	</c:forEach>
	</table>
	</div>
</c:if>