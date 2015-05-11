### Run Tomcat container

    docker run -d -p 8080:8080 --name app --link db:db-container marcelocenerine/mapa-servicos-publicos-app:latest


### Access the app in your browser: 
http://localhost:8080/mapa-servicos-publicos/home
