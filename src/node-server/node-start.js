var express = require('express');
var app = express();
var bodyParse = require('body-parser');
var Config = require('./Config');

var port = process.env.PORT || 3001;

var db = require('./db');

app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');

app.use('/public', express.static(__dirname + '/public'));
app.use(express.static(__dirname + '/public'));
app.use(bodyParse.json());
app.use(bodyParse.urlencoded({extended: true}));
app.use(require('./controllers'));

app.listen(port, function() {
    console.log('Node is listen to port:' + port);
})