<!-- 登录界面 -->
<script setup>
export default {
  data() {
    return {
      isChecked: false
    };
  },
  methods: {
    checkboxChange(e) {
      this.isChecked = e.detail.value.length > 0;
    }
  }
}
</script>

<template>
        <div class="login">
            <div class="login-list"> 
                <h2>欢迎使用 <span>iNews</span> </h2>
                <p>创建新新用户,发现新世界</p>
				<input type="text" placeholder="请输入昵称">
                <input type="text" placeholder="请输入密码">
				<input type="text" placeholder="确认密码">
				<input type="text" placeholder="请输入邮箱">
				<input type="text" placeholder="请绑定手机号">
                <div class="agrement">
                    <checkbox :checked="isChecked" @change="checkboxChange" />
                    <span>已阅读并同意 <a href="">《用户服务协议》及《隐私政策》</a></span>
                </div>
                <button>注册新用户</button>
            </div>
        </div>
</template>

<style scoped>
.login{
    
}
.login-list{
    padding: 30px;
    /* height: 100%; */
    /* width: 100%; */
}
.login-list .van-icon{
    font-size: 25px;
    color: #00000077;
}
.login-list h2{
    font-size: 24px;
    margin-top: 30px;
    /* 改变字与字间的间距 */
    letter-spacing: 2px;
    color: #1b3a3be0;
    font-weight: 900;
}
.login-list h2 span{
    letter-spacing: 0px;
    font-weight: 900;
    font-size: 28px;
    color: #31C27C;
}
.login-list p{
    font-weight: 500;
    letter-spacing: 3px;
    padding: 5px 0;
    margin-bottom: 50px;
    color: #0000007c;
}
/* 只选中login-list子元素的input类型,避免影响下面的勾选框input */
.login-list>input{
    border: 0;
    border-bottom: 1px solid #aaaaaa;
    background: transparent;
    width: 100%;
    margin-top: 20px;
    font-size: 20px;
    padding-bottom: 5px;
}
.login-list input::placeholder{
    color: #0000004b;
    font-weight: 700;
}
.agrement{
    display: flex;
    font-size: 12px;
    margin: 15px 0;
    align-items: center;
}
.agrement [type=checkbox]{
    margin-right: 5px;
}
.login-list>button{
	margin-top: 20px;
    width: 100%;
    height: 40px;
    background-color: #31C27C;
    color: white;
    font-weight: 600;
    font-size: 16px;
}
.login-title{
    margin-top: 20px;
    color: #00000085;
}
</style>
