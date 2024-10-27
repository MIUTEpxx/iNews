<!-- 登录界面 -->


<template>
        <div class="login">
            <div class="login-list"> 
                <h2>欢迎使用 <span>iNews</span> </h2>
                <p>登录账户,博览天下事</p>
                 <input type="text" v-model="userId" placeholder="请输入账号">
				 <input type="password" v-model="password" placeholder="请输入密码">
                <div class="agrement">
                    <checkbox :checked="isChecked" @click="checkbox()" />
                    <span>已阅读并同意 <a href="">《用户服务协议》及《隐私政策》</a></span>
                </div>
                <button @click="login">登录</button>
                <div class="login-title" @click="goSignUp">
                    <span>没有账号?点击注册!</span>
                </div>
				<view class="warning">
					<view class="userIdIsNumeric" v-if="!this.userIdIsNumeric">
						*账号只能包含数字!
					</view>
				</view>	
            </div>
        </div>
</template>

<script setup>
export default {
  data() {
    return {
      isChecked: false,
	  userIdIsNumeric:true,//账号只由数组组成
	  userId:'',
	  password:'',
	  user:{},
	  app:{},
    };
  },
  watch: {
    userId: { handler: function(newVal, oldVal) {
		if(newVal!='') 
			this.userIdIsNumeric=/^\d+$/.test(this.userId)//检查是否只包含数字
		}},
   },
  onload(){
	this.app = getApp();
  },
  methods: {
    checkbox() {
      this.isChecked = !this.isChecked;
    },
	goSignUp(){//前往注册界面
		uni.navigateTo({
			url:`/pages/user/signUp`					
		})
	},
	login(){//登录账号
		if(!this.isChecked){
			//未勾选阅读并同意《用户服务协议》及《隐私政策》
			uni.showToast({
			  title: '未勾选阅读并同意\n《用户服务协议》及《隐私政策》',
			  icon: 'error',
			  duration: 1500 // 弹窗显示的时间，单位毫秒
			});
			return
		}
		if(!this.userIdIsNumeric){
			//账号格式不正确
			uni.showToast({
			  title: '账号格式不正确',
			  icon: 'error',
			  duration: 1500 // 弹窗显示的时间，单位毫秒
			});
			return
		}
		uni.request({
		    url: "http://localhost:9090/user/login/"+this.userId+"/"+this.password, 
			method:'GET',
		    success: res => {
		        this.user=res.data;
				console.log(this.user);
				if(this.user!=null&&this.user!=""&&!this.user.error){//登录成功!更新用户全局数据
					this.$set(getApp().globalData,'userId',this.user.id)
					this.$set(getApp().globalData,'isLoggedIn',true)
					this.$forceUpdate()
					// 弹出登录成功的提示
					 uni.showToast({
					   title: '登录成功',
					   icon: 'success',
					   duration: 1500 // 弹窗显示的时间，单位毫秒
					 });
					 					
					 // 2秒后回到上一个页面
					 setTimeout(() => {
					   uni.navigateBack({
					     delta: 2 // 返回上一级页面
					   });
					 }, 1500);       
				}else{
					// 弹出登录失败的提示
					 uni.showToast({
					   title: '密码错误或账号不存在',
					   icon: 'error',
					   duration: 1500 // 弹窗显示的时间，单位毫秒
					 });
				}
		    },
			fail: (err) => {
			      // 登录请求失败的处理逻辑
			      console.error('账号登录失败:', err);
			    }
		})
	}
  }
}
</script>

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
.warning{
	padding: 10px;
	font-size: 14px;
	color: red;
}
</style>
