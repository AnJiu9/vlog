<template>
  <v-app>
    <nav-bar></nav-bar>
      <div class="bgc">
        <img src="https://share--app.oss-cn-hangzhou.aliyuncs.com/bg/20201215091529.gif" class="img">
          <div class="msg">
            <v-col>
              404
            </v-col>
            <v-col>
              孩子生气离家出走了Ծ‸Ծ
            </v-col>
          </div>
      </div>
    <my-footer></my-footer>
  </v-app>
</template>

<script>
import NavBar from '../components/NavBar'
import MyFooter from '../components/MyFooter'
export default {
  name: 'Page404',
  components:{
    NavBar,
    MyFooter
  },
  data() {
    return {}
  },
  created() {},
  methods: {}
}
</script>
<style lang="scss" scoped>
.bgc {
  // width: 100%;
  height: 100vh;
  // background: url('https://share--app.oss-cn-hangzhou.aliyuncs.com/bg/20201210231200.jpg');
  background-image: linear-gradient(to right,rgb(131, 96, 195,.5), rgb(46, 191, 145,.5));
  // background-image: linear-gradient(to right,rgb(101, 78, 163), rgb(234, 175, 200));
  background-size: cover;
  background-image: fixed;
  text-align: center;
  .img{
    position: absolute;
    width: 30%;
    top: 220px;
    left: 300px;
  }
}
.msg{
  position: absolute;
  top: 250px;
  left: 800px;
  font-size: 30px;
  font-weight: 400;
}
</style>
