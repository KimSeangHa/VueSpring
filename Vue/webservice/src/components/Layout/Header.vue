<template>
    <div>
        <transition name='toggle'>
    
        <div class='left' v-if='fade'>
            <ul>
                <li><router-link to="/Diagram">구조</router-link></li>
                <li><router-link to="/Explan">설명</router-link></li>
                <li><router-link to="/APIManage">API 관리</router-link></li>
                <li><router-link to="/MemoApp">MemoApp</router-link></li>
                <li><router-link to="/MemoApp">추가예정</router-link></li> 
            </ul>
            <ul class="menu_bottom">    
                <li v-if="loginAuth" ><div @click="Logout">로그아웃</div></li> 
                <li v-if="!loginAuth"><router-link to="/Login">로그인</router-link></li> 
                <li v-if="!loginAuth"><router-link to="/Register">회원가입</router-link></li>
            </ul>
        </div>
        </transition>
    <img id='left_btn' class="menu_img" @click='enter' src='../../assets/images/menu.png'>
    </div>
</template>

<script> 
export default {
    name: 'Header',
    data: function() {
        return {
            fade: false
        }
    },
    methods: {
        Logout () {
            this.$store.commit('login/Logout', { MemberToken: this.memberToken })
        },
        enter: function () {
            this.fade = !this.fade
        }
    },
    computed: {
        loginAuth: function() {
            return this.$store.state.login.loginResult
        },
        memberToken: function() {
            return this.$store.state.login.loginToken
        }
    }
}
</script>

<style>


li a {
    text-decoration: none;
    color: #fff;
}

li a:hover {
    color: red;
    text-decoration: none;
}

a{color:black}
  a:hover{color:rgb(255,98,124);}
  .left{
    position:fixed; 
    top:0px; 
    width:300px;
    height:1000px; 
    background:#172d58;
    padding:40px 0;
    float:left;
    z-index:1
  }
  .toggle-enter-active, .toggle-leave-active{
    transition: all 1s
  }
  .toggle-enter , .toggle-leave-to{
    left:-300px;
    width:0px;
  }
  .toggle-enter-to , .toggle-leave {
    left:0px;
    width:300px;
  }
  .left ul {padding: 0 30px; position:fixed; margin-top: 80px;}
  .left ul li { 
    font-size:25px;  
    height:50px;
    list-style: none;            
    color: white;               
    font-size: 14px;   
    cursor: pointer;  
    border: none;
} 
  .left ul li#menu_top {font-family: 'Passion One', cursive; font-size:50px; height:170px;}
  .left ul li#menu_top div {line-height: 0.8}
  .left ul li#company {font-size:16px; margin-top:150px; }
  #left_btn {position:fixed; top:0px; left:0px; cursor:pointer}

  .menu_img {
      width: 30px;
      margin-top: 30px;
      margin-left: 30px;
      z-index: 2;
  }

  .menu_bottom {
      position: fixed;
      bottom: 0px;
  }

</style>