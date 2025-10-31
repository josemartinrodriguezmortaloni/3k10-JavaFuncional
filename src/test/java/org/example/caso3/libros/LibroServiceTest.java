package org.example.caso3.libros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests para verificar operaciones funcionales sobre libros.
 */
class LibroServiceTest {
    
    private LibroService service;
    private List<Libro> libros;
    
    @BeforeEach
    void setUp() {
        service = new LibroService();
        
        // Dataset con autores repetidos para testing de agrupación
        libros = Arrays.asList(
                Libro.builder().titulo("Cien Años de Soledad").autor("Gabriel García Márquez").paginas(471).precio(45.0).build(),
                Libro.builder().titulo("El Principito").autor("Antoine de Saint-Exupéry").paginas(96).precio(20.0).build(),
                Libro.builder().titulo("Don Quijote").autor("Miguel de Cervantes").paginas(863).precio(60.0).build(),
                Libro.builder().titulo("1984").autor("George Orwell").paginas(328).precio(35.0).build(),
                Libro.builder().titulo("Rebelión en la Granja").autor("George Orwell").paginas(144).precio(25.0).build(),
                Libro.builder().titulo("El Amor en los Tiempos del Cólera").autor("Gabriel García Márquez").paginas(368).precio(50.0).build(),
                Libro.builder().titulo("Crónica de una Muerte Anunciada").autor("Gabriel García Márquez").paginas(120).precio(30.0).build(),
                Libro.builder().titulo("Harry Potter y la Piedra Filosofal").autor("J.K. Rowling").paginas(309).precio(42.0).build(),
                Libro.builder().titulo("Harry Potter y el Cáliz de Fuego").autor("J.K. Rowling").paginas(636).precio(55.0).build(),
                Libro.builder().titulo("El Señor de los Anillos").autor("J.R.R. Tolkien").paginas(1178).precio(80.0).build()
        );
    }
    
    @Test
    void testListarTitulosLibrosLargos() {
        List<String> titulosLargos = service.listarTitulosLibrosLargos(libros);
        
        // Verificar que todos tienen más de 300 páginas
        assertEquals(7, titulosLargos.size());
        
        // Verificar ordenamiento alfabético
        assertEquals("1984", titulosLargos.get(0));
        assertEquals("Cien Años de Soledad", titulosLargos.get(1));
        assertEquals("Don Quijote", titulosLargos.get(2));
        assertEquals("El Amor en los Tiempos del Cólera", titulosLargos.get(3));
        assertEquals("El Señor de los Anillos", titulosLargos.get(4));
        assertEquals("Harry Potter y el Cáliz de Fuego", titulosLargos.get(5));
        assertEquals("Harry Potter y la Piedra Filosofal", titulosLargos.get(6));
        
        // Verificar que no incluye libros cortos
        assertFalse(titulosLargos.contains("El Principito")); // 96 páginas
        assertFalse(titulosLargos.contains("Rebelión en la Granja")); // 144 páginas
    }
    
    @Test
    void testCalcularPromedioPaginas() {
        double promedio = service.calcularPromedioPaginas(libros);
        
        // Promedio: (471+96+863+328+144+368+120+309+636+1178)/10 = 451.3
        assertEquals(451.3, promedio, 0.1);
    }
    
    @Test
    void testCalcularPromedioPaginasListaVacia() {
        double promedio = service.calcularPromedioPaginas(Arrays.asList());
        assertEquals(0.0, promedio);
    }
    
    @Test
    void testContarLibrosPorAutor() {
        Map<String, Long> librosPorAutor = service.contarLibrosPorAutor(libros);
        
        // Verificar cantidad de autores
        assertEquals(6, librosPorAutor.size());
        
        // Verificar conteos específicos
        assertEquals(3, librosPorAutor.get("Gabriel García Márquez"));
        assertEquals(2, librosPorAutor.get("George Orwell"));
        assertEquals(2, librosPorAutor.get("J.K. Rowling"));
        assertEquals(1, librosPorAutor.get("Miguel de Cervantes"));
        assertEquals(1, librosPorAutor.get("Antoine de Saint-Exupéry"));
        assertEquals(1, librosPorAutor.get("J.R.R. Tolkien"));
    }
    
    @Test
    void testObtenerLibroMasCaro() {
        Optional<Libro> libroMasCaro = service.obtenerLibroMasCaro(libros);
        
        // Verificar que existe
        assertTrue(libroMasCaro.isPresent());
        
        // Verificar que es el libro correcto
        assertEquals("El Señor de los Anillos", libroMasCaro.get().getTitulo());
        assertEquals(80.0, libroMasCaro.get().getPrecio());
        assertEquals("J.R.R. Tolkien", libroMasCaro.get().getAutor());
    }
    
    @Test
    void testObtenerLibroMasCaroListaVacia() {
        Optional<Libro> libroMasCaro = service.obtenerLibroMasCaro(Arrays.asList());
        
        // Verificar que devuelve Optional vacío
        assertFalse(libroMasCaro.isPresent());
    }
    
    @Test
    void testObtenerLibroMasCaroConVariosLibros() {
        // Verificar que el método encuentra correctamente el máximo entre varios precios
        List<Libro> librosTest = Arrays.asList(
                Libro.builder().titulo("Libro A").autor("Autor A").paginas(200).precio(10.0).build(),
                Libro.builder().titulo("Libro B").autor("Autor B").paginas(300).precio(50.0).build(),
                Libro.builder().titulo("Libro C").autor("Autor C").paginas(400).precio(30.0).build()
        );
        
        Optional<Libro> libroMasCaro = service.obtenerLibroMasCaro(librosTest);
        
        assertTrue(libroMasCaro.isPresent());
        assertEquals("Libro B", libroMasCaro.get().getTitulo());
        assertEquals(50.0, libroMasCaro.get().getPrecio());
    }
}

