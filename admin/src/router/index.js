import {createRouter, createWebHistory} from 'vue-router';

const routes = [
    {
        path: '/',
        component: () => import('../views/MainView.vue'),
        children: [{
            path: 'welcome',
            component: () => import('../views/main/WelcomeView.vue'),
        }, {
            path: 'about',
            component: () => import('../views/main/AboutView.vue'),
        }, {
            path: 'base/',
            children: [
                {
                    path: 'station',
                    component: () => import('../views/main/base/StationView.vue'),
                },
                {
                    path: 'train',
                    component: () => import('../views/main/base/TrainView.vue'),
                },
                {
                    path: 'train-station',
                    component: () => import('../views/main/base/TrainStationView.vue'),
                },
                {
                    path: 'train-carriage',
                    component: () => import('../views/main/base/TrainCarriageView.vue'),
                }, {
                    path: 'train-seat',
                    component: () => import('../views/main/base/TrainSeatView.vue'),
                }
            ]
        }, {
            // 注意和其他路由的区别, 这是为了以后, 是可以写多级路由的
            path: 'batch/',
            children: [{
                path: 'job',
                component: () => import('../views/main/batch/JobView.vue'),
            }]
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
