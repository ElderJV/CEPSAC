# CEPSAC - API Backend

API para gestión de usuarios, cursos, matrículas y pagos.

## Índice
- [Convenciones](#convenciones)
- [Autenticación](#autenticación)
- [Endpoints](#endpoints)
  - [Usuarios](#1-usuarios)
  - [Países](#2-países)
  - [Tipos de Identificación](#3-tipos-de-identificación)
  - [Categorías](#4-categorías)
  - [Cursos / Diplomados](#5-cursos--diplomados)
  - [Programaciones de Curso](#6-programaciones-de-curso)
  - [Matrículas](#7-matrículas)
  - [Pagos](#8-pagos)
  - [Métodos de Pago](#9-métodos-de-pago)
  - [Descuentos](#10-descuentos)
  - [Sponsors](#11-sponsors)
  - [Extras Frontend / Reportes](#12-extras-frontend--reportes)

## Convenciones
- **Base URL**: `http://localhost:8080`
- **Formato**: JSON (`Content-Type: application/json`)
- **Respuestas**: usar códigos HTTP estándar.
- A menos que se indique lo contrario, los `POST` aceptan un `body` JSON con parámetros.

## Autenticación
- `POST /auth/login`
  Body:
  ```json
  { "correo": "alejandrocastillo@gmail.com", "password": "********" }
  ```

## Endpoints

### 1. Usuarios
- `GET /usuarios/listar` — Listar todos los usuarios
- `POST /usuarios/obtener` — Detalle de un usuario
  Body: `{ "idUsuario": 5 }`
- `POST /usuarios/crear` — Crear usuario
- `POST /usuarios/actualizar` — Actualizar usuario
- `POST /usuarios/eliminar` — Eliminar usuario
- `POST /usuarios/matriculas` — Matrículas de un usuario
  Body: `{ "idUsuario": 7 }`
- `POST /usuarios/pagos` — Pagos de un usuario

### 2. Países
- `GET /paises/listar`
- `POST /paises/obtener`
- `POST /paises/crear`
- `POST /paises/actualizar`
- `POST /paises/eliminar`

### 3. Tipos de Identificación
- `GET /tipos-identificacion/listar`
- `POST /tipos-identificacion/obtener`
- `POST /tipos-identificacion/crear`
- `POST /tipos-identificacion/actualizar`
- `POST /tipos-identificacion/eliminar`

### 4. Categorías
- `GET /categorias/listar`
- `POST /categorias/obtener`
- `POST /categorias/crear`
- `POST /categorias/actualizar`
- `POST /categorias/eliminar`

### 5. Cursos / Diplomados
- `GET /cursos/listar`
- `POST /cursos/obtener`
- `POST /cursos/crear`
- `POST /cursos/actualizar`
- `POST /cursos/eliminar`
- `POST /cursos/programaciones` — Listar programaciones de un curso

### 6. Programaciones de Curso
- `GET /programaciones/listar`
- `POST /programaciones/obtener`
- `POST /programaciones/crear`
- `POST /programaciones/actualizar`
- `POST /programaciones/eliminar`
- `POST /programaciones/matriculas` — Matrículas de una programación

### 7. Matrículas
- `GET /matriculas/listar`
- `POST /matriculas/obtener`
- `POST /matriculas/crear`
- `POST /matriculas/actualizar`
- `POST /matriculas/eliminar`
- `POST /matriculas/cancelar`
- `POST /matriculas/pagos` — Pagos de una matrícula

### 8. Pagos
- `GET /pagos/listar`
- `POST /pagos/obtener`
- `POST /pagos/crear`
- `POST /pagos/actualizar`
- `POST /pagos/eliminar`

### 9. Métodos de Pago
- `GET /metodos-pago/listar`
- `POST /metodos-pago/obtener`
- `POST /metodos-pago/crear`
- `POST /metodos-pago/actualizar`
- `POST /metodos-pago/eliminar`

### 10. Descuentos
- `GET /descuentos/listar`
- `POST /descuentos/obtener`
- `POST /descuentos/crear`
- `POST /descuentos/actualizar`
- `POST /descuentos/eliminar`

### 11. Sponsors
- `GET /sponsors/listar`
- `POST /sponsors/obtener`
- `POST /sponsors/crear`
- `POST /sponsors/actualizar`
- `POST /sponsors/eliminar`

### 12. Extras Frontend / Reportes
- `GET /dashboard/estadisticas` — Métricas generales
- `POST /reportes/pagos`
  Body: `{ "desde": "2025-01-01", "hasta": "2025-01-31" }`
- `POST /reportes/matriculas`
  Body: `{ "cursoId": 2 }`

---
Sugerencia: agrega ejemplos de request/response por endpoint según vayas estabilizando los contratos.