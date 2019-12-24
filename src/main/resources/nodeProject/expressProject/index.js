const express = require('express');
const app = express();
//app.METHOD(PATH, HANDLER),METHOD可以为get,post,put,delete
app.get('/hello', (req, res, next) => res.send('hello don'));
//app.use(PATH, HANDLER)中的HANDLER可以为函数也可以为router对象
app.use('/hello2', (req, res, next) => res.send('hello don2'));

const myRouterObj = require('./myrouterobj');
//访问地址：localhost:3000/useRouter/ot
app.use('/useRouter', myRouterObj.obj);
//有了router对象，可以将路由套用至应用程式
const router = express.Router();
//在请求之前执行一些方法
router.use((req, res, next) => {
    console.log(req);
    next();
})
router.get('/apple', (req, res) => res.send('apple'));
router.get('/orange', (req, res) => res.send('orange'));
//这样在访问/apple和/orange前就统一加上了/app,不需要写很多遍/app
//参数路由（:name）访问地址:localhost:3000/app/hello/don
router.get('/hello/:name', (req, res) => res.send(req.params.name));
app.use('/app', router);

//直接访问public下的静态文件
//访问地址：localhost:3000/bg2.jpg
app.use(express.static('public'));

app.listen(3000, () => console.log('监听3000端口'));