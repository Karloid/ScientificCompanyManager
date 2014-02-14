// create an new instance of a pixi stage
var stage = new PIXI.Stage(0xAABBCC);

// create a renderer instance
var renderer = PIXI.autoDetectRenderer(800, 600);

// add the renderer view element to the DOM
document.body.appendChild(renderer.view);

requestAnimFrame(animate);

// create a soldierTexture from an image path
var soldierTexture = PIXI.Texture.fromImage("game/images/soldier.png");
var grass1Texture = PIXI.Texture.fromImage("game/images/grass1.png");
var grass2Texture = PIXI.Texture.fromImage("game/images/grass2.png");

// create a new Sprite using the soldierTexture
var soldier = new PIXI.Sprite(soldierTexture);

// center the sprites anchor point
soldier.anchor.x = 0.5;
soldier.anchor.y = 0.5;

// move the sprite t the center of the screen
soldier.position.x = 200;
soldier.position.y = 150;

var tiles = new Array(10);
for (var i = 0; i < 10; i++) {
    tiles[i] = new Array(10);
}
loadGroundTiles(stage, tiles);

stage.addChild(soldier);

function animate() {

    requestAnimFrame( animate );

    // just for fun, let's rotate mr rabbit a little
    //soldier.rotation += 0.1;

    // render the stage
    renderer.render(stage);
}

function loadGroundTiles(stage, tiles) {
    var json = httpGet("/game/tiles");
    alert(json);
    obj = JSON.parse(json);
    alert(obj);
  //  for (var x in obj) {
  //      for (var y in  )
  //  }
}

function httpGet(theUrl)
{
    var xmlHttp = null;

    xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false );
    xmlHttp.send( null );
    return xmlHttp.responseText;
}