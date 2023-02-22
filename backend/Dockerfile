FROM maven:3.8.5-openjdk-17

WORKDIR /super_chollo
COPY . .
RUN mvn clean install

RUN mvn dependency:resolve

CMD mvn spring-boot:run