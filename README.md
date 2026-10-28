# Proyecto: Puzzle/Simulación - Frontier

## 1. Integrantes del Equipo 
- Aguero, Matias 
- Vasquez, Lourdes 
- Bystray, Andres 
- Feito, Mariano

---

## 2. Dominio y Alcance del Sistema
### Descripción del Problema
**Frontier** es una aplicación de escritorio basada en una simulación de control fronterizo. El jugador ocupa el rol de un oficial encargado de inspeccionar a diferentes ingresantes y decidir si pueden atravesar la frontera. Cada ingresante posee información personal y puede presentar distintos tipos de documentación. Estos datos deben ser evaluados según las políticas fronterizas vigentes.

El jugador debe decidir si **acepta** o **rechaza** a cada ingresante. El sistema compara la decisión del jugador con el resultado obtenido mediante las políticas fronterizas. Las decisiones correctas aumentan la reputación del jugador, mientras que las decisiones incorrectas la disminuyen. Si la reputación llega a cero, la partida finaliza con una derrota.

---

### Objetivo del Sistema
El objetivo es desarrollar un juego funcional y extensible que permita aplicar los principales conceptos de la **Programación Orientada a Objetos**. El proyecto busca mantener una arquitectura modular para facilitar la incorporación futura de nuevos tipos de ingresantes, documentos, políticas fronterizas y eventos.

---

### Alcance del Sistema
La versión actual permite completar una partida con diez ingresantes, una de tres variaciones de reglas elegida al iniciar, interrogatorio con respuesta predefinida, revisión de documentos, decisiones de ingreso y resultados de victoria o derrota. Incluye tutorial y persistencia de puntajes en SQLite.

---

## Funcionalidades Principales
#### Sistema de Ingresantes
- Se utiliza una jerarquía basada en la clase abstracta `Ingresante`, con las clases concretas `IngresanteRegular` y `Enemigo`.
- Cada personaje contiene nombre declarado, altura, peso, tipo de ingresante, raza, clan, ciudad de origen, documentos, motivo de visita y una ruta de imagen para su representación visual.
- `darPresentacion()` y `esAmenaza()` tienen implementaciones en las subclases. `responderInterrogatorio()` está implementado en `Ingresante` y devuelve el motivo de visita.
- `FabricaIngresantes.crearPlantelInicial()` construye diez personajes y mezcla su orden.
- `Partida` conserva el plantel inicial, la fila y el ingresante actual. La fila utiliza una lista.
- La fábrica se invoca una vez en `Main`. Al reiniciar, se reconstruye la fila a partir del mismo plantel y orden cargados; se vuelve a sortear la variación de reglas.

#### Sistema de Documentación
- `DNI`, `Pasaporte` y `Permiso` heredan de la clase abstracta `Documento`.
- Los atributos comunes son nombre, origen, indicador `valido` y fecha de vencimiento. Los números de identificación y demás datos específicos pertenecen a cada tipo de documento.
- Un ingresante puede presentar varios documentos o no presentar ninguno.
- `FabricaVistaDocumento` crea paneles para los tres tipos registrados y `VistaMesaDocumentos` los presenta sobre la mesa. `MoverComponentes` permite arrastrarlos.

#### Sistema de Políticas Fronterizas
`PoliticaFronteriza` define el contrato `esValido(Ingresante)` y un motivo de rechazo: `getMotivoRechazo();`. `Resolutor` recorre sus políticas y permite el ingreso cuando todas se cumplen; devuelve la primera política que falla para explicar una decisión incorrecta.

Al iniciar la partida, `Partida.cargarPoliticasDelDia()` reconstruye el conjunto activo con estas verificaciones:
- Coherencia de nombres y orígenes **entre los documentos**.
- Coincidencia entre el número del DNI y el DNI indicado en el permiso.
- Coincidencia de la raza del ingresante con la declarada en DNI y pasaporte.
- Vigencia de los documentos y fechas de nacimiento que no sean posteriores a la fecha del juego.
- Documentación requerida: DNI, pasaporte y permiso de entrada.
- Restricciones de ciudad, clan y raza cuando la variación elegida devuelve listas de prohibiciones no vacías.
- Hay tres variaciones de reglas del libro de reglas:
  `ReglasVariacion1`: Solo humanos; prohíbe las otras razas definidas en el juego. 
  `ReglasVariacion2`: Prohíbe los orígenes `ZOMBAR` y `NAMEK`. 
  `ReglasVariacion3`: Sin prohibiciones de ciudad, clan o raza; se mantienen las verificaciones documentales.

#### Sistema de Reputación
`Jugador` conserva la reputación y `GestorReputacion` determina su variación según la decisión tomada y el resultado del resolutor. La partida comienza y se reinicia con **10 puntos**, y la reputación se mantiene entre **0 y 10**.
Los aciertos se limitan al máximo de 10 puntos. `ResultadoDecision` reúne si el personaje podía ingresar, si la decisión fue correcta, la reputación resultante, si se perdió la partida y el motivo del error.

### Cómo se Juega
1. Seleccionar **Jugar** en el menú. Se reinicia la reputación, se elige una variación de reglas y la ventanilla queda vacía.
2. Consultar el **libro de reglas** y usar el **megáfono** para llamar al primer ingresante.
3. Pulsar **Interrogar**. La respuesta del personaje habilita la solicitud de documentos; el diálogo visual se oculta automáticamente.
4. Pulsar **Documentos** para desplegarlos en la mesa. Revisar sus datos y contrastarlos con las reglas. Los documentos se pueden arrastrar.
5. Pulsar el **sello** para abrir la decisión. Elegir **Aceptar** o **Rechazar**, o usar **Volver** para continuar la revisión.
6. Esperar la animación del sello. El modelo evalúa la decisión, actualiza la reputación y muestra el resultado. Cerrar el cartel con **Aceptar**.
7. Si quedan ingresantes y la reputación es mayor que cero, volver a usar el megáfono. El siguiente personaje requiere nuevamente interrogatorio y solicitud de documentos.
8. Se obtiene **victoria** al resolver al último ingresante sin quedar en cero. Se obtiene **derrota** cuando la reputación llega a cero, incluso si ese era el último ingresante.
9. Al cerrar el cartel de la última decisión se muestra la pantalla final, desde la que se puede guardar el puntaje o volver al menú.

Las acciones fuera de orden son rechazadas por el estado actual mediante `IllegalStateException`; el controlador presenta el mensaje correspondiente.

---

### Interfaz Gráfica (IGU)
La interfaz se implementa con Java Swing. `MenuPrincipal` presenta Jugar, Tutorial, Puntajes, Salir y el control de música. `Escenario` reúne el puesto, los controles de inspección, el contador de ingresantes restantes y un reloj con la hora real del sistema; ese reloj no limita la duración de la partida.

`VistaTutorial` permite recorrer ocho páginas. `VistaInterrogatorio` muestra el intercambio de diálogo durante cuatro segundos. `VistaDecision` permite aceptar, rechazar o volver a la revisión; `VistaAnimacionSello` representa visualmente la decisión. Los resultados y errores se presentan mediante carteles y anuncios, mientras que `VistaConsola` aporta información de diagnóstico en la terminal.

`GestorVista` coordina las vistas y recibe notificaciones del modelo.

---

### Persistencia
La persistencia está implementada con **SQLite y JDBC**. La configuración principal utiliza `PuntajeDAOSQLite`, que crea la tabla `puntajes` si no existe y guarda nombre del jugador, reputación final y fecha. El ranking muestra hasta cuatro registros, ordenados por reputación descendente.

Desde la pantalla final se puede guardar el puntaje ingresando un nombre. `PuntajeDAOMemoria` ofrece el mismo contrato sobre una lista en memoria como implementación alternativa. El guardado conserva resultados; reanudar una partida en curso queda fuera del alcance actual.

---
 
## 3. Arquitectura y Diseño 
### Patrón de Diseño de Comporatmiento
- Observer
<img width="1441" height="1006" alt="Frontier - Patron Observer" src="https://github.com/user-attachments/assets/7a3810c4-02c1-48d1-96bb-d426fe2dfe4c" />
Aplicamos observer a las notificaciones de los cambios del juego, conectando el Modelo con la Vista sin romper el encapsulamiento. Cuando el Resolutor evalúa un ingresante, o cuando avanza el reloj de la jornada, el Modelo notifica el cambio. La interfaz de Java Swing (que está "observando" esos datos) escucha el aviso y actualiza las barras de reputación, el dinero o el cronómetro automáticamente.

- State
<img width="2015" height="921" alt="Frontier - jerarquia general de Patron State" src="https://github.com/user-attachments/assets/191c3f4c-770f-487f-b0ef-7aee4dc27b9c" />
Usamos state porque el jugador siempre tiene los mismos controles disponibles: megáfono, pasaporte, sello y los botones Aceptar/Rechazar. Lo que cambia es qué debe pasar cuando los usa, según la fase de la atención. Sin state, partida o controladorjuego tendrían un if/switch sobre una bandera en cada acción, y cada fase nueva obligaría a tocar todos esos métodos. Con state, partida actúa como contexto y delega en estadopartida.

### Patrón de Diseño Creacionales
- Simple Factory
<img width="1704" height="1214" alt="Frontier Factory Fabrica Vista de Documentos" src="https://github.com/user-attachments/assets/b14a9d45-5a17-44cf-9e1f-1c97912b2fb3" />
<img width="1740" height="1080" alt="Frontier - Factory Fabrica de Ingresantes" src="https://github.com/user-attachments/assets/4a8ce7d5-a354-44d1-91af-567894980fd4" />
Se implementó este patrón creacional para gestionar la instanciación dinámica de los componentes visuales (Vistas) correspondientes a los Documentos y los Ingresantes. Dado que la variedad de personajes y papeles a renderizar varía constantemente en tiempo de ejecución durante cada turno, la fábrica centraliza la creación de los paneles de Java Swing.

### Patrón de Diseño Estructural (Facade)
- Facade (ControladorJuego con GestorVista).
- Los mismos se detallan en el siguiente documento: [Frontier - Patrones de diseño.docx](https://github.com/user-attachments/files/33466417/Frontier.-.Patrones.de.diseno.docx)

### Diagramas de Diseño
### **Diagrama de Clases UML (General por tema de simplificacion)**
<img width="2690" height="1115" alt="diagrama de clases general UML - arquitectura MVC" src="https://github.com/user-attachments/assets/ac6769a0-d82a-455f-be87-e508a00bf726" />

--

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

