
const mysql = require('mysql');
const Config = require('./Config');

var db = mysql.createPool({
    host: Config.dbAddress,
    user: Config.dbUserName,
    password: Config.dbPassword,
    database: Config.dbName,
    multipleStatements: true,
    supportBigNumbers: true,
    bigNumberStrings: true,
    dateStrings: true,
}); //连接池

module.exports = db;
