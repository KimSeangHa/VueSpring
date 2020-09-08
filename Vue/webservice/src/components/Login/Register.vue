<template>
    <div class="register_form">
        <div class="register_title">Sign Up</div>
        <div class="register_content_form">
            <table class="register_table">
                <tr>
                    <td class="registger_text">Name : </td>
                    <td class="register_input"><input v-model="name" type="text" placeholder="이름을 입력해주세요."></td>
                </tr>
                <tr>
                    <td class="registger_text">ID : </td>
                    <td class="register_input"><input v-model="id" type="text" placeholder="아이디를 입력해주세요."></td>
                    <td class="register_chk_form"><button @click="memberChk()" class="register_chk_btn">중복체크</button></td>
                </tr>
                <tr>
                    <td class="registger_text">Password : </td>
                    <td class="register_input"><input v-model="password" type="password" placeholder="비밀번호를 입력해주세요."></td>
                </tr>
                <tr>
                    <td class="registger_text">Re enter Password : </td>
                    <td class="register_input"><input @keyup="passwordChk()" v-model="re_password" type="password" placeholder="비밀번호를 다시 입력해주세요."></td>
                </tr>
            </table>
            <div v-if="!passChk" class="password_chk">비밀번호에 입력된 내용이 일치하지 않습니다.</div>
            <div class="register_btn_form">
                <button @click="register()" class="register_btn">회원가입</button>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: "Register",
    data: function() {
        return {
            name: '',
            id: '',
            password: '',
            re_password: '',
            passChk: false,
            memberOver: false
        }
    },
    methods: {
        reset () {
          this.id = ''
          this.name = ''
          this.password = ''
          this.re_password = ''
          this.passChk = ''
          this.memberOver = false
        },
        passwordChk () {
            if (this.password === this.re_password) {
                this.passChk = true
            } else {
                this.passChk = false
            }
            console.log(this.passChk)
        },
        memberChk() {
            if (!this.id) {
                alert("아이디를 입력해주세요.")
                return false
            }
            axios.post('http://222.99.179.54:9090/webservice/MemberChk', { 
                member_id: this.id
            })
            .then((result) => {
                if (result.data === 0) {
                    alert("사용가능한 아이디입니다.")
                    this.memberOver = true
                    return
                } else {
                    alert("사용중인 아이디입니다. 다른 아이디를 입력해주세요.")
                    this.memberOver = false
                    return false
                }
            })
            .catch(e => {
                alert("Error : 관리자에게 문의해주세요.")
                console.log(e)
            }) 
        },
        register () {
            if (!this.id || !this.name || !this.password || !this.re_password) {
                alert('필수사항을 입력해주세요.')
                return false
            }
            
            if (this.passChk === false) {
                alert('비밀번호에 입력된 내용이 일치하지 않습니다.')
                return false
            }

            if (this.memberOver === true) {
                const regDate = this.$moment().format('YYYY-MM-DD HH:mm:ss')
                axios.post('http://222.99.179.54:9090/webservice/InsertMember', { 
                    member_name: this.name,
                    member_id: this.id,
                    member_password: this.password,
                    member_regDate: regDate
                })
                .then((result) => {
                    if (result.data === 0) {
                    alert("회원가입이 완료되었습니다.")
                    this.reset()
                    } else {
                    alert("회원가입을 실패하였습니다. 관리자에게 문의해주세요.")
                    }
                })
                .catch(e => {
                    alert("Error : 관리자에게 문의해주세요.")
                    console.log(e)
                }) 
            } else {
                alert('아이디 중복체크를 해주세요.')
            }
        } 
    }
}
</script>

<style>

.register_title {
    text-align: center;
    font-size: 24px;
    margin-top: 50px;
    
}

.register_content_form {
    text-align: left;
    font-size: 16px;
    color: #696868;
    margin-top: 50px;
    padding-bottom: 50px;
}

.register_table {
    margin:0 auto;
}

.register_table .registger_text{
    width:180px;
    height:60px;
}

.register_chk_form {
    width: 100px;
    height: 60px;
}
.register_table .register_input input{
    height: 40px;
    width: 250px;
}

.register_btn_form {
    text-align: center;
    margin-top: 50px;
}

.register_btn {
    width: 100px;
    height: 40px;
    position: relative;
    border: 1px solid #ddd;
    background-color : #fff;
    border-radius: 8px;
    font-size: 14px;
}

.register_chk_btn {
    width: 80px;
    height: 40px;
    position: relative;
    border: 1px solid #ddd;
    background-color : #fff;
    border-radius: 8px;
    font-size: 13px;
    margin-left: 10px;
}
.register_btn:hover, .register_chk_btn:hover{
    background-color : #172d58;
    color:#fff;
}

.password_chk {
    color:red;
    text-align: center;
    font-size: 13px;
}
</style>
