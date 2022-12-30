# Tp Traceability

Réalisé par :
- ALIANE Lydia
- ALLA Jean-Charles

# dépôt github

https://github.com/charlister/Traceability

# Identifiant de connexion d'un utilisateur :

  - email : user1@gmail.com
  - password : password

# 3 projets maven :

- WithException : le projet initial ne contenant que les exceptions.
- WithLoggingManually : semblable au projet initial, mais avec les logs en plus.
- AnalysisAndTransformation : le projet à partir duquel l’on peut injecter du 
code dans le projet WithException et analyser le fichier de journalisation 
contenu dans le projet WithLoggingManually.

# Attention :

- Les trois projets doivent impérativement se trouver dans le même 
répertoire.
- Avant la transformation de code prévu dans le projet 
AnalysisAndTransformation, il convient de supprimer le dossier spooned.
- Avant d’analyser le fichier de journalisation contenu dans le projet 
WithLoggingManually, nous devons fermer ce dernier car le fichier est 
ouvert lorsque que le projet est en cours d’exécution.

# Ressources

- [Documentation java.util.logging](https://docs.oracle.com/javase/8/docs/api/java/util/logging/package-summary.html)
- [Spoon](https://spoon.gforge.inria.fr/first_analysis_processor.html#:~:text=Spoon%20is%20a%20library%20to%20build%20and%20manipulates,java%20-cp%20spoon-core-10.2.0-jar-with-dependencies.jar%20spoon.Launcher%20%20-i%20MyClass.java%20--gui)
- [Vidéo YouTube : OW2con'18 Spoon: open source library to analyze, rewrite, transform, transpile Java source code](https://www.youtube.com/watch?v=ZZzdVTIu-OY)
- [Lire un fichier](https://www.baeldung.com/reading-file-in-java)
- [Convertir un fichier XML en JSONObject](https://www.javatpoint.com/convert-xml-to-json-in-java)
