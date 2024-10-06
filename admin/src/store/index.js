import {createStore} from 'vuex'

const MEMBER = "MEMBER";

export default createStore({
    state: {
        member: window.SessionStorage.get(MEMBER) || {} // 避免控制针
    },
    getters: {},
    mutations: {
        setMember(state, _member) {
            state.member = _member;
            window.SessionStorage.set(MEMBER, _member);
        }
    },
    actions: {
        //相当于异步任务
    },
    modules: {}
})
