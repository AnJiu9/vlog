<template>
  <div class="bg row">
    <v-form ref="form" v-model="valid" lazy-validation class="col" v-if="!flag">
        <v-text-field v-model="phone" :counter="11" :rules="phoneRules" label="Phone" required></v-text-field>
        <v-text-field v-model="password" :rules="passRules" label="Password" required></v-text-field>
        <v-text-field v-model="verifyCode" label="verifyCode" required></v-text-field>
        <img class="verify" @click.prevent="getVerifyCode" ref="codeImg" />
      <v-checkbox
        v-model="checkbox"
        :rules="[(v) => !!v || '同意才能继续!']"
        label="同意社区协议?"
        required
      ></v-checkbox>
      <!-- <v-btn :disabled="!valid" color="success" class="mr-4" @click="validate">
        验证
      </v-btn> -->
      <v-btn color="primary" class="mr-4" @click="submit">
        登录
      </v-btn>
      <v-btn color="amber darken-1" @click="reset">
        重置
      </v-btn>
    </v-form>

    <v-overlay absolute  z-index="5" class="mask"></v-overlay>
  </div>
</template>
<script>
export default {
  name: 'Login',
  data: () => ({
    flag: false,
    valid: true,
    phone: '18805150273',
    phoneRules: [
      (v) => !!v || '手机号不能为空',
      (v) => (v && v.length === 11) || '手机号必须为11位'
    ],
    password: '123123',
    passRules: [(v) => !!v || '密码不能为空', (v) => (v.length >=6 && v.length <= 10) || '密码必须在6到10位之间'],
    checkbox: false,
    verifyCode:'',
  }),

  created(){
    //页面创建，即请求获得验证码去填充图片标签
    this.getVerifyCode()
  },

  methods: {
    validate() {
      this.$refs.form.validate()
    },
    reset() {
      this.$refs.form.reset()
    },
    getVerifyCode(){
      //点击验证码图片，重新请求验证码
      this.axios.get('/captcha?phone=' + this.phone, { responseType: 'blob'}).then((res) => {
        let img = this.$refs.codeImg
        let url = window.URL.createObjectURL(res.data)
        img.src = url
      })
    },
    submit(){
      this.axios({
        method: 'POST',
        url: '/user/captchaLogin',
        data: {
          phone: this.phone,
          password: this.password,
          captcha: this.verifyCode
        }
      }).then((res) => {
        if(res.data.code === 1){
          this.flag = !this.flag
          alert('登录成功')
          this.$store.commit('login', res.data.data);
          this.$router.push('/')
        }else {
          alert('登录失败')
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.row {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  .col {
    z-index: 10;
    flex: 0 0 50%;
    border-radius: 10px;
    // height: 80px;
    // line-height: 80px;
    // background-color: #0f9d58;
    background-image: linear-gradient(to right, #c2f3e883 0%, #f1cdb6be 30%);
    // margin-top: -300px;

  }
}
.bg {
  // width: 100%;
  height: 100vh;
  background: url('https://share--app.oss-cn-hangzhou.aliyuncs.com/bg/20201210231200.jpg');
  background-color: #666666;
  background-size: cover;
  background-image: fixed;

}
.mask {
  background-image: linear-gradient(to right, #bf30ac 0%, #0f9d58 100%);
  opacity: 0.45;
  z-index: 5;
}
</style>
