# Dockerizing the http://www.mapaservicopublico.com.br/ application
# This Docker container hosts a pre-populated database (mongodb) with almost 
# 200.000 places that offer public services in all Brazilian states.

FROM       marcelocenerine/mongodb-docker-container:latest
MAINTAINER Marcelo Cenerino <marcelocenerine@gmail.com>

WORKDIR /tmp/mapaservicospublicos/
ADD data ./

RUN ["chmod", "+x", "restore_backup.sh"]
RUN ["./restore_backup.sh", "db_bkp_20150511.tar.gz"]
