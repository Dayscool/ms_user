FROM openjdk:11
EXPOSE 10100
ADD target/ms_user.jar ms_user.jar
ENTRYPOINT ["java", "-jar", "/ms_user.jar"]

