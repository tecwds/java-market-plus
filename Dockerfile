# maven 3.9 + openjdk-21
FROM maven:3.9-eclipse-temurin-21-jammy as builder

# 设置工作目录
WORKDIR /source

COPY market-plus/pom.xml .

# 缓存依赖
RUN mvn dependency:go-offline

# 复制源代码
COPY market-plus .

# 打包构建

# 跳过 JUnit 测试
ARG MAVEN_OPTS="-DskipTests"

# 构建项目
RUN mvn clean package $MAVEN_OPTS

FROM openjdk:21-jdk

WORKDIR /app

# 使用 openjdk-21 作为运行时
COPY --from=builder /source/target/market-plus.jar /app/market-plus.jar

EXPOSE 39150

ENTRYPOINT ["java", "-jar", "market-plus.jar"]