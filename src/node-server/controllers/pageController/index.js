var express = require('express');
var router = express.Router();

router.all('/', function(req, res){
    res.send('');
});

router.use('/title', require('./titleBar'));



module.exports = router;
