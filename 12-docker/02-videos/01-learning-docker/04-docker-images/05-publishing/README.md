# Publishing

> `docker build .`

```
...
Step 6 : ENTRYPOINT python hello.py
 ---> Running in 12aeb1afbe44
 ---> a03cc877d1a6 <- image id
...
```

> `docker images`

```
REPOSITORY          TAG                 IMAGE ID            CREATED                  SIZE
<none>              <none>              a03cc877d1a6        Less than a second ago   59.51 MB
alpine              latest              13e1761bf172        2 weeks ago              4.797 MB
```

> `docker tag a03c pwillhan/hello-dockerhub:1.0`

> `docker tag a03c pwillhan/hello-dockerhub:latest`

> `docker images`

```
REPOSITORY                 TAG                 IMAGE ID            CREATED             SIZE
rickfast/hello-dockerhub   1.0                 fa5bffc9316b        9 minutes ago       59.51 MB
rickfast/hello-dockerhub   latest              fa5bffc9316b        9 minutes ago       59.51 MB
alpine                     latest              13e1761bf172        2 weeks ago         4.797 MB
```

> `docker login`

> `docker push pwillhan/hello-dockerhub`

```
The push refers to a repository [docker.io/pwillhan/hello-dockerhub]
6392cf8779a7: Pushed 
f787b5ebbd30: Pushed 
a226ffeedc57: Pushed 
8f01a53880b9: Mounted from library/alpine 
1.0: digest: sha256:eb823a797d2319d647abfdfe599ed86c3bedfb0df589fdb69747673a4c0718ad size: 4123
6392cf8779a7: Layer already exists 
f787b5ebbd30: Layer already exists 
a226ffeedc57: Layer already exists 
8f01a53880b9: Layer already exists 
latest: digest: sha256:87b1f86e19a684bfedc99fd41e157b3dbb71ef44f8dcf859499c49958921eaea size: 4126
```

> go to https://hub.docker.com/r/pwillhan/hello-dockerhub/ and https://hub.docker.com/r/pwillhan/hello-dockerhub/tags/
