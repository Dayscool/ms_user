FROM openjdk:11
EXPOSE 8080
ADD target/ms_user.jar ms_user.jar
ENTRYPOINT ["java", "-jar", "/ms_user.jar"]

