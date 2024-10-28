<template>
	<view class="user-hd-unLogin">
		<view class="user-img">
			<img src="../../static/images/logo2.png" alt="" />
		</view>
		<view class="user-info">
			<view class="user-name">
				{{this.userName}}
			</view>
			<view class="login-button" @click="goLogin">
				登录账号
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name:"user-hd-unLogin",
		data(){
			return{
				userName:"游客123"
			};
		},
		methods:{
			goLogin(){
				uni.navigateTo({
					url:`/pages/user/login`					
					//url:`/pages/detail/detail?cid=$51&id=1`
				})
			},
		}
	}
</script>

<style>
.user-hd-unLogin{
		display: flex;
		justify-content: flex-start;
		margin: 10px;
		padding:20px;
		background: #31C27C;
		border-radius: 10px;
		// box-shadow: 1px 1px 3px 1px #c8c8c8;
		.user-img {
			img{
				border: 2px solid #fff;
				border-radius: 50%;
			}
		}
		.user-info{
			margin-left: 20px;
			color: #fff;
			
			.user-name{
				font-weight: 800;
				font-size: 20px;
				/* 改变字与字间的间距 */
				letter-spacing: 3px;
			}
			.login-button{
				padding: 5px 10px;
				margin: 20px 0 0 0;
				border: 3px solid white;
				border-radius: 30px;
				color: white;
				font-size: 14px;
			}
		}
	}	
</style>