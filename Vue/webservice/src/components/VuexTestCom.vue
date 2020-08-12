<template>
    <div>
        <button @click="addCounter">+++++++++++</button>
    </div>
</template>

<script>
export default {
    name: 'VuexTestCom',
    methods: {
        addCounter () {
           this.$store.state.test.counter++  
        }
    }
}
</script>

<style>
</style>