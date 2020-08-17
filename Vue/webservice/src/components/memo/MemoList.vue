<template>
    <div class="list-form">
        <p class="MemoList-text"><img :src="require(`@/assets/images/memo_icon.png`)" class="memoImg" />Memo List</p>
        <ul>
            <li v-for="(item, index) in memoList" :key="item.id">
                <div @click="showTitle = true" class="memo-title-view">{{ item.title }}</div>
                <input v-if="showTitle" v-model="retitle" type="text" placeholder="제목의 수정사항을 입력해주세요." class="memo-title">
                <br>
                <div @click="showContent = true" class="memo-content-view">{{ item.content }}</div>
                <textarea v-if="showContent" v-model="recontent" placeholder="내용의 수정사항을 입력해주세요." class="memo-content"></textarea>
                <br>
                <button class="deleteMemoBtn" @click="deleteMemo(item, index)">삭제</button>
                <button class="rewriteMemoBtn" @click="rewriteMemo(retitle, recontent, index)">수정</button>
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
            rewriteContent : '',
            retitle: '',
            recontent: '',
            showTitle: false,
            showContent: false
        }
    },
    computed: {
        ...mapState('memo', ['memoList'])
    },
    methods: {
        deleteMemo (item, index) {
            this.$store.commit('memo/deleteMemo', { item, index })
            alert('삭제가 완료되었습니다.')
        },
        reset () {
            this.retitle = ''
            this.recontent = ''
            this.showTitle = false
            this.showContent = false
        },
        rewriteMemo (retitle, recontent, index) {
            if ( retitle == '' && recontent == '') {
                alert('수정사항이 없습니다.')
            } else if ( retitle != '' && recontent == '') {
                const type = 1
                this.$store.commit('memo/rewriteMemo', { type, retitle, index })
                this.reset()
                alert('수정이 완료되었습니다.')
            } else if ( retitle == '' && recontent != '') {
                const type = 2
                this.$store.commit('memo/rewriteMemo', { type, recontent, index })
                this.reset()
                alert('수정이 완료되었습니다.')
            } else {
                const type = 3
                recontent = retitle.concat('/', recontent)
                this.$store.commit('memo/rewriteMemo', { type, recontent, index })
                this.reset()
                alert('수정이 완료되었습니다.')
            }
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

.memoImg {
    width: 30px;
    height: 30px;
    margin-right: 5px;
    margin-bottom: 3px;
}

.memo-title-view {
    width: 100%;
    padding: 10px;
    margin-top: 20px;  
    border: 0.8px solid #a9a9a9;
    font-weight: 700;
}

.memo-content-view {
    width: 100%;
    height: 100px;
    margin-top: 20px;
    border: 0.8px solid #a9a9a9  
}

li {
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
    background-color : #ccc;
    border-radius: 8px;
    font-size: 14px;
    margin-right:20px;
}

.deleteMemoBtn:hover, .rewriteMemoBtn:hover {
    background-color : #172d58;
    color:#fff;
}
</style>