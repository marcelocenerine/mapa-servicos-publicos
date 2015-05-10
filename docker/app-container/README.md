. Run Tomcat container

+
[source, text]
----
docker run -d -p 8080:8080 --name app --link db:db-container marcelocenerine/mapa-servicos-publicos-app:latest
----
