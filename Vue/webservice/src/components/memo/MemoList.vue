<template>
    <div class="list-form">
        <p class="MemoList-text"><img :src="require(`@/assets/images/memo_icon.png`)" class="memoImg" />Memo List</p>
        <ul>
            <li v-for="(item, index) in memoList" :key="item.id">
                <div style="text-align: right">
                    <div class="date-view">등록 : {{ item.regDate }}</div>
                    <div v-if="item.modifyDate" class="date-view">수정 : {{ item.modifyDate }}</div>
                </div>
                <textarea 
                    @keyup="modify($event, index)" 
                    :value="item.content" 
                    class="memo-content"
                    @keyup.enter.exact="enterFunc"
                    @keydown.enter.shift.exact="enterFunc"
                    :maxlength="contentMax"
                >
                </textarea>
                <br>
                <div style="width:100%; text-align:right;">
                    <div class="limitText" >500글자 이하로 작성해주세요.</div>
                </div>
                <div style="text-align: right;">
                    <div class="button_wrap">
                        <button class="deleteMemoBtn" @click="deleteMemo(item, index)">삭제</button>
                    </div>
                    <div class="button_wrap">
                        <button class="rewriteMemoBtn" @click="rewriteMemo(content, index, item)">수정</button>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
    name: 'MemoList',
    data: function() {
        return {
            content : '',
            contentMax : 500
        }
    },
    created () {
        this.getMemoList ()  
    },
    computed: {
        member_token: function() {
            return this.$store.state.login.loginToken
        },

        ...mapState('memo', ['memoList']),
        

    },
    methods: {
        getMemoList () {
            const member_token = this.member_token
            this.$store.commit('memo/getMemoList', member_token )
        },
        deleteMemo (item, index) {
            const member_token = this.member_token
            this.$store.commit('memo/deleteMemo', { item, index, member_token })
        },
        modify (event, index) {
            console.log(index)
            console.log(event.target.value)
            this.content = event.target.value
        },
        rewriteMemo (content, index, item) {
            if ( content === '' ) {
                alert("수정사항이 없습니다.")
                return false
            }

            const modifyDate = this.$moment().format('YYYY-MM-DD HH:mm:ss')
            console.log(content, index, item, modifyDate)
            const member_token = this.member_token
            this.$store.commit('memo/rewriteMemo', { content, index, item, modifyDate, member_token})
        },
        enterFunc () {
            const enter = '\n'
            this.content = this.content + enter
            console.log(this.content)
        }
    }
}
</script>

<style>
.list-form {
    border-left: none;
    border-right: none;
    padding-bottom: 40px;
    padding-right: 40px;
    padding-top: 20px;
    position: relative;
}

.MemoList-text {
    font-size: 18px;
    margin-left: 40px;
}

.date-view {
    font-size: 11px;
    padding-bottom: 15px;
    width:150px;
    display: inline-block;
    text-align: right;
    color: #6b6b6b;
}
.memoImg {
    width: 30px;
    height: 30px;
    margin-right: 5px;
    margin-bottom: 3px;
}

.memo-content-view {
    width: 100%;
    height: 100px;
    margin-top: 20px;
    border: 0.8px solid #a9a9a9  
}

.list-form li {
    list-style: none;
    margin-top: 20px;
    padding-bottom: 30px;
    border-bottom: 1px solid #ddd;
}

.deleteMemoBtn, .rewriteMemoBtn {
    width: 80px;
    height: 40px;
    margin-top: 20px;
    position: relative;
    border: 1px solid #ddd;
    background-color : #fff;
    border-radius: 8px;
    font-size: 14px;
}

.deleteMemoBtn:hover, .rewriteMemoBtn:hover {
    background-color : #172d58;
    color:#fff;
}

.button_wrap {
    display: inline-block;
    text-align: right;
    width: 100px;
}


</style>