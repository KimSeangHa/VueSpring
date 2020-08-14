<template>
    <div class="memo-wrap">
        <memo-form />
        <memo-list />
    </div>
</template>

<script>
import MemoForm from '@/components/memo/MemoForm.vue'
import MemoList from '@/components/memo/MemoList.vue'

export default {
    name: 'MemoApp',
    components: {
        MemoForm,
        MemoList
    }
}
</script>

<style>
.memo-wrap {
    margin:0 auto;
    width: 70%;
    height: 100%;
    background-color: #fff;
}
</style>