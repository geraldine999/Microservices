<h1 align="center">MICROSERVICIO VENTAS</h1>

<p align="center">
   <img src="https://img.shields.io/badge/Estado-En%20Desarrollo-green">
   <img src="https://img.shields.io/badge/Licencia-%20GlobalLogic-yellow">
   <img src="https://img.shields.io/badge/Base de datos-%20MYSQL-blue">
   <img src="https://img.shields.io/badge/Código-%20Java-blue">
   <img src="https://img.shields.io/badge/Framework-%20Spring-blue">
   </p>
   ![GitHub Org's stars](https://img.shields.io/github/stars/ventas?style=social)

  

## Funcionalidades del componente 

- `Guardar una venta`: El proceso guarda una venta luego de obtener los requisitos necesarios como lista de productos con sus cantidades y precios unitarios, el cliente y metodo de pago que haya elegido.
- `Listar ventas`: Se visualiza la lista de ventas que existen.
- `Buscar venta segun el cliente`: Se obtiene una lista de las compras que haya realizado un determinado cliente.
- `Buscar una determinada venta`: Se obtiene una venta segun su codigo.

## Guia para levantar mysql desde docker

Desde la consola linux se deberan ejecutar los siguientes comandos:

- `Paso 1`: docker pull mysql/mysql-server:8.0
- `Paso 2`: docker run -d -p 13306:3306 --name mysql_container -e MYSQL_ROOT_PASSWORD=secret mysql/mysql-server:8.0 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
- `Paso 3`: docker exec -it mysql_container mysql -uroot -p
- `Paso 4`: create user 'mysqluser' identified by 'secret';
- `Paso 5`: GRANT ALL PRIVILEGES ON     * . *      TO 'mysqluser'@'%';
- `Paso 6`: exit;
- `Paso 7`: Abrir MySQL Workbench y proceder a loguearse con el usuario anteriormente creado

## Pruebas
La cobertura de pruebas automatizadas se presentan en forma de pruebas unitarias utilizando JUnit5 y mockito

## Futuro
Filtrar la lista de ventas segun parametros opcionales

## Autores
<ul>
<li>Adelman Geraldine</li>
<li>Aguilera Martin</li>
<li>Delgado Gonzalo</li>
<li>Rivero Francisco</li>
<li>Viola Federico</li>
</ul>
