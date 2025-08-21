# 🥖 Bakery Inventory System

Inventory management system for bakeries developed with Spring Boot. It allows efficient control of products, ingredients, suppliers, and real-time inventory movements.

## 🚀 Features

- **Product Management**: Complete control of bakery products (breads, cakes, etc.)
- **Inventory Control**: Real-time tracking of stock levels
- **Supplier Management**: Registration and administration of suppliers
- **Inventory Movements**: Recording of entries and exits
- **DTO Architecture**: Use of Data Transfer Objects for a clean presentation layer
- **RESTful API**: Well-defined endpoints for integration
- **Validations**: Data validation across all operations

## 🛠️ Technologies

- **Spring Boot** 3.x
- **Spring Data JPA** - For data access
- **Spring Web** - REST API
- **PostgreSQL** - Relational database
- **Lombok** - Reduction of boilerplate code
- **Maven** - Dependency management
- **Hibernate Validator** - Bean validation
- **MapStruct** - Object mapping (if you used it)

## ⚙️ Architecture

```
src/main/java/com/bakery/inventory/
├── controller/          # REST Controllers
├── service/             # Business logic
├── repository/          # Data access
├── model/              # JPA Entities
├── dto/                # Data Transfer Objects
├── mapper/             # Mappers (if you used MapStruct)
└── exception/          # Exception handling
```

## 📦 Data Model

### Main Entities:
- **Product**: Bakery products (bread, cakes, etc.)
- **Ingredient**: Ingredients used in products
- **Supplier**: Suppliers of ingredients
- **Inventory**: Stock records
- **InventoryMovement**: Entry/exit movements

## 🎯 Main Endpoints

### Products
```
GET    /api/products              # List all products
GET    /api/products/{id}         # Get product by ID
POST   /api/products              # Create new product
PUT    /api/products/{id}         # Update product
DELETE /api/products/{id}         # Delete product
```

### Inventory
```
GET    /api/inventory             # List inventory
GET    /api/inventory/{id}        # Get inventory item
POST   /api/inventory/movement    # Record movement
GET    /api/inventory/low-stock   # Low stock products
```

### Suppliers
```
GET    /api/suppliers             # List suppliers
GET    /api/suppliers/{id}        # Get supplier by ID
POST   /api/suppliers             # Create new supplier
PUT    /api/suppliers/{id}        # Update supplier
```

## 🚀 Installation

### Prerequisites
- Java 17 or higher
- Maven 3.8+
- PostgreSQL 12+

### Environment Variables
```env
# Database Configuration
DB_URL=jdbc:postgresql://localhost:5432/bakerydb
DB_USERNAME=bakeryuser
DB_PASSWORD=bakerypass

# Application Configuration
SERVER_PORT=8080
```

### Installation Steps
```bash
# Clone the repository
git clone https://github.com/alejandrogch7/Bakery-inventory.git
cd Bakery-inventory

# Build the project
mvn clean package

# Run the application
mvn spring-boot:run
```

## 🗃️ Database Setup

1. Create PostgreSQL database:
```sql
CREATE DATABASE bakerydb;
CREATE USER bakeryuser WITH PASSWORD 'bakerypass';
GRANT ALL PRIVILEGES ON DATABASE bakerydb TO bakeryuser;
```

2. The application will automatically create the necessary tables thanks to JPA/Hibernate.

## 🧪 Testing

```bash
# Run unit tests
mvn test

# Run integration tests
mvn verify
```

## 📊 DTO Pattern Implementation

The project uses the DTO pattern to separate the presentation layer from the domain layer:

```java
// Entity (Model)
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    // getters and setters...
}

// DTO for presentation
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer availableStock;
    // getters and setters...
}
```

## 🎨 Benefits of Using DTOs

- ✅ **Security**: Does not expose entities directly
- ✅ **Flexibility**: You can customize information per endpoint
- ✅ **Performance**: Only transfer necessary data
- ✅ **Maintenance**: Clear separation of responsibilities
- ✅ **Validation**: Specific control per operation

## 📈 Use Cases

### 1. Register New Product
1. Create product in `/api/products`
2. System automatically assigns ID and dates
3. Product available for inventory

### 2. Register Incoming Goods
1. Create movement in `/api/inventory/movement`
2. Specify type: ENTRY
3. System automatically updates stock

### 3. Register Sale
1. Create movement in `/api/inventory/movement`
2. Specify type: EXIT
3. System verifies available stock

## 📄 License

This project is under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## 👥 Author

**AlexDev** - [@alejandrogch7](https://github.com/alejandrogch7)

## 🙏 Acknowledgments

- Spring Boot for the robust framework
- Development community for contributions
- Bakers and pastry chefs who inspired this project

## 🆘 Support

To report issues or suggestions:
1. Open an [issue](https://github.com/alejandrogch7/Bakery-inventory/issues)
2. Clearly describe the problem
3. Include steps to reproduce it
