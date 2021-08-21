FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/books-mysql.jar books-mysql.jar
ENTRYPOINT ["java","-jar","books-mysql.jar"]