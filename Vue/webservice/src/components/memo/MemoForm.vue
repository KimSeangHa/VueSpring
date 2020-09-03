<template>
    <div >
        <p class="MemoAdd-text"><img :src="require(`@/assets/images/memo_icon.png`)" class="memoImg" />Add Memo</p>
        <p class="MemoAdd-text telegram" @click="Makebot"><img :src="require(`@/assets/images/telegram.jpg`)" class="memoImg" />Make Bot</p>
        <div class="memo-form">
            <!-- <ckeditor :editor="editor" v-model="content"></ckeditor> -->
            <textarea 
                v-model="content" 
                class="memo-content" 
                placeholder="내용을 입력해주세요."
                @keydown.enter.exact.prevent
                @keyup.enter.exact="enterFunc"
                @keydown.enter.shift.exact="enterFunc"
            >
            </textarea>
            <br>
            <div style="text-align: right;">
                <button button class="addMemoBtn" @click="addMemo">등록</button>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'MemoForm',
    data: function () {
        return {
            content: '',
        }
    },
    methods: {
        reset () {
            this.content = ''
        },
        addMemo () { 
            if ( content === '') {
                alert("내용을 입력해주세요.")
                return false
            }
            
            const { content, member_token } = this
            const id = new Date().getTime()
            const regDate = this.$moment().format('YYYY-MM-DD HH:mm:ss')
            const curDate = this.$moment().format('YYYY-MM-DD')
            console.log(curDate);
            this.$store.commit('memo/addMemo', { content, id, regDate, curDate, member_token })
            this.reset()
        },
        Makebot () {
            axios.post('http://localhost:9090/webservice/Makebot', { 
                member_token: this.member_token
            })
            .then((result) => {
                console.log(result)
                if (result.status === 200) {
                    if(result.data === 100) {
                        this.$store.commit('login/Logout', { MemberToken: this.memberToken })
                    } else if (result.data === 1) {
                        alert("권한이 없습니다. 관리자를 통하여 실행해주세요.")
                    } else {
                        alert("Bot 생성 완료 !")
                    }
                } else {
                    alert("Bot 생성 실패. 관리자에게 문의해주세요.")
                }
            })
            .catch(e => {
                alert("Error : 관리자에게 문의해주세요.")
                console.log(e)
            }) 
        },
        enterFunc () {
            console.log('aaa')
            const enter = '\n'
            this.content = this.content + enter
            console.log(this.content)
        }
    },
    computed: {
        member_token: function() {
            return this.$store.state.login.loginToken
        }
    }
}
</script>

<style>
.memo-form {
    border: 1px solid #ccc;
    border-left: none;
    border-right: none;
    border-top: none;
    padding: 20px;
    margin: 20px;
    padding-bottom: 40px;
    padding-top: 0px;
    position: relative;
}

.memoImg {
    width: 30px;
    height: 30px;
    margin-right: 5px;
    margin-bottom: 3px;
}

.MemoAdd-text {
    font-size: 18px;
    margin-left: 40px;
    margin-top: 30px;
    display: inline-block;
}

.telegram {
    float: right;
    margin-right: 40px;
    cursor: pointer;
}
.memo-content {
    width: 100%;
    height: 100px;
}

.addMemoBtn {
    width: 80px;
    height: 40px;
    margin-top: 20px;
    position: relative;
    border: 1px solid #ddd;
    background-color : #fff;
    border-radius: 8px;
    font-size: 14px;
}

.addMemoBtn:hover {
    background-color : #172d58;
    color:#fff;
}
</style>