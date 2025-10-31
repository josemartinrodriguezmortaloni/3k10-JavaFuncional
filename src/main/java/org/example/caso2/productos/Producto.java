package org.example.caso2.productos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un producto de inventario.
 * Usa Lombok para generar automáticamente código boilerplate.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;
}

