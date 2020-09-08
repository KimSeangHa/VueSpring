<template>
  <div id="app">
    <div id="nav">
      <Header />
    </div>
      <router-view class="content-form" />
  </div>
</template>

<script>
import Header from '@/components/Layout/Header.vue'

export default {
  components: {
    Header
  }
}
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}
