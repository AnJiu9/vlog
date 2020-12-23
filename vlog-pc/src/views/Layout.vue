<template>
  <v-app>
    <nav-bar></nav-bar>
    <v-card>
      <v-app-bar
        color="#fcb69f"
        clipped-left
        dark
        :src="user.banner"
        height="500"
      >
        <template v-slot:img="{props}">
          <v-img v-bind="props" gradient="to top right, rgba(67, 198, 172,0.3), rgba(248, 255, 174,0.5)"></v-img>
        </template>
        <v-row justify="center">
          <v-card-title>
            <h1>{{user.signature}}</h1>
          </v-card-title>
        </v-row>
      </v-app-bar>
    </v-card>
    <v-main class="grey lighten-3">
      <v-container class="main-content">
        <router-view/>
      </v-container>
    </v-main>
    <my-footer></my-footer>
  </v-app>
</template>

<script>
import {mapState} from 'vuex'
import NavBar from '../components/NavBar'
import MyFooter from '../components/MyFooter'
export default {
  name: 'Layout',
  components: {
    NavBar,
    MyFooter
  },
  computed: {
    ...mapState({
        loginStatus: (state) => state.loginStatus,
        loginUser: (state) => state.loginUser,
        user: (state) => state.user
    })
  }
}
</script>

<style lang="scss" scoped>
  .main-content{
    box-shadow: 0 15px 35px rgba(50, 50, 93, 0.1), 0 5px 15px rgba(0,0,0,0.07) !important;
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 10px;
    position: relative;
    top: -90px;
    width: 80%;
    margin: 0 auto;
    z-index: 900;
  }
</style>
