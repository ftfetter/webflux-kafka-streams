#!/bin/bash
# Script to initialize Kafka using the docker-compose file and create quickstart + quickstart-result topics
echo "Running docker-compose file"

# Checking which docker compose methods are installed
docker compose version | grep "Docker Compose version"
if [ $? -eq 1 ]
then
  docker-compose version | grep "docker-compose version"
  if [ $? -eq 1 ]
  then
    echo "Could not found any docker-compose file runner. Please check if it is installed and try again."
  else
    docker-compose up -d
  fi
else
  docker compose up -d
fi

echo "Creating Kafka topics"
echo " - \`quickstart\` Topic"
docker exec broker kafka-topics --bootstrap-server broker:9092 --create --topic quickstart
echo " - \`quickstart-result\` Topic"
docker exec broker kafka-topics --bootstrap-server broker:9092 --create --topic quickstart-result