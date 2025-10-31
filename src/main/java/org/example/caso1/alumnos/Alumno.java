package org.example.caso1.alumnos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un alumno con su información académica.
 * Usa Lombok para generar automáticamente getters, setters, toString, equals y hashCode.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alumno {
    private String nombre;
    private double nota;
    private String curso;
}

