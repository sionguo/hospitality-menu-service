FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /opt/app
COPY ./ ./
RUN ./gradlew --no-daemon clean check bootJar

FROM eclipse-temurin:17-jre-jammy
RUN mkdir /home/nobody
WORKDIR /home/nobody
USER 65534
COPY --from=builder /opt/app/service/build/libs/*-boot.jar /home/nobody/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar" ]
