<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Triedge</title>
    <head>
        <s:include value="includes/header.jsp"/>
    </head>
</head>
<body>
<s:include value="includes/menu.jsp"/>

<div class="tr-hidden-separator"></div>

<div class="tr-content tr-padding">
    <h1>Minecraft Plugin (Spigot)</h1>
    <a class="tr-button" href="https://triedge.ovh/MCPlugin19.jar">Plugin 1.19.2</a>
    <p>Ce plugin offre une nouvelle façon d'explorer le jeu et apporte des améliorations au gameplay. Ce dessous la liste des ajouts de gameplay avec leur description. La liste des craft est également disponible.</p>

    <h2>Teleporteurs</h2>
    <p>
        Comme leurs noms l'indiquent, les teleporteurs permettent de se teleporter d'un endrois a l'autre. Pour ce faire, vous devez enregistrer un point d'arrivé, et construire un teleporteur pour vous y rendre.
    </p>
    <p>
        Pour enregistrer un teleporteur il faut se placer sur un bloc de diamant et faire la commande: /warp create <span class="gold">NOM_DU_TP</span>. Ensuite vous pouvez voir le TP dans la liste des TP avec la commande:
        /warp list. Pour supprimer un TP il suffit d'executer /warp delete <span class="gold">NOM_DU_TP</span>.
    </p>
    <p>
        Pour se teleporter sur un TP enregistré, il faut construire un teleporteur. Il faut un bloc de diamant sur lequel il faudra se tenir et placer une pancarte
        sur un mur en face. La pancarte doit comporter le texte suivant: TP:NOM_DU_TP sur la premiere ligne. On peut ecrire ce qu'on veut sur les autres lignes.
    </p>
    <p>
        Il est possible de créer des téléporteurs caché (qui ne s'afficheront pas dans la liste des téléporteurs enregistré). Ceci pour par example faire
        des téléporteurs "utilitaires" pour se déplacer d'une maison à l'autre.
        Pour créer un téléporteurs caché il suffit de le nommer avec le préfix <span class="gold">h_</span> quand vous l'enregistrez. Par example: <span class="gold">/warp create h_maison</span>.
        Attention par contre, comme ils ne s'affichent pas dans la liste des téléporteurs, il ne faut pas oublier le nom que vous lui donnez, il faudra le noter!
    </p>
    <p>
        Il est maintenant possible d'affecter un groupe à un teleporteur pour limiter les personnes pouvant utiliser ce teleporteur. Si aucun group n'est spécifié, tout le monde peu l'utiliser.
        Pour créer un groupe il faut utiliser la commande: /warpgroup create <span class="gold">NOM_DU_GROUP</span><br />
        Pour ajouter un joueur au groupe: /warpgroup add <span class="gold">NOM_DU_GROUP</span> <span class="green">NOM_DU_JOUEUR</span> <br />
        Pour affecter un groupe à un téléporteur il faut faire: /warp setgroup <span class="gold">NOM_DU_TP</span> <span class="gold">NOM_DU_GROUP</span><br />
        Pour supprimer un joueur d'un group: /warpgroup remove <span class="gold">NOM_DU_GROUP</span> <span class="green">NOM_DU_JOUEUR</span> <br />
        Pour supprimer un group: /warpgroup delete <span class="gold">NOM_DU_GROUP</span><br />
        Pour ouvrir le téléporteur à tout le monde: /warp removegroup <span class="gold">NOM_DU_TP</span><br />
    </p>

    <h3>Commandes:</h3>
    <ul>
        <li>/warp list - Permet de lister les TP enregistrés</li>
        <li>/warp create <span class="gold">NOM_DU_TP</span> - Permet d'enregistrer un TP</li>
        <li>/warp delete <span class="gold">NOM_DU_TP</span> - Permet de supprimer un TP</li>
        <li>/warp setgroup <span class="gold">NOM_DU_TP</span> <span class="gold">NOM_DU_GROUP</span> - Permet d'affecter un groupe à un TP</li>
        <li>/warp removegroup <span class="gold">NOM_DU_TP</span> - Permet de délier le group d'un TP, ça ne supprime pas le groupe, mais le TP est ouvert à tous</li>
        <li>/warpgroup list - Permet de lister les groupes enregistrés</li>
        <li>/warpgroup list <span class="gold">NOM_DU_GROUP</span> - Permet de lister les joueurs dans un groupe</li>
        <li>/warpgroup create <span class="gold">NOM_DU_GROUP</span> - Permet de créer un groupe vide, affecter un groupe vide à un TP permet de bloquer le TP à tous</li>
        <li>/warpgroup delete <span class="gold">NOM_DU_GROUP</span> - Permet de supprimer un groupe, si un TP est lié à ce groupe, le TP ne fonctionnera plus</li>
        <li>/warpgroup add <span class="gold">NOM_DU_GROUP</span> <span class="green">NOM_DU_JOUEUR</span> - Permet d'ajouter un joueur au groupe, seul les joueurs dans le groupe pourront se téléporter</li>
        <li>/warpgroup remove <span class="gold">NOM_DU_GROUP</span> <span class="green">NOM_DU_JOUEUR</span> - Permet de supprimer un joueur du groupe</li>
    </ul>

    <div class="tr-horizontal-splitter"></div>

    <h2>Les inventaires</h2>
    <p>
        Il est possible d'ouvrir des inventaires supplémentaires n'importe ou. Il suffit d'utiliser la commande /inv [0-99 | share | list ]
    </p>
    <h3>Commandes:</h3>
    <ul>
        <li>/inv - Permet d'ouvrir l'inventaire <span class="gold">0</span></li>
        <li>/inv 1 - Permet d'ouvrir l'inventaire <span class="gold">1</span>, vous pouvez mettre n'importe quel nombre</li>
        <li>/inv list - Permet de lister les inventaires personnels deja créés</li>
        <li>/inv share - Permet d'ouvrir l'inventaire partagé avec les autres joueurs</li>
    </ul>

    <div class="tr-horizontal-splitter"></div>

    <h2>Les outils customs</h2>
    <p>
        Des outils customs sont disponible au craft (voir section crafts du site) comme <span class="gold">Improved Gold Axe</span> et autres. Ces items ont une durabilitée
        amélioré visible dans la description de l'item. Vous pouvez voir le nombre de bloc minable restant.
    </p>
    <p>
        De plus, lorsque vous minez un bloc de minerai (or, diamant etc) avec une pioche custom, vous receverez plus de minerai qu'avec une pioche normale.
    </p>
</div>
</body>
</html>
