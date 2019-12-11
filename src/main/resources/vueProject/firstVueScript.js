var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!',
        flag: true,
        items: [{
            text: '我是1号'
        }, {
            text: 'Don'
        }, {
            text: 'hello world'
        }]
    },
    methods: {
        firstMethod: function () {
            console.log("firstMethod");
        },
        secondMethod: function () {
            console.log("secondMethod");
            this.message = "响应secondMethod方法";
        }
    }
})