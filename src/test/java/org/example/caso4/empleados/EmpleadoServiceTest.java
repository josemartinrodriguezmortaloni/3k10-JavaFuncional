package org.example.caso4.empleados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests para verificar operaciones funcionales sobre empleados.
 */
class EmpleadoServiceTest {
    
    private EmpleadoService service;
    private List<Empleado> empleados;
    
    @BeforeEach
    void setUp() {
        service = new EmpleadoService();
        
        // Dataset con múltiples departamentos: IT, Ventas, RRHH, Finanzas
        empleados = Arrays.asList(
                Empleado.builder().nombre("Juan Pérez").departamento("IT").salario(3500.0).edad(28).build(),
                Empleado.builder().nombre("María García").departamento("IT").salario(4200.0).edad(32).build(),
                Empleado.builder().nombre("Carlos López").departamento("Ventas").salario(2800.0).edad(25).build(),
                Empleado.builder().nombre("Ana Martínez").departamento("Ventas").salario(2500.0).edad(30).build(),
                Empleado.builder().nombre("Pedro Rodríguez").departamento("RRHH").salario(3000.0).edad(35).build(),
                Empleado.builder().nombre("Laura Fernández").departamento("Finanzas").salario(3800.0).edad(29).build(),
                Empleado.builder().nombre("Diego Sánchez").departamento("IT").salario(3200.0).edad(26).build(),
                Empleado.builder().nombre("Sofia González").departamento("Ventas").salario(1800.0).edad(23).build(),
                Empleado.builder().nombre("Lucas Torres").departamento("RRHH").salario(2700.0).edad(31).build(),
                Empleado.builder().nombre("Valentina Díaz").departamento("Finanzas").salario(4500.0).edad(27).build(),
                Empleado.builder().nombre("Mateo Ruiz").departamento("IT").salario(2900.0).edad(24).build(),
                Empleado.builder().nombre("Emma Morales").departamento("Ventas").salario(2200.0).edad(33).build()
        );
    }
    
    @Test
    void testListarEmpleadosSalarioAlto() {
        List<Empleado> empleadosSalarioAlto = service.listarEmpleadosSalarioAlto(empleados);
        
        // Verificar que todos tienen salario > 2000
        assertTrue(empleadosSalarioAlto.stream()
                .allMatch(empleado -> empleado.getSalario() > 2000));
        
        // Verificar cantidad correcta (11 empleados tienen salario > 2000)
        assertEquals(11, empleadosSalarioAlto.size());
        
        // Verificar ordenamiento descendente
        assertEquals("Valentina Díaz", empleadosSalarioAlto.get(0).getNombre());
        assertEquals(4500.0, empleadosSalarioAlto.get(0).getSalario());
        
        assertEquals("María García", empleadosSalarioAlto.get(1).getNombre());
        assertEquals(4200.0, empleadosSalarioAlto.get(1).getSalario());
        
        assertEquals("Laura Fernández", empleadosSalarioAlto.get(2).getNombre());
        assertEquals(3800.0, empleadosSalarioAlto.get(2).getSalario());
        
        // Verificar que están ordenados descendentemente
        for (int i = 0; i < empleadosSalarioAlto.size() - 1; i++) {
            assertTrue(empleadosSalarioAlto.get(i).getSalario() >= 
                      empleadosSalarioAlto.get(i + 1).getSalario());
        }
        
        // Verificar que no incluye empleados con salario <= 2000
        assertFalse(empleadosSalarioAlto.stream()
                .anyMatch(e -> e.getNombre().equals("Sofia González"))); // salario 1800
    }
    
    @Test
    void testCalcularSalarioPromedio() {
        double promedio = service.calcularSalarioPromedio(empleados);
        
        // Promedio: (3500+4200+2800+2500+3000+3800+3200+1800+2700+4500+2900+2200)/12 = 3091.67
        assertEquals(3091.67, promedio, 0.01);
    }
    
    @Test
    void testCalcularSalarioPromedioListaVacia() {
        double promedio = service.calcularSalarioPromedio(Arrays.asList());
        assertEquals(0.0, promedio);
    }
    
    @Test
    void testCalcularSalariosPorDepartamento() {
        Map<String, Double> salariosPorDepartamento = service.calcularSalariosPorDepartamento(empleados);
        
        // Verificar que hay 4 departamentos
        assertEquals(4, salariosPorDepartamento.size());
        
        // Verificar sumas totales
        // IT: 3500 + 4200 + 3200 + 2900 = 13800
        assertEquals(13800.0, salariosPorDepartamento.get("IT"), 0.01);
        
        // Ventas: 2800 + 2500 + 1800 + 2200 = 9300
        assertEquals(9300.0, salariosPorDepartamento.get("Ventas"), 0.01);
        
        // RRHH: 3000 + 2700 = 5700
        assertEquals(5700.0, salariosPorDepartamento.get("RRHH"), 0.01);
        
        // Finanzas: 3800 + 4500 = 8300
        assertEquals(8300.0, salariosPorDepartamento.get("Finanzas"), 0.01);
    }
    
    @Test
    void testObtenerEmpleadosMasJovenes() {
        List<String> empleadosMasJovenes = service.obtenerEmpleadosMasJovenes(empleados);
        
        // Verificar que devuelve exactamente 2 empleados
        assertEquals(2, empleadosMasJovenes.size());
        
        // Verificar que son los más jóvenes (23 y 24 años)
        assertEquals("Sofia González", empleadosMasJovenes.get(0)); // edad 23
        assertEquals("Mateo Ruiz", empleadosMasJovenes.get(1)); // edad 24
    }
    
    @Test
    void testObtenerEmpleadosMasJovenesConMenosDe2Empleados() {
        List<Empleado> unEmpleado = Arrays.asList(
                Empleado.builder().nombre("Empleado 1").departamento("IT").salario(3000.0).edad(30).build()
        );
        
        List<String> empleadosMasJovenes = service.obtenerEmpleadosMasJovenes(unEmpleado);
        
        // Debe devolver solo 1 empleado si no hay más
        assertEquals(1, empleadosMasJovenes.size());
        assertEquals("Empleado 1", empleadosMasJovenes.get(0));
    }
    
    @Test
    void testCalcularSalariosPorDepartamentoVerificaCollector() {
        // Test adicional para verificar que el collector suma correctamente
        List<Empleado> empleadosTest = Arrays.asList(
                Empleado.builder().nombre("Emp1").departamento("IT").salario(1000.0).edad(25).build(),
                Empleado.builder().nombre("Emp2").departamento("IT").salario(1500.0).edad(26).build(),
                Empleado.builder().nombre("Emp3").departamento("Ventas").salario(2000.0).edad(27).build()
        );
        
        Map<String, Double> salarios = service.calcularSalariosPorDepartamento(empleadosTest);
        
        assertEquals(2500.0, salarios.get("IT"), 0.01);
        assertEquals(2000.0, salarios.get("Ventas"), 0.01);
    }
}

