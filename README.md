# Proyecto: Puzzle/Simulación - Frontier

## 1. Integrantes del Equipo 
- Aguero, Matias 
- Vasquez, Lourdes 
- Bystray, Andres 
- Feito, Mariano

## 2. Dominio y Alcance del Sistema
### Descripción del Problema

**Frontier** es una aplicación de escritorio basada en una simulación de control fronterizo. El jugador ocupa el rol de un oficial encargado de inspeccionar a diferentes ingresantes y decidir si pueden atravesar la frontera. Cada ingresante posee información personal y puede presentar distintos tipos de documentación. Estos datos deben ser evaluados según las políticas fronterizas vigentes. Las políticas pueden establecer restricciones relacionadas con:

- Clan.
- Raza.
- Ciudad de origen.
- Tipo de ingresante.
- Documentación presentada.
- Validez de los documentos.
- Otras condiciones incorporadas durante el desarrollo.

El jugador debe decidir si **acepta** o **rechaza** a cada ingresante. El sistema compara la decisión del jugador con el resultado obtenido mediante las políticas fronterizas. Las decisiones correctas aumentan la reputación del jugador, mientras que las decisiones incorrectas la disminuyen. Si la reputación llega a cero, la partida finaliza.

---

### Objetivo del Sistema
El objetivo es desarrollar un juego funcional y extensible que permita aplicar los principales conceptos de la **Programación Orientada a Objetos**. El diseño busca facilitar la incorporación futura de:

- Nuevos tipos de ingresantes.
- Nuevos documentos.
- Nuevas políticas fronterizas.
- Interrogatorios.
- Diferentes jornadas.
- Nuevos enemigos.
- Eventos especiales.
- Diferentes finales.
- Persistencia de partidas.

El proyecto busca mantener una arquitectura modular para evitar que la incorporación de nuevas funcionalidades requiera modificar grandes partes del sistema existente.

---

## 3. Funcionalidades Principales
### Sistema de Ingresantes
El sistema posee una jerarquía de ingresantes representada mediante una clase abstracta `Ingresante`. Actualmente existen:

- `IngresanteRegular`
- `Enemigo`

Cada ingresante contiene información como:

- Nombre declarado.
- Altura.
- Peso.
- Tipo de ingresante.
- Raza.
- Clan.
- Ciudad de origen.
- Documentación presentada.

Los distintos tipos de ingresantes pueden implementar comportamientos diferentes mediante polimorfismo, por ejemplo:

- Responder interrogatorios.
- Presentarse ante el jugador.
- Determinar si representan una amenaza.

---

### Sistema de Documentación
Los ingresantes pueden presentar uno o más documentos. Actualmente se encuentran modelados los siguientes tipos:

- `DNI`
- `Pasaporte`
- `Permiso`
- `Placa`
- `PapelEnTramite`

Todos ellos heredan de la clase abstracta `Documento`. Los documentos poseen información común como:

- Número de identificación.
- Fecha de vencimiento.
- Indicador de falsificación.

Además, cada documento puede contener información específica dependiendo de su tipo. El objetivo es que esta documentación pueda ser comparada posteriormente con los datos declarados por el ingresante y con las reglas fronterizas vigentes.

---

### Sistema de Políticas Fronterizas
Las condiciones de ingreso se encuentran separadas mediante una jerarquía de políticas. La clase abstracta:

`PoliticaFronteriza`

define el comportamiento común:

`esValido(Ingresante ingresante)`

Actualmente se encuentran implementadas políticas como:

- `PoliticaClan`
- `PoliticaIngresante`

Cada política evalúa una condición determinada de forma independiente. La clase `Resolutor` mantiene una colección de políticas y determina si un ingresante puede atravesar la frontera. Para que el ingreso sea válido, todas las políticas activas deben cumplirse. Conceptualmente:

`Politica1 AND Politica2 AND Politica3 ...`

Esta estructura permite agregar nuevas políticas sin modificar el funcionamiento interno del `Resolutor`.

---

### Sistema de Reputación
El jugador posee un valor de reputación que representa su desempeño durante la partida. Las clases principales involucradas son:

- `Jugador`
- `GestorReputacion`

El sistema compara:

- La decisión tomada por el jugador.
- El resultado obtenido mediante las políticas fronterizas.

Las reglas actuales son:

- Aceptar correctamente a un ingresante válido → aumenta la reputación.
- Rechazar correctamente a un ingresante inválido → aumenta la reputación.
- Aceptar incorrectamente a un ingresante inválido → disminuye la reputación.
- Rechazar incorrectamente a un ingresante válido → disminuye la reputación.

Cuando la reputación llega a cero, se considera finalizada la partida.

---

### Mecánica de Decisión
El flujo básico implementado es:

1. Se presenta un ingresante.
2. El sistema evalúa las políticas fronterizas activas.
3. Se determina internamente si el ingresante puede ingresar.
4. El jugador selecciona **Aceptar** o **Rechazar**.
5. El sistema compara ambas decisiones.
6. Se actualiza la reputación.
7. Se informa el resultado.

---

### Interfaz Gráfica
La interfaz está desarrollada utilizando **Java Swing**. Actualmente existen las siguientes clases principales de vista:

- `MenuPrincipal`
- `Escenario`
- `VistaDecision`
- `VistaConsola`
- `BotonInvisible`

El sistema permite actualmente:

- Mostrar el menú principal.
- Iniciar la partida.
- Mostrar el escenario del puesto fronterizo.
- Abrir una ventana de decisión.
- Aceptar o rechazar al ingresante.
- Mostrar información y resultados por consola.

Se encuentra planificada la incorporación progresiva de:

- Visualización gráfica del ingresante actual.
- Visualización interactiva de documentos.
- Panel de políticas vigentes.
- Indicador visual de reputación.
- Interrogatorios.
- Cambio automático al siguiente ingresante.
- Información correspondiente al día actual.

---

### Persistencia
La persistencia todavía no se encuentra implementada. Se contempla incorporar posteriormente un sistema que permita:

- Guardar una partida.
- Recuperar el progreso.
- Conservar información del jugador.
- Mantener el día y estado actual de la partida.
 
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



