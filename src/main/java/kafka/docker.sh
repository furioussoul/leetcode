docker run -itd -p 9190:9092 -e KAFKA_ZOOKEEPER_CONNECT=47.114.167.224:2181 -e KAFKA_ADVERTISED_HOST_NAME=47.114.167.224 -e KAFKA_ADVERTISED_PORT=9190 --name kafka1  wurstmeister/kafka
docker run -itd -p 9191:9092 -e KAFKA_ZOOKEEPER_CONNECT=47.114.167.224:2181 -e KAFKA_ADVERTISED_HOST_NAME=47.114.167.224 -e KAFKA_ADVERTISED_PORT=9191 --name kafka2  wurstmeister/kafka
docker run -itd -p 9192:9092 -e KAFKA_ZOOKEEPER_CONNECT=47.114.167.224:2181 -e KAFKA_ADVERTISED_HOST_NAME=47.114.167.224 -e KAFKA_ADVERTISED_PORT=9192 --name kafka3  wurstmeister/kafka
