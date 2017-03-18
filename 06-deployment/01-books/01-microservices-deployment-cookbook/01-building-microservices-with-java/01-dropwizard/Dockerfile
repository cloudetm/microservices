FROM openjdk:8
RUN mkdir -p /opt/packt/geolocation
ADD target/app-1.0-SNAPSHOT.jar /opt/packt/geolocation/
EXPOSE 8080
CMD ["java", "-jar", "/opt/packt/geolocation/app-1.0-SNAPSHOT.jar"]
