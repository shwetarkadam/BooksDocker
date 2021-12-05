FROM maven:3.8.2-jdk-11

WORKDIR /
EXPOSE 8085
COPY pom.xml .
RUN mvn clean package -DskipTests
CMD mvn spring-boot:run
RUN mvn -B clean verify
