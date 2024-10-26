<!-- 登录界面 -->
<template>
        <div class="signUp" v-if="!this.signUp_sucess">
            <div class="signUp-list"> 
                <h2>欢迎使用 <span>iNews</span> </h2>
                <p>创建新新用户,发现新世界</p>
				<input type="text" v-model='nickname' placeholder="请输入昵称">
                <input type="text" v-model='password1' placeholder="请输入密码">
				<input type="text" v-model='password2' placeholder="确认密码">
				<input type="text" v-model='email' placeholder="请输入邮箱">
				<input type="text" v-model='phoneNumber' placeholder="请绑定手机号">
                <div class="agrement">
                    <checkbox :checked="isChecked" @click="checkbox()" />
                    <span>已阅读并同意 <a href="#">《用户服务协议》及《隐私政策》</a></span>
                </div>
                <button @click="signUp">注册新用户</button>
			</div>
			<view class="warning">
				<view class="nicknameBeUsed" v-if="this.nicknameBeUsed">
					*昵称已被使用!
				</view>
				<view class="passwordNotSame" v-if="this.passwordNotSame">
					*密码不一致!
				</view>
				<view class="passwordNum" v-if="this.passwordNum">
					*密码要多于6个字符!
				</view>
				<view class="emailBeUsed" v-if="this.emailBeUsed">
					*该邮箱已被用户注册!
				</view>
				<view class="phoneNumberBeUsed" v-if="this.phoneNumberBeUsed">
					*该手机号已被用户注册!
				</view>
			</view>	
        </div>
		<view class="signUp-sucess" v-else>
			<h2>欢迎使用 <span>iNews</span> </h2>
			<p>创建新新用户,发现新世界</p>
			<view class="sucess">注册成功!</view>
			<view class="userinfo">
				<p>您的账号为:<span class="userid">{{this.userid}}</span></p>
				<p>您的账号昵称为:<span class="nickname">{{this.nickname}}</span></p>
				<p>您的密码为:<span class="password">{{this.password1}}</span></p>
			</view>
		</view>
</template>

<script setup>
export default {
  data() {
    return {
	  userid:'',
	  nickname:'',
	  password1:'',
	  password2:'',
	  email:'',
	  phoneNumber:'',
	  isChecked: false,
	  nicknameBeUsed:false,
	  passwordNotSame:false,
	  passwordNum:false,
	  emailBeUsed:false,
	  phoneNumberBeUsed:false,
	  signUp_sucess:false,
    };
  },
  watch: {
    password1: { handler: function(newVal, oldVal) {
    		this.checkPassword();
		}},
	password2: { handler: function(newVal, oldVal) {
			this.checkPassword();
		}},
	nickname: { handler: function(newVal, oldVal) {
			this.nicknameBeUsed=false;
		}},  
	email: { handler: function(newVal, oldVal) {
			this.emailBeUsed=false;
		}},  
	phoneNumber: { handler: function(newVal, oldVal) {
			this.phoneNumberBeUsed=false;
		}},  		
   },
  methods: {
    checkbox() {
		this.isChecked=!this.isChecked;
    },
	checkPassword(){
		//检查密码是否规范
		if(this.password1!=this.password2){this.passwordNotSame=true}
		else{this.passwordNotSame=false}
		if(this.password1.length<6){this.passwordNum=true}
		else{this.passwordNum=false}
	},
	async checkNickname(){
		//检查昵称是否被使用
		uni.request({
		    url: "http://localhost:9090/user/checkNickname/"+this.nickname, 
		    success: res => {
				console.log(res.data)
				if(res.data==null||res.data==''){this.nicknameBeUsed=false}
				else{this.nicknameBeUsed=true}
				// console.log("1 nicknameBeUsed ",this.nicknameBeUsed)
		    },
			fail: (err) => {// 请求失败的处理逻辑
			      console.error('数据请求失败:', err);
			    }
		})
	},
	async checkEmail(){
		//检查邮箱是否被使用		
		uni.request({
		    url: "http://localhost:9090/user/checkEmail/"+this.email, 
		    success: res => {
				if(res.data==null||res.data==''){this.emailBeUsed=false}
				else{this.emailBeUsed=true}
				// console.log("1 emailBeUsed ",this.emailBeUsed)
		    },
			fail: (err) => {// 请求失败的处理逻辑
			      console.error('数据请求失败:', err);
			    }
		})
	},
	async checkPhoneNumber(){
		//检查手机号是否被使用		
		uni.request({
		    url: "http://localhost:9090/user/checkPhone/"+this.phoneNumber, 
		    success: res => {
				if(res.data==null||res.data==''){this.phoneNumberBeUsed=false}
				else{this.phoneNumberBeUsed=true}
				//console.log("1 phoneNumberBeUsed",this.phoneNumberBeUsed)
		    },
			fail: (err) => {// 请求失败的处理逻辑
			      console.error('数据请求失败:', err);
			    }
		})
	},
	async signUp(){
		if(!this.isChecked){
			//未勾选阅读并同意《用户服务协议》及《隐私政策》
			uni.showToast({
			  title: '未勾选阅读并同意\n《用户服务协议》及《隐私政策》',
			  icon: 'error',
			  duration: 1500 // 弹窗显示的时间，单位毫秒
			});
			return
		}
		//async await确保同步执行,使this.nicknameBeUsed等标志被赋值后再用于判断
		 await this.checkNickname()
		 await this.checkEmail()
		 await this.checkPhoneNumber()
		
		if(this.passwordNotSame||this.passwordNum||
			this.nicknameBeUsed||this.emailBeUsed||this.phoneNumberBeUsed){
			//用户注册信息错误提示
			uni.showToast({
			  title: '用户信息有误\n无法注册',
			  icon: 'error',
			  duration: 1500 // 弹窗显示的时间，单位毫秒
			});
			return
		}
		else{
			const user={
				id:null,
				password:this.password1,
				nickname:this.nickname,
				email:this.email,
				phone:this.phoneNumber,
				picurl:'/images/user/0.jpg',
			}
			uni.request({
			    url: "http://localhost:9090/user", 
				method:"POST",
				data:user,
				header: {
				      'content-type': 'application/json' // 默认值
				    },
			    success: res => {
					console.log("res.data ",res.data)
					if(res.data>0){
						this.signUp_sucess=true;
						this.userid=res.data;
					}
					else{
						uni.showToast({
						  title: '注册失败\n请再试一次',
						  icon: 'error',
						  duration: 1000 // 弹窗显示的时间，单位毫秒
						});
					}
			    },
				fail: (err) => {// 请求失败的处理逻辑
				      console.error('数据请求失败:', err);
				    }
			})
		}
	}
  }
}
</script>

<style scoped>
.signUp{
    
}
.signUp-list{
    padding: 0 30px 0 30px;
    /* height: 100%; */
    /* width: 100%; */
}
.signUp-list .van-icon{
    font-size: 25px;
    color: #00000077;
}
.signUp-list h2{
    font-size: 24px;
    margin-top: 30px;
    /* 改变字与字间的间距 */
    letter-spacing: 2px;
    color: #1b3a3be0;
    font-weight: 900;
}
.signUp-list h2 span{
    letter-spacing: 0px;
    font-weight: 900;
    font-size: 28px;
    color: #31C27C;
}
.signUp-list p{
    font-weight: 500;
    letter-spacing: 3px;
    padding: 5px 0;
    margin-bottom: 50px;
    color: #0000007c;
}
/* 只选中login-list子元素的input类型,避免影响下面的勾选框input */
.signUp-list>input{
    border: 0;
    border-bottom: 1px solid #aaaaaa;
    background: transparent;
    width: 100%;
    margin-top: 20px;
    font-size: 20px;
    padding-bottom: 5px;
}
.signUp-list input::placeholder{
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
.signUp-list>button{
	margin-top: 20px;
    width: 100%;
    height: 40px;
    background-color: #31C27C;
    color: white;
    font-weight: 600;
    font-size: 16px;
}
.signUp-title{
    margin-top: 20px;
    color: #00000085;
}
.signUp .warning{
	padding: 10px;
	font-size: 14px;
	color: red;
}
.signUp-sucess{
	padding: 0 30px 0 30px;
	
	h2{
		font-size: 24px;
		margin-top: 30px;
		/* 改变字与字间的间距 */
		letter-spacing: 2px;
		color: #1b3a3be0;
		font-weight: 900;	
		
		span{
			letter-spacing: 0px;
			font-weight: 900;
			font-size: 28px;
			color: #31C27C;
		}
	}
	p{
		font-weight: 500;
		letter-spacing: 3px;
		padding: 5px 0;
		margin-bottom: 50px;
		color: #0000007c;
	}
	.sucess{
		margin-bottom:20px;
		letter-spacing: 0px;
		font-weight: 900;
		font-size: 28px;
		color: #31C27C;
	}
	.userinfo{
		p{
			margin-bottom:10px;
			font-size: 18px;
			font-weight:700;
			color:#595959e0;
		}
		span{
			font-size:20px;
			font-weight:700;
			color: #31C27C;
		}
	}
}
</style>
