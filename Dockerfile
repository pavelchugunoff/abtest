FROM openjdk:11
COPY . /abtest
WORKDIR /abtest
RUN ./gradlew --no-daemon bootJar
ENTRYPOINT ["java", "-jar", "build/libs/abtest-0.0.1-SNAPSHOT.jar"]