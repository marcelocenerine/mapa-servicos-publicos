<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Prova de conceito</title>
</head>
<body>
	<h1>
		Teste as URLs abaixo em seu browser.  
	</h1>
	<ul>
		<li><b>Cidade por ID:</b> <a href="api/city/355220">http://mongo-web.herokuapp.com/api/city/355220</a> (testem outros IDs IBGE)</li>
		<li><b>Cidade por UF:</b> <a href="api/state/SP/city">http://mongo-web.herokuapp.com/api/state/SP/city</a> (testem outras UFs)</li>
		<li><b>Todas cidades:</b> <a href="api/city">http://mongo-web.herokuapp.com/api/city</a></li>
	</ul>
	
</body>
</html>
