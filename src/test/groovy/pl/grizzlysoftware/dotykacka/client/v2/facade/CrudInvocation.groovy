package pl.grizzlysoftware.dotykacka.client.v2.facade

import pl.grizzlysoftware.dotykacka.client.v2.model.CloudEntity

import java.util.function.Function

class CrudInvocation<T extends CloudEntity> {
    String entityName
    String methodType
    Function<T, T> setupInvocation
    Function<T, T> methodInvocation
    Function<T, T> cleanupInvocation

    CrudInvocation(String entityName,
                   String methodType,
                   Function<T, T> setupInvocation,
                   Function<T, T> methodInvocation,
                   Function<T, T> cleanupInvocation) {
        this.entityName = entityName
        this.methodType = methodType
        this.setupInvocation = setupInvocation
        this.methodInvocation = methodInvocation
        this.cleanupInvocation = cleanupInvocation
    }
}
