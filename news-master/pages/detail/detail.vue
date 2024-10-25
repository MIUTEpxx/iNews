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
		<view class="interaction">
			<view class="favorites" v-if="!this.isFavorited" @click="changeFavorite">
				<i class="iconfont icon-shoucang"></i>
			</view>
			<view class="favorites-yes" v-if="this.isFavorited" @click="changeFavorite">
				<i class="iconfont icon-shoucang"></i>
			</view>
		</view>
	</view>
</template>

<script>
	import {parseTime} from "@/utils/tool.js"
	
	
	export default {
		data() {
			return {
				options:null,
				detail:{},
				app:{},
				isFavorited: false, // 表示新闻是否被收藏
			};
		},
		onLoad(e){			
			this.options=e;//e 包含了 cid 和 id
			// console.log("cid: " + e.cid); // 输出新闻分类的ID
			//console.log("id: " + e.id);   // 输出新闻的ID
			this.getDetail();
			this.app=getApp();
		},
		onShow() {
		},
		   watch: {
		      'app.globalData': {
		        handler: function(newVal, oldVal) {
		          // 当app.globalData中的任何属性发生变化时，这个函数会被调用
		          if (newVal.isLoggedIn) {
					  //若为登录状态 检测该新闻是否已被该用户收藏
		             this.checkFavorite();
					 this.addHits();
		          }
		        },
		        deep: true // 设置深度监听
		      }
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
						//console.log("res.data.content: "+res.data.content)
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
			},
			checkFavorite(){
				//检查该新闻是否已被收藏			
				uni.request({
					url:"http://localhost:9090/api/favorites/check/"
					+getApp().globalData.userId+"/"+this.options.id,
					success:res=>{
						if(res.data==true){
							//用户收藏了该条新闻
							this.isFavorited=true;
						}else{
							//用户未收藏该新闻
							this.isFavorited=false;
						}
					},
					fail: (err) => {
					      // 请求失败的处理逻辑
					      console.error('用户收藏数据请求失败:', err);
					    }
				})
			},
			changeFavorite(){
				//点击收藏按钮
				if(this.app.globalData.isLoggedIn==false){
					// 弹出提示
					 uni.showToast({
					   title: '请登录后操作',
					   icon:"error",
					   duration: 1000 // 弹窗显示的时间，单位毫秒
					 });
					 return;
				}
				if(this.isFavorited==true){
					//取消收藏
					uni.request({
						url:"http://localhost:9090/api/favorites/delete/"
						+getApp().globalData.userId+"/"+this.options.id,
						method:"DELETE",
						success:res=>{
							console.log("delete? ",res.data);
							if(res.data==true){
								//成功取消收藏
								this.isFavorited=false;
								// 弹出提示
								 uni.showToast({
								   title: '取消收藏',
								   duration: 1000 // 弹窗显示的时间，单位毫秒
								 });
							}else{
								//失败
								this.isFavorited=true;
								// 弹出提示
								 uni.showToast({
								   title: '取消收藏失败',
								   icon:"error",
								   duration: 1000 // 弹窗显示的时间，单位毫秒
								 });
							}
						},
						fail: (err) => {
						      // 请求失败的处理逻辑
						      console.error('用户收藏数据请求失败:', err);
						    }
					})
					
				}else{
					//加入收藏
					uni.request({
						url:"http://localhost:9090/api/favorites/add/"
						+getApp().globalData.userId+"/"+this.options.id,
						method:"POST",
						success:res=>{
							console.log("add? ",res.data);
							if(res.data==true){
								//成功添加收藏
								this.isFavorited=true;
								// 弹出提示
								 uni.showToast({
								   title: '收藏成功',
								   duration: 1000 // 弹窗显示的时间，单位毫秒
								 });
							}else{
								//失败
								this.isFavorited=false;
								// 弹出提示
								 uni.showToast({
								   title: '收藏失败',
								   icon:"error",
								   duration: 1000 // 弹窗显示的时间，单位毫秒
								 });
							}
						},
						fail: (err) => {
						      // 请求失败的处理逻辑
						      console.error('用户收藏数据请求失败:', err);
						    }
					})
				}
			},
			addHits(){
				//增加该新闻的浏览量
				uni.request({
					url:"http://localhost:9090/api/news/hits/"
					+this.options.id,
					method:"PUT",
					success:res=>{
	
					},
					fail: (err) => {
					      // 请求失败的处理逻辑
					      console.error('新闻浏览量增加请求失败', err);
					    }
				})
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
	.interaction{
		display: flex;
		padding:20rpx;
		background: #e9f8ec;
		border-radius: 25px;
		.favorites{
			padding: 5px;
			background: white;
			border-radius: 50%;
			border: 3px solid #5d5b5b;
			i{
				color:#5d5b5b;
				font-size: 40px;
			}
		}
		.favorites-yes{
			padding: 5px;
			background: #feee89;
			border-radius: 50%;
			border: 3px solid #ff6d00;
			i{
				color:#ff6d00;
				font-size: 40px;
			}
		}
	}
}
</style>
