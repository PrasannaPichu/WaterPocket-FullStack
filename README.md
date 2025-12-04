# 💧 Water Pocket - Premium Water Delivery System

**Water Pocket** is a full-stack web application designed to streamline water can deliveries for households and retailers. It features a robust **User App** for booking and tracking, and a powerful **Admin Dashboard** for inventory, subscription, and revenue management.

---

## 🚀 Features

### 👤 User Module
* **Authentication:** Secure Login & Registration for customers.
* **Booking Options:**
    * **One-Time Order:** Buy 20L/10L cans or Party Boxes (24pcs).
    * **Subscriptions:** Flexible plans (Daily, Alternate Days, Weekly).
* **Live Tracking:** Real-time order status updates (Placed → Shipped → Delivered).
* **Wallet System:** Digital wallet for handling refunds and quick payments.
* **Subscription Management:** Users can Pause or Resume their subscriptions anytime.
* **Order History:** Detailed view of past orders and transactions.

### 🛡️ Admin Module
* **Dashboard:** Visual analytics including revenue charts and order counters.
* **Inventory Management:** Update product prices dynamically for Personal vs. Retailer customers.
* **Order Logistics:** Manage order status (Shipped, Out for Delivery, Delivered).
* **Subscription Manager:** specialized tools to log daily deliveries (+1, +2 cans) and track subscription progress.
* **Calendar View:** A visual calendar to track daily dispatch schedules and revenue.

---

## 🛠️ Tech Stack

| Component | Technology |
| :--- | :--- |
| **Frontend** | React.js, Tailwind CSS, Lucide Icons, Axios |
| **Backend** | Java Spring Boot (REST API) |
| **Database** | MongoDB |
| **Build Tools** | Maven (Java), npm (React) |
| **IDE** | VS Code |

---

## ⚙️ Setup & Installation

Follow these steps to run the project locally on your machine.

### 1. Prerequisites
* **Node.js** & **npm** installed.
* **Java JDK 17+** installed.
* **MongoDB** installed and running locally on port `27017`.

### 2. Database Setup
Ensure your MongoDB service is active.
* The application is configured to automatically create the database `waterpocket_db` and necessary collections (`orders`, `users`, `products`) when the backend starts.
* **Connection String:** `mongodb://localhost:27017/waterpocket_db`

### 3. Backend Setup (Spring Boot)
1.  Navigate to the backend folder:
    ```bash
    cd backend
    ```
2.  Install dependencies and start the server:
    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```
    *The server will start at `http://localhost:8080`.*

### 4. Frontend Setup (React)
1.  Open a new terminal and navigate to the frontend folder:
    ```bash
    cd frontend
    ```
2.  Install dependencies:
    ```bash
    npm install
    ```
3.  Start the application:
    ```bash
    npm start
    ```
    *The app will automatically open at `http://localhost:3000`.*

---

## 📂 Project Structure

```text
WaterPocket-FullStack/
├── backend/
│   ├── src/main/java/com/waterpocket/backend/
│   │   ├── controller/   # API Endpoints (Order, Auth, Product)
│   │   ├── model/        # Database Schemas (User, Order, Product)
│   │   └── repository/   # MongoDB Repositories
│   ├── src/main/resources/
│   │   └── application.properties # DB Configuration
│   └── pom.xml           # Dependencies
│
└── frontend/
    ├── public/
    │   └── owner.jpg     # Assets
    ├── src/
    │   ├── pages/        # Admin & User Page Components
    │   ├── App.js        # Main Component logic
    │   └── index.css     # Tailwind imports
    └── package.json

👨‍💻 Founder & Contact
Prasanna Somasekar M
Founder & CEO, Water Pocket
📞 Contact: +91 97911 48594
📧 Email: prasannasomasekar@gmail.com
🚀 Mission: "Dedicated to bringing pure, mineral-rich hydration to every doorstep."
image of the owner is placed inside the project

📜 License
This project is developed for educational and portfolio purposes.
