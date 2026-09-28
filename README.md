# Electro Pi - QA Automation Technical Assessment

This repository contains a unified test automation architecture and technical assessment solution for **Electro Pi's POS & Inventory Management System**.

It includes two independent UI test automation frameworks, Postman API collections, SQL validation queries, and CI/CD workflow strategies:

1. **Selenium Suite:** Java 25 + Selenium WebDriver + TestNG + Maven (Page Object Model)
2. **Playwright Suite:** TypeScript + Playwright (Page Object Model)

---

## 📁 Repository Structure

```text
Electro_Pi_QA_Automation_Repository/
│
├── .github/
│   └── workflows/                         # CI/CD GitHub Actions workflows
│
├── selenium-suite/                        # Selenium + Java 25 Project
│   ├── src/
│   │   ├── main/java/com/ElectroPI/qa/
│   │   │   ├── config/                   # Config readers
│   │   │   ├── factoryPage/              # DriverFactory initialization
│   │   │   ├── pages/                    # Page Object classes
│   │   │   └── utils/                    # Helper utilities
│   │   │
│   │   └── test/
│   │       ├── java/tests/                # TestNG tests & listeners
│   │       └── resources/
│   │           └── config.properties      # Environment configuration
│   │
│   ├── testng.xml                         # TestNG suite execution XML
│   └── pom.xml                            # Maven dependencies & build settings
│
├── playwright-suite/                      # Playwright + TypeScript Project
│   ├── pages/                             # Page Object Model classes
│   ├── tests/                             # Test spec files
│   ├── dev.env                            # Environment variables
│   ├── playwright.config.ts               # Playwright test configuration
│   └── package.json                       # Node dependencies & scripts
│
├── postman/                               # API testing assets
│   ├── ElectroPi_Inventory_API.postman_collection.json
│   └── ElectroPi_Staging.postman_environment.json
│
├── sql/                                   # Database validation scripts
│   └── category_validation.sql
│
├── .gitignore
└── README.md
```

---

## ⚙️ Environment Configuration

Each framework manages its environment configuration independently through dedicated environment files.

This approach avoids hardcoding configuration values and allows the test suites to be executed against different environments.

### 1️⃣ Selenium Suite

Configuration file:

```text
selenium-suite/src/test/resources/config.properties
```

Example:

```properties
base.url=https://staging.electropi-app.com
admin.username=admin@electropi.com
admin.password=SecurePassword123
```

The Selenium framework loads these properties through `ConfigReader.java`.

### 2️⃣ Playwright Suite

Configuration file:

```text
playwright-suite/dev.env
```

Example:

```env
BASE_URL=https://staging.electropi-app.com
STORE_ADMIN_EMAIL=admin@electropi.com
STORE_ADMIN_PASSWORD=SecurePassword123
```

The Playwright framework uses `dotenv` to load these variables at runtime.

> **Security Note:** In a real project, credentials should not be committed to the repository. Use environment variables, GitHub Secrets, or another secrets-management solution.

---

## 💻 Prerequisites

Ensure the following are installed before running the test suites:

| Tool                 | Version             |
| -------------------- | ------------------- |
| Java Development Kit | Java 25             |
| Apache Maven         | v3.8+               |
| Node.js              | v18.0.0 or higher   |
| Postman              | Desktop Application |

---

# 🚀 Installation & Execution

## 1️⃣ Selenium Suite — Java 25

### Step 1: Navigate to the Selenium directory

```bash
cd selenium-suite
```

### Step 2: Verify Java 25

```bash
java -version
```

### Step 3: Compile the Project

Clean previous build artifacts and compile:

```bash
mvn clean compile
```

### Step 4: Run Tests

Run the complete TestNG suite:

```bash
mvn clean test
```

Run a specific test class:

```bash
mvn test -Dtest=InventoryTest
```

Run an individual test method:

```bash
mvn test -Dtest=InventoryTest#testAddProductSuccessfully
```

### Override Environment Properties

Configuration values can be overridden dynamically through Maven properties:

```bash
mvn test \
-Dbase.url="https://staging.electropi-app.com" \
-Dadmin.username="admin@electropi.com" \
-Dbrowser="chrome"
```

---

# 2️⃣ Playwright Suite — TypeScript

### Step 1: Navigate to the Playwright directory

```bash
cd playwright-suite
```

### Step 2: Install project dependencies

```bash
npm install
```

### Step 3: Install Playwright browser binaries

```bash
npx playwright install --with-deps
```

### Step 4: Run Tests

Run the complete test suite in headless mode:

```bash
npx playwright test
```

Run tests in headed mode:

```bash
npx playwright test --headed
```

Run a specific test file:

```bash
npx playwright test tests/inventory.spec.ts
```

### Override Environment Variables

Environment variables can also be provided dynamically at runtime:

```bash
BASE_URL="https://staging.electropi-app.com" \
STORE_ADMIN_EMAIL="admin@electropi.com" \
npx playwright test
```

### View HTML Report

```bash
npx playwright show-report
```

---

# 3️⃣ Postman API Collection

The repository contains the following Postman files:

```text
postman/
├── ElectroPi_Inventory_API.postman_collection.json
└── ElectroPi_Staging.postman_environment.json
```

### Import the Collection

1. Open Postman.
2. Click **Import**.
3. Import both JSON files from the `/postman/` directory.
4. Select **ElectroPi Staging** from the environment dropdown.

### Authentication

Run:

```text
1. Authentication - Get Token
```

The request test script automatically saves the generated Bearer Token into:

```text
{{token}}
```

### Create Inventory Item

Run:

```text
2. Create Inventory Item
```

The request validates:

* HTTP Status: `201 Created`
* Response payload
* Required response fields

---

# 4️⃣ Database Validation

Database validation is provided through:

```text
sql/category_validation.sql
```

The query validates that the created inventory item is mapped to the expected category.

```sql
SELECT
    p.id AS product_id,
    p.item_name,
    p.sku,
    p.price,
    p.quantity,
    c.id AS category_id,
    c.category_name
FROM products p
INNER JOIN categories c
    ON p.category_id = c.id
WHERE p.sku = 'MS-001'
  AND c.category_name = 'Electronics';
```

The validation checks the relationship between:

* Product
* SKU
* Category
* Category mapping
* Price
* Quantity

---

# 🔄 CI/CD Strategy

The repository includes a GitHub Actions workflow:

```text
.github/workflows/ci.yml
```

The pipeline is designed to execute API and UI automation as part of the CI/CD workflow.

### Pipeline Flow

```text
Environment Provisioning
        │
        ├── JDK 25
        └── Node.js
        │
        ▼
   API Validation
        │
        └── Newman / Postman Collection
        │
        ▼
    UI Test Execution
        │
        ├── Selenium + Java
        ▼
   Test Reporting
        │
        ├── Execution Reports```

### Environment Provisioning

The GitHub Actions runner is configured with:

* Ubuntu
* JDK 25
* Node.js

### API Stage

The Postman collection is executed using **NEWMAN**.

### UI Test Stage

Selenium suites .

### Artifact Reporting

The workflow uploads test execution reports and failure screenshots as CI artifacts.

---

## 🧪 Testing Approach

The repository demonstrates multiple levels of automated validation:

| Layer    | Tool             | Purpose                             |
| -------- | ---------------- | ----------------------------------- |
| UI       | Selenium         | End-to-end UI validation            |
| UI       | Playwright       | End-to-end UI validation            |
| API      | Postman / Newman | API request and response validation |
| Database | SQL              | Data and relationship validation    |
| CI/CD    | GitHub Actions   | Automated test execution            |

---

## 🏗️ Automation Architecture

Both UI frameworks follow the **Page Object Model (POM)** approach.

### Selenium

```text
TestNG Tests
     │
     ▼
Page Objects
     │
     ▼
DriverFactory
     │
     ▼
Selenium WebDriver
     │
     ▼
Electro Pi Application
```

### Playwright

```text
Tests
  │
  ▼
Page Objects
  │
  ▼
Playwright
  │
  ▼
Electro Pi Application
```

This structure separates test scenarios from UI implementation details and makes the automation code easier to maintain and extend.
