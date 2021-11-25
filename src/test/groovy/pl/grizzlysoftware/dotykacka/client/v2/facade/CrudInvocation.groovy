package pl.grizzlysoftware.dotykacka.client.v2.facade


import java.util.function.Function

class CrudInvocation {
    String entityName
    String methodType
    Function<?, ?> setupInvocation
    Function<?, ?> methodInvocation
    Function<?, ?> cleanupInvocation

    CrudInvocation(String entityName,
                   String methodType,
                   Function<?, ?> setupInvocation,
                   Function<?, ?> methodInvocation,
                   Function<?, ?> cleanupInvocation) {
        this.entityName = entityName
        this.methodType = methodType
        this.setupInvocation = setupInvocation
        this.methodInvocation = methodInvocation
        this.cleanupInvocation = cleanupInvocation
    }
}
