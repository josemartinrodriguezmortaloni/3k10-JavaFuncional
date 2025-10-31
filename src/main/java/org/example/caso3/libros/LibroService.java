package org.example.caso3.libros;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio que implementa operaciones funcionales sobre libros.
 * Demuestra el manejo de promedios, agrupación y valores máximos con Streams.
 */
public class LibroService {
    
    /**
     * Lista los títulos de libros con más de 300 páginas, ordenados alfabéticamente.
     * 
     * @param libros lista de libros
     * @return lista de títulos ordenados alfabéticamente
     */
    public List<String> listarTitulosLibrosLargos(List<Libro> libros) {
        return libros.stream()
                .filter(libro -> libro.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .collect(Collectors.toList());
    }
    
    /**
     * Calcula el promedio de páginas de todos los libros.
     * 
     * @param libros lista de libros
     * @return promedio de páginas, o 0.0 si la lista está vacía
     */
    public double calcularPromedioPaginas(List<Libro> libros) {
        return libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
    }
    
    /**
     * Agrupa los libros por autor y cuenta cuántos libros tiene cada uno.
     * 
     * @param libros lista de libros
     * @return mapa con autor y cantidad de libros
     */
    public Map<String, Long> contarLibrosPorAutor(List<Libro> libros) {
        return libros.stream()
                .collect(Collectors.groupingBy(
                        Libro::getAutor,
                        Collectors.counting()
                ));
    }
    
    /**
     * Obtiene el libro más caro de la lista.
     * 
     * @param libros lista de libros
     * @return Optional con el libro más caro, o empty si la lista está vacía
     */
    public Optional<Libro> obtenerLibroMasCaro(List<Libro> libros) {
        return libros.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio));
    }
}

