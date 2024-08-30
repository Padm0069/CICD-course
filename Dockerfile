FROM openjdk:17-alpine
EXPOSE 8090
ADD target/CICD-demo.jar CICD-demo.jar
ENTRYPOINT ["java", "-jar", "/cicd-github-actions.jar"]