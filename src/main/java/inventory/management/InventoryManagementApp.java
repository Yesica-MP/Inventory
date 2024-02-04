/**
# Inventory Management System

This project is a REST API for managing inventory, specifically focusing on liquor inventory. It includes entities such as Brand, Supplier, Category.

## Table of Contents

- [Entities](#entities)
  - [Brand](#brand)
  - [Supplier](#supplier)
  - [Category](#category)
  - [SupplierCategory](#suppliercategory)
- [Entity Relationships](#entity-relationships)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)

## Entities

### Brand

Represents a liquor brand in the inventory management system.

- **ID:** Unique identifier for the brand.
- **Name:** The name of the brand.
- **Description:** Description of the brand.
- **Price:** Price of the liquor from this brand.
- **Category:** The category to which the liquor belongs.
- **Supplier:** The supplier providing this brand of liquor.

### Supplier

Represents a supplier in the inventory management system.

- **ID:** Unique identifier for the supplier.
- **Name:** The name of the supplier.
- **Phone:** Contact phone number for the supplier.
- **Address:** Address of the supplier.
- **Brands:** List of liquor brands supplied by this supplier.
- **Categories:** List of categories supplied by this supplier.

### Category

Represents a category of liquor in the inventory management system.

- **ID:** Unique identifier for the category.
- **Category Name:** The name of the category.
- **Brands:** List of brands belonging to this category.
- **Suppliers:** List of suppliers providing liquor in this category.

### SupplierCategory

Represents the association between suppliers and categories in the inventory management system.

- **ID:** Unique identifier for the supplier-category association.
- **SupplierID:** The ID of the supplier associated with the category.
- **CategoryID:** The ID of the category associated with the supplier.

## Entity Relationships

- A Brand belongs to a single Supplier and a single Category.
- A Supplier has a one-to-many relationship with Brand and a many-to-many relationship with Category.
- A Category has a one-to-many relationship with Brand and a many-to-many relationship with Supplier.

### Error Handling

In this project, we have implemented a comprehensive error handling mechanism to enhance the robustness of the application. Key aspects of our error handling approach include:

- **Custom Exceptions:** We have defined custom exception classes to handle specific error scenarios, providing meaningful error messages to the clients.

- **Global Exception Handling:** A global exception handler is in place to catch unexpected errors across the application. It logs relevant information and returns a standardized error response.

- **HTTP Status Codes:** Our API adheres to standard HTTP status codes to convey the success or failure of each request. Detailed information about the status and error is provided in the response body.

- **Logging:** We utilize logging extensively to capture error details, aiding in debugging and issue resolution. Logs are generated for both expected and unexpected errors.

- **User-Friendly Error Messages:** For client-facing errors, we ensure that error messages are user-friendly and guide clients on corrective actions.

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository.
2. Set up your database and configure the application properties.
3. Build and run the project.

*/
package inventory.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagementApp {
	
	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApp.class, args);

	}

}
