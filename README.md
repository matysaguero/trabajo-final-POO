# Proyecto: Puzzle/Simulación - Frontier

## 1. Integrantes del Equipo 

- Aguero, Matias 
- Vasquez, Lourdes 
- Bystray, Andres 
- Feito, Mariano

## 2. Dominio y Alcance del Sistema 

### Descripción del Problema
Se busca desarrollar una aplicación de escritorio basada en una simulación de control fronterizo. El jugador ocupa el rol de un oficial encargado de inspeccionar a diferentes ingresantes y determinar si pueden atravesar la frontera.

Cada ingresante presenta información personal y documentación que deberá ser comparada con las reglas fronterizas vigentes durante el día. Estas reglas pueden establecer restricciones relacionadas con la ciudad de origen, clan, raza, tipo de ingresante, documentación presentada u otras condiciones.

El jugador deberá decidir si acepta o rechaza a cada ingresante. Las decisiones correctas aumentarán su reputación, mientras que permitir el ingreso de una persona que no cumple los requisitos o rechazar incorrectamente a una persona válida tendrá consecuencias negativas.

### Objetivo del Sistema
El sistema será un juego funcional y extensible que permitirá al jugador experimentar las mecánicas básicas del género. El diseño debe ser modular para facilitar la adición de nuevos finales, enemigos o interacciones en el futuro, aplicando rigurosamente los conceptos del paradigma orientado a objetos.

### Funcionalidades Principales (Features)
- **Sistema de Ingresantes:**
    - Se presentan diferentes personas que intentan atravesar la frontera.
    - Los ingresantes poseen información personal como nombre, ciudad de origen, clan y tipo.
    - Existen distintos comportamientos de ingresantes, como ingresantes regulares y enemigos.
    - Los ingresantes pueden responder de forma diferente durante un interrogatorio.
- **Sistema de Documentación:**
    - Cada ingresante puede presentar uno o más documentos.
    - Los documentos contienen información que puede ser comparada con los datos declarados por el ingresante.
    - Algunos documentos pueden presentar irregularidades o falsificaciones.
- **Política Fronteriza:**
    - Cada jornada posee reglas que determinan quién puede atravesar la frontera.
    - Las reglas pueden establecer restricciones según ciudad, clan, raza u otras características.
    - El jugador deberá analizar al ingresante y su documentación teniendo en cuenta las reglas vigentes.
    - El sistema determinará si el ingresante cumple o no las condiciones necesarias para ingresar.
- **Mecánicas del juego:**
    - El jugador puede aceptar o rechazar a cada ingresante.
    - La decisión realizada se compara con el resultado establecido por la política fronteriza.
    - Una decisión correcta aumenta la reputación del jugador.
    - Una decisión incorrecta disminuye la reputación.
    - Los enemigos pueden producir consecuencias especiales si logran atravesar la frontera.
- **Interfaz Gráfica (IGU):**
    - Visualización del ingresante actual.
    - Visualización de los documentos presentados.
    - Panel con las reglas fronterizas vigentes.
    - Visualización de la reputación del jugador.
    - Controles para aceptar o rechazar al ingresante.
    - Opción para continuar con el siguiente ingresante.
- **Persistencia:**
    - Se contempla incorporar un sistema de guardado y carga del progreso de la partida.
    - Esta funcionalidad se desarrollará en etapas posteriores.
 
## 3. Arquitectura y Diseño 
### Patrón de Diseño
### Diagramas de Diseño
### **Diagrama de Clases UML (Conceptual)**
### **Prototipo de la IGU (Wireframe)**

## 4. Stack Tecnologico
    - Lenguaje: Java.
    - IDE: Visual Studio Code.
    - Framework de IGU: Java Swing.
    - Control de Versiones: Git y GitHub.
    - Persistencia: A definir durante las siguientes etapas del proyecto.



