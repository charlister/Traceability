# Tp Traceabilité

## Question 1

Présentation des packages :

- _model_ : Les classes **User** et **Product** figurent dans ce package.
- _repository_ : on y trouve le code permettant de gérer une base de donnée locale où les données seront inscrites dans la mémoire le temps de l'exécution du programme.
- _exception_ : les exceptions pouvant survenir lors de l'exécution des requêtes.
- _data_ : on y fabrique les données initiales. On pourra se connecter avec les identifiants suivants :
  - email : _user1@gmail.com_
  - password : _password_
- _main_ : le package principal à partir duquel sera lancer l'application depuis la classe **Application**.

## Question 2

Pour la suite du TP, nous choisissons d'utiliser _java.util.logging_ comme outil pour faire la journalisation.

## Question 3

Pour cette question, nous utilisons Spoon pour faire du logging en utilisant l’outil de logging choisi dans la question 2 (c'est-à-dire java.util.logging.Logger), de sorte que les journaux générés peuvent être exploités pour créer des profils d’utilisateurs comme suit :

- Un profil pour ceux qui ont principalement effectué des opérations de lecture sur le dépôt.
- Un profil pour ceux qui ont principalement effectué des opérations d’écriture sur le dépôt.
- Un profil pour ceux qui ont recherché les produits les plus chers sur le dépôt.

À nous de personnaliser la structure du profil. Les informations les plus importantes à inclure sont les informations de l’utilisateur et les fonctionnalités de marquage du profil. (par exemple, les opérations de lecture/écriture effectuées par un utilisateur pour les profils de lecture/écriture principalement exécutés).

Remarque : L’utilisation de JSON est fortement recommandée pour le format de stockage des profils en raison de sa légèreté et de sa facilité de sérialisation/dé-sérialisation vers/depuis une entité de persistance (par exemple, système de fichiers, base de données, etc.).

```XML
<!-- Dépendance Spoon -->
<dependency> 
  <groupId>fr.inria.gforge.spoon</groupId>
  <artifactId>spoon-core</artifactId>
  <version>10.2.0</version>
</dependency>
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-core</artifactId>
  <version>2.19.0</version>
</dependency>
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-slf4j-impl</artifactId>
  <version>2.19.0</version>
</dependency>
```

## Ressources

- [Documentation java.util.logging](https://docs.oracle.com/javase/8/docs/api/java/util/logging/package-summary.html)
- [Spoon](https://spoon.gforge.inria.fr/first_analysis_processor.html#:~:text=Spoon%20is%20a%20library%20to%20build%20and%20manipulates,java%20-cp%20spoon-core-10.2.0-jar-with-dependencies.jar%20spoon.Launcher%20%20-i%20MyClass.java%20--gui)
- [Vidéo YouTube : OW2con'18 Spoon: open source library to analyze, rewrite, transform, transpile Java source code](https://www.youtube.com/watch?v=ZZzdVTIu-OY)
- [Lire un fichier](https://www.baeldung.com/reading-file-in-java)
- [Convertir un fichier XML en JSONObject](https://www.javatpoint.com/convert-xml-to-json-in-java)

# A faire

- Design patern Builder ?
- Lire la suite des questions pour être sûr d'avoir tout traité.
- Ajouter les informations de l'utilisateur pour le profilage.
- Faire le profilage (créer un parser de fichier log, ...).
