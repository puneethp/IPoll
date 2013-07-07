<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %> 
<ul class="nav nav-list">
	<li class="nav-header">Register for</li>
	<li><a href="<s:url action='voteridReg.action' />" alt="Voter ID">Voter ID</a></li>
	<li><a href="<s:url action='partyReqReg.action' />" alt="Party">Party</a></li>
	<li><a href="<s:url action='candidatureRegReqAction.action' />" alt="Candidature">Candidature</a></li>
	<c:if test="${sessionScope.user.type eq  \"WardUser\"}">
		<li class="nav-header">Manage requests</li>
		<li><a href="<s:url action='requestForVoterIdWardUser.action' />" alt="View VoterID">Voter ID</a></li>
		<li><a href="<s:url action='ReqForCandidatureWardUser.action' />" alt="View Candidature">Candidature</a></li>
		<li class="nav-header">Physical Votes</li>
		<li><a href="<s:url action='ReqWardUserVote.action' />" alt="View Candidature">Vote</a></li>
		<li class="nav-header">Add</li>
		<li><a href="<s:url action='ReqWardUserVoterId.action' />" alt="View Candidature">VoterId</a></li>
	</c:if>
	<li class="nav-header">Blog</li>
	<li><a href="<s:url action='blog.action' />" alt="Blog">Create</a></li>
	<li><a href="<s:url action='viewBlogs.action' />" alt="Blog">View All</a></li>
	<li><a href="<s:url action='myblogs.action' />" alt="Blog">My Blogs</a></li>
	<li class="nav-header">Vote</li>
	<li><a href="<s:url action='reqvote.action' />" alt="Vote">Vote</a></li>
	<c:if test="${sessionScope.user.type eq  \"Admin\"}">
	<li class="nav-header">Manage requests</li>
	<li><a href="<s:url action='requestForPartyAdmin.action' />" alt="Party">Party</a></li>
	<li class="nav-header">Election</li>
	<li><a href="<s:url action='createElection.action' />" alt="Add Election">Add Election</a></li>
	<li><a href="<s:url action='viewElections.action' />" alt="Add Election">View Elections</a></li>
	<li class="nav-header">Users</li>
	<li><a href="<s:url action='ReqAddWardUser.action' />" alt="Add WardUser">Add WardUser</a></li>
	<li class="nav-header">Ward</li>
	<li><a href="<s:url action='ReqAddWard.action' />" alt="Add Ward">Add Ward</a></li>
	<li class="nav-header">Edit Pages</li>
	<li><a href="<s:url action='EditPage.action' />?page=News" alt="edit news">News</a></li>
	<li><a href="<s:url action='EditPage.action' />?page=Help" alt="edit home">Help</a></li>
	<li><a href="<s:url action='EditPage.action' />?page=Home" alt="edit help">Home</a></li>
	</c:if>
	<c:if test="${sessionScope.user.type eq\"Candidate\"}">
		<li class="nav-header" id="manage">Manage requests</li>
		<li><a href="<s:url action='ReqForCandidatureToCandidate' />" alt="View Candidature">Candidature</a></li>
	</c:if>
	<c:if test="${sessionScope.user.type eq\"PartyHead\"}">
		<li class="nav-header" id="manage">Manage requests</li>
		<li><a href="<s:url action='viewReqToPartyHead' />" alt="View Candidature">Candidature</a></li>
	</c:if>
	<c:if test="${sessionScope.user.type eq\"CandidateParty\"}">
		<li class="nav-header" id="manage">Manage requests</li>
		<li><a href="<s:url action='viewReqToPartyHead' />" alt="View Candidature">Party Members</a></li>
		<li><a href="<s:url action='ReqForCandidatureToCandidate' />" alt="View Candidature">Candidature</a></li>
	</c:if>
</ul>
<!-- 
	<div class="heading">Register for</div>
	<a href="<s:url action='voteridReg.action' />" alt="Voter ID">Voter ID</a>
	<br />
	<a href="<s:url action='partyReqReg.action' />" alt="Party">Party</a>
	<br />
	<a href="<s:url action='candidatureRegReqAction.action' />" alt="Candidature">Candidature</a>
	<br />
	<c:if test="${sessionScope.user.type eq  \"WardUser\"}">
	<div class="heading">Manage requests</div>
	<a href="<s:url action='requestForVoterIdWardUser.action' />" alt="View VoterID">Voter ID</a>
	<br />
	<a href="<s:url action='ReqForCandidatureWardUser.action' />" alt="View Candidature">Candidature</a>
	<br />
	<div class="heading">Physical Votes</div>
	<a href="<s:url action='ReqWardUserVote.action' />" alt="View Candidature">Vote</a>
	<br />
	<div class="heading">Add</div>
	<a href="<s:url action='ReqWardUserVoterId.action' />" alt="View Candidature">VoterId</a>
	<br />
	</c:if>
	<c:if test="${sessionScope.user.type eq  \"Admin\"}">
	<div class="heading">Manage requests</div>
	<a href="<s:url action='requestForPartyAdmin.action' />" alt="Party">Party</a>
	<br />
	<div class="heading">Election</div>
	<a href="<s:url action='createElection.action' />" alt="Add Election">Add Election</a>
	<br />
	<a href="<s:url action='viewElections.action' />" alt="Add Election">View Elections</a>
	<br />
	<div class="heading">Users</div>
	<a href="<s:url action='ReqAddWardUser.action' />" alt="Add WardUser">Add WardUser</a>
	<br />
	<div class="heading">Ward</div>
	<a href="<s:url action='ReqAddWard.action' />" alt="Add Ward">Add Ward</a>
	<br />
	<div class="heading">Edit Pages</div>
	<a href="<s:url action='EditPage.action' />?page=News" alt="edit news">News</a>
	<br />
	<a href="<s:url action='EditPage.action' />?page=Help" alt="edit home">Help</a>
	<br />
	<a href="<s:url action='EditPage.action' />?page=Home" alt="edit help">Home</a>
	<br />
	</c:if>
	<c:if test="${sessionScope.user.type eq\"Candidate\"}">
		<div class="heading" id="manage">Manage requests</div>
		<a href="<s:url action='ReqForCandidatureToCandidate' />" alt="View Candidature">Candidature</a>
		<br />
	</c:if>
	<c:if test="${sessionScope.user.type eq\"PartyHead\"}">
		<div class="heading" id="manage">Manage requests</div>
		<a href="<s:url action='viewReqToPartyHead' />" alt="View Candidature">Candidature</a>
		<br />
	</c:if>
	<c:if test="${sessionScope.user.type eq\"CandidateParty\"}">
		<div class="heading" id="manage">Manage requests</div>
		<a href="<s:url action='viewReqToPartyHead' />" alt="View Candidature">Party Members</a>
		<br />
		
		<a href="<s:url action='ReqForCandidatureToCandidate' />" alt="View Candidature">Candidature</a>
		<br />
	</c:if>
	<div class="heading">Blog</div>
	<a href="<s:url action='blog.action' />" alt="Blog">Create</a>
	<br />
	<a href="<s:url action='viewBlogs.action' />" alt="Blog">View All</a>
	<br />
	<a href="<s:url action='myblogs.action' />" alt="Blog">My Blogs</a>
	<br />
	<div class="heading">Vote</div>
	<a href="<s:url action='reqvote.action' />" alt="Vote">Vote</a>
	<!--  <div class="heading">Search</div>
	<center>
	<div id="searchBox">
		<span>Google</span><br>
		<input type="text" class="textBox" />
		<button class="button" alt="go">Go</button>
	</div>
	</center>
	 -->