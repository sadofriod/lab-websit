var express = require('express');
var router = express.Router();
var db = require('../../../db')
router.all('/', function(req, res){
    res.send('');
});

/**
 * @param token
 * @return data
 */
router.post('/get',(req,res)=>{
    if(!req.body.token){
        res.end();
    }else {
        db.query('SELECT * FROM CNCAB_TITLE_DATA',(e,d)=>{
            if(!e){
                res.json({code:0,data:d})
            }else {
                res.json({code:999,data:[]})
            }
        })
    }
})

/**
 * @param titleName
 * @return
 */
router.post('/del',(req,res)=>{
    if(!req.body.token){
        res.end();
    }else {
        db.query('DELETE FROM CNCAB_TITLE_DATA WHERE title_name=?',
            [req.body.titleName],
            (e,d)=>{
            if(!e){
                res.json({code:0,data:[]})
            }else {
                res.json({code:999,data:[]})
            }
        })
    }
})

/**
 * @param titleName titleUrl
 * @return
 */
router.post('/add',(req,res)=>{
    let message = [];
    for (let key in req.body){
        message.push(req.body[key])
    }
    if(!req.body.token){
        res.end();
    }else {
        db.query('INSERT INTO CNCAB_TITLE_DATA VALUES (,?,?)',
            message,
            (e,d)=>{
                if(!e){
                    res.json({code:0,data:[]})
                }else {
                    res.json({code:999,data:[]})
                }
            })
    }
})

module.exports = router;
