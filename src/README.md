# Digital Vault

A terminal based password manager and digital vault built in Java.
Digital Vault allows users to add, modify, categorize and store sensitive data such as 
web credentials, credit cards and secure notes.

## Features 

**User management**: Secure user registration, login and logout functionalities with active session handling using tokens.

**Vault items**: Suport for 3 types of secured items:
- Web credentials (username, passwords, URLs)
- Credit Cards (card number, cardholder name, CVV)
- Secure Notes 

**Categories**: Organise vault items using various categories (Social, Banking, Shop, Personal etc)

**Validation**: Input validation for things like email, urls, card numbers etc.

**Terminal Interface** An interactive CLI client with commands resembling common Linux commands

**Flexible Storage**: Support for both In-Memory storage (for testing) and persistent local Database Storage.



## Project Strucutre

The project has the following arhitecture:
- `model`: Contains core entities (`User`, `Vault Item`, `UserSession` etc)
- `dao`: Data Acces Object handling database operations
- `service`: Core bussines logic (Authentification, Vault Management, Audit)
- `ui`: Terminal interface and command handlers
- `validation`: Dedicated validators for user input
- `exception`: Custom exceptions for services, database and validations
- `factory`: Factory patterns for creating vault items


# Getting Started

# Prerequisites

Ensure you have the following installed on your machine:
- Java Development Kit
- Apache Maven
- Docker and Docker Compose (for Database)


## Installation and setup

1. Clone the repository and navigate to the project directory
```bash
git clone https://github.com/fernandodonea/DigitalVault
cd DigitalVault
```

2. Start the database using Docker
```bash
docker compose up -d
```

3. Build the project using Maven
```bash
mvn clean install
```

4. Run the application
```bash
mvn exec:java -Dexec.mainClass="digital.vault.Main"
```
(You can also run the `Main.java` from your IDE)

## Usage

Once the application is running, you will pe prompted with command line interface.

The available commands are the following:
- `help` : show the list of all the commands 
- `register <username> <email> <password>` : creates a new user with said credentials
- `login <username> <password>` : log in as an existing user
- `logout`: logout and end current session
- `ls`: list all vault items
- `touch <vault-item-type>` : create a new vault item
- `rm <item-id>` : delete an item with set id
- `cat <item-id>` : display an item with set it
- `reveal <item-id>` : show sensitive data (requires master password)
- `nano <item-id>` : edit an item with set it
-  `quit` : exit the application

