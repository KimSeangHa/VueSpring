<template>
  <div>
    <h1>TESaaaaaaT</h1><br>
    <button @click="getData">DB 테스트</button>
    <br>
    <button class="btn btn-primary">aaa</button>
  </div>
</template>

<script>
export default {
    methods: {
        getData () {
            this.$http.get('http://222.99.179.54:9090/webservice/dbtest', {
            })
            .then((result) => {
                console.log(result)
            })
            .catch(e => {
                console.log(e)
            })
        }
    },
    mounted () {
       // alert()
    }
}
</script>