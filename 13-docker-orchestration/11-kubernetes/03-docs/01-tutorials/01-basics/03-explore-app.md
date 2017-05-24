# Explore Your App

> Check application configuration

```
kubectl get pods
```

*view containers inside Pod*

```
kubectl describe pods
```

> Show app in terminal

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

> View container logs

```
kubectl logs $POD_NAME
```

> Executing command on the container

*list the environment variables*

```
kubectl exec $POD_NAME env
```

*start bash in Pod's container*

```
$ kubectl exec -ti $POD_NAME bash
root@kubernetes-bootcamp-3271566451-j2cvs:/# ls
bin   core  etc   lib	 media	opt   root  sbin       srv  tmp  var
boot  dev   home  lib64  mnt	proc  run   server.js  sys  usr
root@kubernetes-bootcamp-3271566451-j2cvs:/# cat server.js 
var http = require('http');
var requests=0;
var podname= process.env.HOSTNAME;
var startTime;
var host;
var handleRequest = function(request, response) {
  response.setHeader('Content-Type', 'text/plain');
  response.writeHead(200);
  response.write("Hello Kubernetes bootcamp! | Running on: ");
  response.write(host);
  response.end(" | v=1\n");
  console.log("Running On:" ,host, "| Total Requests:", ++requests,"| App Uptime:", (new Date() - startTime)/1000 , "seconds", "| Log Time:",new Date());
}
var www = http.createServer(handleRequest);
www.listen(8080,function () {
    startTime = new Date();;
    host = process.env.HOSTNAME;
    console.log ("Kubernetes Bootcamp App Started At:",startTime, "| Running On: " ,host, "\n" );
});
root@kubernetes-bootcamp-3271566451-j2cvs:/# curl localhost:8080
Hello Kubernetes bootcamp! | Running on: kubernetes-bootcamp-3271566451-j2cvs | v=1
root@kubernetes-bootcamp-3271566451-j2cvs:/# exit
exit
```

