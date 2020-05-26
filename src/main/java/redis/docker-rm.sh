main.java.docker stop $(main.java.docker ps -a | grep redis | awk '{print$1}')
main.java.docker rm $(main.java.docker ps -a | grep redis | awk '{print$1}')