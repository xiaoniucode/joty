docker run -d \
  --name mysql \
  --restart=always \
  -p 3306:3306 \
  -v /home/lighthouse/tinynote/mysql/data:/var/lib/mysql \
  -v /home/lighthouse/tinynote/mysql/conf:/etc/mysql/conf.d \
  -v /home/lighthouse/tinynote/mysql/logs:/var/log/mysql \
  -e MYSQL_ROOT_PASSWORD=123456 \
  -e TZ=Asia/Shanghai \
  mysql:5.7
