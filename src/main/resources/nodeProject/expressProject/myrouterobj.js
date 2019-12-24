const express = require('express');
const myRouterObj = express.Router();
myRouterObj.get('/ot', (req, res, next) => res.send('hello don,this is ot'));
module.exports = {
    obj: myRouterObj
};