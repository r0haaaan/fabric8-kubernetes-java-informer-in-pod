package io.fabric8.testing;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
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
            SharedInformerFactory informerFactory = k8s.informers();

            SharedIndexInformer<Pod> podInformer = informerFactory.sharedIndexInformerFor(Pod.class,
                    PodList.class,
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
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            interruptedException.printStackTrace();
        }
    }
}