<template>
    <div class="login_wrap">
        <login-form />
    </div>
</template>

<script>
import LoginForm from '@/components/Login/LoginForm.vue'

export default {
    name: "Login",
    components: {
        LoginForm
    }
}
</script>

<style>
.login_wrap {
    margin:0 auto;
    width: 70%;
}
</style>
