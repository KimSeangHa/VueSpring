<template>
    <div >
        <p class="MemoAdd-text"><img :src="require(`@/assets/images/memo_icon.png`)" class="memoImg" />Add Memo</p>
        <div class="memo-form">
            <input v-model="title" class="memo-title" type="text" placeholder="제목을 입력해주세요.">
            <br>
            <textarea v-model="content" class="memo-content" placeholder="내용을 입력해주세요."></textarea>
            <br>
            <button class="addMemoBtn" @click="addMemo">등록</button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'MemoForm',
    data: function () {
        return {
            title: '',
            content: ''
        }
    },
    methods: {
        reset () {
            this.title = ''
            this.content = ''
        },
        addMemo () {
            const { title, content } = this
            const id = new Date().getTime()

            this.$store.commit('memo/addMemo', { title, content, id})
            this.reset()
            console.log(this.$store.state.memo.memoList)
            alert("메모 등록이 완료되었습니다.")
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
}

.memo-title {
    width: 100%;
    padding: 10px;
    margin-top: 20px;
}

.memo-content {
    width: 100%;
    height: 100px;
    margin-top: 20px;
}

.addMemoBtn {
    width: 80px;
    height: 40px;
    margin-top: 20px;
    position: relative;
    border: 1px solid #ddd;
    background-color : #ccc;
    border-radius: 8px;
    font-size: 14px;
}

.addMemoBtn:hover {
    background-color : #172d58;
    color:#fff;
}
</style>