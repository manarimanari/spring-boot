# spring-boot
projet maven
. Configuration Maven
Le pom.xml contient les dépendances nécessaires pour Spring Boot (Data JPA, Thymeleaf, Web, DevTools) et le connecteur MySQL. Dans ce cas, nous utilisons la version Spring Boot 2.2.6.RELEASE et Java 21 :

xml

<properties>
    <java.version>21</java.version>
</properties>
2. Configuration de la base de données
Le fichier application.properties configure les informations de connexion à la base de données MySQL, avec le spring.datasource.url, spring.datasource.username et spring.datasource.password. Il permet également de gérer la génération automatique du schéma de la base avec Hibernate via :

properties

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3. La couche de domaine (Entity)
L'entité User est définie avec des annotations JPA (@Entity, @Id, @GeneratedValue) et des contraintes de validation (@NotBlank pour name et email). Cette classe modèle les utilisateurs qui seront stockés dans la base de données.

4. La couche de référentiel (Repository)
Spring Data JPA simplifie la création de DAO via des interfaces comme CrudRepository :

java

@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
Cela permet d’avoir des fonctionnalités CRUD de base sans écrire de DAO manuellement.

5. La couche contrôleur (Controller)
Le contrôleur UserController gère les requêtes HTTP GET et POST pour afficher les formulaires, créer et modifier des utilisateurs, ainsi que les supprimer. Les principales méthodes sont :

GET pour afficher un formulaire ou des informations (/signup, /edit/{id}).
POST pour ajouter, mettre à jour ou supprimer un utilisateur via les méthodes addUser, updateUser, et deleteUser.
6. La couche de présentation (Vue)
Les fichiers HTML pour l'interface utilisateur sont créés sous src/main/resources/templates et utilisent Thymeleaf. Voici les fichiers essentiels :

add-user.html : Formulaire pour ajouter un utilisateur.
update-user.html : Formulaire pour mettre à jour un utilisateur existant.
index.html : Affiche la liste des utilisateurs avec des liens pour éditer et supprimer.
7. Exécution de l'application
Le point d'entrée principal de l'application est défini dans DemothymeleafApplication :

java

@SpringBootApplication
public class DemothymeleafApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemothymeleafApplication.class, args);
    }
}
Une fois l'application démarrée (via IDE ou en exécutant mvn spring-boot:run), nous pouvons accéder à l'application à http://localhost:8080 et interagir avec l'interface CRUD.

Résultat final
L'application permet de gérer une liste d'utilisateurs, où nous pouvons ajouter, mettre à jour, et supprimer des utilisateurs à travers une interface simple avec Thymeleaf, le tout connecté à une base de données MySQL.
