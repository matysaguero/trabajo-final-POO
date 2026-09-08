# Proyecto: Puzzle/Simulación - Frontier
Se aplicó el Polimorfismo en las clase abstacta Ingresante, en la lista de Documentos presente como atributo y en los comportamientos abstractos "responderInterrogatorio" y "darPresentacion".

En las clases hijas de Ingresante, siendo Enemigo y Politico (próxima a desarrollar), se sobreescriben los comportamientos ya mencionados en base al carácter de los personajes con los que va a interactuar el jugador. 
Por ahora, los comportamientos de ambos se diferencian únicamente en los diálogos que ofrecen y que aparecerán en pantalla, pero la idea es que a partir de ello, la decisión que tome el jugador con respecto a dejar pasar o no a Enemigo o a Político, interactúe de cierta manera con el Escenario.