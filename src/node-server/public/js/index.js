var _createClass = function () {function defineProperties(target, props) {for (var i = 0; i < props.length; i++) {var descriptor = props[i];descriptor.enumerable = descriptor.enumerable || false;descriptor.configurable = true;if ("value" in descriptor) descriptor.writable = true;Object.defineProperty(target, descriptor.key, descriptor);}}return function (Constructor, protoProps, staticProps) {if (protoProps) defineProperties(Constructor.prototype, protoProps);if (staticProps) defineProperties(Constructor, staticProps);return Constructor;};}();function _classCallCheck(instance, Constructor) {if (!(instance instanceof Constructor)) {throw new TypeError("Cannot call a class as a function");}} /*
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          Johan Karlsson (DonKarlssonSan)
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          https://twitter.com/DonKarlssonSan
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          MIT License, see Details View
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        */var
BlackHole = function () {
  function BlackHole(x, y) {_classCallCheck(this, BlackHole);
    this.pos = new Vector(x, y);
  }_createClass(BlackHole, [{ key: "applyGravityOn", value: function applyGravityOn(

    thing) {
      var dist = thing.pos.sub(this.pos);
      var length = dist.getLength();
      var g = 2000 / (length * length);

      // We keep the angle of the distance
      dist.setLength(g);
      thing.vel.subFrom(dist);
    } }]);return BlackHole;}();var


Particle = function () {
  function Particle(x, y) {_classCallCheck(this, Particle);
    this.pos = new Vector(x, y);
    this.vel = new Vector(0, 0);
  }_createClass(Particle, [{ key: "move", value: function move(

    force) {
      if (this.vel.getLength() > 4) {
        this.vel.setLength(4);
      }
      this.pos.addTo(this.vel);
    } }, { key: "draw", value: function draw()

    {
      var r = this.pos.sub(new Vector(w / 2, h / 2)).getLength() / 60;
      ctx.beginPath();
      ctx.arc(this.pos.x, this.pos.y, r, 0, Math.PI * 2);
      ctx.fill();
    } }]);return Particle;}();


var canvas = void 0;
var ctx = void 0;
var w = void 0,h = void 0;
var particles = void 0;
var blackHole = void 0;

function setup() {
  canvas = document.querySelector("#canvas");
  ctx = canvas.getContext("2d");
  reset();
  setupParticles();
  blackHole = new BlackHole(w / 2, h / 2);
  window.addEventListener("resize", reset);
}

function setupParticles() {
  particles = [];

  for (var i = 0; i < 10; i++) {
    var p = new Particle(Math.random() * w, Math.random() * h);
    particles.push(p);
  }
}

function reset() {
  w = canvas.width = window.innerWidth;
  h = canvas.height = window.innerHeight;
}

function draw() {
  requestAnimationFrame(draw);
  ctx.fillStyle = "black";
  ctx.fillRect(0, 0, w, h);
  ctx.fillStyle = "white";
  particles.forEach(function (p) {
    blackHole.applyGravityOn(p);
    p.draw();
    p.move();
  });
  var newParticle = new Particle(random(-50, w + 50), random(-50, h + 50));
  particles.push(newParticle);
  particles = particles.filter(function (p) {return blackHole.pos.sub(p.pos).getLength() > 2;});
}

function random(min, max) {
  if (max === undefined) {
    max = min;
    min = 0;
  }
  return Math.floor(Math.random() * (max - min)) + min;
}

setup();
draw();