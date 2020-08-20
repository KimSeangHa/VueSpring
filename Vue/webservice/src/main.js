import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'
import VueMoment from 'vue-moment'

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.use(VueMoment)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
