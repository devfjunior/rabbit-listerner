FROM maven:3.6.3-jdk-8 as maven
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn package -DskipTests

FROM openjdk:8-jre-alpine
RUN mkdir -p  /usr/src/app/
COPY --from=maven target/RABBIT_BACKEND.war /usr/src/app/Backend.war
ENTRYPOINT ["java", "-jar","/usr/src/app/Backend.war"]