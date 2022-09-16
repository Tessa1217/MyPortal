FROM openjdk:11-jre
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY Wallet_portal /home/Wallet_portal
ENTRYPOINT ["java","-jar", "/app.jar"]