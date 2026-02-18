# GameElements Library

Bibliothèque Java pour créer des eléments de jeu Carcassonne.

## TileBuilder

**Rôle** : Construit une tuile à partir d'une chaînes de caractère formatée.

### Format de Chaîne
```
[Orientation][TopEdge]-[RightEdge]-[BottomEdge]-[LeftEdge]

Orientation: N, E, S, W
Edge: [topology][id] ou [topology1][id1]r[road_id][topology2][id2]
Topology: f (field), c (city)

Deux éléments possédant le même id sont reliés sur la tuile.
```

### Utilisation
Pour construire ces tuiles:  
![tile1 exemple](images/tile_exemple1.png)
![tile2 exemple](images/tile_exemple2.png)

```java
TileBuilder builder = new TileBuilder();
Tile tile1 = builder.build("Nc3-f1r4f2-f2-f2r4f1"); 
Tile tile2 = builder.build("Nc1-c1-f2r0f3-c1"); 
```

La méthode build renvoie WrongTileSyntaxException si la chaine est mal formée.

## Tile

**Rôle** : Représente une tuile de jeu avec 4 bords.

### Orientation

La tuile possède une orientation défini à sa création et qui ne peux pas être modifiée.  
On peut l'obtenir avec la méthode getOrientation().

Orientation NORTH:  
![tile with north orientation](images/tile_exemple1.png)

Orientation EAST:  
![tile with east orientation](images/tile_exemple1_East.png)

Orientation SOUTH:  
![tile with south orientation](images/tile_exemple1_South.png)

Orientation WEST:  
![tile with west orientation](images/tile_exemple1_West.png)

### Direction et Edge

Une tuile possède quatre `edge` représentant chaque bord. De plus il existe 4 directions (TOP, RIGHT, BOTTOM, LEFT) pour situé un bord. Ainsi on peut récupérer un bord de la tuile grace à la méthode getEdge(Direction).
 
![tile direction exemple](images/tile_exemple1_direction.png)
![tile direction exemple](images/tile_exemple1_East_direction.png)

Peu importe l'orientation de la tuile, getEdge(Top) renvoie toujours le bord situé en haut comme illustré ci-dessus. Ainsi getEdge(Top) renvoie une ville dans le premier cas et des plaines avec une route dans le second.

### Vérification compatibilité

On peut vérifier la compatibilité d'une autre tuile placé sur une direction d'une tuile initial en fonction de leurs orientations.

Cas compatible:  
![left tile](images/tile_exemple1_East.png)
![right tile](images/tile_exemple2_South.png)  
La tuile de gauche est orienté vers l'est et la tuile de droite vers le sud.
```java
tuileGauche.isCompatible(tuileDroite,Direction.RIGHT); // renvoie True
tuileDroite.isCompatible(tuileGauche,Direction.LEFT); // renvoie True
```

Cas non compatible:  
![left tile](images/tile_exemple1_South.png)
![right tile](images/tile_exemple2_South.png)  
La tuile de gauche est orienté vers le sud et la tuile de droite vers le sud.
```java
tuileGauche.isCompatible(tuileDroite,Direction.RIGHT); // renvoie False
tuileDroite.isCompatible(tuileGauche,Direction.LEFT); // renvoie False
```

### Connections et terminaison route

Cas 1:  
![tile exemple](images/tile_exemple1.png)
```java
tile.getExitRoadDirection(Direction.RIGHT); // renvoie LEFT
tile.getExitRoadDirection(Direction.LEFT); // renvoie RIGHT
tile.getExitRoadDirection(Direction.TOP); // NoRoadException
tile.isRoadTerminated(Direction.RIGHT); // renvoie False
tile.isRoadTerminated(Direction.TOP); // renvoie False
```

Cas 1:  
![tile exemple](images/tile_exemple2.png)
```java
tile.isRoadTerminated(Direction.RIGHT); // renvoie False
tile.isRoadTerminated(Direction.BOTTOM); // renvoie True
tile.getExitRoadDirection(Direction.BOTTOM); // NoRoadException
```

## Orientation

L'enum Orientation possèdent les méthodes suivante permettant de manipuler ses instances simplement:  
NORTH.rotateHalf() -> SOUTH  
NORTH.rotateLeft() -> WEST  
NORTH.rotateRight() -> EAST  

## Direction

L'enum Direction possèdent les méthodes suivante permettant de manipuler ses instances simplement:  
TOP.toOpposite() -> BOTTOM  
TOP.toLeft() -> LEFT  
TOP.toRight() -> RIGHT  
TOP.getOldDirection(EAST) -> LEFT (return the old direction of something before the orientation)  
TOP.getNewDirection(EAST) -> RIGHT (return the new direction of something after the orientation) 

## Zone

Un `Edge` représentant un bord peut être un `EdgeNoRoad` ou un `EdgeWithRoad` et possède respectivement une ou deux zones.  
Une `Zone` possède une certaine topologie (`CITY` ou `FIELD`) que l'on obtient avec `getTopology()` et des zones connectées.  

Pour obtenir l'ensemble des zones connectées à une zone: 
```java
zone.getConnectingZones()
edgeNoRoad.getConnectingZones()
edgeWithRoad.getZone1ConnectingZones()
edgeWithRoad.getZone2ConnectingZones()
```

Pour savoir si une zone n'est connectée à aucune autre zone: 
```java
zone.isFinished()
edgeNoRoad.isZoneFinished()
edgeWithRoad.isZone1Finished()
edgeWithRoad.isZone2Finished()
```
Par exemple:  
![tile exemple](images/tile_exemple2.png)  
Sur la tuile ci-dessus la zone de gauche a pour zones connectées la zone en haut et à droite et respectivement pour la zone en haut et à droite. Les zones en bas sont terminées.


## Umls
