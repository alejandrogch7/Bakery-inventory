# 🥖 Bakery Inventory System

Sistema de gestión de inventario para panaderías desarrollado con Spring Boot. Permite el control eficiente de productos, ingredientes, proveedores y movimientos de inventario en tiempo real.

## 🚀 Características

- **Gestión de Productos**: Control completo de productos de panadería (panes, pasteles, etc.)
- **Control de Inventario**: Seguimiento en tiempo real de existencias
- **Gestión de Proveedores**: Registro y administración de proveedores
- **Movimientos de Inventario**: Registro de entradas y salidas
- **Arquitectura DTO**: Uso de Data Transfer Objects para una capa de presentación limpia
- **API RESTful**: Endpoints bien definidos para integración
- **Validaciones**: Validación de datos en todas las operaciones

## 🛠️ Tecnologías

- **Spring Boot** 3.x
- **Spring Data JPA** - Para acceso a datos
- **Spring Web** - API REST
- **PostgreSQL** - Base de datos relacional
- **Lombok** - Reducción de código boilerplate
- **Maven** - Gestión de dependencias
- **Hibernate Validator** - Validación de beans
- **MapStruct** - Mapeo de objetos (si lo usaste)

## ⚙️ Arquitectura

```
src/main/java/com/bakery/inventory/
├── controller/          # Controladores REST
├── service/             # Lógica de negocio
├── repository/          # Acceso a datos
├── model/              # Entidades JPA
├── dto/                # Data Transfer Objects
├── mapper/             # Mapeadores (si usaste MapStruct)
└── exception/          # Manejo de excepciones
```

## 📦 Modelo de Datos

### Entidades Principales:
- **Product**: Productos de la panadería (pan, pasteles, etc.)
- **Ingredient**: Ingredientes utilizados en los productos
- **Supplier**: Proveedores de ingredientes
- **Inventory**: Registro de existencias
- **InventoryMovement**: Movimientos de entrada/salida

## 🎯 Endpoints Principales

### Productos
```
GET    /api/products              # Listar todos los productos
GET    /api/products/{id}         # Obtener producto por ID
POST   /api/products              # Crear nuevo producto
PUT    /api/products/{id}         # Actualizar producto
DELETE /api/products/{id}         # Eliminar producto
```

### Inventario
```
GET    /api/inventory             # Listar inventario
GET    /api/inventory/{id}        # Obtener item de inventario
POST   /api/inventory/movement    # Registrar movimiento
GET    /api/inventory/low-stock   # Productos con bajo stock
```

### Proveedores
```
GET    /api/suppliers             # Listar proveedores
GET    /api/suppliers/{id}        # Obtener proveedor por ID
POST   /api/suppliers             # Crear nuevo proveedor
PUT    /api/suppliers/{id}        # Actualizar proveedor
```

## 🚀 Instalación

### Prerrequisitos
- Java 17 o superior
- Maven 3.8+
- PostgreSQL 12+

### Variables de Entorno
```env
# Database Configuration
DB_URL=jdbc:postgresql://localhost:5432/bakerydb
DB_USERNAME=bakeryuser
DB_PASSWORD=bakerypass

# Application Configuration
SERVER_PORT=8080
```

### Pasos de instalación
```bash
# Clonar el repositorio
git clone https://github.com/alejandrogch7/Bakery-inventory.git
cd Bakery-inventory

# Compilar el proyecto
mvn clean package

# Ejecutar la aplicación
mvn spring-boot:run
```

## 🗃️ Configuración de Base de Datos

1. Crear base de datos PostgreSQL:
```sql
CREATE DATABASE bakerydb;
CREATE USER bakeryuser WITH PASSWORD 'bakerypass';
GRANT ALL PRIVILEGES ON DATABASE bakerydb TO bakeryuser;
```

2. La aplicación creará automáticamente las tablas necesarias gracias a JPA/Hibernate.

## 🧪 Pruebas

```bash
# Ejecutar pruebas unitarias
mvn test

# Ejecutar pruebas de integración
mvn verify
```

## 📊 DTO Pattern Implementation

El proyecto utiliza el patrón DTO para separar la capa de presentación de la capa de dominio:

```java
// Entidad (Model)
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    // getters and setters...
}

// DTO para presentación
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer availableStock;
    // getters and setters...
}
```

## 🎨 Ventajas del uso de DTOs

- ✅ **Seguridad**: No expone entidades directamente
- ✅ **Flexibilidad**: Puedes personalizar la información por endpoint
- ✅ **Rendimiento**: Solo transfieres los datos necesarios
- ✅ **Mantenimiento**: Separación clara de responsabilidades
- ✅ **Validación**: Control específico por operación

## 📈 Casos de Uso

### 1. Registrar nuevo producto
1. Crear producto en `/api/products`
2. El sistema asigna automáticamente ID y fechas
3. Producto disponible para inventario

### 2. Registrar entrada de mercancía
1. Crear movimiento en `/api/inventory/movement`
2. Especificar tipo: ENTRADA
3. El sistema actualiza automáticamente el stock

### 3. Registrar venta
1. Crear movimiento en `/api/inventory/movement`
2. Especificar tipo: SALIDA
3. El sistema verifica stock disponible

## 📄 Licencia

Este proyecto está libre de licencias.

## 👥 Autor

**AlexDev** - [@alejandrogch7](https://github.com/alejandrogch7)

## 🙏 Agradecimientos

- Spring Boot por el framework robusto
- Comunidad de desarrollo por las contribuciones
- Panaderos y reposteros que inspiraron este proyecto

## 🆘 Soporte

Para reportar problemas o sugerencias:
1. Abre un [issue](https://github.com/alejandrogch7/Bakery-inventory/issues)
2. Describe claramente el problema
3. Incluye pasos para reproducirlo
