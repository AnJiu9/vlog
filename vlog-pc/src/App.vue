<template>
  <v-app id="app">
    <v-main>
      <router-view />
    </v-main>
    <v-btn class="mx-2" fab dark color="light-green darken-3" fixed bottom right v-if="show" @click="backToTop">
      <v-icon dark>
        mdi-arrow-up
      </v-icon>
    </v-btn>
  </v-app>
</template>

<script>
// import HelloWorld from './components/HelloWorld'
//防抖函数
// function debounce(func, wait = 1000){
//   let timeout
//   return function(event){
//     clearTimeout(timeout)
//     timeout = setTimeout(() => {
//       func.call(this.event)
//     }, wait);
//   }
// }
export default {
  // name: 'App',

  // components: {
  //   HelloWorld
  // },

  data: () => ({
    show: false
  }),
  mounted(){
    //等到页面产生滚动才执行监听
    this.$nextTick(function(){
      window.addEventListener('scroll', this.scrollToTop)
    })
  },
  methods:{
    // onScroll(){
    //   let scrolled = document.documentElement.scrollTop || document.body.scrollTop
    //   this.show = scrolled > 400
    // },
    // onScroll: debounce(function(e){
    //   console.log(e)
    //   let scrolled = document.documentElement.scrollTop || document.body.scrollTop
    //   this.show = scrolled > 400
    // }),
    backToTop(){
      //加定时器，平滑过渡回到顶部
      let timer = setInterval(() => {
        let ispeed = Math.floor(-this.scrollTop / 5)
        document.documentElement.scrollTop = document.body.scrollTop = this.scrollTop + ispeed
        if (this.scrollTop === 0){
          clearInterval(timer)
        }
      },16)
    },
    //计算距离顶部的高度， 当高度大于400显示回顶部图标，小于400则隐藏
    scrollToTop(){
      let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      this.scrollTop = scrollTop
      this.show = scrollTop > 200
    }
  }
}
</script>

<style lang="scss">
.move {
  animation-duration: 1s;
  animation-delay: 0.5s;
}
a:link{
  text-decoration: none;
}
.bg-color {
  background-image: linear-gradient(to right, rgb(67, 198, 172) 0%, rgb(248, 255, 174) 100%);
  opacity: 0.5;
}
</style>
