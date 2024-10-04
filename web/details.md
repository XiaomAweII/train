# 创建基于vue3的web模块

## 1. 使用`vue create web`创建web模块

### 弹出选项窗口

选择`Manually select features`(即手动选择属性)

按下回车进入到第一个选择当中

### 配置介绍

1. Babel: 编译用的可选
2. TypeScript: 不选, 使用原生js即可
3. Progressive Web App (PWA) Support
4. Router: 多页面应用需要Router
5. Vuex: 全局保存变量用的,需要
6. CSS Pre-processors: 目前不需要
7. Linter/Formatter: 代码检查规范,可选
8. Unit Testing: 测试,不选
9. E2E Testing: 测试,不选

### 选择版本

选择 3.x 的版本, 即Vue3

### 历史模式

控制台询问是否使用历史模式, 所谓历史模式, 体现在浏览器地址, 正常地址就是一堆/斜杠, 有的前边有#号, 就不是历史模式,
是hash模式, 为了使得地址更好看, 我们选择历史模式;

### 代码检查规范配置

- ESLint with error prevention only : 选这个 , 最简单的报错配置
- ESLint + Airbnb config
- ESLint + Standard config
- ESLint + Prettier : 代码更漂亮, 少个空格都可能报错

### 选择什么时候检查代码规范

- Lint on save : 选这个, 在保存的时候检查
- Lint and fix on commit : 提交代码到git的时候检查

### 配置到哪里

- In dedicated config files : 每一个生成到各自的文件
- In package.json : 选这个, 生成到一个总体的文件

### 询问是否保存以上配置信息作为一个快捷方式

可以选是或不是,无所谓

## 2. 启动

1. 现在默认是使用 `npm run server` 启动, 可以在web模块下`package.json`当中, 找到`scripts`中的`serve`,
   `"serve": "vue-cli-service serve"`,这个`server`就是启动命令里的`server`,启动的端口默认是8080, 可以加参数`--port 9000`,使用9000端口启动, 启动的话有三种方式, 可以使用`npm`命令,也可以在`package.json`文件当中找到对应位置,有个启动,也可以找到`package.json`右键,点击`Show npm Scripts`双击`serve`启动
2. 打包的话就是使用`build`指令, 对应的就是这个`"build": "vue-cli-service build"`

## 3. web模块

### node_modules

相当于maven帮我们下载下来的jar包, 后端都在本地maven仓库, 前端就在这里, 很多依赖, 不进行git上传

### public

这里的`indexl.html`其实就是我们启动的首页, 当然会加载很多东西, 是一个入口, 页面内容不在这里

### src

这里包括我们后端源代码

#### assets

资源文件, 比如图片资源

#### components

组件, 自己的组件都在这里

#### router

路由, 进行页面跳转, 在router可以定义项目有哪些路径, 所有路由都在这里

#### store

存放一些全局变量, 方便页面之间传递参数

#### view

后续的所有页面都在这里

### App.vue

可以在main.js中查看一下

```js
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

createApp(App).use(store).use(router).mount('#app')
```

含义是创建一个App,用到了store,用到了router,#app的意思是ip等于app的div,css选择器

这样App.vue就会渲染到index.html当中

所以,页面的内容, 通过main.js(框架配置入口), 将index.html(html页面入口)和App.vue(vue页面入口)关联起来

文件当中还有一个`<router-view/>`, 各个路由(url地址)对应不同的内容(vue页面),会填充到<router-view/>区域

package.json还可以查看依赖, 后期如果想用什么组件, 可以直接在文件中添加依赖


# Web模块集成Ant Design Vue

Ant Design Vue 是阿里团队开源的基于Vue的UI组件, 文档链接: antdv.com

UI组件有很多可选: 一种是选择基于CSS的Bootstrap, 适合各种前端搭建, 一种是选择基于Vue的UI组件, 只能用于Vue框架

Element(由饿了么团队开源)在Vue2时是最热门框架, Vue3出来后, 没有第一时间跟着升级, 后来才出来基于Vue3的Element Plus

安装`npm i --save ant-design-vue`,这里的i就是install,--save就会引入引来而不是只下载,老版本需要, 新版本不需要

安装完后会在`package.json`当中发现导入了版本, 还有`package-lock.json`,会有一个`^3.2.15`这里的`^`意思是兼容小版本的升级,然后`package-lock.json`会帮我们锁定版本,避免组件库不兼容

在main.js当中使用全局, css样式最新版的好像不需要引入了, 

安装图标库`npm install --save @ant-design/icons-vue`