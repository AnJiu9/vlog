<template>
  <div class="container">
    <div class="row">
      <div v-for="(card,index) in cards" :key="index" class="cards">
        <h3>{{ card.title }}</h3>
        <img :src="card.bgImg" class="cover">
        <p>{{card.content}}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data(){
    return {
      cards:[],
    }
  },
  created(){
    this.axios.get("http://localhost:8080/api/cards").then((res) =>{
      console.log(res.data.data)
      this.cards = res.data.data
      console.log(JSON.stringify(this.cards));
    })     
  },   
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .container{
    width: 80%;
    margin: 0 auto;
  }
  .row{
    display: flex;
    flex-wrap: wrap;
  }
  .cards{
    flex: 0 0 25%;
    height: 300px;
    background-color: rgb(153, 158,207);
    padding: 10px;
    margin:  10px;
    border-radius: 10px;
  }
  .cover{
    width: 100%;
    height: 150px;
    border-radius: 5px;
  }
</style>
