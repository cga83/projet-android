# Projet Android - Tournages Parisiens

## Introduction
### Description de l'application
Notre application regroupe des lieux de tournages de films à Paris. Il est possible de voir la liste des films avec les adresses de tournages et il est possible de visualiser les lieux de tournages sur une carte Google Maps.
Voici la page d'accueil de l'application :

![Ecran d'accueil](screenshot/accueil.png?raw=true "Application")

### Récupération des données
## Requête API
Les données ont été trouvées sur [ce site](https://opendata.paris.fr/explore/dataset/tournagesdefilmsparis2011/api/).
Une requête a été faite pour récupérer les données dans la MainActivity. Pour cela, un ```Uri.Builder``` a été utilisé : 
```
uri.scheme("https")
                .authority(BASE_URL)
                .appendPath("api")
                .appendPath("records")
                .appendPath("1.0")
                .appendPath("search")
                .appendQueryParameter("dataset", "tournagesdefilmsparis2011")
                .appendQueryParameter("rows", NB_ROWS) // Chargement de toutes les données
                .appendQueryParameter("facet", "realisateur")
                .appendQueryParameter("facet", "organisme_demandeur")
                .appendQueryParameter("facet","type_de_tournage")
                .appendQueryParameter("facet","ardt");
```
Cela correspond à faire une requête à l'adresse https://opendata.paris.fr/api/records/1.0/search/?dataset=tournagesdefilmsparis2011&rows=2801&facet=realisateur&facet=organisme_demandeur&facet=type_de_tournage&facet=ardt.

## Stockage dans une base de donnée locale

### Map
## Création de la Map

## Utilisation de clusters

## Personnalisation des clusters


