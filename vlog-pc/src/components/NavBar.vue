<template>
  <div class="nav-wrapper" ref="navbar" :class="{bgColor:active}">
    <v-row class="nav-transparent mx-2 pl-12" justify="space-between" align="center">
      <v-col cols="12" md="3">
        <router-link to="/index">
          <h1 class="lime--text">{{user.nickname}}的博客</h1>
        </router-link>
      </v-col>
      <v-col cols="12" md="8">
        <router-link v-for="(item, index) in items" :to="item.path" :key="index" link class="mr-2">
          <v-btn text small class="nav-item text-md-h6 white--text" >
            <v-icon right dark class="mr-2">
              {{item.icon}}
            </v-icon>
            {{item.text}}
          </v-btn>
        </router-link>
      </v-col>

      <v-col cols="12" md="1">
        <v-menu right top>
          <template v-slot:activator="{on, attrs}">
            <v-btn icon v-bind="attrs" v-on="on">
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </template>
          <v-list color="accent">
            <v-list-item link>
              <v-list-item-title>系统设置</v-list-item-title>
            </v-list-item>
            <v-list-item link>
              <v-list-item-title>主题设置</v-list-item-title>
            </v-list-item>
            <v-list-item @click="logout" link>
              <v-list-item-title>退出登录</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'NavBar',
  data: () => ({
    active: false,
    items: [
      {
        icon: 'mdi-home',
        text: '主页',
        path: '/index'
      },
      {
        icon: 'mdi-format-list-bulleted-square',
        text: '分类',
        path: '/category'
      },
      {
        icon: 'mdi-message',
        text: '消息',
        path: '/message'
      },
      {
        icon: 'mdi-tag',
        text: '标签',
        path: '/tag'
      },
      {
        icon: 'mdi-link',
        text: '友链',
        path: '/friend'
      },
      {
        icon: 'mdi-account',
        text: '我的',
        path: '/my'
      },
      {
        icon: 'mdi-alert-circle',
        text: '暂无',
        path: '/404'
      }
    ]
  }),
  computed: {
    ...mapState({
        loginStatus: (state) => state.loginStatus,
        user: (state) => state.user
    })
  },
  mounted(){
    //等到页面产生滚动才执行监听
    this.$nextTick(function(){
      window.addEventListener('scroll', this.onScroll)
    })
  },
  methods:{
    onScroll(){
      //取得滚动的起点，也就是文档流的顶部
      let scrolled = document.documentElement.scrollTop || document.body.scrollTop
      //顶部nav-wrapper元素的高度
      // if (document.getElementsByClassName('nav-wrapper')[0]){
      //   header_height = document.getElementsByClassName('nav-bar')[0].offsetHeight
      //   console.log('滚动条位置:' + scrolled)
      // }
      //如果当前滚动点大于指定值， active变为true，当前导航就会加上背景渐变色；否则还原
      this.active = scrolled > 200
    },
    logout(){
      this.$store.commit('logout')
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="scss" scoped>
.nav-wrapper{
  position: fixed;
  top: 10;
  left: 0;
  right: 0;
  z-index: 1000;
}
.bgColor{
  background-image: linear-gradient(to right, rgb(31, 64, 55) 0%, rgb(153, 242, 200) 100%);
}
</style>