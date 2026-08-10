# Proyecto: Puzzle/Simulación - ?????????????????????

## 1. Integrantes del Equipo 

- Aguero, Matias 
- Vasquez, Lourdes 
- Bystray, Andres 
- Feito, Mariano

## 2. Dominio y Alcance del Sistema 

### Descripción del Problema
Se busca desarrollar una aplicación de escritorio basada en una simulación de un oficial de control fronterizo. El jugador debe utilizar sus herramientas, lógica e intuición para determinar si una persona puede ingresar al país o no desde su puesto en la frontera. Los enemigos intentarán engañarte mediante detalles en los requisitos para dañar al país desde dentro y perderás dinero por cada ilegal hasta que superes el día.

### Objetivo del Sistema
El sistema será un juego funcional y extensible que permitirá al jugador experimentar las mecánicas básicas del género. El diseño debe ser modular para facilitar la adición de nuevos finales, enemigos o interacciones en el futuro, aplicando rigurosamente los conceptos del paradigma orientado a objetos.

### Funcionalidades Principales (Features)
- **Control fronterizo:**
    - El jugador puede seleccionar y revisar la documentación presentada.
    - El jugador puede seleccionar y marcar los errores o incongruencias.
    - El jugador puede sellar el pase con un aprobado o desaprobado.
    - Los errores o faltas tienen un costo.
- **Sistema de Eventos de Enemigos:**
    - Los enemigos aparecen de manera random durante el día.
    - Existen diferentes tipos de enemigos (ej: ilegales, infiltrados, atentados ).
    - Los enemigos tienen un efecto dependiendo de su tipo.
- **Mecánicas de Juego:**
    - El jugador cuenta con una cantidad inicial de faltas diaria dependiendo de la dificultad del día.
    - Si un ilegal ingresa al país se restará una falta.
    - El jugador al llegar superar la barrera de faltas diarias pierde automáticamente.
    - Si un infiltrado ingresa al país puede restar más de una falta o perder el juego.

- **Interfaz Gráfica (IGU):**
    - Visualización del npc neutral u hostil, faltas, documentación, escenario fronterizo.
    - Panel de control para seleccionar errores y si se aprueba el paso o no.
    - Botón para que pase el siguiente npc.
- **Persistencia:**
    - Sistema de guardado y carga del progreso de los días.
