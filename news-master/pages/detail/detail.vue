<template>
	<view class="detail">
		<view class="title">{{detail.title}}</view>
		<view class="info">
			<view class="author">编辑：{{detail.author}}</view>
			<view class="time">发布日期：{{detail.posttime}}</view>
		</view>
		<view class="content">
			<rich-text :nodes="detail.content"></rich-text>			
		</view>
		<view class="description">
		</view>
	</view>
</template>

<script>
	import {parseTime} from "@/utils/tool.js"
	
	
	export default {
		data() {
			return {
				options:null,
				detail:{}
			};
		},
		onLoad(e){			
			this.options=e;//e 包含了 cid 和 id
			// console.log("cid: " + e.cid); // 输出新闻分类的ID
			console.log("id: " + e.id);   // 输出新闻的ID
			this.getDetail();
		},
		methods:{
			getDetail(){
				uni.request({
					// url:"https://ku.qingnian8.com/dataApi/news/detail.php",
					// data:this.options,
					url:"http://localhost:9090/api/detail/"+this.options.id,
					success:res=>{
						//console.log("res.data.posttim: "+res.data.posttim)
						res.data.posttime=parseTime(res.data.posttime)
						console.log("res.data.content: "+res.data.content)
						///<img/gi 是一个正则表达式，其中：<img 是要查找的字符串。
						//g 表示全局匹配，即匹配整个字符串中所有出现的 
						//i 表示不区分大小写
						
						res.data.content=res.data.content.replace(/<img/gi,'<img style="max-width:100%"')
						this.detail=res.data
						this.saveHistory()
						
						uni.setNavigationBarTitle({
							title:this.detail.title
						})
					}
				})
			},
			saveHistory(){
				
				let historyArr=uni.getStorageSync("historyArr") || []
				let item={
					id:this.detail.id,
					classid:this.detail.classid,
					picurl:this.detail.picurl,
					title:this.detail.title,
					looktime:parseTime(Date.now())
				}
				
				let index=historyArr.findIndex(i=>{
					return i.id==this.detail.id
				})
				
				if(index>=0){
					historyArr.splice(index,1)
				}
								
				historyArr.unshift(item)	
				historyArr=historyArr.slice(0,10)		
				uni.setStorageSync("historyArr",historyArr)
			}
		}
	}
</script>

<style lang="scss">
.detail{
	padding:30rpx;
	.title{
		font-size: 46rpx;
		color:#333;
	}
	.info{
		background: #F6F6F6;
		padding:20rpx;
		font-size: 25rpx;
		color:#666;
		display: flex;
		justify-content: space-between;
		margin:40rpx 0;
	}
	.content{
		padding-bottom:50rpx;		
	}
	.description{
		background: #FEF0F0;
		font-size: 26rpx;
		padding:20rpx;
		color:#F89898;
		line-height: 1.8em;
	}
}
</style>
