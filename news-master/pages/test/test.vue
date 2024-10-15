<template>
	<view class="test">
		测试
		<view class="clickTest" @click="clickTest">123123</view>
	</view>
</template>

<script>
	export default {
			
		methods:{
			clickTest(){
				uni.navigateTo({
					url:`/pages/test/test2`
				})
				console.log("123")
			}
		}
	}
</script>

<style>
	.test{
		color: black;
		
	}
	.clickTest{
		background: gray;
	}
</style>