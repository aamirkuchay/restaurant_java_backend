FROM openjdk:17
WORKDIR /app

COPY target/zomato_docker_app.jar zomato_docker_app.jar

ENTRYPOINT ["java","-jar","zomato_docker_app.jar"]

EXPOSE 8089