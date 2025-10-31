package org.example.caso2.productos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests para verificar operaciones funcionales sobre productos.
 */
class ProductoServiceTest {
    
    private ProductoService service;
    private List<Producto> productos;
    
    @BeforeEach
    void setUp() {
        service = new ProductoService();
        
        // Dataset con múltiples categorías: Electrónica, Ropa, Alimentos
        productos = Arrays.asList(
                Producto.builder().nombre("Laptop").categoria("Electrónica").precio(1200.0).stock(15).build(),
                Producto.builder().nombre("Mouse").categoria("Electrónica").precio(25.0).stock(50).build(),
                Producto.builder().nombre("Teclado").categoria("Electrónica").precio(80.0).stock(30).build(),
                Producto.builder().nombre("Monitor").categoria("Electrónica").precio(350.0).stock(20).build(),
                Producto.builder().nombre("Camisa").categoria("Ropa").precio(45.0).stock(100).build(),
                Producto.builder().nombre("Pantalón").categoria("Ropa").precio(65.0).stock(80).build(),
                Producto.builder().nombre("Zapatos").categoria("Ropa").precio(120.0).stock(40).build(),
                Producto.builder().nombre("Arroz").categoria("Alimentos").precio(15.0).stock(200).build(),
                Producto.builder().nombre("Aceite").categoria("Alimentos").precio(30.0).stock(150).build(),
                Producto.builder().nombre("Smartphone").categoria("Electrónica").precio(800.0).stock(25).build()
        );
    }
    
    @Test
    void testListarProductosCarosOrdenados() {
        List<Producto> productosCaros = service.listarProductosCarosOrdenados(productos);
        
        // Verificar que todos tienen precio > 100
        assertTrue(productosCaros.stream()
                .allMatch(producto -> producto.getPrecio() > 100));
        
        // Verificar cantidad correcta
        assertEquals(4, productosCaros.size());
        
        // Verificar ordenamiento descendente
        assertEquals("Laptop", productosCaros.get(0).getNombre());
        assertEquals(1200.0, productosCaros.get(0).getPrecio());
        
        assertEquals("Smartphone", productosCaros.get(1).getNombre());
        assertEquals(800.0, productosCaros.get(1).getPrecio());
        
        assertEquals("Monitor", productosCaros.get(2).getNombre());
        assertEquals(350.0, productosCaros.get(2).getPrecio());
        
        assertEquals("Zapatos", productosCaros.get(3).getNombre());
        assertEquals(120.0, productosCaros.get(3).getPrecio());
        
        // Verificar que están ordenados descendentemente
        for (int i = 0; i < productosCaros.size() - 1; i++) {
            assertTrue(productosCaros.get(i).getPrecio() >= productosCaros.get(i + 1).getPrecio());
        }
    }
    
    @Test
    void testCalcularStockPorCategoria() {
        Map<String, Integer> stockPorCategoria = service.calcularStockPorCategoria(productos);
        
        // Verificar que hay 3 categorías
        assertEquals(3, stockPorCategoria.size());
        
        // Verificar stocks totales
        // Electrónica: 15 + 50 + 30 + 20 + 25 = 140
        assertEquals(140, stockPorCategoria.get("Electrónica"));
        
        // Ropa: 100 + 80 + 40 = 220
        assertEquals(220, stockPorCategoria.get("Ropa"));
        
        // Alimentos: 200 + 150 = 350
        assertEquals(350, stockPorCategoria.get("Alimentos"));
    }
    
    @Test
    void testGenerarReporteProductos() {
        String reporte = service.generarReporteProductos(productos);
        
        // Verificar que contiene el formato correcto
        assertTrue(reporte.contains("Laptop;1200.0"));
        assertTrue(reporte.contains("Mouse;25.0"));
        assertTrue(reporte.contains("Smartphone;800.0"));
        
        // Verificar que usa ";" como separador
        String[] partes = reporte.split(";");
        assertEquals(20, partes.length); // 10 productos x 2 campos (nombre y precio)
        
        // Verificar estructura: debe alternar entre nombre y precio
        assertTrue(reporte.startsWith("Laptop;1200.0"));
    }
    
    @Test
    void testCalcularPrecioPromedioGeneral() {
        double promedio = service.calcularPrecioPromedioGeneral(productos);
        
        // Promedio: (1200+25+80+350+45+65+120+15+30+800)/10 = 273.0
        assertEquals(273.0, promedio, 0.01);
    }
    
    @Test
    void testCalcularPrecioPromedioGeneralListaVacia() {
        double promedio = service.calcularPrecioPromedioGeneral(Arrays.asList());
        assertEquals(0.0, promedio);
    }
    
    @Test
    void testCalcularPrecioPromedioPorCategoria() {
        Map<String, Double> promedioPorCategoria = service.calcularPrecioPromedioPorCategoria(productos);
        
        // Verificar que hay 3 categorías
        assertEquals(3, promedioPorCategoria.size());
        
        // Electrónica: (1200 + 25 + 80 + 350 + 800) / 5 = 491.0
        assertEquals(491.0, promedioPorCategoria.get("Electrónica"), 0.01);
        
        // Ropa: (45 + 65 + 120) / 3 = 76.67
        assertEquals(76.67, promedioPorCategoria.get("Ropa"), 0.01);
        
        // Alimentos: (15 + 30) / 2 = 22.5
        assertEquals(22.5, promedioPorCategoria.get("Alimentos"), 0.01);
    }
}

