# Dockerizing the http://www.mapaservicopublico.com.br/ application
# This Docker container hosts a pre-populated database (mongodb) with almost 
# 200.000 places that offer public services in all Brazilian states.

FROM       marcelocenerine/mongodb-docker-container:latest
MAINTAINER Marcelo Cenerino <marcelocenerine@gmail.com>

ADD data /tmp/mapaservicospublicos/data/

RUN /tmp/mapaservicospublicos/data/restore_backup.sh /tmp/mapaservicospublicos/data/db_bkp_20150426.tar.gz
