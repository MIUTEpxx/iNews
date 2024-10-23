
<template>
	<view class="user-hd-unLogin">
		<view class="user-img">
			<img :src="this.user.picurl" alt="" />
		</view>
		<view class="user-info">
			<view class="user-name">
				{{this.user.nickname}}
			</view>
			<view class="button">
				<view class="login-button" @click="goLogin">
					切换账号
				</view>
				<view class="signOut-button " @click="signOut">
					退出账号
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name:"user-hd",
		data(){
			return{
				
			};
		},
		props:{
			userId: {
				type: Number,
					default: 0
				},
				user: {
					type: Object,
					default() {
						return {
							email:"123@email.com",
							nickname:"pxx",
							phone:"123456",
							picurl:"http://localhost:9090/images/user/10086.jpg"
						}
					}
				}		
		},
		methods:{
			goLogin(){
				uni.navigateTo({
					url:`/pages/user/login`					
					//url:`/pages/detail/detail?cid=$51&id=1`
				})
			},
			signOut(){
				this.$set(getApp().globalData,'userId',0)
				this.$set(getApp().globalData,'isLoggedIn',false)
				this.$forceUpdate()
			},
		}
	}
</script>

<style>
.user-hd-unLogin{
		display: flex;
		justify-content: space-between;
		margin: 10px;
		padding:20px;
		background: #31C27C;
		border-radius: 10px;
		// box-shadow: 1px 1px 3px 1px #c8c8c8;
		
		.user-img {
			img{
				max-width: 100px;
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
			.button{
				display: flex;
				.signOut-button,
				.login-button{
					padding: 5px 10px;
					margin: 20px 10px 0 0;
					border: 3px solid white;
					border-radius: 30px;
					color: white;
					font-size: 14px;
				}
			}
			
		}
	}	
</style>