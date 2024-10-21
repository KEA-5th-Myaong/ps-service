FROM openjdk:17
COPY build/libs/popolog-ps-service.jar popolog-ps-service.jar
ENTRYPOINT ["java", "-jar", "/popolog-ps-service.jar"]