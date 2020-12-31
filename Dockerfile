FROM java:8
VOLUME /tmp
ADD ./target/MyFlashSale-1.0.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
EXPOSE 8080