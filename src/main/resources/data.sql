
------ 1. ROLES ------

INSERT INTO roles (nombre) VALUES ('ROLE_VENDEDOR');
INSERT INTO roles (nombre) VALUES ('ROLE_COMPRADOR');


------ 2. USUARIOS (VENDEDORES) ------

INSERT INTO usuarios (email, password, nombre, direccion, telefono) 
VALUES ('vendedor1@marketplace.com', '123456', 'Joyería Central', 'Av. Corrientes 1234', '1122334455');

INSERT INTO usuarios (email, password, nombre, direccion, telefono) 
VALUES ('vendedor2@marketplace.com', '123456', 'Platería Moderna', 'Calle Florida 567', '1199887766');


------ 3. ASIGNACIÓN DE ROLES (Tabla Intermedia) ------

-- id_rol 1 (vendedor) 
INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (1, 1);
INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (2, 1);


------ 4. CATEGORÍAS ------

INSERT INTO categorias (nombre, slug, descripcion, publicado) 
VALUES ('Anillos', 'anillos', 'Colección de anillos de oro, plata y platino', true);

INSERT INTO categorias (nombre, slug, descripcion, publicado) 
VALUES ('Collares', 'collares', 'Collares finos con piedras preciosas y diamantes', true);

INSERT INTO categorias (nombre, slug, descripcion, publicado) 
VALUES ('Aros', 'aros', 'Aros de diseño exclusivo, pasantes y argollas', true);

INSERT INTO categorias (nombre, slug, descripcion, publicado) 
VALUES ('Pulseras', 'pulseras', 'Pulseras y esclavas de plata 925', true);


------ 5. PRODUCTOS ------

-- Categoria 1 (Anillos)
INSERT INTO productos (nombre, descripcion, precio, stock, tipo, id_categoria, id_vendedor) 
VALUES ('Anillo de Plata 925 Liso', 'Anillo clásico de plata maciza estilo alianza', 45000.0, 15, 'FISICO', 1, 1);

INSERT INTO productos (nombre, descripcion, precio, stock, tipo, id_categoria, id_vendedor) 
VALUES ('Anillo Solitario Oro 18k', 'Anillo de compromiso con diamante de 0.5 quilates', 320000.0, 3, 'FISICO', 1, 2);

-- Categoria 2 (Collares)
INSERT INTO productos (nombre, descripcion, precio, stock, tipo, id_categoria, id_vendedor) 
VALUES ('Collar Punto de Luz', 'Collar fino de oro blanco con pequeña zirconia', 85000.0, 8, 'FISICO', 2, 1);

INSERT INTO productos (nombre, descripcion, precio, stock, tipo, id_categoria, id_vendedor) 
VALUES ('Gargantilla de Perlas', 'Gargantilla clásica con perlas cultivadas de 8mm', 115000.0, 5, 'FISICO', 2, 2);

-- Categoria 3 (Aros)
INSERT INTO productos (nombre, descripcion, precio, stock, tipo, id_categoria, id_vendedor) 
VALUES ('Aros Argolla Plata', 'Aros estilo argolla de 2cm de diámetro en plata 925', 28000.0, 20, 'FISICO', 3, 1);

-- Catgoria 4 (Pulseras)
INSERT INTO productos (nombre, descripcion, precio, stock, tipo, id_categoria, id_vendedor) 
VALUES ('Esclava de Oro', 'Pulsera rígida de oro amarillo 18 quilates', 210000.0, 4, 'FISICO', 4, 1);