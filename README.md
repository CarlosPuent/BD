## Requisitos Previos

Antes de ejecutar el proyecto, asegúrese de tener instalado lo siguiente:

- **Node.js** (v18 o superior)
- **Angular CLI**  
  \`npm install -g @angular/cli\`
- **Java 17** o superior
- **Apache Maven**
- **MySQL Server**
- (Opcional) **MySQL Workbench** para facilitar la administración de la base de datos

---

## Importación de la Base de Datos

Dentro del proyecto se encuentra el script 'bd_liquidity' necesario para cargar los datos de prueba. Puede hacerlo de dos formas:

### Opción 1: Importar archivo `.sql`

1. Crear una base de datos en MySQL llamada: \`liquidityFinance_DB\`
2. Abrir MySQL Workbench
3. Cargar el archivo `.sql` ubicado en el proyecto
4. Ejecutar el script para crear las tablas y registros de ejemplo

### Opción 2: Importar desde Dump Folder

1. Abrir MySQL Workbench
2. Ir a: `Server > Data Import`
3. Seleccionar la opción:  
   **Import from Dump Project Folder**
4. Indicar la ruta del dump folder:  
   `bd_liquidity.sql`
5. Seleccionar como base de datos destino: `liquidityFinance_DB`
6. Click en `Start Import`

Una vez completado, la base de datos incluirá:

- Usuarios y credenciales
- Roles (`ROLE_USER`, `ROLE_ADMIN`) esto debe de haber en la tabla roles
- Categorías de productos
- Productos asociados a múltiples categorías

---

## Configuración del Backend

1. Ingresar a la carpeta \`liquidity\`
2. Configurar las credenciales en el archivo \`src/main/resources/application.properties\`:

\`\`\`properties
spring.datasource.url=jdbc:mysql://localhost:3306/liquidityFinance_DB
spring.datasource.username=SU_USUARIO
spring.datasource.password=SU_CONTRASEÑA
\`\`\`

## Acceso de prueba

A continuación, se presentan credenciales preconfiguradas para ingresar al sistema:

### Administrador

- **Usuario:** puente  
- **Contraseña:** 12345  
- **Rol:** ROLE_ADMIN
