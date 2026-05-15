# Project CI - API de Gestión de Tareas

Proyecto desarrollado en Java con Spring Boot para demostrar un entorno de Integración Continua (CI) utilizando GitHub Actions y notificaciones automáticas en Slack.

## Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Maven
- Spring Data JPA
- MariaDB
- H2 Database (tests)
- JUnit 5
- GitHub Actions
- Slack Webhooks

---

## Objetivo del proyecto

Implementar un entorno básico de Integración Continua que permita:

- Versionado del código fuente
- Build automático
- Ejecución de pruebas automatizadas
- Generación del artefacto `.jar`
- Notificaciones automáticas en Slack

---

## Pipeline CI/CD

El pipeline se ejecuta automáticamente al realizar un `git push` sobre la rama `main`.

### Etapas del pipeline

1. Checkout del código
2. Configuración de Java 17
3. Compilación del proyecto
4. Ejecución de tests automáticos
5. Empaquetado de la aplicación
6. Notificación del resultado en Slack

---

## Ejecución local

### Clonar repositorio

```bash
git clone https://github.com/TU_USUARIO/TU_REPO.git
```

### Ejecutar aplicación

```bash
./mvnw spring-boot:run
```

### Ejecutar tests

```bash
./mvnw test
```

---

## Base de datos para testing

Para los tests automáticos se utiliza H2 en memoria, evitando dependencias externas durante la ejecución del pipeline CI.

Archivo utilizado:

```text
src/test/resources/application.properties
```

---

## Integración con Slack

El proyecto envía notificaciones automáticas a Slack utilizando Incoming Webhooks configurados mediante GitHub Secrets.

Secret utilizado:

```text
SLACK_WEBHOOK_URL
```

---

## Estructura general

```text
Developer
   ↓ git push
GitHub Repository
   ↓
GitHub Actions
   ↓
Build + Tests
   ↓
Artifact (.jar)
   ↓
Slack Notification
```

---

## Autor

Proyecto realizado por Christian Gomez para la materia de Ingenieria y Calidad del Software - UTN FRRe.
