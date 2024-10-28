<template>
<view class="content" >
			<div class="row" v-for="item in newsArr" :key="item.newsid">
				<newsbox :item="item" @click.native="goDetail(item)">123</newsbox>
			</div>
		</view>
</template>

<script>
		import {parseTime} from "@/utils/tool.js"
		
	export default {
			data() {
				return {
					app:{},
					newsArr:{},
				};
			},
			onLoad() {
				this.app=getApp();
			},
			watch: {
			   'app.globalData': {
			     handler: function(newVal, oldVal) {
			       // 当app.globalData中的任何属性发生变化时，这个函数会被调用
			       if (newVal.isLoggedIn) {
					//若为登录状态 获取用户收藏的新闻
			          this.getHistoryData();
			       }
			     },
			     deep: true // 设置深度监听
			   }
			},
		methods:{
			//跳转到详情页
			goDetail(item){
				uni.navigateTo({
					url:`/pages/detail/detail?id=${item.newsid}`
				})
			},
			
			//获取浏览记录
			getHistoryData(){
				uni.request({
				    url: "http://localhost:9090/api/history/getAll/"+getApp().globalData.userId, 
					method:'GET',
				    success: res => {
						res.data.forEach(data => data.looktime=parseTime(data.looktime) );
				        this.newsArr=res.data;
						console.log(this.newsArr);
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
	.content{
		padding:30rpx;
		.row{
			border-bottom:1px dotted #efefef;
			padding:20rpx 0;
		}
	}
</style>
