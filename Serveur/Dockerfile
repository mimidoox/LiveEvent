FROM openjdk:17
WORKDIR /App

COPY /target/Serveur-0.0.1-SNAPSHOT.jar .

# Expose port 8761
EXPOSE 8761

CMD ["java", "-jar" , "Serveur-0.0.1-SNAPSHOT.jar"]