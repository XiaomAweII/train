import {createRouter, createWebHistory} from 'vue-router';

const routes = [
    {
        path: '/',
        component: () => import('../views/MainView.vue'),
        children: [{
            path: 'welcome',
            component: () => import('../views/main/WelcomeView.vue'),
        },{
            path: 'about',
            component: () => import('../views/main/AboutView.vue'),
        },{
            path: 'station',
            component: () => import('../views/main/StationView.vue'),
        },{
            path: 'train',
            component: () => import('../views/main/TrainView.vue'),
        },{
            path: 'train-station',
            component: () => import('../views/main/TrainStationView.vue'),
        },{
            path: 'train-carriage',
            component: () => import('../views/main/TrainCarriageView.vue'),
        },{
            path: 'train-seat',
            component: () => import('../views/main/TrainSeatView.vue'),
        },{
            // 注意和其他路由的区别, 这是为了以后, 是可以写多级路由的
            path: 'batch/job',
            name: 'batch/job',
            component: () => import('../views/main/JobView.vue'),
        }]
    },
    {
        path: '',
        redirect: '/welcome'
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})


export default router
