<template>
    <div class="header-wrap">
        <div class="header-form">
            <ul>
                <li><router-link to="/">Neto App</router-link></li>
                <li>버스 사이트</li>
                <li>ㅇㅁㄴㅇㄻㄴㅇ</li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Header'
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
    width: 150px;    
    height: 50px;    
    font-size: 14px;                          
    margin-top:20px;  
    cursor: pointer;  
}

.header-form ul li:hover{         
    color: red; 
}

li a {
    text-decoration: none;
}
</style>