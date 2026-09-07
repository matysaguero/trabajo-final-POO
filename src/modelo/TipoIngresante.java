package modelo;

public enum TipoIngresante {
    CIUDADANO,
    TURISTA,
    TRABAJADOR,
    ALIADO
}
//Se utiliza un enumerado para representar los tipos 
// distintos de ingresantes "buenos" posibles sin la 
// necesidad de realizar herencia que sea redundante. 
// Esto es porque los distintos tipos de ingresantes "buenos" no 
// tienen diferencias en su comportamiento o atributos, solo en su tipo. 
//25/8/26 21:00 pm.