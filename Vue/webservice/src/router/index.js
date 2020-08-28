import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const requireAuth = () => (from, to, next) => {
  if (localStorage.getItem('loginAuth')) return next() // loginResult === true면 페이지 이동
  alert('로그인이 필요한 서비스입니다.')
  next('/Login') // loginResult === false면 다시 로그인 화면으로 이동
}

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/test',
    name: 'Test',
    component: () => import('../views/Test.vue')
  },
  {
    path: '/vuex',
    name: 'Vuex',
    component: () => import('@/views/VuexTest.vue')
  },
  {
    path: '/MemoApp',
    name: 'MemoApp',
    component: () => import('@/views/MemoApp.vue'),
    beforeEnter: requireAuth()
  },
  {
    path: '/Register',
    name: 'Register',
    component: () => import('@/views/Register.vue')
  },
  {
    path: '/Login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
