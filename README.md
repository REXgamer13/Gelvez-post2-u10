# Productos Service — Análisis de Calidad con SonarQube

## 📋 Actividad U10: PostContenido 2

### Descripción de la Actividad

Esta es la **Unidad 10 - PostContenido 2** (U10_PostContenido_2), una actividad académica diseñada para practicar y demostrar competencias en:

- **Análisis de calidad de código** utilizando SonarQube
- **Configuración y asignación de Quality Gates** personalizados
- **Identificación y corrección** de issues de código
- **Monitoreo de métricas** de calidad (Reliability, Maintainability, Cobertura)
- **Buenas prácticas** en desarrollo Java y Spring Framework

### Propósito del Proyecto

El proyecto **"Productos Service"** es una aplicación Spring Boot de ejemplo que se utiliza como caso de estudio para evaluar:

- ✅ La calidad y confiabilidad del código
- ✅ Los problemas identificables por herramientas de análisis estático
- ✅ La aplicación de patrones de diseño y mejores prácticas
- ✅ La cobertura de código mediante pruebas unitarias
- ✅ La configuración y monitoreo de quality gates en CI/CD

---

## 📊 Estado Inicial del Análisis

| Categoría | Cantidad | Rating |
|-----------|----------|--------|
| Bugs | 0 | ✅ A |
| Vulnerabilidades | 0 | ✅ A |
| Code Smells | 0 | ⚠️ A |
| Reliability Issues | 1 | ⚠️ Medium |
| Maintainability Issues | 2 | ⚠️ Medium |
| Cobertura | 3.1% | 📊 — |
| Duplications | 0.0% | ✅ Clean |

**Líneas de código:** 245  
**Versión:** 0.0.1-SNAPSHOT  
**Quality Gate:** ✅ Passed  
**Última análisis:** 3 minutos atrás

---

## 🔍 Hallazgos Principales Identificados

### Issue 1: Inyección de Campo (Field Injection)
**Archivo:** `ProductoService.java`, línea **L11**  
**Severidad:** ⚠️ Medium  
**Categoría:** Reliability

**Descripción:** 
Se detectó el uso de inyección de campo (field injection) mediante anotación `@Autowired` en un campo privado. Esto no es una práctica recomendada según los estándares de Spring Framework y SonarQube.

**Código Problemático:**
```java
// ❌ Incorrecto - Field Injection
@Autowired
private ProductoRepository repo;
```

**Solución Recomendada:**
```java
// ✅ Correcto - Constructor Injection
private final ProductoRepository repo;

public ProductoService(ProductoRepository repo) {
    this.repo = repo;
}
```

**Beneficios de la corrección:**
- ✅ Mayor testabilidad (fácil inyectar mocks)
- ✅ Mejor inyección de dependencias explícita
- ✅ Mejor seguimiento y documentación de dependencias
- ✅ Menos acoplamiento al framework

---

### Issue 2: Parámetros de Método No Utilizados
**Archivo:** `ProductoService.java`, línea **L14**  
**Severidad:** ⚠️ Medium  
**Categoría:** Maintainability

**Descripción:** 
El método `procesarProducto()` contiene parámetros sin usar: `"cat"`, `"activo"`, `"proveedor"`. Estos parámetros no son utilizados dentro del cuerpo del método, lo que genera confusión.

**Parámetros Problemáticos:**
- `cat` (String)
- `activo` (boolean)  
- `proveedor` (String)

**Código Problemático:**
```java
// ❌ Antes - Con parámetros no utilizados
public Producto procesarProducto(String n, Double p, Integer s, 
                                  String cat, boolean activo, String proveedor) {
    Producto producto = new Producto();
    // Código que SOLO usa: n, p, s
    // Los parámetros cat, activo, proveedor no se utilizan
}
```

**Solución Recomendada:**
```java
// ✅ Después - Parámetros limpios
public Producto procesarProducto(String n, Double p, Integer s) {
    Producto producto = new Producto();
    // Interfaz clara y sin confusión
    // Solo se reciben los parámetros necesarios
}
```

**Beneficios:**
- ✅ Interfaz clara y comprensible
- ✅ Menos confusión para otros desarrolladores
- ✅ Mantenimiento más fácil
- ✅ Reduce la complejidad cognitiva

---

## 📈 Métricas de Cobertura

- **Líneas para cubrir:** 29
- **Líneas cubiertas:** ~8 (3.1%)
- **Líneas duplicadas:** 277
- **Porcentaje de duplicación:** 0.0%

### Análisis:
- La **cobertura es muy baja** (3.1%), se recomienda aumentarla a al menos 80%
- **La duplicación es limpia** (0.0%), indicando código original sin código duplicado

---

## 🎯 Recomendaciones de Mejora

### 1. Aumentar la Cobertura de Pruebas Unitarias
- **Objetivo:** Alcanzar al menos 80% de cobertura
- **Prioridad:** Alta
- **Clases a enfocarse:**
  - `ProductoService`
  - `Producto`
  - Controladores REST

### 2. Refactorizar la Inyección de Dependencias
- **Cambiar de field injection a constructor injection**
- **Beneficios:** Mejor transparencia, testabilidad y documentación
- **Tiempo estimado:** 15-30 minutos

### 3. Limpiar la Interfaz de Métodos
- **Remover parámetros no utilizados**
- **Mantener métodos enfocados y simples**
- **Aplicar principio SOLID (Single Responsibility)**

### 4. Implementar Pruebas de Integración
- **Validar interacción con el repositorio**
- **Asegurar comportamiento esperado en escenarios reales**
- **Probar casos extremos y manejo de excepciones**

---

## 📸 Galería de Imágenes del Análisis SonarQube

### 1️⃣ Dashboard Principal
![Dashboard SonarQube](docs/Dashboard.png)
*Vista general del proyecto mostrando Quality Gate, bugs, vulnerabilidades y cobertura en el Dashboard de SonarQube*

---

### 2️⃣ Estado del Quality Gate en el Dashboard
![Estado Quality Gate](docs/Estado%20del%20Quality%20Gate%20en%20Dashboard.png)
*Detalle del estado del Quality Gate personalizado en el Dashboard, indicando si el proyecto pasó o falló los criterios establecidos*

---

### 3️⃣ Crear un Quality Gate Personalizado
![Crear Quality Gate](docs/crear%20un%20Quality%20Gate%20%20personalizado.png)
*Pasos para crear un Quality Gate personalizado en SonarQube con criterios específicos de calidad*

---

### 4️⃣ Asignar el Quality Gate al Proyecto
![Asignar Quality Gate](docs/Asignar%20el%20Quality%20Gate%20al%20proyecto.png)
*Proceso de asignación del Quality Gate creado al proyecto Productos Service*

---

### 5️⃣ Problemas de Confiabilidad (Reliability Issues)
![Reliability Issues](docs/Reability.png)
*Listado de issues de confiabilidad (Reliability) identificados en el código, incluyendo field injection y otros problemas*

---

### 6️⃣ Detalles de Confiabilidad - Issue Reporte 1
![Issues Reability 1](docs/IssuesR1.png)
*Primer reporte detallado de issues de confiabilidad con descripción, ubicación y recomendaciones*

---

### 7️⃣ Problemas de Mantenibilidad (Maintainability Issues)
![Maintainability Issues](docs/Maintainability.png)
*Listado de issues de mantenibilidad (Maintainability) identificados, como parámetros no utilizados y code smells*

---

### 8️⃣ Detalles de Mantenibilidad - Issue Reporte 1
![Issues Maintainability 1](docs/IssuesM1.png)
*Primer reporte detallado de issues de mantenibilidad con análisis de complejidad y métodos problemáticos*

---

### 9️⃣ Detalles de Mantenibilidad - Issue Reporte 2
![Issues Maintainability 2](docs/IsuuesM2.png)
*Segundo reporte detallado de issues de mantenibilidad, continuando con el análisis de problemas identificados*

---

### 🔟 Re-ejecutar el Análisis
![Re-ejecutar Análisis](docs/Re-ejecutar%20el%20An%C3%A1lisis.png)
*Interfaz de SonarQube mostrando cómo re-ejecutar el análisis de calidad después de aplicar correcciones al código*

---

## 🔧 Configuración de SonarQube

```properties
# Endpoint de SonarQube
sonar.host.url=http://localhost:9000

# Identificación del Proyecto
sonar.projectKey=com.universidad:productos-service
sonar.projectName=Productos Service
sonar.projectVersion=0.0.1-SNAPSHOT

# Configuración de Fuentes
sonar.sources=src/main/java
sonar.tests=src/test/java

# Cobertura de Código
sonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml

# Quality Gate
sonar.qualitygate=Universidad-Quality-Gate
```

---

## 📝 Proceso de Análisis en SonarQube

### Paso 1: Configurar el Proyecto
1. Crear nuevo proyecto en SonarQube
2. Asignar la clave del proyecto: `com.universidad:productos-service`
3. Configurar los parámetros de análisis

### Paso 2: Crear Quality Gate Personalizado
1. Ir a **Administration** → **Quality Gates**
2. Crear nuevo Quality Gate con criterios personalizados
3. Definir umbrales para bugs, vulnerabilidades, code smells, cobertura

### Paso 3: Asignar Quality Gate
1. Seleccionar el proyecto
2. Ir a **Project Settings** → **Quality Gate**
3. Asignar el Quality Gate personalizado

### Paso 4: Ejecutar Análisis
```bash
mvn clean verify sonar:sonar
```

### Paso 5: Revisar Resultados
1. Acceder al Dashboard del proyecto
2. Analizar los issues identificados
3. Corregir problemas según recomendaciones

### Paso 6: Re-ejecutar Análisis
1. Aplicar correcciones al código
2. Ejecutar nuevamente el análisis
3. Verificar mejora en las métricas

---

## ✅ Próximos Pasos y Plan de Acción

- [ ] **Semana 1:** Revisar y corregir los issues de Reliability (constructor injection)
- [ ] **Semana 1:** Revisar y corregir los issues de Maintainability (parámetros no utilizados)
- [ ] **Semana 2:** Aumentar la cobertura de pruebas unitarias a 50%
- [ ] **Semana 2:** Implementar pruebas de integración
- [ ] **Semana 3:** Alcanzar 80% de cobertura de código
- [ ] **Semana 3:** Re-ejecutar análisis de SonarQube
- [ ] **Semana 4:** Monitorear métricas continuamente en el tiempo

---

## 📚 Referencias y Recursos

### Herramientas Utilizadas
- **SonarQube:** https://www.sonarqube.org/
- **Maven:** https://maven.apache.org/
- **Spring Framework:** https://spring.io/projects/spring-framework
- **JaCoCo:** https://www.jacoco.org/jacoco/ (Coverage Reports)

### Documentación Oficial
- **SonarQube Rules:** https://rules.sonarsource.com/
- **Spring Boot Documentation:** https://spring.io/projects/spring-boot
- **Maven SonarQube Scanner:** https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-maven/

### Mejores Prácticas
- **Clean Code Principles:** Robert C. Martin
- **Spring Best Practices:** https://spring.io/guides
- **Code Quality Standards:** https://www.sonarqube.org/features/quality-gate/

---

## 👥 Información del Proyecto

**Proyecto:** Productos Service  
**Versión:** 0.0.1-SNAPSHOT  
**Tipo:** Spring Boot Application  
**Equipo:** Desarrollo - Universidad  
**Última Actualización:** 2026-05-16  
**Estado:** En Desarrollo 🚀

---

## 📄 Licencia y Notas

Este proyecto es parte de una actividad académica para demostrar competencias en:
- ✅ Uso de herramientas de análisis de código
- ✅ Implementación de quality gates
- ✅ Mejora continua de calidad de código
- ✅ Aplicación de patrones de diseño
- ✅ Buenas prácticas en desarrollo Java

---

**Generado:** 2026-05-16  
**Documento:** README.md  
**Versión:** 2.0


