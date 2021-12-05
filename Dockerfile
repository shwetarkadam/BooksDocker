FROM maven:3.8.2-jdk-11
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
EXPOSE 8085
RUN mvn clean package -DskipTests
CMD mvn spring-boot:run
RUN mvn -B clean verify -DskipTests

FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
#ADD target/books-mysql.jar books-mysql.jar
ENTRYPOINT ["java","-jar","app.jar"]
