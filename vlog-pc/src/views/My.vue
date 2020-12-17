<template>
  <v-row align="start">
    <v-col cols="20" md="3">
      <side-bar></side-bar>
    </v-col>
    <v-col cols="20" md="17" class="ml-12">
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
    return {}
  },
  created() {},
  methods: {}
}
</script>
