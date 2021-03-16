# k8s-Beginner
# Quick guide

## REST API EndPoint
```
@RestController
public class TestK8sController {

    @GetMapping()
    String hello() {
        return "HELLO KUBE";
    }

}
```
## Create the Docker Image
- docker build -t <Docker_username>/k8s-demo
```
# Docker 환경 구성
FROM adoptopenjdk/openjdk11:alpine-jre

# 해당 이미지를 관리하는 사람
LABEL maintainer="huhyun"

# 컨테이너가 필요한 데이터를 저장하는 곳
VOLUME /tmp

# 외부에 노출되는 포트 번호
EXPOSE 8080

# 현재 JAR 파일 변수 설정
ARG JAR_FILE=target/*.jar

# demo.jar의 이름으로 JAR 파일 복사
COPY ${JAR_FILE} demo.jar

# 컨테이너 실행 시 실행될 명령어 "java -jar demo.jar"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/demo.jar"]
```

