<template>
	<view class="user">
		<!-- 用户未登录 -->
		<view v-if="!app.globalData.isLoggedIn" >
			<user-hd-unLogin></user-hd-unLogin>
		</view>
		<!-- 用户登录 -->
		<view v-if="app.globalData.isLoggedIn" >
			<user-hd  :userId="app.globalData.userId" :user="user"></user-hd>
		</view>
		<user-body></user-body>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				app:{},
				user:{}
			};
		},
		onLoad(){//页面加载时触发，只会调用一次
			this.app = getApp();
			this.getUserInfo();
		},
		onShow(){//页面显示/切入前台时触发
		},
		  watch: {
		     'app.globalData': {
		       handler: function(newVal, oldVal) {
		         // 当app.globalData中的任何属性发生变化时，这个函数会被调用
		         if (newVal.isLoggedIn) {
		           // 登录状态变化为已登录，可以在这里重新获取用户信息或者触发页面更新
		           this.getUserInfo();
		         }
		       },
		       deep: true // 设置深度监听
		     }
		   },
		methods:{
			//获取用户数据
			getUserInfo(){
				if(!this.app.globalData.isLoggedIn) return;
				uni.request({
				    url: "http://localhost:9090/user/"+this.app.globalData.userId, 
					method:'GET',
				    success: res => {
				        this.user=res.data,
						this.user.picurl='http://localhost:9090' + this.user.picurl;
						//console.log("123",this.user);
				    },
					fail: (err) => {
					      // 请求失败的处理逻辑
					      console.error('用户id:'+this.app.globalData.userId+' 数据请求失败:', err);
					    }
				})
			}
		}
	}
</script>

<style lang="scss">
.user{
	
}
</style>
