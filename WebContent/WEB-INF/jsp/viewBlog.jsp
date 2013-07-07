<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@page import="dao.Blog"%>
<%@page import="dao.User"%>
<%@page import="dao.Comments"%>

<script language="javascript" type="text/javascript">
	function like_blog() {
		var xmlhttp;
		var txt, xx, x, i;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				x = xmlhttp.responseXML.documentElement
						.getElementsByTagName("msg");
				if (x.length == 1) {

					xx = x[0].getElementsByTagName("Query");
					var temp = xx[0].firstChild.nodeValue;
					if (temp == "Like") {

						xx = x[0].getElementsByTagName("value");
						if (xx[0].firstChild.nodeValue == "1") {
							var temp = document.getElementById("likes");
							var t = parseInt(temp.innerHTML);
							t = t + 1;
							temp.innerHTML = t;
							var temp3 = document.getElementById("likebutton");
							temp3.disabled = true;
							isValid = true;
						} else if (xx[0].firstChild.nodeValue == "true") {
							var temp = document.getElementById("idError");
							temp.innerHTML = "<p>Error: Can't like now.</p>";
							isValid = false;
						} else {
							var temp = document.getElementById("idError");
							temp.innerHTML = "<p>Error: Internal Error please try later.</p>";
							isValid = false;
						}
					}
				}
			}
		}
		var pval = "blog_id=" + document.getElementById("blog_id").value;
		xmlhttp.open("GET", "./LikeTheBlog?" + pval, true);
		xmlhttp.send();
	}
</script>
<c:if test="${not empty blog }">
	<div class="span12">
		<h1>${blog.title }</h1>
		<div class="span11">${blog.post}</div>
		<label class="label label-important" id="idError"></label>
	</div>
	<div class="span12">
		<div class="span6">
			<div>
				Views <span class="badge badge-info">${blog.views}</span>
			</div>
		</div>
		<div class="span6">
			<div class="pull-right">
				<c:if test="${userLikes eq 0 }">
					<button class="btn" id="likebutton" onClick="like_blog()">like</button>
				</c:if>
				<c:if test="${userLikes eq 1 }">Like</c:if>
				<span class="badge badge-success">${likes}</span>
			</div>
		</div>
	</div>

	<div class="span12">
		<form action="commentBlog.action">
			<fieldset>
				<legend>Comment</legend>
				<input id="blog_id" name="blog_id" type="hidden"
					value="${blog.blog_id }">
				<textarea class="span12" name="comment" id="comment"
					placeholder="Have a thought ?? leave a comment...."></textarea>
				<input type="submit" class="btn btn-primary pull-right" value="comment" />
				<br/>
			</fieldset>
		</form>
	</div>
	<div class="span12">
		<%
			int count = 0;
		%>
		<c:if test="${not empty comments }">
			<c:forEach var="comment" items="${comments }">
				
					<blockquote>
						${comment.comment}
						<small>${comment.user_name}-${comment.date}</small>
					</blockquote>
					<%
						count++;
					%>
			</c:forEach>
		</c:if>
	</div>

</c:if>