# 使用 OpenJDK 17 的官方 Alpine 镜像
FROM openjdk:17-jdk-alpine
# 设置工作目录
WORKDIR /app
# 创建用于存储临时文件和日志的目录
RUN mkdir -p /tmp /app/logs
# 复制 JAR 文件到容器中，并重命名为 app.jar
COPY joty-1.0.0.jar app.jar
# 声明容器运行时监听的端口
EXPOSE 8090
# 设置容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "app.jar"]
