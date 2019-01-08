FROM java:openjdk-8-jdk
VOLUME /tmp
ADD target/contract-listener-0.0.1-SNAPSHOT.jar /work/contract-listener.jar
RUN chmod a+x /work/contract-listener.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "/work/contract-listener.jar"]
