<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
	var count = 1;
	var isValid = false;
	function addCandidate() {
		if (count <= 10) {
			var newCandidate, parent, newLine;
			newCandidate = document.createElement("input");
			newLine = document.createElement("br");
			parent = document.getElementById("CandidateList");
			newCandidate.setAttribute('id', "candidate" + count);
			newCandidate.setAttribute('name', "candidate" + count);
			newCandidate.setAttribute('type', "text");
			newCandidate.setAttribute('onblur', "return CheckCandidate(this)");
			parent.appendChild(newCandidate);
			parent.appendChild(newLine);
			count++;

		}

		else {
			alert("Cannot add more Candidates.");
		}
		return true;
	}
	function CheckCandiate(me) {
		var user = document.getElementById("username_value");
		var candidate = me.value;
		if (candidate == user) {
			alert("Cannot add Self in Candidate List to approve.");
			return false;
		}
		return true;
	}
	function hasFilledfields() {
		if (document.getElementById('constituency').value.length != 0) {
			var temp = document.getElementById("error");
			temp.innerHTML = "";
			return true;
		}
		var temp = document.getElementById("error");
		temp.innerHTML = "Error: Empty Fields.";
		return false;
	}
	function verifyForm() {
		verify_ward();
		if (hasFilledfields()) {
			var temp = document.getElementById("error");
			temp.innerHTML = "";
			return isValid;
		}
		var temp = document.getElementById("error");

		temp.innerHTML = "Error: Invalid Fields.";
		return isValid;
	}
	function verify_ward() {
		var xmlhttp;
		var txt, xx, x, i;
		if (window.XMLHttpRequest) {
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
					if (temp == "wardvalid") {

						xx = x[0].getElementsByTagName("value");
						if (xx[0].firstChild.nodeValue == "false") {
							var temp = document.getElementById("idError");
							temp.innerHTML = "<p>Error: Ward Doesnt Exists try a different id.</p>";
							isValid2 = false;
						} else if (xx[0].firstChild.nodeValue == "true") {
							var temp = document.getElementById("idError");
							temp.innerHTML = "";
							isValid2 = true;
						} else {
							var temp = document.getElementById("idError");
							temp.innerHTML = "<p>Error: Internal Error please try later.</p>";
							isValid2 = false;
						}
					}
				}
			}
		}
		var pval = "wardid=" + document.getElementById("constituency").value;
		xmlhttp.open("GET", "./WardIdExist?" + pval, true);
		xmlhttp.send();
	}
</script>



<form action="candidateReg.action" enctype="multipart/form-data"
	method="post" onsubmit="verifyForm()">
	<fieldset>
		<legend>Candidature Registration</legend>
		<div class="idError"></div>
		<div class="label label-error" id="error">${requestScope.error}</div>
		<label>Constituency</label> <input type="text" onblur="verify_ward()"
			size="10" name="constituency" id="constituency" /> <label>Representing
			Party</label> <input type="text" name="representingParty"
			id="representingParty" /> <label>Symbol</label> <input type="file"
			name="userPhoto" id="userPhoto" /> <label>Candidates</label>
		<div id="CandidateList">
			<br />
		</div>
		<input value="Add Candidate(s)" class="btn" type="button"
			onclick="return addCandidate()" /> <br /> <input type="submit"
			class="btn btn-primary" value="Submit" />
	</fieldset>
</form>
