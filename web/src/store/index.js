import {createStore} from 'vuex'

export default createStore({
    state: {
        //state相当于属性, 定义属性都是在这里
        member: {}
    },
    getters: {},
    mutations: {
        //mutations相当于set方法, 修改属性都在这里
        setMember(state, _member) {
            state.member = _member
        }
    },
    actions: {
        //相当于异步任务
    },
    modules: {
        //模块的使用, 如果项目很大,模块很多的话使用
        // a:{
        //     state: {
        //         //state相当于属性, 定义属性都是在这里
        //         member: {}
        //     },
        //     getters: {},
        //     mutations: {
        //         //mutations相当于set方法, 修改属性都在这里
        //         setMember(state, _member) {
        //             state.member = _member
        //         }
        //     },
        //     actions: {
        //         //相当于异步任务
        //
        //     },
        // },
        // b:{
        //     state: {
        //         //state相当于属性, 定义属性都是在这里
        //         member: {}
        //     },
        //     getters: {},
        //     mutations: {
        //         //mutations相当于set方法, 修改属性都在这里
        //         setMember(state, _member) {
        //             state.member = _member
        //         }
        //     },
        //     actions: {
        //         //相当于异步任务
        //
        //     },
        // }
    }
})
