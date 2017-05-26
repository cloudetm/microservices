# Install and configure kubernetes

https://github.com/kubernetes/minikube

https://kubernetes.io/docs/getting-started-guides/minikube/

> Installation

*Mac*

```
brew cask install minikube
```

> Quickstart

```
minikube delete # optional - delete the old vm

minikube start

kubectl run hello-minikube --image=gcr.io/google_containers/echoserver:1.4 --port=8080
kubectl expose deployment hello-minikube --type=NodePort

kubectl get pod

curl $(minikube service hello-minikube --url)

minikube stop
```

> Dashboard

```
minikube dashboard

http://192.168.99.100:30000/#!/workload?namespace=default
```

> Test

```
$ kubectl get service
NAME                  CLUSTER-IP   EXTERNAL-IP   PORT(S)          AGE
hello-minikube        10.0.0.61    <nodes>       8080:30220/TCP   13m
kubernetes            10.0.0.1     <none>        443/TCP          1d
kubernetes-bootcamp   10.0.0.155   <nodes>       8080:31112/TCP   1d
```

http://192.168.99.100:30220/

```
CLIENT VALUES:
client_address=172.17.0.1
command=GET
real path=/
query=nil
request_version=1.1
request_uri=http://192.168.99.100:8080/
```

http://192.168.99.100:31112/

```
Hello Kubernetes bootcamp! | Running on: kubernetes-bootcamp-3271566451-j2cvs | v=1
```
