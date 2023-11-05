<h1 align="center">MICROSERVICIO Tarjetas</h1>

<p align="center">
   <img src="https://img.shields.io/badge/Estado-En%20Desarrollo-green">
   <img src="https://img.shields.io/badge/Licencia-%20GlobalLogic-yellow">
   <img src="https://img.shields.io/badge/Base de datos-%20MYSQL-blue">
   <img src="https://img.shields.io/badge/Código-%20Java-blue">
   <img src="https://img.shields.io/badge/Framework-%20Spring-blue">
   </p>
   ![GitHub Org's stars](https://img.shields.io/github/stars/ventas?style=social)

  

## Funcionalidades del componente 

- `Obtener tarjeta por cliente y número de tarjeta`: El proceso busca obtener la tarjeta asociada al cliente y validar que el número coincida con la tarjeta.
- `Obtener tarjeta por número de tarjeta`: El proceso busca obtener la tarjeta asociada al número enviado por parametro.

## Guia para levantar el mock de soap desde soapUI

Desde la aplicación SoapUI se deben realizar los siguientes pasos:

- `Paso 1`: Ingresar desde la barra de Tareas a a la solapa "File" y luego seleccionar la opción "Import Project".
- `Paso 2`: Seleccionar en la carpeta base del proyecto Tarjetas el archivo "cards-new-soapui-project.xml". Se debera haber creado el proyecto en Soap.
- `Paso 3`: Hacer un doble-click en "GetCardsSoap MockService" dentro del proyecto en SoapUI. 
- `Paso 4`: Por último, oprimir el botón con símbolo Play para correr el mock.
- `Paso 5`: Con estos pasos ya se puede realizar las pruebas del microservicio.

## Pruebas
La cobertura de pruebas automatizadas se presentan en forma de pruebas unitarias utilizando JUnit5 y mockito.
Para realizar pruebas del microservicio se recomiendo abrir el archivo "Trabajo-Grupal-Tarjetas.postman_collection.json" desde postman y ejecutar los recursos.

## Autores
<ul>
<li>Adelman Geraldine</li>
<li>Aguilera Martin</li>
<li>Delgado Gonzalo</li>
<li>Rivero Francisco</li>
<li>Viola Federico</li>
</ul>
