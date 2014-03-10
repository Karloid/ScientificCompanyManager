// create an new instance of a pixi stage
var stage = new PIXI.Stage(0xAABBCC);

// create a renderer instance
var CELL_SIZE = 32;
var WIDTH_WORLD = 30;
var HEIGHT_WORLD = 20;


var renderer = PIXI.autoDetectRenderer(CELL_SIZE * WIDTH_WORLD, CELL_SIZE * HEIGHT_WORLD);

var actionX = -1;
var actionY = -1;
renderer.view.onmousedown = function (e) {
    actionX = (e.x - renderer.view.offsetLeft);
    actionY = (e.y - renderer.view.offsetTop);
};

var keyEnum = { W_Key: 0, A_Key: 1, S_Key: 2, D_Key: 3, ONE_Key: 4, TWO_Key: 5, THREE_Key: 6, FOUR_Key: 7};
var keyArray = new Array(8);
var keyArrayLastSended = new Array(8);

keyArray[0] = false;
keyArray[1] = false;
keyArray[2] = false;
keyArray[3] = false;
keyArray[4] = false;
keyArray[5] = false;
keyArray[6] = false;
keyArray[7] = false;

document.onkeydown = function onKeyDown(e) {
    tmpkey = String.fromCharCode(e.keyCode);
    if (tmpkey == 'W')
        keyArray[keyEnum.W_Key] = true;
    if (tmpkey == 'A')
        keyArray[keyEnum.A_Key] = true;
    if (tmpkey == 'S')
        keyArray[keyEnum.S_Key] = true;
    if (tmpkey == 'D')
        keyArray[keyEnum.D_Key] = true;
    if (tmpkey == '1')
        keyArray[keyEnum.ONE_Key] = true;
    if (tmpkey == '2')
        keyArray[keyEnum.TWO_Key] = true;
    if (tmpkey == '3')
        keyArray[keyEnum.THREE_Key] = true;
    if (tmpkey == '4')
        keyArray[keyEnum.FOUR_Key] = true;
    //  httpPost("/game/action", tmpkey + " is down");
}

document.onkeyup = function onKeyUp(e) {
    tmpkey = String.fromCharCode(e.keyCode);

    if (tmpkey == 'W')
        keyArray[keyEnum.W_Key] = false;
    if (tmpkey == 'A')
        keyArray[keyEnum.A_Key] = false;
    if (tmpkey == 'S')
        keyArray[keyEnum.S_Key] = false;
    if (tmpkey == 'D')
        keyArray[keyEnum.D_Key] = false;
    if (tmpkey == '1')
        keyArray[keyEnum.ONE_Key] = false;
    if (tmpkey == '2')
        keyArray[keyEnum.TWO_Key] = false;
    if (tmpkey == '3')
        keyArray[keyEnum.THREE_Key] = false;
    if (tmpkey == '4')
        keyArray[keyEnum.FOUR_Key] = false;

    //httpPost("/game/action", tmpkey + " is up");
}


// add the renderer view element to the DOM
document.getElementById("gameview").appendChild(renderer.view);
var bulletsAndGun = document.getElementById("bulletsAndGun");
var listGuns = document.getElementById("listGuns");
var stats = document.getElementById("stats");

//renderer.view.addEventListener("mousedown", function() {});


requestAnimFrame(animate);

var container = new PIXI.DisplayObjectContainer();

// create a soldier1Texture from an image path
var soldier1Texture = PIXI.Texture.fromImage("game/images/soldier1.png");
var soldier2Texture = PIXI.Texture.fromImage("game/images/soldier2.png");
var soldier3Texture = PIXI.Texture.fromImage("game/images/soldier3.png");
var soldier4Texture = PIXI.Texture.fromImage("game/images/soldier4.png");

var bulletTexture = PIXI.Texture.fromImage("game/images/bullet.png");


// create a new Sprite using the soldier1Texture
//var soldier = new PIXI.Sprite(soldier1Texture);

var soldiers = {};
/*
 // center the sprites anchor point
 soldier.anchor.x = 0.5;
 soldier.anchor.y = 0.5;

 // move the sprite t the center of the screen
 soldier.position.x = 200;
 soldier.position.y = 150;
 */

var playerId;
var lastUpdateDate = new Date().getTime();
var DELAY = 300;
var TILES_TYPES;

var BULLETS = [];
var ITEMS_CONTAINERS = [];

loadGroundTiles(container);

/*var graphics = new PIXI.Graphics();
 container.addChild(graphics);

 graphics.lineStyle(5, 0xFF3300, 1);

 graphics.drawRect(50, 250, 100, 100);*/


//container.addChild(soldier);

stage.addChild(container);

stage.onTouchMove = function (touchData) {
    alert("TOUCH MOVE");
}

function arrayEquals(keyArray, keyArrayLastSended) {
    for (i = 0; i < 8; i++) {
        if (keyArray[i] != keyArrayLastSended[i]) {
            return false;
        }
    }
    return true;
}
function handleUserInput() {

    if (!arrayEquals(keyArray, keyArrayLastSended)) {
        var request = "{ \"w\" : " + isKeyDown(keyEnum.W_Key) +
            ", \"a\" : " + isKeyDown(keyEnum.A_Key) +
            ", \"s\" : " + isKeyDown(keyEnum.S_Key) +
            ", \"d\" : " + isKeyDown(keyEnum.D_Key) +

            ", \"1\" : " + isKeyDown(keyEnum.ONE_Key) +
            ", \"2\" : " + isKeyDown(keyEnum.TWO_Key) +
            ", \"3\" : " + isKeyDown(keyEnum.THREE_Key) +
            ", \"4\" : " + isKeyDown(keyEnum.FOUR_Key) +

            "}"
        httpPost("/game/keyAction", request);
        keyArrayLastSended[0] = keyArray[0];
        keyArrayLastSended[1] = keyArray[1];
        keyArrayLastSended[2] = keyArray[2];
        keyArrayLastSended[3] = keyArray[3];
        keyArrayLastSended[4] = keyArray[4];
        keyArrayLastSended[5] = keyArray[5];
        keyArrayLastSended[6] = keyArray[6];
        keyArrayLastSended[7] = keyArray[7];
    }
    if (actionX != -1 && actionY != -1) {
        var request = "{ \"x\" : " + actionX +
            ", \"y\" : " + actionY +
            "}"
        httpPost("/game/mouseAction", request);
        actionX = -1;
        actionY = -1;
    }


}
function getTexture(spriteType) {
    if (spriteType == 1) {
        return soldier1Texture;
    } else if (spriteType == 2) {
        return soldier2Texture;
    } else if (spriteType == 3) {
        return soldier3Texture;
    } else if (spriteType == 4) {
        return soldier4Texture;
    }

}
function getPlayer(id, spriteId, name) {
    var soldier = soldiers[id];
    if (!soldier) {
        soldier = {
            id: id,
            spriteId: spriteId,
            sprite: new PIXI.Sprite(getTileTextureById(spriteId)),
            hpBar: new PIXI.Graphics(),
            nameText: new PIXI.Text(name, {font: "13px Arial bold", fill: "blue"}),
            hp: 0
        };
        soldier.sprite.anchor.x = 0.5;
        soldier.sprite.anchor.y = 0.5;

        soldier.nameText.anchor.x = 0.5;
        soldier.nameText.anchor.y = 0.5;

        soldiers[id] = soldier;

        container.addChild(soldier.sprite);
        container.addChild(soldier.hpBar);
        container.addChild(soldier.nameText);

    }
    return soldier;
}
function getBullet(id) {
    var bullet = BULLETS[id];
    if (!bullet) {
        bullet = {
            id: id,
            sprite: new PIXI.Sprite(bulletTexture),
            updated: false
        };
        bullet.sprite.anchor.x = 0.5;
        bullet.sprite.anchor.y = 0.5;
        BULLETS[id] = bullet;

        container.addChild(bullet.sprite);

    }
    return bullet;
}
function removeOldBullets() {
    for (var i = BULLETS.length - 1; i >= 0; i--) {
        if (BULLETS[i] && !BULLETS[i].updated) {
            container.removeChild(BULLETS[i].sprite);
            BULLETS.splice(i, 1);
        }
    }

    for (var i = BULLETS.length - 1; i >= 0; i--) {
        if (BULLETS[i]) {
            BULLETS[i].updated = false;
        }
    }
}
function updatePlayer(player, x, y, hp) {
    player.sprite.position.x = x;
    player.sprite.position.y = y;

    player.nameText.position.x = x;
    player.nameText.position.y = y - 26;

    player.hp = hp;
    // return;
    container.removeChild(player.hpBar);
    player.hpBar = new PIXI.Graphics();
    player.hpBar.lineStyle(5, 0xFF0000, 1);
    player.hpBar.moveTo(x - CELL_SIZE / 2 - 1, y - CELL_SIZE / 2);
    player.hpBar.lineTo(x - CELL_SIZE / 2 + CELL_SIZE / 100 * player.hp, y - CELL_SIZE / 2);
    container.addChild(player.hpBar)


}
function removeOldItemsContainers() {
    for (var i = ITEMS_CONTAINERS.length - 1; i >= 0; i--) {
        if (ITEMS_CONTAINERS[i] && !ITEMS_CONTAINERS[i].updated) {
            container.removeChild(ITEMS_CONTAINERS[i].sprite);
            ITEMS_CONTAINERS.splice(i, 1);
        }
    }

    for (var i = ITEMS_CONTAINERS.length - 1; i >= 0; i--) {
        if (ITEMS_CONTAINERS[i]) {
            ITEMS_CONTAINERS[i].updated = false;
        }
    }
}


function getItemContainer(itemContainerParam) {

    var itemContainer = ITEMS_CONTAINERS[itemContainerParam.id];
    if (!itemContainer) {
        itemContainer = {
            id: itemContainerParam.id,
            sprite: new PIXI.Sprite(getTileTextureById(itemContainerParam.spriteId)),
            updated: false
        };
        itemContainer.sprite.anchor.x = 0.5;
        itemContainer.sprite.anchor.y = 0.5;
        ITEMS_CONTAINERS[itemContainerParam.id] = itemContainer;

        container.addChild(itemContainer.sprite);

    }
    return itemContainer;
}


function updateHUD(obj) {
    bulletsAndGun.textContent = obj.playerBullets + ' ' + obj.playerGun;
    listGuns.textContent = '';
    for (i = 0; i < obj.playerGuns.length; i++) {
        listGuns.textContent = listGuns.textContent + " " + (i + 1) + ". " + obj.playerGuns[i];
    }

    var ul = document.createElement('ol')
    while (stats.firstChild) {
        stats.removeChild(stats.firstChild);
    }
    stats.appendChild(ul);
    for (i = 0; i < obj.players.length; i++) {
        var li = document.createElement('li');
        ul.appendChild(li);
        li.textContent +=  obj.players[i].name + " - " + obj.players[i].k + " - " + obj.players[i].d;
    }
//    stats.textContent += "</ul>";
}
function getWorldState() {
    var currentDate = new Date().getTime();
    if (currentDate - lastUpdateDate < DELAY) {
        return;
    }
    lastUpdateDate = currentDate;
    var json = httpGet("/game/state", false);
    obj = JSON.parse(json);
    for (j = 0; j < obj.players.length; j++) {
        player = getPlayer(obj.players[j].id, obj.players[j].spriteId, obj.players[j].name);

        updatePlayer(player, obj.players[j].x, obj.players[j].y, obj.players[j].hp);
    }


    for (jj = 0; jj < obj.itemsContainers.length; jj++) {
        tmpItemContainer = getItemContainer(obj.itemsContainers[jj]);
        tmpItemContainer.sprite.position.x = obj.itemsContainers[jj].x;
        tmpItemContainer.sprite.position.y = obj.itemsContainers[jj].y;

        tmpItemContainer.updated = true;
    }

    removeOldItemsContainers();

    for (i = 0; i < obj.bullets.length; i++) {
        bullet = getBullet(obj.bullets[i].id);
        bullet.sprite.position.x = obj.bullets[i].x;
        bullet.sprite.position.y = obj.bullets[i].y;
        bullet.updated = true;
    }

    removeOldBullets();


    // update HUD
    updateHUD(obj);


}
function testGraphics() {

}
function animate() {
    handleUserInput();

    getWorldState();

    testGraphics();

    requestAnimFrame(animate);

    renderer.render(stage);
}

function getTileTextureById(id) {
    for (i = 0; i <= TILES_TYPES.length - 1; i++) {
        if (TILES_TYPES[i].id == id) {
            return TILES_TYPES[i].textureImg;
        }
    }
    return null;
}
function loadGroundTiles(container) {
    var json = httpGet("/game/tiles", true);
    //  alert(json);
    obj = JSON.parse(json);
    WIDTH_WORLD = obj.width;
    HEIGHT_WORLD = obj.height;
    playerId = obj.playerId;
    DELAY = obj.delay;
    TILES_TYPES = obj.tilesTypes.tiles;
    for (i = 0; i <= TILES_TYPES.length - 1; i++) {
        currentTile = TILES_TYPES[i];
        currentTile.textureImg = PIXI.Texture.fromImage("game/images/" + currentTile.texture);
        //  alert(currentTile.textureImg );
    }
    var tile;
    for (x = 0; x < WIDTH_WORLD; x++) {
        for (y = 0; y < HEIGHT_WORLD; y++) {
            tile = new PIXI.Sprite(getTileTextureById(obj.tiles[x][y]));

            tile.position.x = x * CELL_SIZE;
            tile.position.y = y * CELL_SIZE;
            container.addChild(tile);
        }
    }
    //   alert(obj.tiles[1][1]);

}

function httpGet(theUrl, withParams) {
    var xmlHttp = null;

    //  theUrl += document.location.substring(document.location.indexOf('?'));
    if (withParams) {
        theUrl += document.location.search;
    }
    xmlHttp = new XMLHttpRequest();
//    xmlHttp.setCopen("GET", theUrl, false);
    xmlHttp.open("GET", theUrl, false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

function httpPost(theUrl, request) {
    var xmlHttp = null;

    xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", theUrl, true);
    xmlHttp.send(request);
    return xmlHttp.responseText;
}


function isKeyDown(key) {
    return keyArray[key];
}