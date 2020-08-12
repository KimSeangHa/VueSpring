<template>
    <div style="margin:0 auto; width:70%;">
        Parent Counter : {{ $store.state.test.counter }} <br>
        <vuex-testcom />
    </div>
</template>

<script>
import VuexTestcom from '@/components/VuexTestCom.vue'

export default {
    name: 'VuexTest',
    components: {
        VuexTestcom
    }
}
</script>

<style>
</style>
