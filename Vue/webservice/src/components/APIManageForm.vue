<template>
    <div>
        <div class="sub_content">
            <p style="font-size:12px;">*관리자를 통하여 Bot을 대화방에 초대해주세요.</p>
            <br>
            <p style="font-size:12px;">*ChatID(채팅방ID) 확인 방법 : <a href="https://gabrielkim.tistory.com/entry/Telegram-Bot-Token-%EB%B0%8F-Chat-Id-%EC%96%BB%EA%B8%B0">Telegram Bot Check</a></p>
            <p style="font-size:12px; color:red">*Bot Tokent : 수정할 수 없습니다.</p>
        </div>
        <div class="manage_form">
            <table class="manage_table">
                <tr v-if="bot_token">
                    <td class="manage_text">Bot Token: </td>
                    <td class="manage_content"><input readonly :value="bot_token" class="token_form" type="text" placeholder="Bot Token을 입력해주세요."></td>
                </tr>
                <tr>
                    <td class="manage_text">Chat ID: </td>
                    <td class="manage_content"><input v-model='chat_id' class="chatid_form" type="text" placeholder="Chat ID를 입력해주세요."></td>
                </tr>
            </table>
        </div>
        <div class="submit_form">
            <button class="api_button"  v-if="!bot_use"  @click="RegisterBotInfo" >등록</button>
            <button class="api_button" v-if="bot_use" @click="ModifyBotInfo" >수정</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: "APIManageForm",
    data: function() {
        return {
            bot_token: '',
            chat_id: '',
            bot_use: false
        }
    },
    created () {
        this.getBotInfo()
    },
    methods: {
        getBotInfo () {
            console.log("## getBotInfo ##")
            axios.post('http://222.99.179.54:9090/webservice/getBotInfo', { 
                member_token: this.memberToken
            })
            .then((result) => {
                console.log(result.data.data)
                if (result.data.data == 0) {
                    this.bot_token = result.data.bot_token
                    this.chat_id = result.data.chat_id
                    this.bot_use = true
                } else if (result.data.data == 100) {
                    this.$store.commit('login/Logout', { MemberToken: this.memberToken })
                } else {
                    this.bot_token = ''
                    this.bot_use = false
                }
            })
            .catch(e => {
                alert("Error : 관리자에게 문의해주세요.")
                console.log(e)
            }) 
        },
        RegisterBotInfo () {
            console.log("## Register Bot Info ##")
            const regDate = this.$moment().format('YYYY-MM-DD HH:mm:ss')
        
            axios.post('http://222.99.179.54:9090/webservice/RegisterBotInfo', { 
                member_token: this.memberToken,
                chat_id: this.chat_id,
                bot_regdate: regDate
            })
            .then((result) => {
                if (result.data === 0) {
                    alert("텔레그램 봇 정보가 등록되었습니다.")
                    this.bot_use = true
                } else if (result.data === 100) {
                    this.$store.commit('login/Logout', { MemberToken: this.memberToken })
                } else {
                    alert("텔레그램 봇 정보 등록에 실패하였습니다. 관리자에게 문의해주세요.")
                    return false
                }
            })
            .catch(e => {
                alert("Error : 관리자에게 문의해주세요.")
                console.log(e)
            }) 
        },
        ModifyBotInfo () {
            if (this.chat_id == "") {
                alert("채팅방 아이디를 입력해주세요.")
                return
            }

            console.log("## Modify Bot Info ##")
            axios.post('http://222.99.179.54:9090/webservice/ModifyBotInfo', { 
                member_token: this.memberToken,
                chat_id: this.chat_id
            })
            .then((result) => {
                if (result.data === 0) {
                    alert("텔레그램 봇 정보 수정을 완료하였습니다.")
                } else if (result.data === 100) {
                    this.$store.commit('login/Logout', { MemberToken: this.memberToken })
                } else if (result.data === 2) {
                    alert("이미 사용중인 Chat ID 입니다. \nChat ID는 중복이 될 수 없습니다.")
                } else {
                    alert("텔레그램 봇 정보 수정을 실패하였습니다. 관리자에게 문의해주세요.")
                    return false
                }
            })
            .catch(e => {
                alert("Error : 관리자에게 문의해주세요.")
                console.log(e)
            }) 
        }
    },
    computed: {
        memberToken: function() {
            return this.$store.state.login.loginToken
        }
    }
}
</script>

<style>
.sub_content {
    margin-top: 50px;
    text-align: center;
    font-size:14px;
}

.manage_form {
    margin-top: 40px;
    text-align: center;
    padding-bottom: 40px;
}

.manage_table {
    margin:0 auto;
    font-size: 15px;
}

.manage_text {
    width: 120px;
    padding: 15px;
    height: 60px;
}

.manage_content {
    height: 60px;
}

.token_form {
    width: 250px;
    height: 40px;
}

.chatid_form {
    width:250px;
    height: 40px;
}

.submit_form {
    text-align: center;
    margin-bottom: 40px;
}

.api_button {
    width: 80px;
    height: 40px;
    position: relative;
    border: 1px solid #ddd;
    background-color : #fff;
    border-radius: 8px;
    font-size: 13px;
    margin-left: 10px;
}
.api_button:hover {
    background-color : #172d58;
    color:#fff;
}
</style>
