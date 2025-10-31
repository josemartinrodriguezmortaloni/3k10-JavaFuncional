package org.example.caso4.empleados;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Servicio que implementa operaciones funcionales sobre empleados.
 * Demuestra el uso de groupingBy con downstream collectors y operaciones de límite.
 */
public class EmpleadoService {
    
    /**
     * Lista empleados con salario mayor a 2000, ordenados por salario descendente.
     * 
     * @param empleados lista de empleados
     * @return lista de empleados con salario alto ordenados
     */
    public List<Empleado> listarEmpleadosSalarioAlto(List<Empleado> empleados) {
        return empleados.stream()
                .filter(empleado -> empleado.getSalario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .collect(Collectors.toList());
    }
    
    /**
     * Calcula el salario promedio general de todos los empleados.
     * 
     * @param empleados lista de empleados
     * @return salario promedio, o 0.0 si la lista está vacía
     */
    public double calcularSalarioPromedio(List<Empleado> empleados) {
        return empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
    }
    
    /**
     * Agrupa empleados por departamento y calcula la suma de salarios de cada uno.
     * 
     * @param empleados lista de empleados
     * @return mapa con departamento y suma total de salarios
     */
    public Map<String, Double> calcularSalariosPorDepartamento(List<Empleado> empleados) {
        return empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
    }
    
    /**
     * Obtiene los nombres de los 2 empleados más jóvenes.
     * 
     * @param empleados lista de empleados
     * @return lista con los nombres de los 2 empleados más jóvenes
     */
    public List<String> obtenerEmpleadosMasJovenes(List<Empleado> empleados) {
        return empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .collect(Collectors.toList());
    }
}

