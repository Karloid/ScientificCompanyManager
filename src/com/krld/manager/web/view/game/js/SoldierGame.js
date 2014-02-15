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

var keyEnum = { W_Key: 0, A_Key: 1, S_Key: 2, D_Key: 3 };
var keyArray = new Array(4);
var keyArrayLastSended = new Array(4);

keyArray[0] = false;
keyArray[1] = false;
keyArray[2] = false;
keyArray[3] = false;

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

    //httpPost("/game/action", tmpkey + " is up");
}


// add the renderer view element to the DOM
document.getElementById("container").appendChild(renderer.view);

//renderer.view.addEventListener("mousedown", function() {});


requestAnimFrame(animate);

var container = new PIXI.DisplayObjectContainer();

// create a soldierTexture from an image path
var soldierTexture = PIXI.Texture.fromImage("game/images/soldier.png");


// create a new Sprite using the soldierTexture
var soldier = new PIXI.Sprite(soldierTexture);

var soldiers = {};
/*
// center the sprites anchor point
soldier.anchor.x = 0.5;
soldier.anchor.y = 0.5;

// move the sprite t the center of the screen
soldier.position.x = 200;
soldier.position.y = 150;
 */
var tiles = new Array(10);
for (var i = 0; i < 10; i++) {
    tiles[i] = new Array(10);
}

var playerId;
var lastUpdateDate = new Date().getTime();
var DELAY = 300;


loadGroundTiles(container, tiles);

//container.addChild(soldier);

stage.addChild(container);

stage.onTouchMove = function (touchData) {
    alert("TOUCH MOVE");
}

function arrayEquals(keyArray, keyArrayLastSended) {
    for (i = 0; i < 4; i++) {
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

            "}"
        httpPost("/game/keyAction", request);
        keyArrayLastSended[0] = keyArray[0];
        keyArrayLastSended[1] = keyArray[1];
        keyArrayLastSended[2] = keyArray[2];
        keyArrayLastSended[3] = keyArray[3];
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
function getPlayer(id) {
    var soldier = soldiers[id];
    if (!soldier)
    {
        soldier = {
            id: id,
            sprite: new PIXI.Sprite(soldierTexture)
        };
        soldier.sprite.anchor.x = 0.5;
        soldier.sprite.anchor.y = 0.5;
        soldiers[id] = soldier;

        container.addChild(soldier.sprite);

    }
    return soldier;
}
function getPlayersPositions() {
    var currentDate = new Date().getTime();
    if (currentDate - lastUpdateDate < DELAY) {
        return;
    }
    lastUpdateDate = currentDate;
    var json = httpGet("/game/players");
    obj = JSON.parse(json);
    for (i = 0; i < obj.players.length; i++) {
        player = getPlayer(obj.players[i].id);
        player.sprite.position.x = obj.players[i].x;
        player.sprite.position.y = obj.players[i].y;
    }

}
function animate() {
    handleUserInput();

    getPlayersPositions();

    requestAnimFrame(animate);

    renderer.render(stage);
}

function loadGroundTiles(container, tiles) {
    var grass1Texture = PIXI.Texture.fromImage("game/images/grass1.png");
    var grass2Texture = PIXI.Texture.fromImage("game/images/grass2.png");
    var json = httpGet("/game/tiles");
    //  alert(json);
    obj = JSON.parse(json);
    WIDTH_WORLD = obj.width;
    HEIGHT_WORLD = obj.height;
    playerId = obj.playerId;
    var tile;
    for (x = 0; x < WIDTH_WORLD; x++) {
        for (y = 0; y < HEIGHT_WORLD; y++) {
            if (obj.tiles[x][y] == 1) {
                tile = new PIXI.Sprite(grass1Texture);
            } else if (obj.tiles[x][y] == 2) {
                tile = new PIXI.Sprite(grass2Texture);
            }
            tile.position.x = x * CELL_SIZE;
            tile.position.y = y * CELL_SIZE;
            container.addChild(tile);
        }
    }
    //   alert(obj.tiles[1][1]);

}

function httpGet(theUrl) {
    var xmlHttp = null;

    xmlHttp = new XMLHttpRequest();
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