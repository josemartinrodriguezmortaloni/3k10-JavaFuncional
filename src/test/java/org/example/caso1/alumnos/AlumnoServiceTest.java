package org.example.caso1.alumnos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests para verificar las operaciones funcionales sobre alumnos.
 */
class AlumnoServiceTest {
    
    private AlumnoService service;
    private List<Alumno> alumnos;
    
    @BeforeEach
    void setUp() {
        service = new AlumnoService();
        
        // Dataset de prueba con 12 alumnos de diferentes cursos y notas
        alumnos = Arrays.asList(
                Alumno.builder().nombre("Juan Pérez").nota(8.5).curso("1A").build(),
                Alumno.builder().nombre("María García").nota(9.2).curso("1A").build(),
                Alumno.builder().nombre("Carlos López").nota(6.5).curso("1B").build(),
                Alumno.builder().nombre("Ana Martínez").nota(7.8).curso("1A").build(),
                Alumno.builder().nombre("Pedro Rodríguez").nota(5.5).curso("1B").build(),
                Alumno.builder().nombre("Laura Fernández").nota(9.5).curso("1C").build(),
                Alumno.builder().nombre("Diego Sánchez").nota(7.0).curso("1B").build(),
                Alumno.builder().nombre("Sofia González").nota(8.0).curso("1C").build(),
                Alumno.builder().nombre("Lucas Torres").nota(6.0).curso("1A").build(),
                Alumno.builder().nombre("Valentina Díaz").nota(9.8).curso("1C").build(),
                Alumno.builder().nombre("Mateo Ruiz").nota(7.5).curso("1B").build(),
                Alumno.builder().nombre("Emma Morales").nota(8.8).curso("1A").build()
        );
    }
    
    @Test
    void testObtenerAprobadosOrdenados() {
        List<String> aprobados = service.obtenerAprobadosOrdenados(alumnos);
        
        // Verificar que solo hay alumnos con nota >= 7.0
        assertEquals(9, aprobados.size());
        
        // Verificar que están en mayúsculas
        assertTrue(aprobados.stream().allMatch(nombre -> nombre.equals(nombre.toUpperCase())));
        
        // Verificar ordenamiento alfabético
        assertEquals("ANA MARTÍNEZ", aprobados.get(0));
        assertEquals("DIEGO SÁNCHEZ", aprobados.get(1));
        assertEquals("EMMA MORALES", aprobados.get(2));
        
        // Verificar que no incluye desaprobados
        assertFalse(aprobados.contains("CARLOS LÓPEZ")); // nota 6.5
        assertFalse(aprobados.contains("PEDRO RODRÍGUEZ")); // nota 5.5
    }
    
    @Test
    void testCalcularPromedioGeneral() {
        double promedio = service.calcularPromedioGeneral(alumnos);
        
        // Promedio esperado: (8.5+9.2+6.5+7.8+5.5+9.5+7.0+8.0+6.0+9.8+7.5+8.8)/12 = 7.84
        assertEquals(7.84, promedio, 0.01);
    }
    
    @Test
    void testCalcularPromedioGeneralListaVacia() {
        double promedio = service.calcularPromedioGeneral(Arrays.asList());
        
        assertEquals(0.0, promedio);
    }
    
    @Test
    void testAgruparPorCurso() {
        Map<String, List<Alumno>> grupos = service.agruparPorCurso(alumnos);
        
        // Verificar que hay 3 cursos
        assertEquals(3, grupos.size());
        assertTrue(grupos.containsKey("1A"));
        assertTrue(grupos.containsKey("1B"));
        assertTrue(grupos.containsKey("1C"));
        
        // Verificar cantidad de alumnos por curso
        assertEquals(5, grupos.get("1A").size());
        assertEquals(4, grupos.get("1B").size());
        assertEquals(3, grupos.get("1C").size());
        
        // Verificar que los alumnos están en el curso correcto
        assertTrue(grupos.get("1A").stream()
                .allMatch(alumno -> alumno.getCurso().equals("1A")));
    }
    
    @Test
    void testObtenerTop3Promedios() {
        List<Alumno> top3 = service.obtenerTop3Promedios(alumnos);
        
        // Verificar que devuelve exactamente 3 alumnos
        assertEquals(3, top3.size());
        
        // Verificar que están ordenados por nota descendente
        assertEquals("Valentina Díaz", top3.get(0).getNombre());
        assertEquals(9.8, top3.get(0).getNota());
        
        assertEquals("Laura Fernández", top3.get(1).getNombre());
        assertEquals(9.5, top3.get(1).getNota());
        
        assertEquals("María García", top3.get(2).getNombre());
        assertEquals(9.2, top3.get(2).getNota());
        
        // Verificar ordenamiento descendente
        assertTrue(top3.get(0).getNota() >= top3.get(1).getNota());
        assertTrue(top3.get(1).getNota() >= top3.get(2).getNota());
    }
    
    @Test
    void testObtenerTop3PromediosConMenosDe3Alumnos() {
        List<Alumno> dosAlumnos = Arrays.asList(
                Alumno.builder().nombre("Alumno 1").nota(8.0).curso("1A").build(),
                Alumno.builder().nombre("Alumno 2").nota(7.0).curso("1A").build()
        );
        
        List<Alumno> top3 = service.obtenerTop3Promedios(dosAlumnos);
        
        // Debe devolver solo 2 alumnos si no hay más
        assertEquals(2, top3.size());
    }
}

