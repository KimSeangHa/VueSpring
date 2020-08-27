<template>
    <div class="login_form">
        <div class="login_content_form">
            <table class="login_table">
                <tr>
                    <td class="login_text">ID : </td>
                    <td class="login_input"><input v-model="id" type="text" placeholder="아이디를 입력해주세요."></td>
                </tr>
                <tr>
                    <td class="login_text">Password : </td>
                    <td class="login_input"><input v-model="password" type="password" placeholder="비밀번호를 입력해주세요."></td>
                </tr>
            </table>
            <div class="login_btn_form">
                <button @click="login()" class="login_btn">로그인</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Login",
    data: function() {
        return {
            id: '',
            password: ''
        }
    },
    methods: {
        login () {
            this.$store.commit('login/Login', { id: this.id, password: this.password })
        }
    }
}
</script>

<style>
.login_content_form {
    text-align: left;
    font-size: 16px;
    color: #696868;
    margin-top: 80px;
    padding-bottom: 50px;
}

.login_table {
    margin:0 auto;
}

.login_table .login_text{
    width:180px;
    height:60px;
}

.login_chk_form {
    width: 100px;
    height: 60px;
}
.login_table .login_input input{
    height: 40px;
    width: 250px;
}

.login_btn_form {
    text-align: center;
    margin-top: 50px;
}

.login_btn {
    width: 80px;
    height: 40px;
    position: relative;
    border: 1px solid #ddd;
    background-color : #fff;
    border-radius: 8px;
    font-size: 14px;
}

.login_chk_btn {
    width: 80px;
    height: 40px;
    position: relative;
    border: 1px solid #ddd;
    background-color : #fff;
    border-radius: 8px;
    font-size: 13px;
    margin-left: 10px;
}
.login_btn:hover, .register_chk_btn:hover{
    background-color : #172d58;
    color:#fff;
}

</style>
