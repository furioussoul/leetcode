docker stop $(docker ps -a | grep redis | awk '{print$1}')
docker rm $(docker ps -a | grep redis | awk '{print$1}')