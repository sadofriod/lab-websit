var express = require('express');
var router = express.Router();

router.get('/', function(req, res){
    res.send('The host state');
});

module.exports = router;