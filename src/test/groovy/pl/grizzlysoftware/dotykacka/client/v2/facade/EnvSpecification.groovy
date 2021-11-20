package pl.grizzlysoftware.dotykacka.client.v2.facade

import spock.lang.Specification

class EnvSpecification extends Specification {
    Map<String, String> env() {
        return System.getenv();
    }

    Double floatVar(String name, Double defaultValue) {
        return env().getOrDefault(name, defaultValue + "") as Float
    }

    Double doubleVar(String name, Double defaultValue) {
        return env().getOrDefault(name, defaultValue + "") as Double
    }

    Long longVar(String name, Long defaultValue) {
        return env().getOrDefault(name, defaultValue + "") as Long
    }

    Integer intVar(String name, Long defaultValue) {
        return env().getOrDefault(name, defaultValue + "") as Integer
    }

    Byte byteVar(String name, Long defaultValue) {
        return env().getOrDefault(name, defaultValue + "") as Byte
    }

    Short shortVar(String name, Long defaultValue) {
        return env().getOrDefault(name, defaultValue + "") as Short
    }

    String strVar(String name, String defaultValue) {
        return env().getOrDefault(name, defaultValue + "")
    }

    String boolVar(String name, Boolean defaultValue) {
        return env().getOrDefault(name, defaultValue + "") as Boolean
    }

}
