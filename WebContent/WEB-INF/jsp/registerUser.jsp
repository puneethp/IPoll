<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script language="javascript" type="text/javascript">
	var isValid = false;
	function hasFilledfields() {
		if (document.getElementById('user').value.length != 0
				&& document.getElementById('password').value.length != 0
				&& document.getElementById('conPassword').value.length != 0
				&& document.getElementById('email').value.length != 0
				&& document.getElementById('language').value.length != 0) {
			var temp = document.getElementById("error");
			temp.style.visibility = "hidden";
			temp.innerHTML = "";
			return true;
		}
		var temp = document.getElementById("error");
		temp.innerHTML = "<p>Error: Empty Fields.</p>";
		temp.style.visibility = "visible";
		return false;
	}
	function passTest() {
		if (document.getElementById('password').value == document
				.getElementById('conPassword').value) {
			var temp = document.getElementById("passError");
			temp.innerHTML = "";
			temp.style.visibility = "hidden";
			return true;
		}
		var temp = document.getElementById("passError");
		temp.innerHTML = "<p>Error: Password dont match.</p>";
		temp.style.visibility = "visible";
		return false;
	}
	function verifyForm() {
		verify();
		if (passTest() && hasFilledfields()) {
			return isValid;
		}
		return false;
	}
	function verify() {
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
					if (temp == "UserExist") {

						xx = x[0].getElementsByTagName("value");
						if (xx[0].firstChild.nodeValue == "false") {
							var temp = document.getElementById("idError");
							temp.innerHTML = "";
							temp.style.visibility = "hidden";
							isValid = true;
						} else if (xx[0].firstChild.nodeValue == "true") {
							var temp = document.getElementById("idError");
							temp.style.visibility = "visible";
							temp.innerHTML = "<p>Error: User Exists try a different id.</p>";
							isValid = false;
						} else {
							var temp = document.getElementById("idError");
							temp.style.visibility = "visible";
							temp.innerHTML = "<p>Error: Internal Error please try later.</p>";
							isValid = false;
						}
					}
				}
			}
		}
		var pval = "user=" + document.getElementById("user").value;
		xmlhttp.open("GET", "./UserExist?" + pval, true);
		xmlhttp.send();
	}
</script>
<c:if test="${not empty requestScope.msg}">
	<label class="alert alert-error">${requestScope.msg}</label>
</c:if>

<style>
.invi {
	visibility: hidden;
}
</style>
<form onsubmit="return verifyForm()" action="regUser.action">
	<fieldset>
		<legend>Register For User</legend>
		<span id="error" class="label label-warning"></span> <label>User</label>
		<input type="text" onblur="verify()" name="user" id="user" /> <span
			id="idError" class="label label-warning"></span> <label>Password</label>
		<input name="password" id="password" type="password" /> <label>Re-Type</label>
		<input onblur="passTest()" name="conPassword" id="conPassword"
			type="password" /> <span id="passError"
			class="label label-warning"></span> <label>Language</label> <select
			id="language" name="language">
			<option value="EN-US">English</option>
			<option value="KAN">Kanada</option>
			<option value="HIN">Hindi</option>
		</select> <label>Email</label> <input type="text" name="email" id="email" />
		<br />
		<button type="submit" class="btn btn-primary">Register</button>
		<button type="reset" class="btn">Reset</button>
	</fieldset>
</form>
<!-- 
	<s:form onsubmit="return verifyForm()" action="regUser.action">
		<s:label id="error" />
		<s:textfield  size="10" cssClass="textBox" onblur="verify()" label="UserID " name="user" id="user" />
		<s:label id="idError" />
		<s:password  size="10" cssClass="textBox" label="Password " name = "password" id="password" />
		<s:password  size="10" cssClass="textBox" onblur="passTest()" name="conPassword" label="Confirm password " id ="conPassword" />
		<s:label id="passError" />
		<s:combobox cssClass="textBox"  label="Language " id="language" name="language" list="{'EN-US','KAN','HIN','SKT'}" />
		<s:textfield  size="25	" cssClass="textBox" label="Email-ID " name="email" id="email" />
		<s:submit cssClass="inbutton" value="Register" />
	</s:form>  -->
