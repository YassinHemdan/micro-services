FROM openjdk:17-oracle

COPY target/*.jar order.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","order.jar"]