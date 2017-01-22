# Dockerizing the http://www.mapaservicopublico.com.br application
FROM       java:8
MAINTAINER Marcelo Cenerino <marcelocenerine@gmail.com>

COPY *.jar /opt/cenerino/

ENV MONGODB_URI mongodb://db-container:27017/mapaservicospublicosdb

ENTRYPOINT ["java", "-jar", "/opt/cenerino/mapa-servicos-publicos.jar"]
