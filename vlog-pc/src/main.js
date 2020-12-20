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

//引入富文本编辑器
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
Vue.use(VueQuillEditor);

Vue.config.productionTip = false
Vue.use(VueAxios,axios,VDistpicker)
new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App)
}).$mount('#app')