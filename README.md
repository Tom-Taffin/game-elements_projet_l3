# GameElements Library

Bibliothèque Java pour créer des eléments de jeu Carcassonne.

## TileBuilder

**Rôle** : Construit une tuile à partir d'une chaînes de caractère formatée.

### Format de Chaîne
```
[Orientation][Edge1]-[Edge2]-[Edge3]-[Edge4]

Orientation: N, E, S, W
Edge: [topology][id] ou [topology1][id1]r[road_id][topology2][id2]
Topology: f (field), c (city)

Deux éléments possédant le même id sont reliés sur la tuile.
```

### Utilisation
Pour construire cette tuile:  
![tile exemple](images/tile_exemple1.png)

```java
TileBuilder builder = new TileBuilder();
Tile tile = builder.build("Nc1-f1r1f2-f2-f2r1f1"); 
```

La méthode build renvoie WrongTileSyntaxException si la chaine est mal formée.