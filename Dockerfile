FROM maven:3.8.1-jdk-11-slim

WORKDIR /numista
COPY . /numista/

RUN mvn clean install

EXPOSE 8089
ENTRYPOINT ["java","-jar", "./target/numista-graphql.jar"]
