<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script language="javascript" type="text/javascript">
var isValid = false;
function hasFilledfields()
{
	if(document.getElementById('user').value.length!=0&&document.getElementById('password').value.length!=0&&
			document.getElementById('conPassword').value.length!=0&&document.getElementById('email').value
			.length!=0&&document.getElementById('language').value.length!=0)
	{
		var temp = document.getElementById("error");
		temp.innerHTML = "";
		return true;
	}
	var temp = document.getElementById("error");
	temp.innerHTML = "<p>Error: Empty Fields.</p>";
	return false;
}
function passTest()
{
	if(document.getElementById('password').value==document.getElementById('conPassword').value)
	{
		var temp = document.getElementById("passError");
		temp.innerHTML = "";
		return true;
	}
	var temp = document.getElementById("passError");
	temp.innerHTML = "<p>Error: Password dont match.</p>";
	return false;
}
function verifyForm()
{
	//verify();
	isValid = true;
	if(hasFilledfields())
	{
		return isValid;
	}
	return false;
}
function verify()
{
	var xmlhttp;
	var txt,xx,x,i;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  x=xmlhttp.responseXML.documentElement.getElementsByTagName("msg");
		  if(x.length==1)
			{
			  
				xx=x[0].getElementsByTagName("Query");
				var temp = xx[0].firstChild.nodeValue;
				if(temp=="UserExist")
				{
					
					xx=x[0].getElementsByTagName("value");
					if(xx[0].firstChild.nodeValue=="false")
					{
						var temp = document.getElementById("idError");
						temp.innerHTML="";
						isValid = true;
					}
					else if(xx[0].firstChild.nodeValue=="true"){
						var temp = document.getElementById("idError");
						temp.innerHTML = "<p>Error: User Exists try a different id.</p>";
						isValid = false;
					}
					else
					{
						var temp = document.getElementById("idError");
						temp.innerHTML = "<p>Error: Internal Error please try later.</p>";
						isValid = false;
					}
				}
			}
	    }
	  }
	var pval = "user=" + document.getElementById("user").value;
	xmlhttp.open("GET","/IPoll/UserExist?"+pval,true);
	xmlhttp.send();
}
</script>
<c:if test="${not empty requestScope.msg}">
	<label class="label label-error">${requestScope.msg}</label>
</c:if>
<form onsubmit="return verifyForm()" method="post"
	enctype="multipart/form-data" action="partyReg.action">
	<fieldset>
		<legend>Register Party</legend>
		<label id="error" class="label label-error"></label> <label>Name
		</label> <input type="text" id="partyname"
			value="${requestScope.partycard.partyname}" name="partyname" /> <label>Symbol
		</label> <input type="file" name="userPhoto" id="userPhoto" /> <input
			type="submit" class="btn btn-primary" value="Register" />
	</fieldset>
</form>