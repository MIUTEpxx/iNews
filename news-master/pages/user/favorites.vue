<template>
	<view class="content" >
		<div class="row" v-for="item in newsArr" :key="item.id">
			<newsbox :item="item" @click.native="goDetail(item)"></newsbox>
		</div>
	</view>
</template>

<script>
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
		          this.getNewsByUserFavorites();
		       }
		     },
		     deep: true // 设置深度监听
		   }
		},
		methods:{
			getNewsByUserFavorites(){
				uni.request({
				    url: "http://localhost:9090/api/favorites/getNews/"+this.app.globalData.userId, 
					method:'GET',
				    success: res => {
				        this.newsArr=res.data;
				    },
					fail: (err) => {
					      // 请求失败的处理逻辑
					      console.error('用户id:'+this.app.globalData.userId+' 数据请求失败:', err);
					    }
				})
			},
			//跳转到详情页
			goDetail(item){				
				uni.navigateTo({
					url:`/pages/detail/detail?cid=${item.classid}&id=${item.id}`
					//url:`/pages/detail/detail?id=${item.id}`
				})
				//console.log(item.classid);
				//console.log(item.id);
			},
		},
	}
</script>

<style>
	.content{
		padding:30rpx;
		.row{
			border-bottom:1px dotted #efefef;
			padding:20rpx 0;
		}
	}
</style>