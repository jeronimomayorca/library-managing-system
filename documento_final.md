# Sistema de Gestión de Biblioteca: Implementación y Análisis Estructural
**Autor:** Antigravity (IA Assitant)  
**Fecha:** 2026

## 1. Introducción
La gestión eficiente del inventario y de las acciones de los usuarios en Sistemas de Información requiere el diseño meticuloso de la memoria y la forma en que los datos se almacenan y operan. Las estructuras de datos lineales proveen arquitecturas específicas que optimizan estas operaciones según su naturaleza. El presente proyecto implementa un Sistema de Gestión de Biblioteca utilizando el paradigma de Programación Orientada a Objetos en Java. La esencia del sistema recae en prescindir del marco de colecciones estándar de Java (JCF), construyendo "desde cero" cada estructura: un Arreglo Estático para el registro de usuarios, una Lista Enlazada Simple para el inventario de libros, una Cola (FIFO) para las reservas de ejemplares agotados y una Pila (LIFO) para llevar el registro histórico transaccional del sistema. A través de este diseño, se demuestra la aplicación directa de cada modelo abstracto de datos en resolución de problemas contextuales reales.

## 2. Análisis Técnico: Lista Enlazada vs. Arreglos Dinámicos en Inventarios
Cuando se modela un catálogo de libros en una biblioteca (un inventario en constante mutación), la elección de una estructura contenedora impacta decisivamente el rendimiento (Fritelli, Guzman & Tymoschuk, 2020).
El uso de un arreglo estático obliga a predefinir su tamaño máximo en memoria durante el tiempo de compilación o instanciación, acarreando un desperdicio del bloque de memoria contiguo si no se ocupa en tu totalidad, o un desbordamiento inminente si se lo sobrepasa (Ayala San Martín, 2020). Si bien los arreglos dinámicos resuelven la limitación expandiéndose internamente creando e incrustando el bloque a un arreglo mayor ($O(N)$), las supresiones y las adiciones al inicio exigen desplazar subsecuentemente cada posición remanente en otro costo asintótico lineal de memoria.
Por el contrario, la Lista Enlazada Simple implementada carece de un techo preconcebido (Zohonero Martínez & Joyanes Aguilar, 2008). Cada objeto Libro reside en un nodo autónomo desperdigado en memoria *heap*, unido a su secuela puramente vía el direccionamiento referencial (apuntador `siguiente`). Insertar o eliminar elementos transcurre en $O(1)$ una vez alcanzada la locación requerida, sencillamente resituando enlaces de memoria de nodos colindantes. Para un inventario donde el crecimiento es constante y las operaciones de alta o dada de baja se esparcen estocásticamente, esta flexibilización y fragmentación lo vuelve inherentemente superior.

## 3. Relación de Conceptos (LIFO y FIFO)
En este modelado bibliotecario concurren dos entidades operacionales temporales:
- **Pila (LIFO - Last In, First Out):** El historial de transacciones (préstamos, devoluciones, altas de inventario y registro de usuarios) se canaliza mediante una Pila. Matemáticamente, toda operación de "deshacer" revierte inevitablemente al estado del universo informático más inmediato previo. Por lo tanto, el último evento apilado (`Push`) es obligatoriamente el primero manipulable en ser desapilado (`Pop`), encarnando el comportamiento LIFO para mantener la trazabilidad de estado (Fritelli, Guzman & Tymoschuk, 2020).
- **Cola (FIFO - First In, First Out):** La naturalidad social y operativa del préstamo confiere que el primer usuario que solicitó reservar un libro en carestía asuma estricta superioridad de adjudicación frente a un segundo usuario por equidad temporal (Ayala San Martín, 2020). La cola (donde se enfila en cola y se retira por el frente) asegura que el Primer usuario que entra, sea el Primero que lo adquiere a la confirmación de la devolución.

## 4. Pruebas y Resultados
Las casuísticas fueron documentadas en operaciones transaccionales probando el comportamiento en subsistemas cerrados de la plataforma de biblioteca (CLI).

| Caso de Prueba | Funcionalidad Evaluada | Resultado | Persistencia Memoria |
| :--- | :--- | :--- | :--- |
| **A. Inserción Masiva** | Añadir los libros: "Cien Años de Soledad", "El Tunel", "Ficciones", "1984" y "Sapiens" | Éxito. Los libros se enlazaron con un total de 5 nodos. | Los 5 libros se iteran desde `ListaEnlazada.mostrarInventario()`. |
| **B. Cola de Reservas** | El usuario "Juan" solicita "1984" (prestado por "Maria"). Luego, "Luis" también lo solicita. | Éxito. Juan forma el Frente de la Cola por "1984" y Luis encabeza el Final de acuerdo con FIFO. | Se corrobora invocando `ColaReservas.mostrarCola()`. |
| **C. Préstamo Inexigible** | El usuario "Pedro" intenta efectuar la opción 3 (Préstamo) de "Ficciones", un libro que un usuario "Carlos" no ha devuelto aún. | Éxito Controlado. El estado `isDisponible()` devolvió `false`. | Inmediatamente, la ejecución enrumba la inserción a la ColaReservas asegurando no haber préstamos dobles de la misma copia. |

## 5. Referencias Bibliográficas (APA 7)
Ayala San Martín, G. (2020). *Algoritmos y programación: mejores prácticas*. UDLAP.

Fritelli, V., Guzman, A. & Tymoschuk, J. (2020). *Algoritmos y estructuras de datos (2a. ed.)*. Jorge Sarmiento Editor.

Zohonero Martínez, I. & Joyanes Aguilar, L. (2008). *Estructuras de datos en Java*. McGraw-Hill.
