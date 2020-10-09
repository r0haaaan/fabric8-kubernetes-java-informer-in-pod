package io.fabric8.testing;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import io.fabric8.kubernetes.client.dsl.base.OperationContext;
import io.fabric8.kubernetes.client.informers.ResourceEventHandler;
import io.fabric8.kubernetes.client.informers.SharedIndexInformer;
import io.fabric8.kubernetes.client.informers.SharedInformerFactory;
import io.fabric8.testing.crd.PodSet;
import io.fabric8.testing.crd.PodSetList;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleSharedInformerRun {
    private static final Logger log = Logger.getLogger("SimpleSharedInformerRunLogger");
    private static final int RESYNC_PERIOD = 5 * 60 * 1000;
    public static void main(String[] args) {
        try (KubernetesClient k8s = new DefaultKubernetesClient()) {
            log.info("k8s.getConfiguration().getNamespace(): " + k8s.getConfiguration().getNamespace());
            startInformersForPod(k8s);
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            interruptedException.printStackTrace();
        }
    }

    private static void startInformersForPod(KubernetesClient k8s) throws InterruptedException {
        SharedInformerFactory informerFactory = k8s.informers();

        SharedIndexInformer<Pod> podInformer = informerFactory.sharedIndexInformerFor(Pod.class,
                PodList.class,
                new OperationContext().withNamespace("default"),
                RESYNC_PERIOD);

        SharedIndexInformer<PodSet> podSetInformer = informerFactory.sharedIndexInformerForCustomResource(
                new CustomResourceDefinitionContext.Builder()
                        .withKind("PodSet")
                        .withGroup("demo.k8s.io")
                        .withVersion("v1alpha1")
                        .withScope("Namespaced")
                        .build(),
                PodSet.class,
                PodSetList.class,
                new OperationContext().withNamespace("default"),
                RESYNC_PERIOD);

        podInformer.addEventHandler(new ResourceEventHandler<Pod>() {
            @Override
            public void onAdd(Pod pod) {
                log.info("ADDED: " + pod.getMetadata().getNamespace() + "/" + pod.getMetadata().getName());
            }

            @Override
            public void onUpdate(Pod pod, Pod t1) {
                log.info("UPDATED: " + pod.getMetadata().getNamespace() + "/" + pod.getMetadata().getName());
            }

            @Override
            public void onDelete(Pod pod, boolean b) {
                log.info("DELETED: " + pod.getMetadata().getNamespace() + "/" + pod.getMetadata().getName());
            }
        });

        podSetInformer.addEventHandler(new ResourceEventHandler<PodSet>() {
            @Override
            public void onAdd(PodSet podSet) {
                log.info("ADDED: " + podSet.getMetadata().getNamespace() + "/" + podSet.getMetadata().getName());
            }

            @Override
            public void onUpdate(PodSet podSet, PodSet t1) {
                log.info("UPDATED: " + podSet.getMetadata().getNamespace() + "/" + podSet.getMetadata().getName());
            }

            @Override
            public void onDelete(PodSet podSet, boolean b) {
                log.info("DELETED: " + podSet.getMetadata().getNamespace() + "/" + podSet.getMetadata().getName());
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
}
