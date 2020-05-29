kafka-topics.sh  --zookeeper 47.114.167.224:2181 --create --topic  bar-topic --replication-factor 3 --partitions 3
kafka-topics.sh  --zookeeper 47.114.167.224:2181 --list
kafka-topics.sh  --zookeeper 47.114.167.224:2181 --describe bar-topic

kafka-console-producer.sh --broker-list 47.114.167.224:9190 --topic bar-topic
kafka-console-consumer.sh --bootstrap-server 47.114.167.224:9190 --consumer-property group.id=group1 --consumer-property client.id=consumer-1 --topic bar-topic
//消费组列表
kafka-consumer-groups.sh --bootstrap-server 47.114.167.224:9190 --list
//消费分区offset
kafka-consumer-groups.sh  --bootstrap-server 47.114.167.224:9190 --describe --group group1
kafka-consumer-groups.sh --new-consumer --bootstrap-server 192.168.6.65:9092 --group resourcecenteGroup --describe | grep "BUSINESS_LOG"