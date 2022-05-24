SOURCE_IMAGE = os.getenv("SOURCE_IMAGE", default='harbor.pksbeachhouse.com/hello-tanzu-supply-chain-source')
LOCAL_PATH = os.getenv("LOCAL_PATH", default='.')
NAMESPACE = os.getenv("NAMESPACE", default='psheldon')

k8s_custom_deploy(
    'hello-tanzu-supply-chain',
    apply_cmd="tanzu apps workload apply -f ./tap/workload.yaml --live-update" +
               " --local-path " + LOCAL_PATH +
               " --source-image " + SOURCE_IMAGE +
               " --namespace " + NAMESPACE +
               " --yes >/dev/null" +
               " && kubectl get workload hello-tanzu-supply-chain --namespace " + NAMESPACE + " -o yaml",
    delete_cmd="tanzu apps workload delete -f ./tap/workload.yaml --namespace " + NAMESPACE + " --yes",
    deps=['pom.xml', './target/classes'],
    container_selector='workload',
    live_update=[
      sync('./target/classes', '/workspace/BOOT-INF/classes')
    ]
)

k8s_resource("hello-tanzu-supply-chain", port_forwards=["8080:8080"],
            extra_pod_selectors=[{'serving.knative.dev/service': "hello-tanzu-supply-chain"}])

allow_k8s_contexts('workload-iterate-admin@workload-iterate')