<template>
  <v-row align="start">
    <v-col cols="12" md="3" class="mr-6">
      <side-bar></side-bar>
    </v-col>
    <v-col cols="12" md="8" class="ml-12">
      <router-view />
    </v-col>
  </v-row>
</template>

<script>
import SideBar from '../components/SideBar'
export default {
  name: 'My',
  components:{
    SideBar
  },
  data() {
    return {
      id:0
    }
  },
  created() {
    this.id = this.$route.params.id
    alert('你要访问的用户id为：' + this.id)
    this.axios({
      method:'GET',
      url: '/user/' + this.id
    }).then((res) => {
      console.log(res.data.data.id)
      this.$store.commit('visit', res.data.data)
    })
  }
}
</script>
