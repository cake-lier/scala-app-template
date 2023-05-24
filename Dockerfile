FROM eclipse-temurin:17.0.7_7-jre

RUN mkdir /opt/app
COPY application.conf /opt/app/
COPY target/scala-3.2.2/main.jar /opt/app/
CMD ["java", "-jar", "/opt/app/main.jar", "/opt/app/application.conf"]
