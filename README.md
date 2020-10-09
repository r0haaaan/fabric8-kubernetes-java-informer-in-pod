# Running SharedIndexInformers Inside a Pod in Java

In this example project, I will be running SharedIndexInformers from Fabric8 Kubernetes Java Client.

## How to Build
You can compile this project like this:
```
mvn clean install
```

## How to deploy this project to Kubernetes
This project uses [Kubernetes Maven Plugin](https://www.eclipse.org/jkube/docs/kubernetes-maven-plugin) from [Eclipse JKube](https://github.com/eclipse/jkube). You would need access to a Kubernetes Cluster. In my case I was using minikube. 

To point your shell to minikube's docker-daemon, run:```
```
eval $(minikube -p minikube docker-env)
```
You can deploy your application with this command:
```
mvn k8s:build k8s:resource k8s:deploy k8s:log
```
Last goal of plugin would log your application's Pod output in your maven logs. 
```
fabric8-kubernetes-java-informers-in-pod : $ eval $(minikube -p minikube docker-env)
fabric8-kubernetes-java-informers-in-pod : $ mvn k8s:build k8s:resource k8s:deploy k8s:log
[INFO] Scanning for projects...
[INFO] 
[INFO] --------< org.example:fabric8-kubernetes-java-informers-in-pod >--------
[INFO] Building fabric8-kubernetes-java-informers-in-pod 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- kubernetes-maven-plugin:1.0.1:build (default-cli) @ fabric8-kubernetes-java-informers-in-pod ---
[INFO] k8s: Running in Kubernetes mode
[INFO] k8s: Building Docker image in Kubernetes mode
[INFO] k8s: Running generator java-exec
[INFO] k8s: java-exec: Using Docker image quay.io/jkube/jkube-java-binary-s2i:0.0.8 as base / builder
[INFO] k8s: [example/fabric8-kubernetes-java-informers-in-pod:latest] "java-exec": Created docker-build.tar in 91 milliseconds
[INFO] k8s: [example/fabric8-kubernetes-java-informers-in-pod:latest] "java-exec": Built image sha256:bd841
[INFO] k8s: [example/fabric8-kubernetes-java-informers-in-pod:latest] "java-exec": Removed old image sha256:aa496
[INFO] k8s: [example/fabric8-kubernetes-java-informers-in-pod:latest] "java-exec": Tag with latest
[INFO] 
[INFO] --- kubernetes-maven-plugin:1.0.1:resource (default-cli) @ fabric8-kubernetes-java-informers-in-pod ---
[INFO] k8s: Running generator java-exec
[INFO] k8s: java-exec: Using Docker image quay.io/jkube/jkube-java-binary-s2i:0.0.8 as base / builder
[INFO] k8s: Using resource templates from /home/rohaan/work/repos/fabric8-kubernetes-java-informers-in-pod/src/main/jkube
[INFO] k8s: jkube-controller: Adding a default Deployment
[INFO] k8s: jkube-service: Adding a default service 'fabric8-kubernetes-java-informers-in-pod' with ports [8080]
[INFO] k8s: jkube-revision-history: Adding revision history limit to 2
[INFO] 
[INFO] >>> kubernetes-maven-plugin:1.0.1:deploy (default-cli) > install @ fabric8-kubernetes-java-informers-in-pod >>>
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ fabric8-kubernetes-java-informers-in-pod ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ fabric8-kubernetes-java-informers-in-pod ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ fabric8-kubernetes-java-informers-in-pod ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /home/rohaan/work/repos/fabric8-kubernetes-java-informers-in-pod/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ fabric8-kubernetes-java-informers-in-pod ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ fabric8-kubernetes-java-informers-in-pod ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ fabric8-kubernetes-java-informers-in-pod ---
[INFO] Building jar: /home/rohaan/work/repos/fabric8-kubernetes-java-informers-in-pod/target/fabric8-kubernetes-java-informers-in-pod-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-assembly-plugin:3.3.0:single (make-assembly) @ fabric8-kubernetes-java-informers-in-pod ---
[INFO] Building jar: /home/rohaan/work/repos/fabric8-kubernetes-java-informers-in-pod/target/fabric8-kubernetes-java-informers-in-pod-1.0-SNAPSHOT-jar-with-dependencies.jar
[INFO] 
[INFO] --- maven-install-plugin:2.4:install (default-install) @ fabric8-kubernetes-java-informers-in-pod ---
[INFO] Installing /home/rohaan/work/repos/fabric8-kubernetes-java-informers-in-pod/target/fabric8-kubernetes-java-informers-in-pod-1.0-SNAPSHOT.jar to /home/rohaan/.m2/repository/org/example/fabric8-kubernetes-java-informers-in-pod/1.0-SNAPSHOT/fabric8-kubernetes-java-informers-in-pod-1.0-SNAPSHOT.jar
[INFO] Installing /home/rohaan/work/repos/fabric8-kubernetes-java-informers-in-pod/pom.xml to /home/rohaan/.m2/repository/org/example/fabric8-kubernetes-java-informers-in-pod/1.0-SNAPSHOT/fabric8-kubernetes-java-informers-in-pod-1.0-SNAPSHOT.pom
[INFO] Installing /home/rohaan/work/repos/fabric8-kubernetes-java-informers-in-pod/target/classes/META-INF/jkube/kubernetes.yml to /home/rohaan/.m2/repository/org/example/fabric8-kubernetes-java-informers-in-pod/1.0-SNAPSHOT/fabric8-kubernetes-java-informers-in-pod-1.0-SNAPSHOT-kubernetes.yml
[INFO] Installing /home/rohaan/work/repos/fabric8-kubernetes-java-informers-in-pod/target/fabric8-kubernetes-java-informers-in-pod-1.0-SNAPSHOT-jar-with-dependencies.jar to /home/rohaan/.m2/repository/org/example/fabric8-kubernetes-java-informers-in-pod/1.0-SNAPSHOT/fabric8-kubernetes-java-informers-in-pod-1.0-SNAPSHOT-jar-with-dependencies.jar
[INFO] 
[INFO] <<< kubernetes-maven-plugin:1.0.1:deploy (default-cli) < install @ fabric8-kubernetes-java-informers-in-pod <<<
[INFO] 
[INFO] 
[INFO] --- kubernetes-maven-plugin:1.0.1:deploy (default-cli) @ fabric8-kubernetes-java-informers-in-pod ---
[INFO] k8s: Using Kubernetes at https://192.168.39.140:8443/ in namespace default with manifest /home/rohaan/work/repos/fabric8-kubernetes-java-informers-in-pod/target/classes/META-INF/jkube/kubernetes.yml 
[INFO] k8s: Using namespace: default
[INFO] k8s: Creating a Service from kubernetes.yml namespace default name fabric8-kubernetes-java-informers-in-pod
[INFO] k8s: Created Service: target/jkube/applyJson/default/service-fabric8-kubernetes-java-informers-in-pod.json
[INFO] k8s: Creating a Deployment from kubernetes.yml namespace default name fabric8-kubernetes-java-informers-in-pod
[INFO] k8s: Created Deployment: target/jkube/applyJson/default/deployment-fabric8-kubernetes-java-informers-in-pod.json
[INFO] k8s: HINT: Use the command `kubectl get pods -w` to watch your pods start up
[INFO] 
[INFO] --- kubernetes-maven-plugin:1.0.1:log (default-cli) @ fabric8-kubernetes-java-informers-in-pod ---
[INFO] k8s: Using Kubernetes at https://192.168.39.140:8443/ in namespace default with manifest /home/rohaan/work/repos/fabric8-kubernetes-java-informers-in-pod/target/classes/META-INF/jkube/kubernetes.yml 
[INFO] k8s: Using namespace: default
[INFO] k8s: Watching pods with selector LabelSelector(matchExpressions=[], matchLabels={app=fabric8-kubernetes-java-informers-in-pod, provider=jkube, group=org.example}, additionalProperties={}) waiting for a running pod...
[INFO] k8s:  [NEW] fabric8-kubernetes-java-informers-in-pod-7c9db47b48-np5tx status: Running Ready
[INFO] k8s:  [NEW] Tailing log of pod: fabric8-kubernetes-java-informers-in-pod-7c9db47b48-np5tx
[INFO] k8s:  [NEW] Press Ctrl-C to stop tailing the log
[INFO] k8s:  [NEW] 
[INFO] k8s: Starting the Java application using /opt/jboss/container/java/run/run-java.sh ...
[INFO] k8s: INFO exec  java -javaagent:/usr/share/java/jolokia-jvm-agent/jolokia-jvm.jar=config=/opt/jboss/container/jolokia/etc/jolokia.properties -javaagent:/usr/share/java/prometheus-jmx-exporter/jmx_prometheus_javaagent.jar=9779:/opt/jboss/container/prometheus/etc/jmx-exporter-config.yaml -XX:+UseParallelOldGC -XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=20 -XX:GCTimeRatio=4 -XX:AdaptiveSizePolicyWeight=90 -XX:MaxMetaspaceSize=100m -XX:+ExitOnOutOfMemoryError -cp "." -jar /deployments/fabric8-kubernetes-java-informers-in-pod-1.0-SNAPSHOT-jar-with-dependencies.jar  
[INFO] k8s: SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
[INFO] k8s: SLF4J: Defaulting to no-operation (NOP) logger implementation
[INFO] k8s: SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[INFO] k8s: WARNING: An illegal reflective access operation has occurred
[INFO] k8s: WARNING: Illegal reflective access by org.jolokia.util.ClassUtil (file:/usr/share/java/jolokia-jvm-agent/jolokia-jvm.jar) to constructor sun.security.x509.X500Name(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String)
[INFO] k8s: WARNING: Please consider reporting this to the maintainers of org.jolokia.util.ClassUtil
[INFO] k8s: WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
[INFO] k8s: WARNING: All illegal access operations will be denied in a future release
[INFO] k8s: Oct 09, 2020 6:07:31 PM io.fabric8.testing.SimpleSharedInformerRun main
[INFO] k8s: INFO: k8s.getConfiguration().getNamespace(): default
[INFO] k8s: I> No access restrictor found, access to any MBean is allowed
[INFO] k8s: Jolokia: Agent started with URL https://172.17.0.2:8778/jolokia/
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$2 onAdd
[INFO] k8s: INFO: ADDED: default/dummy-podset
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$2 onUpdate
[INFO] k8s: INFO: UPDATED: default/dummy-podset
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$2 onAdd
[INFO] k8s: INFO: ADDED: default/example-podset
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$2 onUpdate
[INFO] k8s: INFO: UPDATED: default/example-podset
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$2 onAdd
[INFO] k8s: INFO: ADDED: rokumar/example-podset
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$2 onUpdate
[INFO] k8s: INFO: UPDATED: rokumar/example-podset
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: default/fabric8-kubernetes-java-informers-in-pod-7c9db47b48-np5tx
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: kube-system/coredns-66bff467f8-gk955
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: kube-system/etcd-minikube
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: kube-system/kube-apiserver-minikube
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: kube-system/kube-controller-manager-minikube
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: kube-system/kube-proxy-r4hc4
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: kube-system/kube-scheduler-minikube
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: kube-system/storage-provisioner
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: tekton-pipelines/tekton-pipelines-controller-664b4695d7-j2drd
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: tekton-pipelines/tekton-pipelines-webhook-859b4d6b4f-c4k4n
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: tekton-pipelines/tekton-triggers-controller-7f6c5477fd-764vl
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onAdd
[INFO] k8s: INFO: ADDED: tekton-pipelines/tekton-triggers-webhook-d65454dff-dc7pm
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s: INFO: UPDATED: default/fabric8-kubernetes-java-informers-in-pod-7c9db47b48-np5tx
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s: INFO: UPDATED: kube-system/coredns-66bff467f8-gk955
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s: INFO: UPDATED: kube-system/etcd-minikube
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s: INFO: UPDATED: kube-system/kube-apiserver-minikube
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s: INFO: UPDATED: kube-system/kube-controller-manager-minikube
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s: INFO: UPDATED: kube-system/kube-proxy-r4hc4
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s: INFO: UPDATED: kube-system/kube-scheduler-minikube
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s: INFO: UPDATED: kube-system/storage-provisioner
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s:  [NEW] fabric8-kubernetes-java-informers-in-pod-7c9db47b48-np5tx status: Running Ready
[INFO] k8s: INFO: UPDATED: tekton-pipelines/tekton-pipelines-controller-664b4695d7-j2drd
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s: INFO: UPDATED: tekton-pipelines/tekton-pipelines-webhook-859b4d6b4f-c4k4n
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s: INFO: UPDATED: tekton-pipelines/tekton-triggers-controller-7f6c5477fd-764vl
[INFO] k8s: Oct 09, 2020 6:07:32 PM io.fabric8.testing.SimpleSharedInformerRun$1 onUpdate
[INFO] k8s: INFO: UPDATED: tekton-pipelines/tekton-triggers-webhook-d65454dff-dc7pm
```