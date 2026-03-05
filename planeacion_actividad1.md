# Planeación de Proyecto: Sistema de Gestión de Biblioteca en Java

## 1. Parámetros Académicos
- **Formato:** Documento APA 7ma Edición.
- **Lenguaje:** Java (JDK 11+).
- **Paradigma:** Orientación a Objetos (POO).
- **Estructuras a Implementar:** Listas Enlazadas (Libros), Arreglos (Usuarios), Pilas (Historial), Colas (Reservas).

---

## 2. Arquitectura de Datos Exigida
Antigravity, debes implementar las siguientes clases para demostrar dominio de memoria:

### A. El Modelo (POJO)
- `Libro`: Atributos (ISBN, Título, Autor, Disponible).
- `Usuario`: Atributos (ID, Nombre, Carrera).
- `Nodo`: Clase genérica para la estructura de la Lista Enlazada.

### B. Estructuras Manuales (No usar Java Collections Framework para la lógica central)
1. **Lista Enlazada Simple (Gestión de Libros):** Implementar métodos `insertar()`, `eliminar()` y `buscar()`.
2. **Pila Manual (Historial de acciones):** Basada en nodos o arreglo para el "Deshacer" de la última acción.
3. **Cola de Espera (Reservas):** Para gestionar usuarios esperando un libro prestado (FIFO).

---

## 3. Instrucciones de Desarrollo para Antigravity

### Paso 1: Documentación (Normas APA)
- **Introducción:** Importancia de las estructuras lineales en la optimización de búsqueda y recuperación de datos.
- **Análisis Técnico:** Justificar por qué una `LinkedList` manual es superior a un `Array` estático para un inventario que crece constantemente.
- **Relación de Conceptos:** Definir abstractamente Pila (LIFO) y Cola (FIFO) aplicadas al contexto bibliotecario.

### Paso 2: Implementación del Código
- **Interfaz de Usuario (CLI):** Un menú tipo `Scanner` con opciones:
    1. Agregar Libro (Lista Enlazada).
    2. Registrar Usuario (Arreglo).
    3. Prestar Libro (Mover a Cola si no está disponible).
    4. Ver última transacción (Pila).
    5. Mostrar Inventario Completo.
- **Validaciones:** Controlar excepciones (`try-catch`) para entradas de datos erróneas del usuario.

### Paso 3: Pruebas y Resultados
- Presentar una tabla con 3 casos de prueba mínimos:
    - Inserción de 5 libros y su persistencia en memoria.
    - Flujo de una reserva (Cola).
    - Intento de préstamo de un libro ya prestado.

---

## 4. Referencias Bibliográficas (APA 7)
- Ayala San Martín, G. (2020). *Algoritmos y programación: mejores prácticas*. UDLAP.
- Fritelli, V., Guzman, A. & Tymoschuk, J. (2020). *Algoritmos y estructuras de datos (2a. ed.)*. Jorge Sarmiento Editor.
- Zohonero Martínez, I. & Joyanes Aguilar, L. (2008). *Estructuras de datos en Java*. McGraw-Hill.

---

## 5. Criterios de Calidad Final
- El código debe incluir Javadoc en cada método principal.
- La nomenclatura debe seguir el estándar `camelCase` de Java.
- El documento final debe ser entregado en PDF con paginación y fuentes oficiales (Times New Roman 12 o Arial 11).