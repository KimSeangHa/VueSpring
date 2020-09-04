<template>
    <div class="header-wrap">
        <div class="header-form">
            <ul>
                <li><router-link to="/Diagram">구조</router-link></li>
                <li><router-link to="/Explan">설명</router-link></li>
                <li><router-link to="/APIManage">API 관리</router-link></li>
                <li><router-link to="/MemoApp">MemoApp</router-link></li>
                <li><router-link to="/MemoApp">추가예정</router-link></li> 
                <li v-if="loginAuth"  style="float:right;"><div @click="Logout">로그아웃</div></li> 
                <li v-if="!loginAuth" style="float:right;"><router-link to="/Login">로그인</router-link></li> 
                <li v-if="!loginAuth" style="float:right;"><router-link to="/Register">회원가입</router-link></li> 
            </ul>
        </div>
    </div>
</template>

<script> 
export default {
    name: 'Header',
    data: function() {
        return {
        }
    },
    methods: {
        Logout () {
            this.$store.commit('login/Logout', { MemberToken: this.memberToken })
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
.header-wrap {
    margin:0 auto;
    width: 100%;
    height: 100px;
    background-color: #172d58;
    position: relative;
}

@media (min-width: 320px) and (max-width: 480px) {
    .header-wrap {
        height: 300px;
    }
}

.header-form {
    margin:0 auto;
    width: 70%;
    height: 100%;
    color: white;
}

.header-form ul li {                       
    list-style: none;            
    color: white;               
    float: left;                
    line-height: 50px;          
    vertical-align: middle;     
    text-align: center; 
    width: 120px;    
    height: 50px;    
    font-size: 14px;                          
    margin-top:20px;  
    cursor: pointer;  
    border: none;
}

.header-form ul li:hover{         
    color: red; 
}

li a {
    text-decoration: none;
    color: #fff;
}

li a:hover {
    color: red;
    text-decoration: none;
}
</style>