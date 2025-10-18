docker run -d \
  --restart always \
  -p 8090:8090 \
  --add-host=host.docker.internal:host-gateway \
  --name joty \
  -v /home/lighthouse/joty/app:/root joty:1.0.0
