package io.fabric8.testing;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.base.OperationContext;
import io.fabric8.kubernetes.client.informers.ResourceEventHandler;
import io.fabric8.kubernetes.client.informers.SharedIndexInformer;
import io.fabric8.kubernetes.client.informers.SharedInformerFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleSharedInformerRun {
    private static final Logger log = Logger.getLogger("SimpleSharedInformerRunLogger");
    private static final int RESYNC_PERIOD = 5 * 60 * 1000;
    public static void main(String[] args) {
        try (KubernetesClient k8s = new DefaultKubernetesClient()) {
            //countPodsAcrossNamespaces(k8s);
            startInformersForPod(k8s);
            //startWatchForPod(k8s);
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            interruptedException.printStackTrace();
        }
    }

    private static void countPodsAcrossNamespaces(KubernetesClient k8s) {
        PodList podList = k8s.pods().list();
        log.info("k8s.pods().list() : " + podList.getItems().size() + " pods found");
        podList = k8s.pods().inAnyNamespace().list();
        log.info("k8s.pods().inAnyNamespace().list() : " + podList.getItems().size() + " pods found");
    }

    private static void startInformersForPod(KubernetesClient k8s) throws InterruptedException {
        SharedInformerFactory informerFactory = k8s.informers();

        SharedIndexInformer<Pod> podInformer = informerFactory.sharedIndexInformerFor(Pod.class,
                PodList.class,
                new OperationContext().withNamespace(null),
                RESYNC_PERIOD);

        podInformer.addEventHandler(new ResourceEventHandler<Pod>() {
            @Override
            public void onAdd(Pod pod) {
                log.info("ADDED: " + pod.getMetadata().getNamespace() + "/" + pod.getMetadata().getName());
            }

            @Override
            public void onUpdate(Pod pod, Pod t1) {
                log.info("ADDED: " + pod.getMetadata().getNamespace() + "/" + pod.getMetadata().getName());
            }

            @Override
            public void onDelete(Pod pod, boolean b) {
                log.info("ADDED: " + pod.getMetadata().getNamespace() + "/" + pod.getMetadata().getName());
            }
        });

        informerFactory.addSharedInformerEventListener(e -> {
            log.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        });
        informerFactory.startAllRegisteredInformers();
        Thread.sleep(30 * 60 * 1000);
        informerFactory.stopAllRegisteredInformers();
    }

    private static void startWatchForPod(KubernetesClient k8s) throws InterruptedException {
        k8s.pods().watch(new Watcher<Pod>() {
            @Override
            public void eventReceived(Action action, Pod pod) {
                log.info(action.name() + " " + pod.getMetadata().getNamespace() + "/" + pod.getMetadata().getName());
            }

            @Override
            public void onClose(KubernetesClientException e) {
                log.info("Closed");
            }
        });
        Thread.sleep(30 * 60 * 1000);
    }
}