# Perishable Cupcake Factory
Fun project for a Cupcake Factory with perishable cupcakes and desserts. The project is based on the following use case:

>La pasticceria vende dolci che hanno un nome ed un prezzo. Ogni dolce è composto da una lista di ingredienti.  
_Opzionale: indicare di ogni ingrediente quantità e unità di misura._  
_Opzionale: La gestione della pasticceria è in mano a Luana e Maria che vogliono avere il proprio account per poter accedere all'area di backoffice tramite email e password._  
>
>Nell’area di backoffice si possono gestire (CRUD) i dolci e metterli in vendita con una certa disponibilità (esempio: 3 torte paradiso in vendita). I dolci in vendita invecchiano ed in base al tempo trascorso dalla loro messa in vendita hanno prezzi diversi: primo giorno prezzo pieno, secondo giorno costano l’80%, il terzo giorno il 20%. Il quarto giorno non sono commestibili e devono essere ritirati dalla vendita. 
>
>Realizzare una pagina vetrina dove tutti possono vedere la lista di dolci disponibili e il prezzo relativo.  
_Opzionale: andando nella pagina del dettaglio del dolce (o tramite overlayer), si scoprono gli ingredienti indicati dalla ricetta._

Project is divided in backend and frontend, using different technologies.  
### Backend
- Java 1.8
- Spring Boot (Spring JPA, Spring Rest, Spring Security)
- MySQL 

It assumes that a MySQL server is running on the machine with an existing schema with name given by `MYSQL_DB` environment variable (default `cupcake_factory`), and migrations are applied with Flyway. Security is ensured using JWT, but it still lacks a full session management.  

Various environment variables can be used to configure the Java application:

| Name             | Default value     | Description       | Mandatory |
|------------------|-------------------|-------------------|-----------|
| `MYSQL_HOST`     | _localhost_       | MySQL host        | no        |
| `MYSQL_PORT`     | _3306_            | MySQL port        | no        |
| `MYSQL_DB`       | _cupcake_factory_ | MySQL schema name | no        |
| `MYSQL_USER`     | <NONE>            | MySQL user        | yes       |
| `MYSQL_PASSWORD` | <NONE>            | MySQL password    | yes       |
| `CUPCAKE_FACTORY_JWT_SECRET` | _8M4uo...UU7xp_            | JWT secret for generating the tokens. It is strongly suggested to feed a new one. This shouldn't be public.   | no       |
| `CUPCAKE_FACTORY_JWT_EXPIRATION` | _86400000_            | Expiration time for JWT tokens.    | no       |
| `CUPCAKE_FACTORY_ALLOWED_ORIGINS` | _http://localhost:8081_            | Allowed origins list for cors.     | no       |
| `CUPCAKE_FACTORY_DAYS_TO_EXPIRE` | _4_            | Application variable. Number of days until a dessert can be declared as expired.    | no       |
| `CUPCAKE_FACTORY_DAYS_PRICE_MODIFIER_MAP` | _{0:1, 1:1, 2:0.8, 3:0.6}_            | Application variable. Map to calculate the price modifiers. Keys are the number of days passed since production and values are the percent at which they should be sold    | no       |

To build the application run the command `mvn clean install -Dmaven.test.skip=true package`

### Frontend
- Vue.js
- Quasar Framework

It assumes a backend is running and reachable. The API backend endpoit is read from the environment variable `VUE_APP_BACKEND_ENDPOINT` (default `http://localhost:8080/` for both dev and prod. Can be changed in `config.json` file).

To build the application you need Quasar Framework installed. Once installed run the command `quasar build`.

# Demo
In the `demo` directory there are the compiled `.jar` for the backend and the built frontend.  
In order to test the application you'll need some pre-requisites:
- Java runtime to run the `.jar`
- An HTTP server to serve the frontend
- A MySQL server with a scheme named `cupcake_factory`
- Ports `8080` and `8081` available (can be changed)

When requirements are met, donwnload or clone the repository and navigate to the `demo` folder using a terminal.  
### Backend
Run the command `java -DMYSQL_USER="<YOUR_USER>" -DMYSQL_PASSWORD="<YOUR_PW>" -jar cupcakefactory-0.0.1-SNAPSHOT.jar` changing the `MYSQL_USER` and `MYSQL_PASSWORD` envs accordingly to your environment. Here others envs can be setted.

### Frontend
Navigate to the folder `spa`. If you have Node you can run the command `npx http-server -p 8081` to download and run an HTTP server on port `8081`. The backend expects the origin `http://localhost:8081` as default, while frontend expects to find the backend at `http://localhost:8080`, so beware on your settings.  

When also the frontend is running you can visit the page `http://localhost:8081` to open the application.  

Enjoy!


# TODO
Since it's a fun project, there are lots of aspects not taken in consideration:
- **TESTS** !!! This is on purpose btw.
- Environment management. Create dev and prod environments (e.g. Flyway data).
- JWT Session management
- Lots of UX like pagination, some edits, etc.
- Docker support
- More...
