package org.example.caso2.productos;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Servicio que implementa operaciones funcionales sobre productos.
 * Demuestra el uso de Streams para generar reportes y estadísticas.
 */
public class ProductoService {
    
    /**
     * Lista productos con precio mayor a 100, ordenados por precio descendente.
     * 
     * @param productos lista de productos
     * @return lista de productos caros ordenados de mayor a menor precio
     */
    public List<Producto> listarProductosCarosOrdenados(List<Producto> productos) {
        return productos.stream()
                .filter(producto -> producto.getPrecio() > 100)
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .collect(Collectors.toList());
    }
    
    /**
     * Agrupa productos por categoría y calcula el stock total de cada una.
     * 
     * @param productos lista de productos
     * @return mapa con categoría y stock total
     */
    public Map<String, Integer> calcularStockPorCategoria(List<Producto> productos) {
        return productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
    }
    
    /**
     * Genera un reporte de productos en formato String separado por ";".
     * Cada entrada contiene: nombre;precio
     * 
     * @param productos lista de productos
     * @return String con formato "NombreProducto1;Precio1;NombreProducto2;Precio2..."
     */
    public String generarReporteProductos(List<Producto> productos) {
        return productos.stream()
                .map(producto -> producto.getNombre() + ";" + producto.getPrecio())
                .collect(Collectors.joining(";"));
    }
    
    /**
     * Calcula el precio promedio de todos los productos.
     * 
     * @param productos lista de productos
     * @return precio promedio, o 0.0 si la lista está vacía
     */
    public double calcularPrecioPromedioGeneral(List<Producto> productos) {
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
    }
    
    /**
     * Calcula el precio promedio por cada categoría.
     * 
     * @param productos lista de productos
     * @return mapa con categoría y precio promedio
     */
    public Map<String, Double> calcularPrecioPromedioPorCategoria(List<Producto> productos) {
        return productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
    }
}

