var express = require('express');
var router = express.Router();

var db = require('../../db');

router.all('/', function(req, res){
    res.send('');
});

/**
 * @param username password
 * @return token 基础信息
 */
router.post('/signIn',(req,res)=>{
    db.query('SELECT * FROM CNCAB_USER_MANAGER WHERE username=?',
        [req.body.username],
        (e,d)=>{
        if(d.length==0){
            res.json({errorCode:1,message:"用户不存在",data:{}})
        }else {
            if(!req.body.password==d[0].password){
                res.json({errorCode:2,message:"密码不匹配",data:{}})
            }else {
                checkCodeCreate((cCode)=>{
                    let tokenCode = cryptPwd(Date().toString()+cCode)
                    db.query('UPDATE CNCAB_USER_MANAGER SET token=? WHERE username=?',
                        [tokenCode,req.body.username],
                        (er,da)=>{
                            if(!er) res.json({errorCode:0,message:"登陆成功",data:{
                                    loginTime:Date().toLocaleString(),
                                    memberName:d[0].username,
                                    isNormal:true,
                                    headSculpture:d[0].headSculpture,
                                    token:tokenCode
                                }})
                            else res.json({errorCode:999,message:"服务器错误",data:{}})
                        })
                })
            }
        }
        })
})

/**
 * @param username password email cellphone sec headSculpture
 * @return token 基础信息
 */
router.post('/signUp',(req,res)=>{
    let message = [];
    for (let key in req.body){
        message.push(req.body[key])
    }
    db.query('INSERT INTO CNCAB_USER_MANAGER VALUES(,?,?,?,,?,?,?,)',
        message,
        (e,d)=>{
            if(!e){
                checkCodeCreate((cCode)=>{
                    let tokenCode = cryptPwd(Date().toString()+cCode)
                    db.query('UPDATE CNCAB_USER_MANAGER SET token=? WHERE username=?',
                        [tokenCode,req.body.username],
                        (er,da)=>{
                            if(!er) res.json({errorCode:0,message:"注册成功",data:{
                                    loginTime:Date().toLocaleString(),
                                    memberName:d[0].username,
                                    isNormal:true,
                                    headSculpture:d[0].headSculpture,
                                    token:tokenCode
                                }})
                            else res.json({errorCode:999,message:"服务器错误",data:{}})
                        })
                })
            }else {
                    res.json({
                        errorCode:999,
                        message:e,
                        data:{}
                })
            }
        })
})


/**
 * 工具
 * **/


function checkCodeCreate(callback){//6位
    let code='';
    for(let i=0;i<6;i++){
        code+=Math.floor(Math.random()*10)
        if(i==5){
            callback(code)
        }
    }
}

function cryptPwd(password) {//生成md5数值
    var md5 = crypto.createHash('md5');
    return md5.update(password).digest('hex');
}

module.exports = router;
