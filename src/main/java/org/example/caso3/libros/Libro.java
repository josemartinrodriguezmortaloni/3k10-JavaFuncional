package org.example.caso3.libros;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un libro de biblioteca.
 * Usa Lombok para generar automáticamente código boilerplate.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {
    private String titulo;
    private String autor;
    private int paginas;
    private double precio;
}

