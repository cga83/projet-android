# Projet Android - Tournages Parisiens

## Introduction
### Description de l'application
Notre application regroupe des lieux de tournages de films à Paris. Il est possible de voir la liste des films avec les adresses de tournages et il est possible de visualiser les lieux de tournages sur une carte Google Maps.
Voici la page d'accueil de l'application :

![Ecran d'accueil](screenshot/accueil.png?raw=true "Application")

### Récupération des données
## Données
Les données dont nous disposons sont, pour chaque tournage :
* type_de_tournage :	une String égale à "TELEFILM", "SERIE TELEVISEE" ou "LONG METRAGE"
* organisme_demandeur :	une String correspondant à l'organisme demandeur du film
* adresse	: une String correspond à l'adresse du tournage
* date_debut : une String correspond à la date de début du tournage
* date_fin : une String correspond à la date de fin du tournage
* realisateur	: une String correspond au réalisateur
* xy : un tableau de Double contenant les coordonnées [latitude, longitude]
* ardt : Un entier correspondant à l'arrondissement dans lequel le tournage à eu lieu
* titre	: Une string correspondant au titre du film/de la série




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

Lorsque l'on effectue la requête, un objet JSON est retourné. Il est du format:
```
parameters	{…}
records	[…]
facet_groups	[…]
```

Les données qui nous intéressent se trouvent dans ```records``` :
```
records
  0	
    datasetid	"tournagesdefilmsparis2011"
    recordid	"865411d3110634059eff5d5edb8c7f0b076f9dd7"
    fields	
      type_de_tournage	"TELEFILM"
      organisme_demandeur	"BIG BAND STORY"
      adresse	"RUE  ROCHER/DE MADRID ET PORTALIS"
      date_fin	"2016-03-31"
      realisateur	"ARNAULD MERCADIER"
      xy	[…]
      ardt	75008
      titre	"COUP DE FOUDRE A JAIPUR"
      date_debut	"2016-03-31"
    geometry	{…}
    record_timestamp	"2018-01-09T16:34:10+00:00"
  1	{…}
  ...
  2801 {..}
 ```
 Les données sur les tournages, qui se trouvent dans ```fields```, n'ont pas pu être récupérées immédiatement. Il a fallu créer une classe ```LieuxTournageRecords```, une classe ```LieuxTournageRecord``` et une classe ```LieuxTournage```.
 La classe ```LieuxTournageRecords``` correspond au tableau records et a un attribut de type LieuxTournageRecord. La classe ```LieuxTournageRecord``` correspond à chaque élément de ce tableau. Elle a un attribut de type LieuxTournage, fields. 
 

## Stockage dans une base de donnée locale
Lorsque l'application est lancée pour la première fois, la requête sur l'API est effectué. Les données sont ensuite stockées dans une base de donnée locale. Cela permet à l'utilisateur d'utiliser l'application hors ligne. Cela permet également de charger les données plus rapidement. Cependant, il faudrait vérifier régulièrement que la base de donnée en ligne n'a pas été mise à jour !
La librairie Room Persistence a été utilisée pour la base de donnée locale.

Pour le stockage dans cette base de donnée locale, le champ geometry a posé problème. En effet, il n'était pas possible de le stocker directement comme un tableau de Double, il a fallu utiliser un TypeConverter (```PositionConverter```).

### Map
## Création de la Map

## Utilisation de clusters

## Personnalisation des clusters

## Filtrage

## FragmentActivity -> Fragment
Initialement, la Map était un FragmentActivity. Pour faciliter le fonctionnement de la toolbar, elle a été transformée en Fragment. Pour cela, il a fallu faire plusieurs modifications (```onCreateView``` plutôt que ```onCreateView```, ```getActivity().getApplicationContext()``` pour récupérer le contexte...)


