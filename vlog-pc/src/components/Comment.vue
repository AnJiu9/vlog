<template>
   <div>
       <v-textarea outlined label="请输入内容" v-model="content"></v-textarea>
       <v-btn @click="saveComment" color="bg-color" rounded dark>发表评论</v-btn>
   </div>
</template>

<script>
export default {
    data(){
        return{
            content: ''
        }
    },
    methods:{
        saveComment(){
            //向父组件传递onClick事件，并将评论内容带去
            this.$emit('onClick',this.content)
            //点击发送后文本框清空
            this.content = ''
        }
    }
}
</script>

<style lang="scss">

</style>