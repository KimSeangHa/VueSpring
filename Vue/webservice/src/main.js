import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'
import VueMoment from 'vue-moment'
import CKEditor from '@ckeditor/ckeditor5-vue';

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.use(VueMoment)
Vue.use(CKEditor)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
