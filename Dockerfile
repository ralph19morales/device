# STEP 1 : BUILD PROJECT

FROM eclipse-temurin:17-alpine as builder

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY build.gradle settings.gradle gradlew /usr/app/
COPY gradle $APP_HOME/gradle

RUN chmod +x ./gradlew && ./gradlew build -x test || return 0
COPY . .
RUN ./gradlew build -x test

# STEP 2 : DEPLOY PROJECT

FROM eclipse-temurin:17-jre-alpine as runner

LABEL maintainer="ralph19.morales@gmail.com"

COPY --from=0 /usr/app/build/libs/main.jar main.jar

EXPOSE 80

ENTRYPOINT ["sh","-c","java -jar main.jar"]