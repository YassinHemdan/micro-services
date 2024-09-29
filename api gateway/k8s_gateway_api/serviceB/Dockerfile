FROM openjdk:17-oracle

COPY target/*.jar serviceB.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","serviceB.jar"]