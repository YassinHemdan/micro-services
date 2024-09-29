FROM openjdk:17-oracle

COPY target/*.jar customer.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","customer.jar"]