FROM openjdk:8
ADD target/spring-boot-trade-store-example-0.0.1-SNAPSHOT.jar spring-boot-trade-store-example-0.0.1-SNAPSHOT.jar
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "spring-boot-trade-store-example-0.0.1-SNAPSHOT.jar"]