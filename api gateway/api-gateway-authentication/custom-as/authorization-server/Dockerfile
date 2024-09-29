FROM openjdk:17-oracle

COPY target/*.jar authorization-server.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","authorization-server.jar"]