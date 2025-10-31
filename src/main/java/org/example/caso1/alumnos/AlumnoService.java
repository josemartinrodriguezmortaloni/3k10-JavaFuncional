package org.example.caso1.alumnos;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Servicio que implementa operaciones funcionales sobre colecciones de alumnos.
 * Aplica el patrón de programación funcional usando Streams de Java.
 */
public class AlumnoService {
    
    /**
     * Obtiene los nombres de alumnos aprobados (nota >= 7) en mayúsculas y ordenados alfabéticamente.
     * 
     * @param alumnos lista de alumnos a procesar
     * @return lista de nombres en mayúsculas ordenados
     */
    public List<String> obtenerAprobadosOrdenados(List<Alumno> alumnos) {
        return alumnos.stream()
                .filter(alumno -> alumno.getNota() >= 7.0)
                .map(alumno -> alumno.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }
    
    /**
     * Calcula el promedio general de todas las notas.
     * 
     * @param alumnos lista de alumnos
     * @return promedio de notas, o 0.0 si la lista está vacía
     */
    public double calcularPromedioGeneral(List<Alumno> alumnos) {
        return alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);
    }
    
    /**
     * Agrupa los alumnos por curso.
     * 
     * @param alumnos lista de alumnos
     * @return mapa con curso como clave y lista de alumnos como valor
     */
    public Map<String, List<Alumno>> agruparPorCurso(List<Alumno> alumnos) {
        return alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
    }
    
    /**
     * Obtiene los 3 alumnos con mejores promedios (notas más altas).
     * 
     * @param alumnos lista de alumnos
     * @return lista con los 3 alumnos de mejor nota
     */
    public List<Alumno> obtenerTop3Promedios(List<Alumno> alumnos) {
        return alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
}

