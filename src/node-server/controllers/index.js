var express = require('express');
var router = express.Router();

router.use('/admin', require('./admin'));
router.use('/state', require('./state'));
router.use('/user', require('./user'));
router.use('/pageController', require('./pageController'));

router.get('/', function(req, res){
    res.render('index');
});

router.post('/signIn', function(req, res){
    res.render('index');
});

router.post('/signUp', function(req, res){
    res.render('index');
});

module.exports = router;
