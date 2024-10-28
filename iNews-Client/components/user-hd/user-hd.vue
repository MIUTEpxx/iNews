
<template>
	<view class="user-hd-unLogin">
		<view class="user-img" @click="goHeadImg()">
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
			goLogin(){//登录界面
				uni.navigateTo({
					url:`/pages/user/login`,
				})
			},
			goHeadImg(){//头像界面
				// 对URL进行编码，避免特殊字符导致的问题
				let picUrl = encodeURIComponent(this.user.picurl); 
				uni.navigateTo({
					url:`/pages/user/headImg?picurl=${picUrl}`//传递数据
				})
			},
			signOut(){//登出账号
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
			 flex: 0 0 30%; /* 参数:不放大，不缩小，基础宽度为30% */
			img{
				width: 100px;
				height:100px;
				border: 2px solid #fff;
				border-radius: 50%;
				/* 使图片等比例缩放 */
				object-fit: cover;
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