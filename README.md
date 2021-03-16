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

## Push the Docker Container to Docker Hub
-  docker push huhyun/k8s-demo  

# Deploy the App to k8s

## yaml
```
apiVersion: apps/v1
kind: Deployment
metadata:
  labels:
    app: demo
  name: demo
spec:
  replicas: 1
  selector:
    matchLabels:
      app: demo
  strategy: {}
  template:
    metadata:
      labels:
        app: demo
    spec:
      containers:
        - image: huhyun/k8s-demo:demo
          name: k8s-beginner
---
apiVersion: v1
kind: Service
metadata:
  labels:
    app: demo
  name: demo
spec:
  ports:
    - name: svc-8080
      port: 8080
      protocol: TCP
      nodePort: 31111
      targetPort: 8080
  selector:
    app: demo
  type: NodePort
```

## yaml deploy
- kubectl apply -f demo.yaml  

## Check for the pods
- kubectl get pod -A

## Expose
- kubectl expose deployment k8s-demo --type=LoadBalancer --name=k8s-demo --port=8080  

## check the external IP
- kubectl get svc k8s-demo  
- <EXTERNAL_IP>:8080






