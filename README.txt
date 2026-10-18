EXAMEN 2 - SISTEMA DE ENVIOS

Requisitos: Java 21 y una instalacion de VS Code con Extension Pack for Java,
o un IDE para proyectos Java comunes.

OPCION 1: Ejecutar en VS Code
1. Abrir la carpeta Envios_Examen2_Final en VS Code.
2. Ejecutar src/App.java.

OPCION 2: Ejecutar en Windows
1. Descomprimir el ZIP completo.
2. Hacer doble clic en Ejecutar.bat (requiere JDK en PATH).

IMPORTANTE: No borre src/iconos. Ahí estan las imagenes de los botones.

El programa inicia con la tabla vacia. El boton + abre el formulario
para ingresar el tipo de envio, codigo, cliente, peso y distancia.
El boton - permite retirar un envio indicando su codigo.
La tabla muestra todos los envios agregados y su tarifa.

El enunciado menciona inicialmente 'Fluvial', pero la tabla de costos
y el diagrama de clases especifican 'Maritimo'. Se usa Maritimo.

EXTENSION A EXPRESS (sin modificar las clases existentes de negocio):
Crear modelos/Express.java que extienda Envio, crear
fabricas/ExpressFactory.java que extienda EnvioFactory y registrar
la nueva fabrica en App.java con sus tarifas cuando sean conocidas.
El enunciado no define valores tarifarios para Express.
