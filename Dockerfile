FROM maven:3.8.2-jdk-11

WORKDIR /
EXPOSE 8085
COPY pom.xml .
RUN mvn clean package -DskipTests
CMD mvn spring-boot:run
RUN mvn -B clean verify

FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/books-mysql.jar books-mysql.jar
ENTRYPOINT ["java","-jar","books-mysql.jar"
