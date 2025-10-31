# Trabajo Practico - Programacion Funcional con Streams

Trabajo realizado por **Jose Martin Rodriguez Mortaloni**

🔗 [Repositorio GitHub](https://github.com/josemartinrodriguezmortaloni/3k10-JavaFuncional)

---

## 📋 Descripcion

Este proyecto implementa **4 casos practicos** de programacion funcional utilizando **Java Streams**, demostrando el uso de operaciones intermedias y terminales para procesar colecciones de forma declarativa.

### Tecnologias Utilizadas

- ☕ **Java 21**
- 🔨 **Gradle 8.13**
- 🏗️ **Lombok 1.18.30** - Reduccion de codigo boilerplate
- ✅ **JUnit 5** - Testing completo

---

## 🚀 Como Ejecutar

### Opcion 1: Ejecutar la Aplicacion Principal

Muestra los resultados de todas las operaciones de los 4 casos practicos:

```bash
./gradlew run
```

### Opcion 2: Ejecutar los Tests

Ejecuta 26 tests que validan todas las operaciones:

```bash
./gradlew test
```

### Opcion 3: Ver Reporte de Tests

Genera un reporte HTML con los resultados:

```bash
./gradlew test
# El reporte se genera en: build/reports/tests/test/index.html
```

---

## 📚 Casos Practicos Implementados

### Caso 1: Gestion de Alumnos 🎓

**Clase:** `Alumno(nombre, nota, curso)`

**Operaciones:**

1. Obtener alumnos aprobados (nota >= 7) en mayusculas y ordenados alfabeticamente
2. Calcular el promedio general de notas
3. Agrupar alumnos por curso
4. Obtener los 3 alumnos con mejores notas

**Conceptos aplicados:**

- `filter()` - Filtrado de aprobados
- `map()` - Transformacion a mayusculas
- `sorted()` - Ordenamiento alfabetico
- `average()` - Calculo de promedio
- `groupingBy()` - Agrupacion por curso
- `limit()` - Limitacion a top 3

**Ubicacion:** `org.example.caso1.alumnos`

---

### Caso 2: Gestion de Productos 🛒

**Clase:** `Producto(nombre, categoria, precio, stock)`

**Operaciones:**

1. Listar productos con precio mayor a $100, ordenados descendentemente
2. Calcular stock total por categoria
3. Generar reporte en formato "nombre;precio" separado por ";"
4. Calcular precio promedio general
5. Calcular precio promedio por categoria

**Conceptos aplicados:**

- `filter()` - Productos > $100
- `sorted(Comparator.reversed())` - Ordenamiento descendente
- `groupingBy()` + `summingInt()` - Suma de stock por categoria
- `joining()` - Generacion de reporte en String
- `averagingDouble()` - Promedios por categoria

**Ubicacion:** `org.example.caso2.productos`

---

### Caso 3: Gestion de Libros 📖

**Clase:** `Libro(titulo, autor, paginas, precio)`

**Operaciones:**

1. Listar titulos de libros con mas de 300 paginas, ordenados alfabeticamente
2. Calcular el promedio de paginas
3. Contar cuantos libros tiene cada autor
4. Obtener el libro mas caro

**Conceptos aplicados:**

- `filter()` - Libros > 300 paginas
- `map()` - Extraccion de titulos
- `mapToInt()` - Conversion para calculo de promedio
- `groupingBy()` + `counting()` - Conteo por autor
- `max()` - Busqueda del maximo
- `Optional` - Manejo seguro de valores nulos

**Ubicacion:** `org.example.caso3.libros`

---

### Caso 4: Gestion de Empleados 💼

**Clase:** `Empleado(nombre, departamento, salario, edad)`

**Operaciones:**

1. Listar empleados con salario mayor a $2000, ordenados descendentemente
2. Calcular salario promedio general
3. Calcular suma de salarios por departamento
4. Obtener los nombres de los 2 empleados mas jovenes

**Conceptos aplicados:**

- `filter()` - Salarios > $2000
- `sorted()` + `Comparator` - Ordenamiento personalizado
- `mapToDouble()` - Conversion para calculo de promedio
- `groupingBy()` + `summingDouble()` - Suma de salarios
- `limit()` - Limitacion a 2 resultados

**Ubicacion:** `org.example.caso4.empleados`

---

## 🏗️ Estructura del Proyecto

```
src/
├── main/java/org/example/
│   ├── Main.java                    # Demostracion ejecutable
│   ├── caso1/alumnos/
│   │   ├── Alumno.java             # Modelo con Lombok
│   │   └── AlumnoService.java      # Operaciones funcionales
│   ├── caso2/productos/
│   │   ├── Producto.java
│   │   └── ProductoService.java
│   ├── caso3/libros/
│   │   ├── Libro.java
│   │   └── LibroService.java
│   └── caso4/empleados/
│       ├── Empleado.java
│       └── EmpleadoService.java
└── test/java/org/example/
    ├── caso1/alumnos/
    │   └── AlumnoServiceTest.java   # 6 tests
    ├── caso2/productos/
    │   └── ProductoServiceTest.java  # 7 tests
    ├── caso3/libros/
    │   └── LibroServiceTest.java     # 6 tests
    └── caso4/empleados/
        └── EmpleadoServiceTest.java  # 7 tests
```

---

## ✅ Resultados de Tests

**26 tests ejecutados - 26 exitosos ✅**

- ✅ AlumnoServiceTest: 6/6 tests pasados
- ✅ ProductoServiceTest: 7/7 tests pasados
- ✅ LibroServiceTest: 6/6 tests pasados
- ✅ EmpleadoServiceTest: 7/7 tests pasados

---

## 👤 Autor

**Jose Martin Rodriguez Mortaloni**  
Ingeniería en Sistemas de la Información - Desarrollo de Software  
Universidad Tecnologica Nacional

---

**© 2025 - Trabajo Practico Funcional**
