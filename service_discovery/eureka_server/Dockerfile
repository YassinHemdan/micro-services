FROM openjdk:17-oracle

COPY target/*.jar eureka_server.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","eureka_server.jar"]