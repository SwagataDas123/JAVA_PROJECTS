# BankStimulationinjava

####  **Introduction**

The Simple Bank Simulation System is designed to replicate the basic operations of a bank, including user registration, login, deposit, withdrawal, balance inquiry, and transaction history management. The system provides an interface for users to perform banking operations securely and track their financial activities in real-time.This system is implemented using Java programming, making it a simple yet effective way to demonstrate the fundamental concepts of object-oriented programming (OOP), such as classes, objects, methods, inheritance, and data storage in memory. The aim is to simulate a banking system that is capable of managing users' accounts and transactions effectively.

#### **Architecture and Program Flow**

**Architecture:**

The system follows a **client-server model** (even though it's implemented in a single application) with the following key components:

- **User Management**: Registers and logs in users.
- **Transaction Management**: Handles deposits, withdrawals, and balance inquiries.
- **Transaction History Management**: Tracks and displays all deposits and withdrawals.

**Program Flow:**

1. **Start**: The program starts and presents the user with a menu of options (Register, Login, Exit).
2. **User Registration**: If the user selects "Register", they provide a username and password, and the system stores the details.
3. **Login**: If the user selects "Login", they provide their credentials (username and password). If valid, they are logged in and given access to the banking operations menu.
4. **Banking Operations**: Once logged in, the user can:
   - Deposit money into their account.
   - Withdraw money from their account.
   - View their current balance.
   - View their transaction history.
5. **Exit**: The program can be exited anytime.

####  **Explanation of Code**

1. **User Registration and Login**:
   - Users register by providing a username and password.
   - The system checks if the username already exists. If it does, it prompts the user to select another one.
   - For login, the system checks the entered credentials against the stored data and grants access if valid.

2. **Banking Operations**:
   - **Deposit**: When a user deposits money, it is added to their balance, and the deposit is recorded in their transaction history.
   - **Withdraw**: When withdrawing money, the system checks if the user has sufficient balance and deducts the withdrawal amount. This is also recorded in the transaction history.
   - **Balance Inquiry**: The current balance is displayed.
   - **Transaction History**: The user can view a list of all transactions (deposits and withdrawals).

3. **Transaction History Management**:
   - Each deposit and withdrawal is logged into an `ArrayList` for transaction tracking.
