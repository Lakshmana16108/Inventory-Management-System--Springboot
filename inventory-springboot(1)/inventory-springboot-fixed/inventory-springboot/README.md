# Inventory Management System
### Spring Boot + Maven + SQLite + JPA + REST API + HTML

Converted from original Java console app — **all original logic preserved**.

---

## Project Structure

```
inventory-management/
├── pom.xml
└── src/
    └── main/
        ├── java/com/inventory/
        │   ├── InventoryApplication.java         ← Main entry (replaces App.java)
        │   ├── model/
        │   │   ├── Person.java                   ← Original Person.java (MappedSuperclass)
        │   │   ├── Supplier.java                 ← Original Supplier.java (JPA Entity)
        │   │   ├── Product.java                  ← Original Product.java (JPA Entity)
        │   │   └── Sale.java                     ← Original Sale.java (JPA Entity)
        │   ├── repository/
        │   │   ├── ProductRepository.java
        │   │   ├── SupplierRepository.java
        │   │   └── SaleRepository.java
        │   ├── service/
        │   │   └── InventoryService.java         ← Original Inventory.java logic
        │   ├── controller/
        │   │   ├── ProductController.java
        │   │   ├── SupplierController.java
        │   │   └── SaleController.java
        │   └── exception/
        │       ├── ProductNotFoundException.java
        │       ├── InsufficientStockException.java
        │       ├── InvalidQuantityException.java
        │       └── GlobalExceptionHandler.java
        └── resources/
            ├── application.properties            ← SQLite config
            └── static/
                └── index.html                    ← Web UI (replaces console menu)
```

---

## How to Run

### Prerequisites
- Java 17+
- Maven 3.6+

### Steps

```bash
# 1. Navigate to project folder
cd inventory-management

# 2. Build the project
mvn clean install

# 3. Run the application
mvn spring-boot:run
```

### Access the App
- **Web UI:** http://localhost:8080
- **REST API Base:** http://localhost:8080/api

The SQLite database file `inventory.db` will be created automatically in the project root.

---

## REST API Endpoints

| Method | URL | Description | Original Menu |
|--------|-----|-------------|---------------|
| POST | /api/suppliers | Add Supplier | Case 1 |
| GET  | /api/suppliers | List all Suppliers | - |
| POST | /api/products | Add Product | Case 2 |
| GET  | /api/products | Display Products | Case 3 |
| POST | /api/sales?saleId=&productId=&qty= | Record Sale | Case 4 |
| GET  | /api/products/low-stock | Show Low Stock (qty < 5) | Case 5 |
| PUT  | /api/products/{id}/stock?newQty= | Update Stock | Case 6 |
| GET  | /api/sales | All Sales | - |

---

## Original Logic Preserved

| Original | Converted To |
|----------|-------------|
| `products.txt` file I/O | SQLite via JPA (inventory.db) |
| `loadProductsFromFile()` | JPA auto-loads from DB |
| `saveProductsToFile()` | `productRepository.save()` |
| `addSupplier()` | POST /api/suppliers |
| `addProduct()` | POST /api/products |
| `displayProducts()` | GET /api/products |
| `recordSale()` | POST /api/sales |
| `showLowStock()` | GET /api/products/low-stock |
| `updateStock()` | PUT /api/products/{id}/stock |
| `addStock()` / `reduceStock()` | Same methods in Product.java |
| Console error messages | HTTP exceptions with proper status codes |

---

## Exception Handling

| Exception | HTTP Status | Trigger |
|-----------|-------------|---------|
| `ProductNotFoundException` | 404 | Product ID not found |
| `InsufficientStockException` | 400 | Not enough stock for sale |
| `InvalidQuantityException` | 400 | Negative quantity in updateStock |
