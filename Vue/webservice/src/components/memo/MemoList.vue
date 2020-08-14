<template>
    <div class="list-form">
        <p class="MemoList-text">Memo List</p>
        <ul>
            <li v-for="(item, index) in memoList" :key="item.id">
                <input type="text" :value=item.title class="memo-title">
                <br>
                <textarea :value=item.content class="memo-content"></textarea>
                <br>
                <button class="deleteMemoBtn" @click="deleteMemo(item, index)">삭제</button>
                <button class="rewriteMemoBtn" @click="rewriteMemo(item, index)">수정</button>
            </li>
        </ul>
    </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
    name: 'MemoList',
    computed: {
        ...mapState('memo', ['memoList'])
    },
    methods: {
        deleteMemo (item, index) {
            this.$store.commit('memo/deleteMemo', { item, index })
            alert('삭제가 완료되었습니다.')
        },
        rewriteMemo (item, index) {
            alert(index)
        }
    }
}
</script>

<style>
.list-form {
    margin-top: 40px;
    margin-bottom: 40px;
}

.MemoList-text {
    font-size: 18px;
    margin-left: 40px;
}

li {
    list-style: none;
    margin-top: 20px;
    padding-bottom: 30px;
    border-bottom: 1px solid #ddd;
}


.memo-title {
    width: 550px;
    padding: 10px;
    margin-top: 20px;
}

.memo-content {
    width: 550px;
    height: 100px;
    margin-top: 20px;
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