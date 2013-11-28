<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="fb-root"></div>
<input type="hidden" id="fb-app-id" value="${applicationScope.facebookAppId}" />

<c:choose>
	<c:when test="${empty sessionScope.user}">
		<div class="boxFacebook">
			<form method="POST">
				<input type="hidden" id="name" name="user.name" />
				<input type="hidden" id="pictureUrl" name="user.pictureUrl" />
				<a class="fb-login-button disableFacebook" href="javascript:void(0)">Entrar com Facebook</a>					
			</form>
		</div>
	</c:when>
	<c:otherwise>
		<div class="boxFacebookUserData">
			<span id="picture"><img src="${sessionScope.user.pictureUrl}"/></span>
			<span id="name">${sessionScope.user.name}</span>
			<span><a href="user/logout">Logout</a></span>
		</div>	
	</c:otherwise>
</c:choose>