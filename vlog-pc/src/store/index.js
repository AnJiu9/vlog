import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    loginStatus: false,
    avatar: '',
    loginUser: {
      id: 1,
      phone: '18805150273',
      password: '4297f44b13955235245b2497399d7a93',
      nickname: 'AnJiu',
      avatar: 'https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/904d05d7-82a3-4856-baf0-b315ecfd7c2c.jpg',
      gender: 2,
      birthday: '1999-10-15',
      address: '江苏省苏州市姑苏区',
      banner: 'https://share--app.oss-cn-hangzhou.aliyuncs.com/bg/20201214164910.png',
      signature: '不期而遇 ｜ 随遇而安',
      createTime: '2020-12-05 12:59:30'
    },
    user:{}
  },
  getters: {},
  mutations: {
    // 登录成功后，用户数据存入本地存储
    login(state, user) {
      state.loginStatus = true
      state.loginUser = user
      state.avatar = user.avatar
      localStorage.setItem('loginUser', JSON.stringify(user))
      // console.log(user)
    },
    //访问用户页面
    visit(state, user){
      state.user = user
    },
    // 退出登录
    logout(state) {
      state.loginStatus = false
      state.user = {}
      state.avatar = ''
      localStorage.removeItem('user')
    },
    setAvatar(state, data) {
      state.avatar = data
    },
    // 修改资料
    editUserInfo(state, obj) {
      if (state.user) {
        state.user.nickname = obj.nickname
        state.user.password = obj.password
        state.user.avatar = obj.avatar
        state.user.gender = obj.gender
        state.user.birthday = obj.birthday
        state.user.address = obj.address
        localStorage.setItem('user', JSON.stringify(state.user))
      }
    }
  },
  actions: {}
})