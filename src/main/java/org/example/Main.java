package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.caso1.alumnos.Alumno;
import org.example.caso1.alumnos.AlumnoService;
import org.example.caso2.productos.Producto;
import org.example.caso2.productos.ProductoService;
import org.example.caso3.libros.Libro;
import org.example.caso3.libros.LibroService;
import org.example.caso4.empleados.Empleado;
import org.example.caso4.empleados.EmpleadoService;

/**
 * Trabajo Practico - Programacion Funcional con Streams en Java
 * 
 * Demostracion ejecutable de operaciones funcionales sobre colecciones
 * aplicando principios SOLID y codigo limpio.
 */
public class Main {
    public static void main(String[] args) {
        printHeader();
        
        demoCaso1Alumnos();
        demoCaso2Productos();
        demoCaso3Libros();
        demoCaso4Empleados();
        
        printFooter();
    }
    
    private static void printHeader() {
        System.out.println("=".repeat(70));
        System.out.println("TRABAJO PRACTICO - PROGRAMACION FUNCIONAL CON STREAMS");
        System.out.println("=".repeat(70));
        System.out.println();
    }
    
    private static void printFooter() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("Demostracion completada exitosamente!");
        System.out.println("Para ejecutar tests completos: ./gradlew test");
        System.out.println("=".repeat(70));
    }
    
    private static void printSection(String title) {
        System.out.println("\n" + "-".repeat(70));
        System.out.println(title);
        System.out.println("-".repeat(70));
    }
    
    // ==================== CASO 1: GESTION DE ALUMNOS ====================
    private static void demoCaso1Alumnos() {
        printSection("CASO 1: GESTION DE ALUMNOS");
        
        AlumnoService service = new AlumnoService();
        List<Alumno> alumnos = Arrays.asList(
            Alumno.builder().nombre("Juan Perez").nota(8.5).curso("1A").build(),
            Alumno.builder().nombre("Maria Garcia").nota(9.2).curso("1A").build(),
            Alumno.builder().nombre("Carlos Lopez").nota(6.5).curso("1B").build(),
            Alumno.builder().nombre("Ana Martinez").nota(7.8).curso("1A").build(),
            Alumno.builder().nombre("Pedro Rodriguez").nota(5.5).curso("1B").build(),
            Alumno.builder().nombre("Laura Fernandez").nota(9.5).curso("1C").build(),
            Alumno.builder().nombre("Diego Sanchez").nota(7.0).curso("1B").build(),
            Alumno.builder().nombre("Sofia Gonzalez").nota(8.0).curso("1C").build()
        );
        
        System.out.println("\n1. Alumnos aprobados (nota >= 7) en mayusculas y ordenados:");
        List<String> aprobados = service.obtenerAprobadosOrdenados(alumnos);
        aprobados.forEach(nombre -> System.out.println("   - " + nombre));
        
        System.out.println("\n2. Promedio general de notas:");
        double promedio = service.calcularPromedioGeneral(alumnos);
        System.out.printf("   Promedio: %.2f%n", promedio);
        
        System.out.println("\n3. Alumnos agrupados por curso:");
        Map<String, List<Alumno>> grupos = service.agruparPorCurso(alumnos);
        grupos.forEach((curso, alums) -> {
            System.out.println("   Curso " + curso + ": " + alums.size() + " alumnos");
            alums.forEach(a -> System.out.printf("      - %s (%.1f)%n", a.getNombre(), a.getNota()));
        });
        
        System.out.println("\n4. Top 3 mejores promedios:");
        List<Alumno> top3 = service.obtenerTop3Promedios(alumnos);
        for (int i = 0; i < top3.size(); i++) {
            Alumno a = top3.get(i);
            System.out.printf("   %d. %s - Nota: %.1f%n", i + 1, a.getNombre(), a.getNota());
        }
    }
    
    // ==================== CASO 2: GESTION DE PRODUCTOS ====================
    private static void demoCaso2Productos() {
        printSection("CASO 2: GESTION DE PRODUCTOS");
        
        ProductoService service = new ProductoService();
        List<Producto> productos = Arrays.asList(
            Producto.builder().nombre("Laptop").categoria("Electronica").precio(1200.0).stock(15).build(),
            Producto.builder().nombre("Mouse").categoria("Electronica").precio(25.0).stock(50).build(),
            Producto.builder().nombre("Monitor").categoria("Electronica").precio(350.0).stock(20).build(),
            Producto.builder().nombre("Camisa").categoria("Ropa").precio(45.0).stock(100).build(),
            Producto.builder().nombre("Zapatos").categoria("Ropa").precio(120.0).stock(40).build(),
            Producto.builder().nombre("Arroz").categoria("Alimentos").precio(15.0).stock(200).build(),
            Producto.builder().nombre("Aceite").categoria("Alimentos").precio(30.0).stock(150).build()
        );
        
        System.out.println("\n1. Productos con precio mayor a $100 (ordenados por precio):");
        List<Producto> caros = service.listarProductosCarosOrdenados(productos);
        caros.forEach(p -> System.out.printf("   - %s: $%.2f%n", p.getNombre(), p.getPrecio()));
        
        System.out.println("\n2. Stock total por categoria:");
        Map<String, Integer> stocks = service.calcularStockPorCategoria(productos);
        stocks.forEach((cat, stock) -> 
            System.out.printf("   %s: %d unidades%n", cat, stock));
        
        System.out.println("\n3. Reporte de productos (formato: nombre;precio):");
        String reporte = service.generarReporteProductos(productos);
        System.out.println("   " + reporte);
        
        System.out.println("\n4. Precio promedio general:");
        double precioPromedio = service.calcularPrecioPromedioGeneral(productos);
        System.out.printf("   $%.2f%n", precioPromedio);
        
        System.out.println("\n5. Precio promedio por categoria:");
        Map<String, Double> promedios = service.calcularPrecioPromedioPorCategoria(productos);
        promedios.forEach((cat, prom) -> 
            System.out.printf("   %s: $%.2f%n", cat, prom));
    }
    
    // ==================== CASO 3: GESTION DE LIBROS ====================
    private static void demoCaso3Libros() {
        printSection("CASO 3: GESTION DE LIBROS");
        
        LibroService service = new LibroService();
        List<Libro> libros = Arrays.asList(
            Libro.builder().titulo("Cien Anos de Soledad").autor("Gabriel Garcia Marquez").paginas(471).precio(45.0).build(),
            Libro.builder().titulo("El Principito").autor("Antoine de Saint-Exupery").paginas(96).precio(20.0).build(),
            Libro.builder().titulo("Don Quijote").autor("Miguel de Cervantes").paginas(863).precio(60.0).build(),
            Libro.builder().titulo("1984").autor("George Orwell").paginas(328).precio(35.0).build(),
            Libro.builder().titulo("Rebelion en la Granja").autor("George Orwell").paginas(144).precio(25.0).build(),
            Libro.builder().titulo("Harry Potter y la Piedra Filosofal").autor("J.K. Rowling").paginas(309).precio(42.0).build(),
            Libro.builder().titulo("El Senor de los Anillos").autor("J.R.R. Tolkien").paginas(1178).precio(80.0).build()
        );
        
        System.out.println("\n1. Libros con mas de 300 paginas (ordenados alfabeticamente):");
        List<String> titulosLargos = service.listarTitulosLibrosLargos(libros);
        titulosLargos.forEach(titulo -> System.out.println("   - " + titulo));
        
        System.out.println("\n2. Promedio de paginas:");
        double promedioPaginas = service.calcularPromedioPaginas(libros);
        System.out.printf("   %.0f paginas%n", promedioPaginas);
        
        System.out.println("\n3. Cantidad de libros por autor:");
        Map<String, Long> librosPorAutor = service.contarLibrosPorAutor(libros);
        librosPorAutor.forEach((autor, cantidad) -> 
            System.out.printf("   %s: %d libro(s)%n", autor, cantidad));
        
        System.out.println("\n4. Libro mas caro:");
        Optional<Libro> masCaro = service.obtenerLibroMasCaro(libros);
        masCaro.ifPresent(libro -> 
            System.out.printf("   %s - $%.2f (%d paginas)%n", 
                libro.getTitulo(), libro.getPrecio(), libro.getPaginas()));
    }
    
    // ==================== CASO 4: GESTION DE EMPLEADOS ====================
    private static void demoCaso4Empleados() {
        printSection("CASO 4: GESTION DE EMPLEADOS");
        
        EmpleadoService service = new EmpleadoService();
        List<Empleado> empleados = Arrays.asList(
            Empleado.builder().nombre("Juan Perez").departamento("IT").salario(3500.0).edad(28).build(),
            Empleado.builder().nombre("Maria Garcia").departamento("IT").salario(4200.0).edad(32).build(),
            Empleado.builder().nombre("Carlos Lopez").departamento("Ventas").salario(2800.0).edad(25).build(),
            Empleado.builder().nombre("Ana Martinez").departamento("Ventas").salario(1800.0).edad(23).build(),
            Empleado.builder().nombre("Pedro Rodriguez").departamento("RRHH").salario(3000.0).edad(35).build(),
            Empleado.builder().nombre("Laura Fernandez").departamento("Finanzas").salario(3800.0).edad(29).build(),
            Empleado.builder().nombre("Diego Sanchez").departamento("IT").salario(3200.0).edad(26).build(),
            Empleado.builder().nombre("Sofia Gonzalez").departamento("Ventas").salario(2500.0).edad(30).build()
        );
        
        System.out.println("\n1. Empleados con salario mayor a $2000 (ordenados por salario):");
        List<Empleado> salarioAlto = service.listarEmpleadosSalarioAlto(empleados);
        salarioAlto.forEach(e -> 
            System.out.printf("   - %s (%s): $%.2f%n", e.getNombre(), e.getDepartamento(), e.getSalario()));
        
        System.out.println("\n2. Salario promedio general:");
        double salarioPromedio = service.calcularSalarioPromedio(empleados);
        System.out.printf("   $%.2f%n", salarioPromedio);
        
        System.out.println("\n3. Suma de salarios por departamento:");
        Map<String, Double> salariosPorDepto = service.calcularSalariosPorDepartamento(empleados);
        salariosPorDepto.forEach((depto, total) -> 
            System.out.printf("   %s: $%.2f%n", depto, total));
        
        System.out.println("\n4. Los 2 empleados mas jovenes:");
        List<String> masJovenes = service.obtenerEmpleadosMasJovenes(empleados);
        for (int i = 0; i < masJovenes.size(); i++) {
            System.out.printf("   %d. %s%n", i + 1, masJovenes.get(i));
        }
    }
}