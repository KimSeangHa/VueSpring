<template>
    <div class="manage_wrap">
        <manage-form />
    </div>
</template>

<script>
import ManageForm from '@/components/APIManageForm.vue'

export default {
    name: "APIManage",
    components: {
        ManageForm
    }
}
</script>

<style>
.manage_wrap {
    margin:0 auto;
    width: 70%;
}
</style>