<template>
    <div class="header-wrap">
        <div class="header-form">
            <ul>
                <li><router-link to="/MemoApp">Configuration</router-link></li>
                <li><router-link to="/MemoApp">Manual</router-link></li>
                <li><router-link to="/MemoApp">API manage</router-link></li>
                <li><router-link to="/MemoApp">MemoApp</router-link></li>
                <li><router-link to="/MemoApp">Supplement</router-link></li>
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