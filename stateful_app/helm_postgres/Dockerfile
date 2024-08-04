FROM openjdk:17-oracle

COPY target/*.jar helm.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","helm.jar"]