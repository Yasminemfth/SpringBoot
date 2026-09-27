# Apprendre le Java 🖥️

Petit projet pour apprendre Java, Spring Boot et Docker.

## Le projet

Création d'un système d'idols et d'acteurs, qui permet de créer une idol en lui donnant un nom, une couleur de cheveux, un genre, etc.

### Popularité et bad buzz

Si l'idol fait une action présente dans la liste des polémiques, elle perd des points de popularité et gagne du bad buzz. Si son bad buzz dépasse un certain seuil, elle fait ce qu'on appelle une **hot take** : elle est alors en plein bad buzz.
À l'inverse, si elle fait une bonne action (don, concert gratuit, charité...), elle regagne des points de popularité. Chaque bonne action a son propre poids : un concert gratuit rapporte plus qu'un simple don.

Les conventions permettent aussi de regagner de la popularité, mais sur la durée : chaque convention ajoute 1 au compteur, et ce n'est qu'après un certain nombre de conventions (10 pour une idol, 5 pour un acteur) que chacune rapporte des points.

### Conventions

Le système de conventions permet à l'idol de regagner de la popularité. Au-delà d'un certain nombre de conventions, chaque nouvelle convention lui rapporte des points.

## Ce que j'ai ajouté

- **Des poids différents pour chaque polémique** : un `date` coûte 10 points, une `insulte` 30.
- **Des niveaux de bad buzz** : selon le score, l'idol est en polémique, en gros bad buzz ou problématique.
- **Les bonnes actions** (dons, concerts gratuits, charité...) pour regagner de la popularité, et le **cool take** qui dit à quel point l'artiste est cool.
- **Un compteur de conventions** : chaque convention ajoute 1 au compteur. Contrairement aux bonnes actions, qui augmentent la popularité tout de suite, les conventions ne rapportent de la popularité qu'au-delà d'un seuil : 10 conventions pour une idol, 5 pour un acteur.
- **Les acteurs**, séparés des idols, avec un film en plus. Ils partagent la même interface mais réagissent différemment, par exemple avec un seuil de conventions plus bas.
- **Les relations** entre une idol et un acteur : elles démarrent neutres (score 0), montent avec les collaborations et baissent avec les clashs. Selon le score : Ennemis, Tendus, Neutre, Amis ou Proches.
- **La modification** d'une idol ou d'un acteur (PUT).
- **Une base PostgreSQL** avec Spring Data JPA : les données ne sont plus perdues au redémarrage.
- **Docker** : tout se lance avec une seule commande.

## Lancer le projet

Avec Docker Desktop ouvert, à la racine du projet :

    docker compose up --build


## URL pour tester

Les routes en **POST** et **PUT** se testent avec Postman. Toutes les requêtes sont dans le fichier `Idol Generator.postman_collection.json`, à importer dans Postman.

### Actions disponibles

**Polémiques** : `date`, `scandale`, `polémique`, `insulte`, `mensonge`, `triche`, `comportement_controverse`

**Bonnes actions** : `don`, `concert_gratuit`, `charite`, `fan_meeting`, `benevolat`, `excuses_publiques`, `collaboration`

## À améliorer (dans le futur)

- Ajouter la suppression (DELETE)
- Contagion du bad buzz : quand l'un fait une polémique, l'autre en subit une partie selon leur relation
