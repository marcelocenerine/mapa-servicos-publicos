# Dockerizing the http://www.mapaservicopublico.com.br application
# This Docker container runs a Tomcat web container that hosts the web application.

FROM       tomcat:latest
MAINTAINER Marcelo Cenerino <marcelocenerine@gmail.com>


RUN cd $CATALINA_HOME/webapps && wget https://drone.io/github.com/marcelocenerine/mapa-servicos-publicos/files/mapa-servicos-publicos-web/target/mapa-servicos-publicos.war > /dev/null 2>&1

ENV MONGOHQ_URL mongodb://db-container:27017/mapaservicospublicosdb
