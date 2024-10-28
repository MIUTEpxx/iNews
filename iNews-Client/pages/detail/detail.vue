<script>
	import {parseTime} from "@/utils/tool.js"

	
	export default {
		data() {
			return {
				options:null,
				detail:{},
				app:{},
				commentItmes:[],
				isFavorited: false, // 表示新闻是否被收藏
				byTime:false,//评论区是否按时间排序(否则按热门排序)
				commentContent:'',
			};
		},
		onLoad(e){			
			this.options=e;//e 包含了 cid 和 id
			// console.log("e:",e);
			this.getDetail();
			this.getComment();
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
			getComment(){
				//获取评论 
				uni.request({
					url:"http://localhost:9090/api/comment/get_by_newsid/"+this.options.id,
					success:res=>{
						this.commentItmes=res.data;
						if(!this.byTime){this.commentItmes.sort((a, b) => b.likes-a.likes)}
						else{this.commentItmes.sort((a, b) => parseInt(b.posttime)-parseInt(a.posttime))}
						this.commentItmes.forEach(item=>{
							item.posttime=parseTime(item.posttime);
							item.picurl='http://localhost:9090' + item.picurl;
							item.newstitle='';
						})
						
						console.log(this.commentItmes);
					}
				})
			},
			getDetail(){
				uni.request({
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
				if(getApp().globalData.isLoggedIn==false) return;
				//保存历史记录
				uni.request({
					url:"http://localhost:9090/api/history/save/"
					+getApp().globalData.userId+'/'+this.options.id,
					method:"POST",
					success:res=>{
						
					},
					fail: (err) => {
					      // 请求失败的处理逻辑
					      console.error('历史记录保存请求失败', err);
					    }
				})
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
			},
			addComment(){
				if(getApp().globalData.isLoggedIn==false){
					uni.showToast({
					  title: '请登录后评论',
					  icon:"error",
					  duration: 1000 // 弹窗显示的时间，单位毫秒
					});
					return;
				}
				if(this.commentContent==''){
					uni.showToast({
					  title: '请输入评论内容',
					  icon:"error",
					  duration: 1000 // 弹窗显示的时间，单位毫秒
					});
					return;
				}
				const comment={
					userid:getApp().globalData.userId,
					newsid:this.options.id,
					respondid:-1,
					posttime:'0',
					content:this.commentContent,
				}
				uni.request({
				    url: "http://localhost:9090/api/comment/add", 
					method:"POST",
					data:comment,
					header: {
					      'content-type': 'application/json' // 默认值
					    },
				    success: res => {
						// console.log("res.data ",res.data)
						if(res.data>0){
							this.getComment();
						}
					},
					fail: err => {// 请求失败的处理逻辑
					      console.error('发送评论请求失败:', err);
					}
				})
			},
			changeOrdination(e){
				this.byTime=e;
				this.getComment();
			},	
		}
	}
</script>

<template>
	<view class="detail-page">
		<view class="detail">
			<view class="title">{{detail.title}}</view>
			<view class="info">
				<view class="author">编辑：{{detail.author}}</view>
				<view class="time">发布日期：{{detail.posttime}}</view>
			</view>
			<view class="content">
				<rich-text :nodes="detail.content"></rich-text>			
			</view>
		</view>
		<view class="comment-section">
			<view class="comment-section-hd">
				<p>评论区</p>
				<hr />
				<view class="ordination">
					<p v-if="this.byTime" @click="changeOrdination(false)">热门</p>
					<p class="selected" v-if="!this.byTime">热门</p>
					<span>|</span>
					<p v-if="!this.byTime" @click="changeOrdination(true)">时间</p>
					<p class="selected" v-if="this.byTime">时间</p>
				</view>
			</view>
			<div class="commentItem" v-for="item in commentItmes" :key="item.commentid">
				<comment :item="item"></comment>
			</div>
			<view class="bottom">
				<span>没有更多评论了...</span>
			</view>
		</view>
		<view class="interaction">
			<view class="favorites" v-if="!this.isFavorited" @click="changeFavorite">
				<i class="iconfont icon-shoucang"></i>
			</view>
			<view class="favorites-yes" v-if="this.isFavorited" @click="changeFavorite">
				<i class="iconfont icon-shoucang"></i>
			</view>
			
			<view class="comment-input">
				<textarea placeholder="请输入评论内容" v-model="commentContent" />
			</view>
			<view class="send-button" @click="addComment()">
				<i class="iconfont icon-icon_sent"></i>
			</view>
		</view>
	</view>
</template>



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
	
}
.comment-section{
	background: #f1f1f1;
	.comment-section-hd{
		background:#fff;
		padding-bottom: 10px;
		hr{
			margin: 5px 0 5px 0;
		}
		p{
			margin-left: 10px;
			font-size: 18px;
			font-weight: 900;
			color: #179c41;
		}
		.ordination{
			display: flex;
			span{
				margin: 0px 5px 0px 5px;
				font-size: 20px;
				font-weight: 900;
				color: #5d5b5b;
			}
			p{
				font-size: 20px;
				font-weight: 500;
				color: #5d5b5b;
			}
			p.selected{
				font-size: 20px;
				font-weight: 600;
				color: #179c41;
			}	
		}
	}
	.bottom{
		height: 100px;
		display: flex;
		justify-content: center;
		margin-top: 20px;
		color: #666;
	}
}	
.interaction{
	position: fixed;
	width: 100%;
	left: 0;
	bottom: calc( var(--window-bottom));
	z-index: 1030;	    
	margin-bottom: 6;
	padding:5px 10px;
	display: flex;
	align-items: center;
	background: #ffffff;
	box-shadow: 0px 0px 3px 1px #d5d5d5;
	border-radius: 10px 10px 0px 0px;
	.favorites{
		padding: 5px;
		background: white;
		border-radius: 50%;
		border: 3px solid #5d5b5b;
		box-shadow: 1px 1px 3px 1px #d5d5d5;
		i{
			color:#5d5b5b;
			font-size: 30px;
		}
	}
	.favorites-yes{
		padding: 5px;
		background: #feee89;
		border-radius: 50%;
		border: 3px solid #ffa45a;
		box-shadow: 1px 1px 3px 1px #d5d5d5;
		i{
			color:#ffa45a;
			font-size: 30px;
		}
	}
	.comment-input{
		width: 80%;
		height: 50px;
		margin-left: 15px;
		padding: 5px;
		background: #e8e8e8;
		border-radius: 10px;
		
		textarea{
			width: 100%;
			height: 100%;
		}
	}	
	.send-button{
		margin-left: 12px;
		padding: 8px 8px;
		background: #56bf7f;
		border-radius: 10px;
		color: #f4f4f4;
		.iconfont{
			font-size: 26px;
		}
	}
}		

</style>
