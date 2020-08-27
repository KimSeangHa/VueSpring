<template>
    <div class="register-wrap">
        <register-form />
    </div>
</template>

<script>
import RegisterForm from '@/components/Login/Register.vue'

export default {
    name: "Register",
    components: {
        RegisterForm
    }
}
</script>

<style>
.register-wrap {
    margin:0 auto;
    width: 70%;
}

</style>
