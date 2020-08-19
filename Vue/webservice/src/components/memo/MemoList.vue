<template>
    <div class="list-form">
        <p class="MemoList-text"><img :src="require(`@/assets/images/memo_icon.png`)" class="memoImg" />Memo List</p>
        <ul>
            <li v-for="(item, index) in memoList" :key="item.id">
                <textarea @keyup="modify($event, index)" :value="item.content" class="memo-content"></textarea>
                <br>
                <button class="deleteMemoBtn" @click="deleteMemo(item, index)">삭제</button>
                <button class="rewriteMemoBtn" @click="rewriteMemo(content, index, item)">수정</button>
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
            content : ''
        }
    },
    computed: {
        ...mapState('memo', ['memoList'])
    },
    methods: {
        deleteMemo (item, index) {
            this.$store.commit('memo/deleteMemo', { item, index })
        },
        modify (event, index) {
            console.log(index)
            console.log(event.target.value)
            this.content = event.target.value
        },
        rewriteMemo (content, index, item) {
            this.$store.commit('memo/rewriteMemo', { content, index, item })
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