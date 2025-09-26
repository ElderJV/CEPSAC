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
- **Base URL**: `http://localhost:PUERTO/api`
- **Formato**: JSON (`Content-Type: application/json`)
- **Respuestas**: usar códigos HTTP estándar.
- Todos los `POST` requieren un `body` JSON con parámetros.

## Autenticación
- `POST /api/auth/login`
  Body:
  ```json
  { "correo": "user@dominio.com", "password": "********" }
  ```

## Endpoints

### 1. Usuarios
- `GET /api/usuarios/listar` — Listar todos los usuarios
- `POST /api/usuarios/obtener` — Detalle de un usuario
  Body: `{ "idUsuario": 5 }`
- `POST /api/usuarios/crear` — Crear usuario
  Body: `{ "nombre": "Juan", "apellido": "Pérez", "correo": "juan@email.com", "password": "123456", "rol": "Alumno", "numeroCelular": "987654321", "numeroIdentificacion": "12345678" }`
- `POST /api/usuarios/actualizar` — Actualizar usuario
  Body: `{ "idUsuario": 5, "nombre": "Juan Carlos", "apellido": "Pérez García" }`
- `POST /api/usuarios/eliminar` — Eliminar usuario
  Body: `{ "idUsuario": 5 }`
- `POST /api/usuarios/matriculas` — Matrículas de un usuario
  Body: `{ "idUsuario": 7 }`
- `POST /api/usuarios/pagos` — Pagos de un usuario
  Body: `{ "idUsuario": 7 }`

### 2. Países
- `GET /api/paises/listar`
- `POST /api/paises/obtener`
  Body: `{ "idPais": 1 }`
- `POST /api/paises/crear`
  Body: `{ "nombre": "Perú", "codigo": "+51" }`
- `POST /api/paises/actualizar`
  Body: `{ "idPais": 1, "nombre": "República del Perú", "codigo": "+51" }`
- `POST /api/paises/eliminar`
  Body: `{ "idPais": 1 }`

### 3. Tipos de Identificación
- `GET /api/tipos-identificacion/listar`
- `POST /api/tipos-identificacion/obtener`
  Body: `{ "idTipoIdentificacion": 1 }`
- `POST /api/tipos-identificacion/crear`
  Body: `{ "nombre": "DNI" }`
- `POST /api/tipos-identificacion/actualizar`
  Body: `{ "idTipoIdentificacion": 1, "nombre": "Documento Nacional de Identidad" }`
- `POST /api/tipos-identificacion/eliminar`
  Body: `{ "idTipoIdentificacion": 1 }`

### 4. Categorías
- `GET /api/categorias/listar`
- `POST /api/categorias/obtener`
  Body: `{ "idCategoria": 1 }`
- `POST /api/categorias/crear`
  Body: `{ "nombre": "Programación", "descripcion": "Cursos de desarrollo de software" }`
- `POST /api/categorias/actualizar`
  Body: `{ "idCategoria": 1, "nombre": "Desarrollo de Software", "descripcion": "Cursos de programación y desarrollo" }`
- `POST /api/categorias/eliminar`
  Body: `{ "idCategoria": 1 }`

### 5. Cursos / Diplomados
- `GET /api/cursos/listar`
- `POST /api/cursos/obtener`
  Body: `{ "idCursoDiplomado": 1 }`
- `POST /api/cursos/crear`
  Body: `{ "titulo": "Java para Principiantes", "descripcion": "Curso introductorio a Java", "tipo": false, "otorgaCertificado": true, "idCategoria": 1 }`
- `POST /api/cursos/actualizar`
  Body: `{ "idCursoDiplomado": 1, "titulo": "Java desde Cero", "descripcion": "Curso completo de Java" }`
- `POST /api/cursos/eliminar`
  Body: `{ "idCursoDiplomado": 1 }`
- `POST /api/cursos/programaciones` — Listar programaciones de un curso
  Body: `{ "idCursoDiplomado": 1 }`

### 6. Programaciones de Curso
- `GET /api/programaciones/listar`
- `POST /api/programaciones/obtener`
  Body: `{ "idAccesoCurso": 1 }`
- `POST /api/programaciones/crear`
  Body: `{ "modalidad": "VIRTUAL", "duracionCurso": 40.0, "fechaInicio": "2025-02-01", "fechaFin": "2025-03-15", "idCursoDiplomado": 1 }`
- `POST /api/programaciones/actualizar`
  Body: `{ "idAccesoCurso": 1, "modalidad": "PRESENCIAL", "duracionCurso": 35.0 }`
- `POST /api/programaciones/eliminar`
  Body: `{ "idAccesoCurso": 1 }`
- `POST /api/programaciones/matriculas` — Matrículas de una programación
  Body: `{ "idAccesoCurso": 1 }`

### 7. Matrículas
- `GET /api/matriculas/listar`
- `POST /api/matriculas/obtener`
  Body: `{ "idMatricula": 1 }`
- `POST /api/matriculas/crear`
  Body: `{ "idProgramacionCurso": 1, "idAlumno": 5, "monto": 500.00 }`
- `POST /api/matriculas/actualizar`
  Body: `{ "idMatricula": 1, "estado": "pagado", "monto": 450.00 }`
- `POST /api/matriculas/eliminar`
  Body: `{ "idMatricula": 1 }`
- `POST /api/matriculas/cancelar`
  Body: `{ "idMatricula": 1 }`
- `POST /api/matriculas/pagos` — Pagos de una matrícula
  Body: `{ "idMatricula": 1 }`

### 8. Pagos
- `GET /api/pagos/listar`
- `POST /api/pagos/obtener`
  Body: `{ "idPago": 1 }`
- `POST /api/pagos/crear`
  Body: `{ "idMatricula": 1, "idMetodoPago": 1, "monto": 250.00, "numeroCuota": 1 }`
- `POST /api/pagos/actualizar`
  Body: `{ "idPago": 1, "monto": 200.00 }`
- `POST /api/pagos/eliminar`
  Body: `{ "idPago": 1 }`

### 9. Métodos de Pago
- `GET /api/metodos-pago/listar`
- `POST /api/metodos-pago/obtener`
  Body: `{ "idMetodoPago": 1 }`
- `POST /api/metodos-pago/crear`
  Body: `{ "tipoMetodo": "TRANSFERENCIA", "descripcion": "Transferencia bancaria", "requisitos": "Adjuntar comprobante" }`
- `POST /api/metodos-pago/actualizar`
  Body: `{ "idMetodoPago": 1, "descripcion": "Transferencia bancaria nacional" }`
- `POST /api/metodos-pago/eliminar`
  Body: `{ "idMetodoPago": 1 }`

### 10. Descuentos
- `GET /api/descuentos/listar`
- `POST /api/descuentos/obtener`
  Body: `{ "idDescuento": 1 }`
- `POST /api/descuentos/crear`
  Body: `{ "tipoDescuento": "PORCENTAJE", "valor": 15.00, "vigente": true, "fechaInicio": "2025-01-01", "fechaFin": "2025-12-31" }`
- `POST /api/descuentos/actualizar`
  Body: `{ "idDescuento": 1, "valor": 20.00, "vigente": false }`
- `POST /api/descuentos/eliminar`
  Body: `{ "idDescuento": 1 }`

### 11. Sponsors
- `GET /api/sponsors/listar`
- `POST /api/sponsors/obtener`
  Body: `{ "idSponsor": 1 }`
- `POST /api/sponsors/crear`
  Body: `{ "nombre": "Empresa ABC", "urlSponsor": "https://empresa-abc.com" }`
- `POST /api/sponsors/actualizar`
  Body: `{ "idSponsor": 1, "nombre": "Empresa ABC S.A.C.", "urlSponsor": "https://www.empresa-abc.com" }`
- `POST /api/sponsors/eliminar`
  Body: `{ "idSponsor": 1 }`

### 12. Extras Frontend / Reportes
- `GET /api/dashboard/estadisticas` — Métricas generales
- `POST /api/reportes/pagos`
  Body: `{ "desde": "2025-01-01", "hasta": "2025-01-31" }`
- `POST /api/reportes/matriculas`
  Body: `{ "cursoId": 2 }`

---
