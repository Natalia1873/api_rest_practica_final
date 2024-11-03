INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('Smartphone XS', 'Telefono movil', 200.00, 5, 'electronica', 'proveedor1', '2023-01-01');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('Laptop Pro', 'Ordenador portatil', 280.00, 150, 'electronica', 'proveedor2', '2023-01-02');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('Cafetera Premium', 'Cafetera automatica', 18.00, 3, 'hogar', 'proveedor3', '2023-01-03');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('Smart TV 4K', 'Televisor HD', 800.00, 250, 'electronica', 'proveedor4', '2023-01-04');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('Juego de Mesa', 'Juego familiar', 2.00, 300, 'jugueteria', 'proveedor5', '2023-01-05');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('Tableta Grafica', 'Tableta diseño', 600.00, 50, 'electronica', 'proveedor6', '2023-01-06');

INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Juan', 'Perez', 'Empresa A', 'Gerente', 'Calle Mayor 123', 28001, 'Madrid', '612345678', '1985-04-12');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Ana', 'Gomez', 'Empresa B', 'Asistente', 'Avenida Sol 45', 08001, 'Barcelona', '634567890', '1990-08-22');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Carlos', 'Lopez', 'Empresa C', 'Director', 'Calle Luna 32', 46001, 'Valencia', '645678901', '1978-12-15');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Maria', 'Martinez', 'Empresa D', 'Analista', 'Paseo Maritimo 10', 29001, 'Malaga', '656789012', '1983-06-30');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Laura', 'Fernandez', 'Empresa E', 'Desarrollador', 'Calle Jardines 56', 50001, 'Zaragoza', '667890123', '1992-11-25');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Pedro', 'Sanchez', 'Empresa F', 'Contador', 'Calle Colon 98', 41001, 'Sevilla', '678901234', '1988-02-17');

INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (1, 1, '2023-10-05', 2, 400.00, 21.00, 484.00);
INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (2, 3, '2023-10-06', 1, 18.00, 21.00, 21.78);
INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (3, 2, '2023-10-07', 5, 1400.00, 21.00, 1694.00);
INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (4, 4, '2023-10-08', 3, 2400.00, 21.00, 2904.00);
INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (5, 5, '2023-10-09', 4, 8.00, 21.00, 9.68);
INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (6, 6, '2023-10-10', 1, 600.00, 21.00, 726.00);