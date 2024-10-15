<template>
	<view class="user">
		<view class="user-hd">
			<view class="user-img">
				<img src="../../static/images/logo2.png" alt="" />
			</view>
			<view class="user-info">
				<view class="name">
					{{userName}}
				</view>
			</view>
		</view>
		
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userName:'游客'
			};
		},
		onShow(){
			this.getUserData()
		},
		methods:{
			//获取用户数据
			getUserData(){
				
			}
		}
	}
</script>

<style lang="scss">
.user{
	.user-hd{
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
			
		}
	}
}
</style>
