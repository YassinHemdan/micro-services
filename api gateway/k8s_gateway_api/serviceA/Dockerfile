FROM openjdk:17-oracle

COPY target/*.jar serviceA.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","serviceA.jar"]