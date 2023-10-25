# Módulos
* **MODULE-OPAPCDT:** Microservicio de apertura de CDT Desmaterializado.
* **MODULE-OPLAAP:** 
* **MODULE-OPLBVBM:** 
* **MODULE-OPLCONSULTA:** 
* **MODULE-OPLGDORACLE:**
* **MODULE-OPLMCRM:** 
* **MODULE-OPLEMAIL:** 
* **MODULE-OPLSHARE:** Se encarga de hacer de librería compartida entre los demás módulos. Este módulo no se despliega.
* **MODULE-OPLSSQLS:** 
* **MODULE-OPLTASASFIJA:** 
* **MODULE-TRADUCTOR:**

# Compilación
## Requeremientos
* Maven
* Java 1.8
* JDK 8

## Instrucciones
1. Desde la raíz del proyecto principal "OPALOBDB", en consola ejecutamos el siguiente comando:
```
$ mvn clean install -DskipTests
```

Este comando compilará todos los módulos y generará un archivo war de cada uno.

**NOTA:** Si se tiene problemas para ejecutar el comando `mvn clean install`, se puede ingresar a la ruta de cada
módulo y ejecutar el comando `mvn compile war:war`.
