<h1 align="center">Servicio Clients</h1>
<p align="center">
   <img src="https://img.shields.io/badge/Aplicación-%20Postman-blue">   
   <img src="https://img.shields.io/badge/Código-%20Java-blue">
   <img src="https://img.shields.io/badge/Framework-%20Spring-blue">
   <img src="https://img.shields.io/badge/Servicio-%20Rest-blue">     
   <img src="https://img.shields.io/badge/Estado-%20V1.0-green">  
   <img src="https://img.shields.io/badge/Licencia-%20GlobalLogic-yellow">
   </p>
   ![GitHub Org's stars](https://img.shields.io/github/stars/ventas?style=social)

## Funcionalidad
<p>Utilizamos este componente para procesar las peticiones que envie el API-Getaway referentes a los clientes y las excepciones que estas puedan presentar.</p>

## Como iniciar el servicio
<ul>
<li>Importar la colección a Postman presente entre los archivos de la carpeta raiz (API-Clients.postman_collection.json)</li>
<li><ul>Configurar el Environment de postman con los siguientes valores:
    <li>variable: base_url</li>
    <li>INITIAL VALUE: https://34b10000-c242-4048-9641-a2b429c0d65a.mock.pstmn.io</li></ul>
</li>
<li>Despues de agregar esto, no olvidar guardar los cambios</li>
<li>Probar cualquiera de los 2 request presentes en la colección con estos valores como pathVariable:<ul>
    <li>getClientById: 1</li>
    <li>getClientByDni: 10200300</li>
    </ul>
</li>
<li>Si responde correctamente estamos listos para compilar la aplicación y acceder a las funciones disponibles.</li>
</ul>

## Funciones disponibles
- `Path: /{id}`: Obtener información del cliente por su codigo de identificacion (ID).
- `Path: /dni/{dni}`: Obtener información del cliente por su DNI.

## Excepciones
- En caso de enviar un id o DNI que no esté en la base de datos, el servicio nos lo avisará
- En caso de enviar el id o DNI vacio el servicio nos avisará que debemos ingresar algun valor

## Autores
<ul>
<li>Adelman Geraldine</li>
<li>Aguilera Martin</li>
<li>Delgado Gonzalo</li>
<li>Rivero Francisco</li>
<li>Viola Federico</li>
</ul>
