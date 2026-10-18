EXAMEN 2 - SISTEMA DE ENVIOS

Ejecutar en VS Code
1. Abrir la carpeta Examen_2_Envios en VS Code.
2. Ejecutar src/App.java.

El programa inicia con la tabla vacia. El boton + abre el formulario
para ingresar el tipo de envio, codigo, cliente, peso y distancia.
El boton - permite retirar un envio indicando su codigo.
La tabla muestra todos los envios agregados y su tarifa.

EXTENSION A EXPRESS (sin modificar las clases existentes de negocio):
Crear modelos/Express.java que extienda Envio, crear
fabricas/ExpressFactory.java que extienda EnvioFactory y registrar
la nueva fabrica en App.java con sus tarifas cuando sean conocidas.
El enunciado no define valores tarifarios para Express.
