# redis6.0.3
# 4.0开始支持cluster


main.java.docker run -itd  -v /workspace/redis/8001/redis.conf:/etc/conf/redis.conf  --network host --name redis8001 redis /etc/conf/redis.conf
main.java.docker run -itd  -v /workspace/redis/8002/redis.conf:/etc/conf/redis.conf  --network host --name redis8002 redis /etc/conf/redis.conf
main.java.docker run -itd  -v /workspace/redis/8003/redis.conf:/etc/conf/redis.conf  --network host --name redis8003 redis /etc/conf/redis.conf
main.java.docker run -itd  -v /workspace/redis/8004/redis.conf:/etc/conf/redis.conf  --network host --name redis8004 redis /etc/conf/redis.conf
main.java.docker run -itd  -v /workspace/redis/8005/redis.conf:/etc/conf/redis.conf  --network host --name redis8005 redis /etc/conf/redis.conf
main.java.docker run -itd  -v /workspace/redis/8006/redis.conf:/etc/conf/redis.conf  --network host --name redis8006 redis /etc/conf/redis.conf



#查看容器在局域网的ip
#main.java.docker network inspect $(main.java.docker network ls |grep redis_bridge| awk '{print$1}')

#redis-cli -c -a redispwd -h redis8001  -p 6379




redis-cli -a redispwd --cluster create --cluster-replicas 1 47.114.167.224:8001 47.114.167.224:8002 47.114.167.224:8003 47.114.167.224:8004 47.114.167.224:8005 47.114.167.224:8006
