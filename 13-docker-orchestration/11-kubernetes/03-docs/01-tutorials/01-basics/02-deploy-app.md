# Deploy an App

> Deploy app

```
kubectl run kubernetes-bootcamp --image=docker.io/jocatalin/kubernetes-bootcamp:v1 --port=8080
```

```
kubectl get deployments
```

> View app

*terminal 1*

```
kubectl proxy
Starting to serve on 127.0.0.1:8001
```

*terminal 2*

```
export POD_NAME=$(kubectl get pods -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}')
echo Name of the Pod: $POD_NAME

curl http://localhost:8001/api/v1/proxy/namespaces/default/pods/$POD_NAME/
```
