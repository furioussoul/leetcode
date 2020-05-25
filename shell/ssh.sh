#生成密钥对
ssh-keygen -t rsa
mv id_rsa ~/.ssh
cat ~/.ssh/id_rsa.pub | ssh {host}@{ip} 'cat>>.ssh/authorized_keys'