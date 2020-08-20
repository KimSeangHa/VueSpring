<template>
    <div class="list-form">
        <p class="MemoList-text"><img :src="require(`@/assets/images/memo_icon.png`)" class="memoImg" />Memo List</p>
        <ul>
            <li v-for="(item, index) in memoList" :key="item.id">
                <div style="text-align: right">
                    <div class="date-view">등록 : {{ item.regDate }}</div>
                    <div v-if="item.modifyDate" class="date-view">수정 : {{ item.modifyDate }}</div>
                </div>
                <ckeditor :editor="editor" :value="item.content" @input="modify($event, index)"></ckeditor>
                <!-- <textarea @keyup="modify($event, index)" :value="item.content" class="memo-content"></textarea> -->
                <br>
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
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

export default {
    name: 'MemoList',
    data: function() {
        return {
            content : '',
            editor: ClassicEditor,
            editorData: '<p>Content of the editor.</p>',
            editorConfig: {
                // The configuration of the editor.
            },
        }
    },
    created () {
        this.getMemoList ()  
    },
    computed: {
        ...mapState('memo', ['memoList'])
    },
    methods: {
        getMemoList () {
            this.$store.commit('memo/getMemoList')
        },
        deleteMemo (item, index) {
            this.$store.commit('memo/deleteMemo', { item, index })
        },
        modify (event, index) {
            console.log(index)
            console.log(event)
            this.content = event
        },
        rewriteMemo (content, index, item) {
            const modifyDate = this.$moment().format('YYYY-MM-DD HH:mm:ss')
            console.log(content, index, item, modifyDate)
            this.$store.commit('memo/rewriteMemo', { content, index, item, modifyDate})
        },
        onEditorInput (a, b, c) {
            console.log(a)
            console.log(b)
            console.log(c)
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