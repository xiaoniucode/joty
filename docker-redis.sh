docker run -d \
  --name redis \
  --restart=always \
  -p 6379:6379 \
  -v /home/lighthouse/redis:/data \
  redis:latest \
  --requirepass 123456
