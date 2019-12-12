Vue.component(
    //如果使用驼峰命名，在html使用该组件时需要使用-分割
    //W3C 规范中的自定义组件名 (字母全小写且必须包含一个连字符)
    //这些组件是全局注册的。也就是说它们在注册之后可以用在任何新创建的 Vue 根实例 (new Vue) 的模板中。
    'firstComponent', {
        template: '<span>第一个组件，替换</span>'
    }
)

Vue.component('li-component', {
    props: ['item'],
    template: '<li>{{item.text}}</li>'
})

var componentApp = new Vue({
    el: '#componentApp',
    data: {
        items: [{
            text: '1号'
        }, {
            text: '2号'
        }]
    }
})