# Apprendre le Java 🖥️

Petit projet pour apprendre Java, Spring Boot et Docker.

## Le projet

Création d'un système d'idols et d'acteurs, qui permet de créer une idol en lui donnant un nom, une couleur de cheveux, un genre, etc.

### Popularité et bad buzz

Si l'idol fait une action présente dans la liste des polémiques, elle perd des points de popularité et gagne du bad buzz. Si son bad buzz dépasse un certain seuil, elle fait ce qu'on appelle une **hot take** : elle est alors en plein bad buzz.

### Conventions

Le système de conventions permet à l'idol de regagner de la popularité. Au-delà d'un certain nombre de conventions, chaque nouvelle convention lui rapporte des points.

## Ce que j'ai ajouté

- **Des poids différents pour chaque polémique** : un  scandalte de `date` coûte 10 points, une `insulte` 30.
- **Des niveaux de bad buzz** : selon le score, l'idol est en polémique, en gros bad buzz ou problématique.
- **Les bonnes actions** (dons, concerts gratuits, charité...) pour regagner de la popularité, et le **cool take** qui dit à quel point l'artiste est cool.
- **Les acteurs**, séparés des idols, avec un film en plus. Ils partagent la même interface mais réagissent différemment : un acteur gagne en populairté dès la 6ᵉ, une idol à partir de la 11ᵉ.
- **Les relations** entre une idol et un acteur : elles démarrent neutres (score 0), montent avec les collaborations et baissent avec les clashs. Selon le score : Ennemis, Tendus, Neutre, Amis ou Proches.
- **La modification** d'une idol ou d'un acteur (PUT).
- **Une base PostgreSQL** avec Spring Data JPA : les données ne sont plus perdues au redémarrage.
- **Docker** : tout se lance avec une seule commande.

## Lancer le projet

Avec Docker Desktop ouvert, à la racine du projet :

    docker compose up --build


## URL pour tester

Les routes en **POST** et **PUT** se testent avec Postman.

### Idol

| Verbe | URL | Rôle |
|---|---|---|
| GET | `/idol` | Lister les idols |
| GET | `/idol/{nom}` | Voir une idol |
| POST | `/idol?nom=Ruby&couleurCheveux=blond&genre=F` | Créer une idol |
| PUT | `/idol?id=1&couleurCheveux=rose` | Modifier une idol |
| POST | `/idol/{nom}/polemic?action=scandale` | Faire une polémique |
| POST | `/idol/{nom}/coolaction?action=charite` | Faire une bonne action |
| POST | `/idol/{nom}/convention` | Aller en convention |
| GET | `/idol/{nom}/hottake` | Niveau de bad buzz |
| GET | `/idol/{nom}/cooltake` | Niveau de coolitude |

### Acteur

Mêmes routes que l'idol, en remplaçant `/idol` par `/acteur`. À la création, on peut ajouter un film :

`POST /acteur?nom=Kaname&couleurCheveux=noir&genre=H&film=Oshi`

### Relation

| Verbe | URL | Rôle |
|---|---|---|
| GET | `/relation` | Lister les relations |
| GET | `/relation/{nomIdol}/{nomActeur}` | Voir une relation |
| POST | `/relation?nomIdol=Ruby&nomActeur=Kaname` | Créer une relation |
| POST | `/relation/collaboration?nomIdol=Ruby&nomActeur=Kaname` | Score +15 |
| POST | `/relation/clash?nomIdol=Ruby&nomActeur=Kaname` | Score -20 |

### Actions disponibles

**Polémiques** : `date`, `scandale`, `polémique`, `insulte`, `mensonge`, `triche`, `comportement_controverse`

**Bonnes actions** : `don`, `concert_gratuit`, `charite`, `fan_meeting`, `benevolat`, `excuses_publiques`, `collaboration`

## À améliorer

- Ajouter la suppression (DELETE)
- Contagion du bad buzz : quand l'un fait une polémique, l'autre en subit une partie selon leur relation
