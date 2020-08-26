<template>
  <div>
    <transition name='toggle'>
 
      <div class='left' v-if='fade'>
        <ul>
          <li v-for='list in linklist' :key="list.content">
            <router-link  :to='list.link' >{{ list.content }}</router-link>
          </li> 
        </ul>
      </div>
 
    </transition>
 
   <img id='left_btn' @click='enter' :src="require(`@/assets/images/menu.png`)">
  </div>
</template>

<script>
export default {
  name: 'left',
  data () {
    return {
      fade: false,
      linklist: [
        {content: '구성/기능', link: '/'},
        {content: '메모장', link: '/MemoApp'},
        {content: '관리', link: '/state'},
        {content: '추가예정', link: '/resister'}
      ]
    }
  },
  methods: {
    enter: function () {
      this.fade = !this.fade
    }
  }
}
</script>

<style>
    li a{
        color:#fff !important;
        text-decoration: none;
    }
    li a:hover{
        color:red !important;
        text-decoration: none;
    }
  .left{
    position:absolute; 
    top:0px; 
    width:250px;
    height:2000px; 
    background:rgba(23,45,88,0.9); 
    padding:40px 0;
    float:left;
    z-index:100;
  }
  .toggle-enter-active, .toggle-leave-active{
    transition: all 1s
  }
  .toggle-enter , .toggle-leave-to{
    left:-250px;
    width:0px;
  }
  .toggle-enter-to , .toggle-leave {
    left:0px;
    width:250px;
  }
  .left ul {padding: 90px 30px; position:fixed}
  .left ul li { font-size:15px;  height:60px; list-style: none;} 
  .left ul li#logo div {line-height: 0.8}
    #left_btn {
        position:fixed; 
        top:20px; 
        left:10px; 
        cursor:pointer;
        width:40px; 
        z-index:100;
    }
</style>

