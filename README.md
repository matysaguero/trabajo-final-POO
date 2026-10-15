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

### Alcance del Sistema
El núcleo jugable comprende la presentación de ingresantes, la inspección de información y documentos, la decisión de ingreso, la evaluación de las políticas y la gestión de reputación. Se consideran ampliaciones futuras los interrogatorios interactivos, las jornadas con reglas variables, los eventos especiales, los nuevos tipos de enemigos y los finales alternativos. No se presentan como funcionalidades terminadas.

---

## Funcionalidades Principales
### Sistema de Ingresantes
- Se utiliza una jerarquía basada en la clase abstracta "Ingresante", con las clases concretas "IngresanteRegular" y "Enemigo".
- Cada personaje contiene nombre declarado, altura, peso, tipo de ingresante, raza, clan, ciudad de origen, documentos y una ruta de imagen para su representación visual.
- Los métodos "darPresentacion()", "responderInterrogatorio()" y "esAmenaza()" permiten comportamientos polimórficos. Su existencia no implica que ya haya un sistema completo de diálogos interactivos.
- "FabricaIngresantes.crearPlantelInicial()" construye diez personajes y mezcla su orden.
- Partida conserva el plantel inicial, la fila y el ingresante actual. La fila utiliza actualmente una lista; todavía no se cambió a Queue.
- La fábrica se invoca una vez en Main. Al reiniciar, se reconstruye la fila a partir del mismo plantel y orden cargados.
  
---

### Sistema de Documentación
- Se modelan "DNI", "Pasaporte", "Permiso", "Placa" y "PapelEnTramite", derivados de la clase abstracta Documento.
- Los documentos comparten número de identificación, fecha de vencimiento e indicador de falsificación, además de atributos específicos según su tipo.
- Un ingresante puede presentar varios documentos.
- "FabricaVistaDocumento" crea sus representaciones visuales y "VistaMesaDocumentos" las presenta en la mesa.
- Está pendiente completar las reglas que contrasten los documentos entre sí, con los datos del personaje y con las condiciones de ingreso. Tener los atributos de vencimiento y falsificación no significa que todas esas validaciones estén activas.

---

### Sistema de Políticas Fronterizas
- La clase abstracta "PoliticaFronteriza" define el contrato "esValido(Ingresante ingresante)".
- "Resolutor" mantiene una colección de políticas y permite el ingreso únicamente si todas se cumplen: "Politica1 AND Politica2 AND Politica3.." y asi sucevamente.
- La configuración actual activa "PoliticaClan" y "PoliticaIngresante".
- Esta separación permite incorporar nuevas evaluaciones mediante implementaciones del contrato, sin agregar al resolutor las reglas particulares de cada política.

---

### Sistema de Reputación
- "Jugador" conserva la reputación y "GestorReputacion" determina su variación según la decisión tomada y el resultado del resolutor.
- La partida comienza y se reinicia con 10 puntos. La reputación se mantiene entre 0 y 10.
- "ResultadoDecision" reúne si el personaje podía ingresar, si la decisión fue correcta, la reputación resultante y si se perdió la partida.
Las reglas implementadas en la versión documentada son:

<img width="790" height="284" alt="image" src="https://github.com/user-attachments/assets/73912f0d-87e8-4e89-9cb2-b80143dbeba2" />


- Los aciertos conservan la reputación; los errores la reducen. Premiar los aciertos con puntos sería un cambio de reglas, no el comportamiento actual. Cuando la reputación llega a cero, se informa la derrota y se vuelve al menú.

---

### Mecánica de Decisión
El flujo presente en el código es:
  1. El jugador selecciona Jugar y se inicia la partida.
  2. Se carga automáticamente el primer ingresante y se muestran sus documentos.
  3. El jugador inspecciona la información y abre la ventana de decisión.
  4. El jugador selecciona Aceptar o Rechazar.
  5. Partida consulta a Resolutor y delega la actualización de puntos en GestorReputacion.
  6. Se genera ResultadoDecision y se notifica a los observadores.
  7. La vista informa el resultado y, si no se perdió, el modelo intenta llamar automáticamente al siguiente ingresante.
  8. El primer ingresante desaparece automáticamente. Después de cada decisión, el jugador llama al siguiente pulsando el botón invisible del altavoz.
      - Solo funciona con la ventanilla vacía.
      - El controlador solicita el siguiente ingresante y el modelo notifica a las vistas.
      - Si no quedan ingresantes, se muestra un aviso. El cierre de jornada sigue pendiente.

---

### Interfaz Gráfica
La interfaz se implementa con Java Swing. Sus componentes principales son:
- MenuPrincipal:	presenta las opciones de inicio y tutorial.
- Escenario:	Muestra el puesto fronterizo y sus controles.
- VistaVentanilla:	Muestra al ingresante actual.
- VistaMesaDocumentos:	Presenta los documentos sobre la mesa.
- VistaDecision:	Muestra las opciones Aceptar y Rechazar.
- VistaTutorial:	Muestra la pantalla de ayuda y el botón Volver.
- VistaConsola:	Informa datos del ingresante y resultados por consola.
- GestorVista:	Coordina las vistas y recibir notificaciones del modelo.
- FabricaVistaDocumento:	Crea la representación visual de cada tipo de documento.
- BotonInvisible:	Proporciona un control gráfico auxiliar.

Se incorporaron al código el menú principal, el escenario, la representación gráfica de ingresantes y documentos, las opciones de decisión y la navegación al tutorial. Están pendientes un panel de políticas vigentes, un indicador permanente de reputación, el sistema de interrogatorios y la información de la jornada actual.

---

### Persistencia
La persistencia todavía no se encuentra implementada. Se contempla incorporar posteriormente un sistema que permita:

- Guardar una partida.
- Recuperar el progreso.
- Conservar información del jugador.
- Mantener el día y estado actual de la partida.
 
## 3. Arquitectura y Diseño 
### Patrón de Diseño
- Observer (GestorVista al Modelo).
- Facade (ControladorJuego con GestorVista).
- Los mismos se detallan en el siguiente documento: [Frontier - Patrones de diseño.docx](https://github.com/user-attachments/files/33466417/Frontier.-.Patrones.de.diseno.docx)

### Diagramas de Diseño
### **Diagrama de Clases UML (General por tema de simplificacion)**
<img width="2690" height="1115" alt="diagrama de clases general UML - arquitectura MVC" src="https://github.com/user-attachments/assets/ac6769a0-d82a-455f-be87-e508a00bf726" />


### Prototipo de la IGU (Wireframe)**
#### Menú principal
<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/2dd4b8ba-a37c-4830-b051-80a611b9be8d" />

#### Escenario del juego
<img width="1558" height="924" alt="image" src="https://github.com/user-attachments/assets/e716bc8c-9478-4f33-b1c0-df1ee8f47d60" />


## 4. Stack Tecnologico
    - Lenguaje: Java.
    - IDE: Visual Studio Code.
    - Framework de IGU: Java Swing.
    - Control de Versiones: Git y GitHub.
    - Persistencia: A definir durante las siguientes etapas del proyecto.



