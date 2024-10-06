import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue'
import * as Icons from '@ant-design/icons-vue'
import axios from 'axios'
import './assets/js/enums';


const app = createApp(App)
app.use(Antd).use(store).use(router).mount('#app')

//全局使用图表
const icons = Icons
for (const i in icons) {
    app.component(i, icons[i]);
}

/**
 * axios拦截器 axios.ininterceptors
 */
//拦截request
axios.interceptors.request.use(function (config) {
    console.log('请求参数：', config);
    return config;
}, error => {
    //失败后拒绝
    return Promise.reject(error);
});
//拦截response-返回结果
axios.interceptors.response.use(function (response) {
    console.log('返回结果：', response);
    return response;
}, error => {
    console.log('返回错误：', error);
    return Promise.reject(error);
});

//设置统一axios
axios.defaults.baseURL = process.env.VUE_APP_SERVER;
//打印日志
console.log('环境：', process.env.NODE_ENV);
console.log('服务端：', process.env.VUE_APP_SERVER);
