<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %>
<script language="javascript" type="text/javascript">
isValid = false;
function hasFilledfields()
{
	if(document.getElementById('name').value.length!=0&&document.getElementById('surName').value.length!=0&&
			document.getElementById('curL1').value.length!=0&&document.getElementById('pobL1').value.length!=0&&
			document.getElementById('userPhoto').value.length!=0&&document.getElementById('curState').value.length!=0
			&&document.getElementById('curCity').value.length!=0&&document.getElementById('curWardNo').value.length!=0)
	{
		var temp = document.getElementById("error");
		temp.innerHTML = "";
		return true;
	}
	var temp = document.getElementById("error");
	temp.innerHTML = "Error: Empty Fields.";
	return false;
}
function verifyForm()
{
	verify_ward();
	if(hasFilledfields()&&verify())
	{
		var temp = document.getElementById("error");
		temp.innerHTML = "";
		return isValid;
	}
	var temp = document.getElementById("error");
	
	temp.innerHTML = "Error: Invalid Fields.";
	return false;
}
function verify()
{
	var day=parseInt(document.getElementById("day").value);
	var month=parseInt(document.getElementById("month").value);
	var year=parseInt(document.getElementById("year").value);
	var divError = document.getElementById("dateError");
	var flag=1;
	switch(month)
	{
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: if(day >31)flag=0;
					break;
		case 2:if( (year%4) == 0)
				{
			 		if(day > 29)
			 			flag=0;
				}
				else
				{
					if(day>28)flag=0;
				}
				break;
		case 4:
		case 6:
		case 9:
		case 11: if(day>30)flag=0;
					break;
	}
	if(flag==0)
	{ 
		divError.innerHTML = "Error in date.Please select a valid date.";
		return false;
	}
	else
	{
		divError.innerHTML = "";
		return true;
	}
}
function verify_ward()
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
				if(temp=="wardvalid")
				{
					
					xx=x[0].getElementsByTagName("value");
					if(xx[0].firstChild.nodeValue=="false")
					{
						var temp = document.getElementById("idError");
						temp.innerHTML = "<p>Error: Ward Doesnot Exists try a different id.</p>";
						
						isValid = false;
					}
					else if(xx[0].firstChild.nodeValue=="true"){
						var temp = document.getElementById("idError");
						temp.innerHTML="";
						isValid = true;
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
	var pval = "city=" + document.getElementById("curCity").value+"&state="+document.getElementById("curState").value+"&wardno="+document.getElementById("curWardNo").value;
	xmlhttp.open("GET","./WardExist?"+pval,true);
	xmlhttp.send();
}
</script>
<div class="main">
	<h2>Voter Id Registration</h2>
	<s:form action="regVoterIdWardUser.action" method="post" enctype="multipart/form-data" onsubmit="return verifyForm()">
		
		<tr>
			<td colspan="2"><div id="error" class="error">${requestScope.error}</div></td>
		</tr>
		<tr>
		<td><label>Name: </label></td>
		<td><input type="text" name="name" id="name" value="${requestScope.votercard.name}" /></td>
		</tr>
		<tr>
		<td><label>Surname: </label></td>
		<td><input type="text" name="surName" value="${requestScope.votercard.surName}" id="surName" /></td>
		</tr>
		<tr>
			<td colspan="2"><label>Date of Birth:</label></td>
		</tr>
		<tr>
			<td colspan="3"><div class="error" id="dateError"></div></td>
		</tr>
		<tr>
			<td>
			</td>
			<td>
				<label>Day:</label>
				<select id="day" name="day">
					<option value="1" selected="selected">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					<option value="26">26</option>
					<option value="27">27</option>
					<option value="28">28</option>
					<option value="29">29</option>
					<option value="30">30</option>
					<option value="31">31</option>
				</select>
				<label>Month:</label>
				<select id="month" name="month">
					<option value="1" selected="selected">January</option>
					<option value="2">February</option>
					<option value="3">March</option>
					<option value="4">April</option>
					<option value="5">May</option>
					<option value="6">June</option>
					<option value="7">July</option>
					<option value="8">August</option>
					<option value="9">September</option>
					<option value="10">October</option>
					<option value="11">November</option>
					<option value="12">December</option>
				</select>
				<label>Year:</label>
				<select id="year" name="year" onblur="verify()">
					<option value="1900" selected="selected">1900</option>
					<option value="1901">1901</option>
					<option value="1902">1902</option>
					<option value="1903">1903</option>
					<option value="1904">1904</option>
					<option value="1905">1905</option>
					<option value="1906">1906</option>
					<option value="1907">1907</option>
					<option value="1908">1908</option>
					<option value="1909">1909</option>
					<option value="1910">1910</option>
					<option value="1911">1911</option>
					<option value="1912">1912</option>
					<option value="1913">1913</option>
					<option value="1914">1914</option>
					<option value="1915">1915</option>
					<option value="1916">1916</option>
					<option value="1917">1917</option>
					<option value="1918">1918</option>
					<option value="1919">1919</option>
					<option value="1920">1920</option>
					<option value="1921">1921</option>
					<option value="1922">1922</option>
					<option value="1923">1923</option>
					<option value="1924">1924</option>
					<option value="1925">1925</option>
					<option value="1926">1926</option>
					<option value="1927">1927</option>
					<option value="1928">1928</option>
					<option value="1929">1929</option>
					<option value="1930">1930</option>
					<option value="1931">1931</option>
					<option value="1932">1932</option>
					<option value="1933">1933</option>
					<option value="1934">1934</option>
					<option value="1935">1935</option>
					<option value="1936">1936</option>
					<option value="1937">1937</option>
					<option value="1938">1938</option>
					<option value="1939">1939</option>
					<option value="1940">1940</option>
					<option value="1941">1941</option>
					<option value="1942">1942</option>
					<option value="1943">1943</option>
					<option value="1944">1944</option>
					<option value="1945">1945</option>
					<option value="1946">1946</option>
					<option value="1947">1947</option>
					<option value="1948">1948</option>
					<option value="1949">1949</option>
					<option value="1950">1950</option>
					<option value="1951">1951</option>
					<option value="1952">1952</option>
					<option value="1953">1953</option>
					<option value="1954">1954</option>
					<option value="1955">1955</option>
					<option value="1956">1956</option>
					<option value="1957">1957</option>
					<option value="1958">1958</option>
					<option value="1959">1959</option>
					<option value="1960">1960</option>
					<option value="1961">1961</option>
					<option value="1962">1962</option>
					<option value="1963">1963</option>
					<option value="1964">1964</option>
					<option value="1965">1965</option>
					<option value="1966">1966</option>
					<option value="1967">1967</option>
					<option value="1968">1968</option>
					<option value="1969">1969</option>
					<option value="1970">1970</option>
					<option value="1971">1971</option>
					<option value="1972">1972</option>
					<option value="1973">1973</option>
					<option value="1974">1974</option>
					<option value="1975">1975</option>
					<option value="1976">1976</option>
					<option value="1977">1977</option>
					<option value="1978">1978</option>
					<option value="1979">1979</option>
					<option value="1980">1980</option>
					<option value="1981">1981</option>
					<option value="1982">1982</option>
					<option value="1983">1983</option>
					<option value="1984">1984</option>
					<option value="1985">1985</option>
					<option value="1986">1986</option>
					<option value="1987">1987</option>
					<option value="1988">1988</option>
					<option value="1989">1989</option>
					<option value="1990">1990</option>
					<option value="1991">1991</option>
					<option value="1992">1992</option>
					<option value="1993">1993</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><label>Sex :</label></td>
			<td><label>Male</label><input checked="checked" type="radio" name="Sex" value="Male" id="Male" />
			<label>Female</label><input type="radio" name="Sex" value="Female" id="female" /></td>
		</tr>
		<tr>
		<td><label>Father's name: </label></td>
		<td><input type="text"  value="${requestScope.votercard.fathersName}" label="Father's name" id="fName" name="fName"  /></td>
		</tr>
		<tr>
			<td colspan="2"><label>Place of Birth :</label></td>
		</tr>
		<tr>
		<td><label>line1: </label></td>
		<td><input type="text"   value="${requestScope.votercard.placeOfBirth}" label="line1 " name="pobL1" size="40" id="pobL1" /></td>
		</tr>
		<tr>
		<td><label>line2: </label></td>
		<td><input type="text"  value="" label="line2 " name="pobL2" size="40" id="pobL2" /></td>
		</tr>
		<tr>
			<td colspan="2"><label>Current Address :</label></td>
		</tr>
		<tr>
		<td><label>line1: </label></td>
		<td><input type="text"  value="${requestScope.votercard.currentAddress}" label="line1 " name="curL1" size="40" id="curL1" /></td>
		</tr>
		<tr>
		<td><label>line2: </label></td>
		<td><input type="text"  value="" label="line2 " name="curL2" size="40" id="curL2" /></td>
		</tr>
		<tr>
		<td colspan="2"><s:label id="idError" /></td>
		</tr>
		<tr>
		<td><label>State: </label></td>
		<td><input type="text" value="${requestScope.votercard.currentAddressState}" label="State " name="curState" id="curState" /></td>
		</tr>
		<tr>
		<td><label>City: </label></td>
		<td><input type="text"  value="${requestScope.votercard.currentAddressCityTown}" label="City " name="curCity" id="curCity" /></td>
		</tr>
		<tr>
		<td><label>WardNo: </label></td>
		<td><input type="text"  value="${requestScope.votercard.currentAddressWardNo}" label="Ward NO " name="curWardNo" id="curWardNo" /></td>
		</tr>
		<tr>
		<td><label>Father's/Wife's id: </label></td>
		<td><input type="text"  value="${requestScope.votercard.relID}" label="Father's/Wife's id " id="relId" name="relId" /></td>
		</tr>
		<s:file label="Photo " name="userPhoto" id="userPhoto" />
		<s:submit cssClass="inbutton" align="center" value="Submit" />
	</s:form>
</div>