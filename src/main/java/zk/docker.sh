docker run -itd --network host -v /data/zk/zk2888:/data -v /data/zk/zk2888/zoo.cfg:/conf/zoo.cfg --name zk2888 zookeeper
docker run -itd --network host -v /data/zk/zk2889:/data -v /data/zk/zk2889/zoo.cfg:/conf/zoo.cfg --name zk2889 zookeeper
docker run -itd --network host -v /data/zk/zk2890:/data -v /data/zk/zk2890/zoo.cfg:/conf/zoo.cfg --name zk2890 zookeeper

