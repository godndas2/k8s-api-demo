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
    app: k8s-demo
  name: k8s-demo
spec:
  replicas: 1
  selector:
    matchLabels:
      app: k8s-demo
  strategy: {}
  template:
    metadata:
      labels:
        app: k8s-demo
    spec:
      containers:
        - image: huhyun/k8s-demo:0.1
          name: k8s-beginner
---
apiVersion: v1
kind: Service
metadata:
  labels:
    app: k8s-demo
  name: k8s-demo
spec:
  ports:
    - name: svc-8080
      port: 8080
      protocol: TCP
      nodePort: 31111
      targetPort: 8080
  selector:
    app: k8s-demo
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

# Prometheus server

Pushgateway를 통해 platfrom내 각종 Resources 의 metric 을 pulling 하고, alertmanager 통해 다른 channel에 notification 하거나 Grafana와 같은 monitoring tool 에 수집한 metric을 전달해 시각화함

# Helm Install

```docker
$ wget https://get.helm.sh/helm-v3.0.0-linux-amd64.tar.gz
$ tar -zxvf helm-v3.0.0-linux-amd64.tar.gz
```
# Prometheus Install

```docker
$ helm install prometheus prometheus-community/prometheus
$ kubectl get pod {POD_NAME}

// chart customizing
$ helm install -f config.yaml prometheus-community/prometheus
```

PersistenctVolume의 enabled 들을 false 로 바꿔준다.  
![image](https://user-images.githubusercontent.com/34512538/111580057-586c5300-87fa-11eb-8d3f-5d75d745ee9a.png)  


ClusterIP 에서 NodePort로 변경해준다.  

```docker
$ kubectl edit svc {SERVICE_NAME}
```
![image](https://user-images.githubusercontent.com/34512538/111580140-70dc6d80-87fa-11eb-83c8-7893cdbffedd.png)

## Prometheus Access
```
$ curl <prometheus-server_ip:port>
```
## AlertManager Access
```
$ curl <alertManager:port>
```

# Grafana Install

```yaml
$ helm repo install grafana https://grafana.github.io/helm-charts
```

## springboot applicatoin pod
![image](https://user-images.githubusercontent.com/34512538/111579914-2c50d200-87fa-11eb-9e88-48f2c3f3cac0.png)






