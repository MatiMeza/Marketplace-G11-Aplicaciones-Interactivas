# Marketplace - Aplicaciones Interactivas

## 🚀 Cómo ejecutar el proyecto

1. Clonar el repositorio o descargar el ZIP
2. Abrir el proyecto en IntelliJ
3. Configurar la base de datos en MySQL (ver sección abajo)
4. Ejecutar la clase `DemoApplication`
5. La API se levanta en:

```
http://localhost:4002
```

---

## 🗄️ Configuración de base de datos

Crear la base de datos en MySQL:

```sql
CREATE DATABASE marketplace;
```

Configurar el archivo `application.properties`:

```properties
spring.application.name=demo
server.port=4002

spring.datasource.url=jdbc:mysql://localhost:3306/marketplace
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🔐 Autenticación con JWT

### Endpoint de login

```
POST /auth/login
```

### Body (ejemplo ADMIN)

```json
{
  "email": "admin@gmail.com",
  "password": "1234"
}
```

### Body (ejemplo USER)

```json
{
  "email": "user@gmail.com",
  "password": "1234"
}
```

### Respuesta

```json
{
  "token": "TOKEN_JWT"
}
```

---

## 🔑 Cómo usar el token

En Postman:

1. Ir a la pestaña **Authorization**
2. Seleccionar **Bearer Token**
3. Pegar el token obtenido en el login
4. Enviar la request

---

## 📌 Endpoints principales

### 🔹 Materiales

```
GET /materiales
POST /materiales
PUT /materiales/{id}
DELETE /materiales/{id}
```

---

### 🔹 Productos (solo ADMIN)

```
GET /productos
POST /productos
PUT /productos/{id}
DELETE /productos/{id}
```

---

### 🔹 Categorías

```
GET /categories
POST /categories
PUT /categories/{id}
DELETE /categories/{id}
```

---

### 🔹 Imágenes

```
GET /imagenes/producto/{productoId}
POST /imagenes
DELETE /imagenes/{id}
```

---

## 🔒 Seguridad

* `/auth/**` → público (no requiere token)
* `/materiales/**` → requiere autenticación
* `/categories/**` → requiere autenticación
* `/imagenes/**` → requiere autenticación
* `/productos/**` → solo rol ADMIN

---

## 🧪 Pruebas realizadas

Se utilizaron requests en Postman para verificar:

* Login con ADMIN y USER
* Acceso a endpoints protegidos con token
* Acceso sin token (403 Forbidden)
* Restricción por roles (USER no puede acceder a productos)

---

## 📁 Colección Postman

La colección se encuentra en:

```
postman/Marketplace-G11.postman_collection.json
```

---

## 📸 Evidencias

Las capturas de pruebas y base de datos se encuentran en:

```
evidencias/
```

---

## ✅ Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Security
* JWT
* MySQL
* JPA / Hibernate

---
