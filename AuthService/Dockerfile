FROM openjdk:17-alpine
MAINTAINER DimaSudakov
COPY authservice-impl/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080