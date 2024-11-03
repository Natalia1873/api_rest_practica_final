INSERT INTO articulo(nombre,direccion,unidad_precio,unidad_stock,tipo,proveedor,fecha) VALUES ("articulo1","calle colon 312",200.0, 5,"electronica", "proveedor1", "2023-01-01");
INSERT INTO articulo(nombre,direccion,unidad_precio,unidad_stock,tipo,proveedor,fecha) VALUES ("articulo2","calle amor 312",280.00, 150, "electronica", "proveedor2", "2023-01-02");
INSERT INTO articulo(nombre,direccion,unidad_precio,unidad_stock,tipo,proveedor,fecha) VALUES ("articulo3","calle pinto 312",18.00, 3, "hogar", "proveedor3", "2023-01-03");
INSERT INTO articulo(nombre,direccion,unidad_precio,unidad_stock,tipo,proveedor,fecha) VALUES ("articulo4","calle antonio lopez 312",800.00,250, "electronica", "proveedor4", "2023-01-04");
INSERT INTO articulo(nombre,direccion,unidad_precio,unidad_stock,tipo,proveedor,fecha) VALUES ("articulo5","calle diamante 312",2.00,300, "jugueteria", "proveedor5", "2023-01-05");
INSERT INTO articulo(nombre,direccion,unidad_precio,unidad_stock,tipo,proveedor,fecha) VALUES ("articulo6","calle ambar 312", 600.0, 50, "electronica", "proveedor6", "2023-01-06");


INSERT INTO cliente(nombre,apellido,empresa, puesto,direccion,codigo_postal, provincia,telefono,fecha_nacimiento) VALUES("Juan", "Pérez", "Empresa A", "Gerente", "Calle Mayor 123", "28001", "Madrid", "612345678", "1985-04-12");
INSERT INTO cliente(nombre,apellido,empresa, puesto,direccion,codigo_postal, provincia,telefono,fecha_nacimiento) VALUES("Ana", "Gómez", "Empresa B", "Asistente", "Avenida Sol 45", "08001", "Barcelona", "634567890", "1990-08-22");
INSERT INTO cliente(nombre,apellido,empresa, puesto,direccion,codigo_postal, provincia,telefono,fecha_nacimiento) VALUES("Carlos", "López", "Empresa C", "Director", "Calle Luna 32", "46001", "Valencia", "645678901", "1978-12-15");
INSERT INTO cliente(nombre,apellido,empresa, puesto,direccion,codigo_postal, provincia,telefono,fecha_nacimiento) VALUES("Maria", "Martínez", "Empresa D", "Analista", "Paseo Marítimo 10", "29001", "Málaga", "656789012", "1983-06-30");
INSERT INTO cliente(nombre,apellido,empresa, puesto,direccion,codigo_postal, provincia,telefono,fecha_nacimiento) VALUES("Laura", "Fernández", "Empresa E", "Desarrollador", "Calle Jardines 56", "50001", "Zaragoza", "667890123", "1992-11-25");
INSERT INTO cliente(nombre,apellido,empresa, puesto,direccion,codigo_postal, provincia,telefono,fecha_nacimiento) VALUES("Pedro", "Sánchez", "Empresa F", "Contador", "Calle Colón 98", "41001", "Sevilla", "678901234", "1988-02-17");



INSERT INTO compra(cliente_id,articulo_id,fecha,cantidad,total,iva, total_iva) VALUES (1, 1, "2023-10-05", 2, 560.00, 0.21, 117.60);
INSERT INTO compra(cliente_id,articulo_id,fecha,cantidad,total,iva, total_iva) VALUES (2, 3, "2023-10-06", 1, 280.00, 0.21, 58.80);
INSERT INTO compra(cliente_id,articulo_id,fecha,cantidad,total,iva, total_iva) VALUES (3, 2, "2023-10-07", 5, 1400.00, 0.21, 294.00);
INSERT INTO compra(cliente_id,articulo_id,fecha,cantidad,total,iva, total_iva) VALUES (4, 4, "2023-10-08", 3, 840.00, 0.21, 176.40);
INSERT INTO compra(cliente_id,articulo_id,fecha,cantidad,total,iva, total_iva) VALUES (5, 5, "2023-10-09", 4, 1120.00, 0.21, 235.20);
INSERT INTO compra(cliente_id,articulo_id,fecha,cantidad,total,iva, total_iva) VALUES (6, 6, "2023-10-10", 1, 280.00, 0.21, 58.80);

