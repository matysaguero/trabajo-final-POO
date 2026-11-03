package modelo.documentos;

public enum RequisitoDocumental {
    DNI,
    PASAPORTE,
    PERMISO
}
/* 
Propósito: representar requisitos del negocio, no tipos de Java. 
Antes no existía una estructura equivalente; las políticas preguntaban directamente 
si un objeto era DNI, Pasaporte o Permiso.

Una política podrá preguntar si un ingresante acredita un requisito documental 
sin preguntar de qué clase es el documento que lo acredita.
*/

