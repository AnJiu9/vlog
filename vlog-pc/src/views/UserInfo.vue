<template>
  <div>
    <v-form ref="form" v-model="valid" lazy-validation v-if="user.id === loginUser.id">
        <v-text-field v-model="nickname" :counter="10" :rules="nameRules" label="昵称"></v-text-field>
        <v-select
            v-model = "genderSelect"
            :items="items"
            :rules="[(v) => !!v || 'Gender is required']"
            label="性别"
            required
        ></v-select>
        <v-row justify="start">
            <v-date-picker v-model="birthdayPicker" elevation="5" header-color="green lighten-1" color="yellow darken-3"></v-date-picker>
        </v-row>

        <v-row justify="start" class="my-6">
            <v-distpicker
            :province="addressSelect.province"
            :city="addressSelect.city"
            :area="addressSelect.area"
            @province="selectProvince"
            @city="selectCity"
            @area="selectArea"
            ></v-distpicker>
        </v-row>

        <v-btn color="primary" class="mr-12" @click="submit">
            提交
        </v-btn>
        <v-btn color="amber darken-1" @click="reset">
            重置
        </v-btn>
    </v-form>
    <div v-else>
        <h2>欢迎{{loginUser.nickname}}来到我的博客ヽ(✿ﾟ▽ﾟ)ノ</h2>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import VDistpicker from 'v-distpicker'
export default {
    name: 'UserInfo',
    components: {
        VDistpicker
    },
    data: () => ({
        valid: true,
        nickname: '',
        nameRules: [
            (v) => !!v || 'Name is required',
            (v) => (v && v.length <= 10) || 'Name must be less than 10 charactors'
        ],
        genderSelect: null,
        items: ['保密','男','女'],
        checkbox: false,
        birthdayPicker: new Date().toISOString().substr(0,10),
        addressSelect: { province:'江苏省', city:'南京市', area:'栖霞区'}
    }),
    computed: {
        ...mapState({
            loginStatus: (state) => state.loginStatus,
            loginUser: (state) => state.loginUser,
            user: (state) => state.user
        })
    },
    created() {},
    methods: {
        reset(){
            this.$refs.form.reset()
        },
        selectProvince(v){
            // console.log(v)
            this.addressSelect.province = v.value
        },
        selectCity(v){
            // console.log(v)
            this.addressSelect.city = v.value
        },
        selectArea(v){
            // console.log(v)
            this.addressSelect.area = v.value
        },
        getGenderTxt(){
            switch (this.genderSelect){
                case 0:
                    return '保密'
                case 1:
                    return '男'
                case 2:
                    return '女'
            }
        },
        submit(){
            // alert(this.items.indexOf(this.genderSelect))
            let newUser = this.user
            newUser.nickname = this.nickname
            newUser.gender = this.items.indexOf(this.genderSelect)
            newUser.birthday = this.birthdayPicker
            newUser.address = `${this.addressSelect.province}${this.addressSelect.city}${this.addressSelect.area}`
            this.$store.commit('editUserInfo', newUser)
            // alert(JSON.stringify(newUser))
            this.axios({
                method: 'post',
                url: '/user/update',
                data: newUser
            }).then((res) => {
                console.log(JSON.stringify(res.data.data))
                this.$store.commit('login', res.data.data)
            })
        }
    }
}
</script>

<style lang="scss">

</style>