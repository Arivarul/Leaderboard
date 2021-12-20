FROM openjdk:11
ADD target/leaderboard-latest.jar ../docker/leaderboard.jar
RUN chmod 777 ../docker/leaderboard.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "../docker/leaderboard.jar"]