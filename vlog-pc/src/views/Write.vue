<template>
    <v-main>
        <v-row class="mx-6">
            <!-- 标签输入框，数据绑定title变量 -->
            <v-text-field v-model="title" label="标题" required></v-text-field>
        </v-row>
        <v-row class="mx-3">
            <v-col cols="12" md="6">
                <!-- 遍历类别数组，数据绑定category变量 -->
                <v-select :items="items" label="选择类别" v-model="category"></v-select>
            </v-col>
        </v-row>
        <v-row class="mx-3">
            <v-checkbox
                v-for="(tag, index) in tags"
                :key="index"
                v-model="tagList"
                :label="tag.tagName"
                color="primary"
                :value="tag.tagName"
                hide-details
                class="mr-3"
            />
        </v-row>
        <v-row>
            <v-col cols="12" md="12" class="px-6">
                <!-- 使用富文本编辑器，绑定content变量 -->
                <quill-editor
                    style="height:500px;"
                    ref="myQuillEditor"
                    v-model="content"
                    :options="editorOption"
                    @blur="onEditorBlur($event)"
                    @focus="onEditorFocus($event)"
                    @ready="onEditorReady($event)"
                />
            </v-col>
        </v-row>
        <v-row justify="center" class="mx-4 mt-12">
            <v-col cols="12" md="4" class="px-6">
                <v-btn color="primary" block class="pa-6" @click="submit">提交</v-btn>
            </v-col>
        </v-row>
    </v-main>
</template>

<script>
import {quillEditor} from 'vue-quill-editor'
import {mapState} from 'vuex'
export default {
    components: {quillEditor},
    data(){
        return{
            title: '',  //标题，文本输入框数据绑定
            items:['数学','深度学习','编程算法','计算机知识','随笔'],   //分类数组，用下拉框显示
            category:'',    //分类，下拉框数据绑定
            content: null,  //内容，富文本框数据绑定
            tags:[],    //向后端请求所有标签数据，得到的数据存入该数组
            tagList:[], //标签数组，复选框数据绑定
            editorOption: {
                //富文本编辑器选项配置
                modules: {
                toolbar: [
                    ['bold', 'italic', 'underline', 'strike'], // 加粗 斜体 下划线 删除线
                    ['blockquote', 'code-block'], // 引用  代码块
                    [{ header: 1 }, { header: 2 }], // 1、2 级标题
                    [{ list: 'ordered' }, { list: 'bullet' }], // 有序、无序列表
                    [{ script: 'sub' }, { script: 'super' }], // 上标/下标
                    [{ indent: '-1' }, { indent: '+1' }], // 缩进
                    [{ direction: 'rtl' }], // 文本方向
                    [{ size: ['small', false, 'large', 'huge'] }], // 字体大小
                    [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题
                    [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
                    [{ font: [] }], // 字体种类
                    [{ align: [] }], // 对齐方式
                    ['clean'], // 清除文本格式
                    ['link', 'image', 'video'] // 链接、图片、视频
                ] //工具菜单栏配置
                },
                placeholder: '请在这里添加描述', //富文本框提示
                readyOnly: false, //是否只读
                theme: 'snow', //主题 snow/bubble
                syntax: true //语法检测
            }
        }
    },
    created(){
        //请求所有标签数据，给发布文章的时候提供选择
        this.axios.get('/tag/all').then((res) => {
            // console.log(res.data.data)
            this.tags = res.data.data
            //只截取前20个展示
            this.tags = this.tags.slice(0,20)
        })
    },
    computed:{
        //计算属性editor就指向了当前的富文本编辑器
        editor(){
            return this.$refs.myQuillEditor.quill
        },
        ...mapState({
            loginStatus: (state) => state.loginStatus,
            loginUser: (state) => state.loginUser
        })
    },
    methods:{
        //失去焦点
        onEditorBlur(editor){
            console.log('editor blur!', editor)
        },
        //获得焦点
        onEditorFocus(editor){
            console.log('editor focus!', editor)
        },
        //编辑器准备就绪
        onEditorReady(editor){
            console.log('editor ready!', editor)
        },
        //编辑器内容发生改变
        onEditorChange({editor, html, text}){
            console.log('editor change!', editor, html, text)
            this.content = html //把当前富文本编辑器的内容实时赋给this.content
        },
        generateUUID(){
            var d = new Date().getTime()
            var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,function(c){
                var r = (d + Math.random() * 16) % 16 | 0
                d= Math.floor(d / 16)
                return (c == 'x' ? r : (r & 0x7) | 0x8).toString(16)
            })
            return uuid
        },
        submit(){
            let id = this.generateUUID()    //生成随机UUID作为文章id
            let list = []
            //遍历复选框数组，得到选中的标签，和文章id拼成一个个的ArticleTag对象，加入list数组
            this.tagList.forEach((element) => {
                let articleTag = {articleId : id, tagName: element}
                list.push(articleTag)
            })
            //创建article对象，即向后端请求发文章接口需要传递的参数
            let article = {
                id: id,
                userId: this.user.id,
                title: this.title,
                category: this.category,
                summary:'文章摘要文章摘要文章摘要文章摘要文章摘要文章摘要文章摘要文章摘要文章摘要文章摘要文章摘要',
                content: this.content,
                tagList: list
            }
            console.log(JSON.stringify(article))
            this.axios({
                url:'/article/post',
                method: 'POST',
                data: article
            }).then((res) => {
                console.log(res.data.data)
                //发表成功跳回首页
                if(res.data.code === 1){
                    alert('发布成功'),
                    this.$router.push('/index')
                }else{
                    alert(res.data.msg)
                }
            })
        }
    }
}
</script>

<style>

</style>