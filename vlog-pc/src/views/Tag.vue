<template>
  <v-row align="center" justify="center" class="px-12">
      <v-col cols="12" class="shrink" md="2" v-for="(tag, index) in tags" :key="index">
          <v-chip
          :color="tag.tagColor"
          label
          text-color="dark-grey"
          large
          style="font-size:18px; font-weight:500;"
          class="px-10"
          >
            {{tag.tagName}}
          </v-chip>
      </v-col>
  </v-row>
</template>

<script>
export default {
    name: 'Tags',
    data: () => ({
        tags: []
    }),
    created(){
        this.axios.get('/tag/all').then((res) => {
            console.log(res.data.data)
            this.tags = res.data.data
        })
    }
}
</script>

<style>

</style>