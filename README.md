# Questionnements sur la modélisation

1. **Quelles classes pourraient être abstraites ?**  
   La classe de base `Canard` devrait être abstraite, car elle définit les attributs et comportements communs à tous les types de canards (nom, points de vie, points d’attaque, méthode d’attaque, etc.). D’autres classes abstraites pourraient être envisagées pour représenter des effets d'état (par exemple, une classe abstraite pour des états tels que "Brûlé", "Gelé" ou "Paralysé") si plusieurs types d'effets partagent des comportements similaires.

2. **Quels comportements communs pourraient être définis dans une interface ?**  
   Des comportements communs, comme l’activation d’une capacité spéciale, pourraient être définis dans une interface. Par exemple, une interface `CapaciteSpeciale` déclarant la méthode `activerCapaciteSpeciale()` garantirait que chaque type de canard puisse implémenter sa propre logique de capacité. On pourrait également définir une interface pour les interactions en combat (ex. `Combattant` avec les méthodes `attaquer()` et `subirDegats()`).

3. **Comment représenter un changement de statut (par exemple, brûlé ou paralysé) dans la modélisation ?**  
   Pour représenter un changement de statut, on peut ajouter un attribut dans la classe `Canard` (ou dans une classe dédiée) qui stocke l’état actuel du canard. Par exemple, on peut utiliser un `enum Status` (contenant des valeurs comme `BRULE`, `PARALYSE`, `GEL`) ou une collection d'effets. Des méthodes pourront ensuite être créées pour appliquer, mettre à jour et retirer ces états lors des différents tours de combat.

4. **Quels seraient les avantages d’utiliser une classe ou une interface supplémentaire pour gérer les capacités spéciales ?**  
   Utiliser une classe ou une interface dédiée pour gérer les capacités spéciales permet de centraliser la logique liée aux capacités, de séparer les préoccupations et de rendre le code plus modulaire. Cela facilite l’extensibilité du projet, car chaque capacité spéciale peut être développée et modifiée indépendamment de la hiérarchie principale des classes. Une interface, par exemple `CapaciteSpeciale`, assure également une uniformité dans la manière d'activer et de gérer ces capacités dans tout le système.

5. **Quels défis sont associés à l’extensibilité de votre modèle pour ajouter de nouveaux types de canards ou de nouvelles capacités ?**  
   Les défis principaux incluent :  
   - **La gestion de la complexité croissante :** Ajouter de nouveaux types et capacités peut introduire de nombreux cas particuliers et augmenter la complexité du système, rendant le code plus difficile à maintenir.  
   - **La cohérence des interactions :** Il faut s’assurer que les nouvelles interactions (multiplicateurs, avantages/désavantages) s’intègrent harmonieusement au système existant sans créer d’incohérences.  
   - **L’impact sur la logique de combat :** Les nouvelles capacités, notamment celles impliquant des effets persistants ou des changements d’état, pourraient nécessiter une refonte de la logique de combat pour être correctement intégrées.  
   - **La robustesse et la couverture de tests :** Chaque nouvelle fonctionnalité doit être accompagnée de tests unitaires et fonctionnels pour prévenir les régressions et garantir la stabilité du simulateur.# Questionnements sur la modélisation

1. **Quelles classes pourraient être abstraites ?**  
   Je pense que la classe de base `Canard` doit être abstraite, car elle regroupe les caractéristiques et méthodes communes à tous les canards (comme le nom, les points de vie, les points d’attaque, et la méthode d’attaque). On pourrait aussi imaginer des classes abstraites pour les états (par exemple, pour "Brûlé", "Gelé" ou "Paralysé") si plusieurs états partagent des comportements semblables.

2. **Quels comportements communs pourraient être définis dans une interface ?**  
   Une interface peut regrouper les actions que tous les canards doivent réaliser. Par exemple, une interface `CapaciteSpeciale` avec la méthode `activerCapaciteSpeciale()` permettrait à chaque type de canard d’implémenter sa propre version de cette action. On pourrait aussi créer une interface pour les interactions en combat, avec des méthodes comme `attaquer()` et `subirDegats()`.

3. **Comment représenter un changement de statut (par exemple, brûlé ou paralysé) dans la modélisation ?**  
   Pour gérer un changement de statut, on peut ajouter un attribut dans la classe `Canard` (ou dans une classe dédiée) qui indique l’état actuel du canard. Par exemple, utiliser un `enum Status` avec des valeurs comme `BRULE`, `PARALYSE` ou `GEL` permet de suivre l’état du canard. On écrira ensuite des méthodes pour appliquer, mettre à jour et retirer ces états pendant le combat.

4. **Quels seraient les avantages d’utiliser une classe ou une interface supplémentaire pour gérer les capacités spéciales ?**  
   En créant une classe ou une interface spécifique pour les capacités spéciales, le code devient plus clair et mieux organisé. Cela permet de gérer les capacités de manière indépendante des autres fonctionnalités des canards, ce qui facilite l’ajout ou la modification de ces capacités sans toucher au reste du système. Une interface comme `CapaciteSpeciale` garantit que tous les canards possèdent bien une méthode pour activer leur capacité spéciale.

5. **Quels défis sont associés à l’extensibilité de votre modèle pour ajouter de nouveaux types de canards ou de nouvelles capacités ?**  
   Les défis principaux sont :  
   - **Augmentation de la complexité :** Plus on ajoute de types et de capacités, plus le code peut devenir compliqué et difficile à maintenir.  
   - **Cohérence des interactions :** Il faut s’assurer que les nouveaux types et leurs interactions (comme les multiplicateurs de dégâts) s’intègrent bien avec ce qui existe déjà, sans créer d’incohérences.  
   - **Impact sur la logique de combat :** Les nouvelles capacités, surtout celles qui affectent l’état du combat (comme les effets persistants), peuvent demander une révision de la logique de combat.  
   - **Tests et fiabilité :** Chaque nouvelle fonctionnalité doit être bien testée pour éviter les bugs et s’assurer que tout fonctionne comme prévu.



# Diagramme classes

![image](https://github.com/user-attachments/assets/16d64195-25ed-4fbe-956c-757404e22a75)
