import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import VueAxios from 'vue-axios'
import axios from 'axios'
import VDistpicker from 'v-distpicker'
import layer from 'vue-layer'
import 'vue-layer/lib/vue-layer.css'
Vue.prototype.$layer = layer(Vue, {
  msgtime: 3,//目前只有一项，即msg方法的默认消失时间，单位：秒
});
axios.defaults.baseURL='http://vue-jyx.cn.utools.club/api'

Vue.config.productionTip = false
Vue.use(VueAxios,axios,VDistpicker)
new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App)
}).$mount('#app')