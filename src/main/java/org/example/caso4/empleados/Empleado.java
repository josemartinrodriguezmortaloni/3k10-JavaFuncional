package org.example.caso4.empleados;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un empleado de una empresa.
 * Usa Lombok para generar automáticamente código boilerplate.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {
    private String nombre;
    private String departamento;
    private double salario;
    private int edad;
}

