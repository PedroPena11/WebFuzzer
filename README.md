# 🚀 WebFuzzer

WebFuzzer es una herramienta de auditoría web desarrollada en Java 21 diseñada para descubrir rutas ocultas y archivos sensibles en servidores web mediante técnicas de fuerza bruta (fuzzing).

## 💡 ¿Por qué WebFuzzer?

A diferencia de otras herramientas, WebFuzzer combina el alto rendimiento de los Hilos Virtuales de Java con un control preciso mediante semáforos, permitiendo escaneos rápidos y estables sin saturar el servidor objetivo ni los recursos de tu máquina.

### ✨ Características Principales

* Concurrencia de Nueva Generación: Implementación de Virtual Threads para ejecutar miles de peticiones en paralelo.

* Control de Flujo: Uso de Semaphore para evitar el bloqueo del servidor y la denegación de servicio accidental.

* Sigilo: Rotación aleatoria de User-Agents para dificultar la detección por parte de firewalls (WAF).

* Modo Silencioso: Filtrado inteligente de resultados para enfocarse únicamente en lo que es relevante (200 OK, 403 Forbidden).

* Reportes Profesionales: Exportación automática de hallazgos a archivos de texto.

### 🛠️ Cómo empezar

#### Requisitos

* Java 21 o superior.

* Un diccionario de palabras (wordlist) en formato .txt.

### 🛠️ Instalación y Uso

1. #### Clonar el repositorio

Primero, descarga el código fuente en tu computadora:

`git clone https://github.com/PedroPena11/WebFuzzer.git`

`cd WebFuzzer`

2. #### Compilación

Como utilizamos características de Java 21, primero debemos compilar los archivos fuente para generar el bytecode necesario:

`javac *.java`
3. #### Ejecución

Una vez compilado, puedes lanzar la herramienta indicando la URL objetivo y tu diccionario de palabras:

`java GemiFuzzer http://<url-objetivo> diccionario.txt`

### 📂 Estructura del Proyecto

* GemiFuzzer.java: Clase principal y orquestador del escaneo.

* MotorHttp.java: Encargado de la lógica de peticiones y headers.

* TareaFuzzing.java: Ejecutor individual que filtra y reporta los hallazgos.

* AgenteUsuario.java: Motor de aleatorización de cabeceras.

### 📈 Rendimiento

* Velocidad: Capaz de procesar miles de rutas en segundos (ajustable mediante el semáforo).

* Eficiencia: Uso mínimo de RAM gracias al procesamiento mediante Streams de Java NIO.

## ⚖️ Aviso Legal

Esta herramienta ha sido creada exclusivamente con fines educativos y de auditoría ética en entornos controlados. El uso de WebFuzzer contra servidores sin autorización expresa es ilegal. ¡Úsala de forma responsable!

Desarrollado con pasión por [PedroPena]