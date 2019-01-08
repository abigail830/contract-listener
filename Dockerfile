FROM java:openjdk-8-jdk
ADD ./target/contract-listener-0.0.1-SNAPSHOT.jar contract-listener.jar
RUN chmod a+x contract-listener.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "./code-generator.jar"]
