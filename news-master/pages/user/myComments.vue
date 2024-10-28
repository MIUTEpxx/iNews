<template>
	<view class="myComments">
			<div class="commentItem" v-for="item in commentItmes" :key="item.commentid">
				<comment :item="item"></comment>
			</div>
	</view>
</template>

<script>
	import {parseTime} from"../../utils/tool.js"
	export default {
		data(){
			return{
				commentItmes:{},
				app:{},
			}
		},
		onLoad() {
			this.app=getApp();
		},
		watch: {
		   'app.globalData': {
		     handler: function(newVal, oldVal) {
		       // 当app.globalData中的任何属性发生变化时，这个函数会被调用
		       if (newVal.isLoggedIn) {
		         // 登录状态变化为已登录，可以在这里重新获取用户信息或者触发页面更新
		         this.getComment()
		       }
		     },
		     deep: true // 设置深度监听
		   }
		 },
		methods:{
			getComment(){
				//获取评论 
				uni.request({
					url:"http://localhost:9090/api/comment/get_by_userid/"+getApp().globalData.userId,
					success:res=>{
						this.commentItmes=res.data;
						this.commentItmes.sort((a, b) => parseInt(b.posttime)-parseInt(a.posttime));
						this.commentItmes.forEach(item=>{
							item.posttime=parseTime(parseTime(item.posttime));
							item.picurl='http://localhost:9090' + item.picurl;
						})
					}
				})
			},
		}
	}
</script>

<style>
</style>