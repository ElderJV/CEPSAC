
-- 1) Países
CREATE TABLE Pais (
  IdPais TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  Nombre VARCHAR(50) NOT NULL,
  Codigo VARCHAR(5),
  PRIMARY KEY (IdPais)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2) TipoIdentificacion
CREATE TABLE TipoIdentificacion (
  IdTipoIdentificacion TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  Nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (IdTipoIdentificacion)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3) Usuario
CREATE TABLE Usuario (
  IdUsuario SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  Rol ENUM('Administrador','Docente','Alumno','Otro') NOT NULL,
  Nombre VARCHAR(50) NOT NULL,
  Apellido VARCHAR(50),
  Correo VARCHAR(255),
  Password VARCHAR(255),
  Estado ENUM('activo','inactivo','suspendido',) DEFAULT 'activo',
  IdCodigoPais TINYINT UNSIGNED,
  NumeroCelular VARCHAR(15),
  IdTipoIdentificacion TINYINT UNSIGNED,
  NumeroIdentificacion VARCHAR(20),
  PRIMARY KEY (IdUsuario),
  UNIQUE KEY uk_usuario_correo (Correo),
  CONSTRAINT fk_usuario_pais FOREIGN KEY (IdCodigoPais) REFERENCES Pais(IdPais) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT fk_usuario_tipoident FOREIGN KEY (IdTipoIdentificacion) REFERENCES TipoIdentificacion(IdTipoIdentificacion) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4) MetodoPago
CREATE TABLE MetodoPago (
  IdMetodoPago TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  TipoMetodo ENUM('EFECTIVO','TRANSFERENCIA','YAPE','PLIN') NOT NULL,
  Descripcion VARCHAR(100),
  Requisitos VARCHAR(500),
  PRIMARY KEY (IdMetodoPago)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5) Sponsors
CREATE TABLE Sponsors (
  IdSponsor TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  Nombre VARCHAR(50) NOT NULL,
  UrlSponsor VARCHAR(255),
  IdUsuario SMALLINT UNSIGNED,
  PRIMARY KEY (IdSponsor),
  CONSTRAINT fk_sponsor_usuario FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6) Categoria
CREATE TABLE Categoria (
  IdCategoria TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  Nombre VARCHAR(50) NOT NULL,
  Descripcion VARCHAR(100),
  IdUsuario SMALLINT UNSIGNED, -- usuario creador/administrador de la categoría
  PRIMARY KEY (IdCategoria),
  CONSTRAINT fk_categoria_usuario FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 7) CursoDiplomado
CREATE TABLE CursoDiplomado (
  IdCursoDiplomado SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  IdCategoria TINYINT UNSIGNED,
  Tipo BOOLEAN DEFAULT FALSE, '0: CURSO, 1: DIPLOMADO'
  OtorgaCertificado BOOLEAN DEFAULT FALSE,
  Titulo VARCHAR(100) NOT NULL,
  UrlCurso VARCHAR(255),
  Objetivo VARCHAR(255),
  Descripcion VARCHAR(255),
  IdUsuario SMALLINT UNSIGNED, -- creador/autor
  PRIMARY KEY (IdCursoDiplomado),
  CONSTRAINT fk_cursod_categoria FOREIGN KEY (IdCategoria) REFERENCES Categoria(IdCategoria) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT fk_cursod_usuario FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 8) Descuento
CREATE TABLE Descuento (
  IdDescuento TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  TipoDescuento ENUM('PORCENTAJE','MONTO') NOT NULL,
  Valor DECIMAL(8,2) NOT NULL,
  Vigente BOOLEAN DEFAULT TRUE,
  FechaInicio DATE,
  FechaFin DATE,
  IdUsuario SMALLINT UNSIGNED, -- usuario que creó/el modificó
  PRIMARY KEY (IdDescuento),
  CONSTRAINT fk_desc_usuario FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 9) ProgramacionCurso (ofertas / accesos a un curso/diplomado)
CREATE TABLE ProgramacionCurso (
  IdAccesoCurso INT UNSIGNED NOT NULL AUTO_INCREMENT,
  Modalidad ENUM('PRESENCIAL','VIRTUAL','HIBRIDO') DEFAULT 'VIRTUAL',
  DuracionCurso DECIMAL(6,2), -- ej. horas o semanas; según tu modelo
  HorasSemanales DECIMAL(6,2),
  FechaInicio DATE,
  FechaFin DATE,
  IdUsuario SMALLINT UNSIGNED, -- docente/administrador responsable
  IdCursoDiplomado SMALLINT UNSIGNED,
  PRIMARY KEY (IdAccesoCurso),
  CONSTRAINT fk_prog_usuario FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT fk_prog_cursod FOREIGN KEY (IdCursoDiplomado) REFERENCES CursoDiplomado(IdCursoDiplomado) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 10) Matricula
CREATE TABLE Matricula (
  IdMatricula SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  IdProgramacionCurso INT UNSIGNED, -- FK a ProgramacionCurso
  IdAlumno SMALLINT UNSIGNED,        -- FK a Usuario (alumno)
  IdUsuario SMALLINT UNSIGNED,       -- usuario que registra la matrícula (administrador)
  FechaMatricula DATETIME DEFAULT CURRENT_TIMESTAMP,
  Estado ENUM('pendiente','pagado','cancelado') DEFAULT 'pendiente',
  Monto DECIMAL(10,2),
  IdDescuento TINYINT UNSIGNED,      -- FK a Descuento (opcional)
  PRIMARY KEY (IdMatricula),
  CONSTRAINT fk_mat_progcurso FOREIGN KEY (IdProgramacionCurso) REFERENCES ProgramacionCurso(IdAccesoCurso) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_mat_alumno FOREIGN KEY (IdAlumno) REFERENCES Usuario(IdUsuario) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT fk_mat_usuario FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT fk_mat_desc FOREIGN KEY (IdDescuento) REFERENCES Descuento(IdDescuento) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 11) Pago
CREATE TABLE Pago (
  IdPago SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  IdMatricula SMALLINT UNSIGNED,  -- FK a Matricula
  IdMetodoPago TINYINT UNSIGNED,  -- FK a MetodoPago
  Monto DECIMAL(10,2) NOT NULL,
  NumeroCuota TINYINT UNSIGNED,
  FechaPago DATETIME DEFAULT CURRENT_TIMESTAMP,
  IdUsuario SMALLINT UNSIGNED, -- usuario que registró el pago
  PRIMARY KEY (IdPago),
  CONSTRAINT fk_pago_matricula FOREIGN KEY (IdMatricula) REFERENCES Matricula(IdMatricula) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_pago_metodop FOREIGN KEY (IdMetodoPago) REFERENCES MetodoPago(IdMetodoPago) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_pago_usuario FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
