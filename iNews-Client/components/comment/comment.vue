<script>
	import {parseTime} from "@/utils/tool.js"
	export default {
		name:"comment",
		props:{
			item:{
				type:Object,
				default(){
					return {
						commentid:0,
						nickname:"pxx",
						picurl:"http://localhost:9090/images/user/10086.jpg",
						content:"test 12321323213213213213213213213213213213213211232132321321321321321321321321321321321321",
						likes:'114514',
						posttime:'2024-10-16 18:04:26',
						newstitle:'',
					}
				}
			}
		},
		data() {
			return {
				is_like:false,
				is_notlike:false,
			};
		},
		methods:{
			goDetail123(){
				//跳转到详情页
				uni.navigateTo({
					url:`/pages/detail/detail?id=${this.item.newsid}`
				})
			},
			changeLikes(like){
				let a=0;
				if(like==1){ this.is_like=!this.is_like; a=-1;}
				else if(like==2){
					this.is_like=!this.is_like;
					this.is_notlike=false;
					a=1;
				}else if(like==3){this.is_notlike=!this.is_notlike;a=1;}
				else{
					this.is_notlike=!this.is_notlike;
					this.is_like=false;
					a=-1;
				}
				uni.request({
					url:"http://localhost:9090/api/comment/addlikes/"+this.item.commentid+'/'+a,
					method:"POST",
					success:res=>{
						this.item.likes+=a;
					}
				})
			},
			deleteComment(){
				if(!confirm('您确定要永久删除该评论吗？')) return;
				uni.request({
					url:"http://localhost:9090/api/comment/delete/"+this.item.commentid,
					method:"DELETE",
					success:res=>{
						this.item.likes+=a;
					}
				})
			},
		}
	}
</script>

<template>
	<!-- 新闻简略信息项 -->
	<view class="comment">
		<view class="comment-hd">
			<view class="user-info">
				<!-- 头像 -->
				<view class="user-img">
					<img :src="item.picurl" alt="" />
				</view>
				<!-- 名称 -->
				<view class="user-nickname">
					<span>{{ item.nickname }}</span>	
				</view>
			</view>
			<view class="time">发布日期：<br/>{{item.posttime}}</view>
		</view>
		<view class="comment-body">
		    <!-- 内容 -->
		    <view class="comment-content">
		        {{ item.content }}    
		    </view>
		    <view class="comment-likes" v-if="item.newstitle==''">
				<!-- 点赞 点踩 -->
				<span class="likes-yes" v-if="is_like" @click="changeLikes(1)"><i class="iconfont icon-good"></i>{{item.likes}}</span>    
		        <span class="likes" v-else @click="changeLikes(2)"><i class="iconfont icon-good"></i>{{item.likes}}</span>
				<span class="notlikes-yes" v-if="is_notlike" @click="changeLikes(3)"><i class="iconfont icon-diancai"></i></span>
				<span class="notlikes" v-else @click="changeLikes(4)"><i class="iconfont icon-diancai"></i></span>    
		    </view>
		</view>
		<view class="source"  v-if="item.newstitle!=''" @click="goDetail123()">
			<p>来自新闻:</p>
			<span>{{item.newstitle}}</span>
		</view>
		<view class="delete" v-if="item.newstitle!=''" @click="deleteComment()">
			<i class="iconfont icon-shanchu"></i><span>删除该条评论</span>
		</view>											 
	</view>
</template>



<style lang="scss">
.comment{
	margin: 20px 10px 10px 10px;
	padding: 10px;
	background: #ffffff;
	border-radius: 10px;
	box-shadow: 1px 1px 3px 1px #d5d5d5;
	.comment-hd{
		display: flex;
		justify-content: space-between;
		align-items: center;
			padding: 5px;
		background: #e4f4e8;
		border-radius: 10px;
		.user-info{
			flex: 0 0 60%; /* 参数:不放大，不缩小，基础宽度为60% */
			display: flex;
			align-items: center;
			.user-img {
				flex: 0 0 30%;
				margin-right: 20px ;
				img{
					width: 100%;
					height:auto;
					border-radius: 50%;
					/* 使图片等比例缩放 */
					object-fit: cover;
					aspect-ratio: 1 / 1;/* 设置宽高比为1:1，确保图片是正方形 */
				}
			}
			.user-nickname{
				flex: 0 0 70%;
				margin-bottom: 10px;
				span{
					font-size: 20px;
					font-weight: 900;
				}
			}
		}
		.time{
			flex: 0 0 40%;
			font-size: 12px;
		}
	}
	.comment-body{
		margin: 10px;
		.comment-content{
			margin: 10px 0;
			/* 将元素设置为块级弹性盒子模型 */
			display: -webkit-box;
			overflow-wrap: break-word;
			-webkit-box-orient: vertical;			
			font-size: 18px;
			color: #474747;   
		}
		.comment-likes{
			display: flex;
			justify-content: flex-start;
			align-items: flex-end;    
			span{
				margin-right: 10px;
			}
			.likes{
				flex: 0 0 20%;
			}
			.likes-yes{
				flex: 0 0 20%;
				color: #56bf7f;
			}
			.notlikes-yes{
				color: #56bf7f;
			}
		}
	}
	.source{
		padding: 10px;
		border-radius: 10px;
		background: #f5f5f5;
		p{
			font-weight: 600;
			color: #747474;
		}
		span{
			font-size: 18px;
		}
	}
	.delete{
		margin: 5px 0 0 5px;
		color: red;
		i.iconfont{
			font-size: 18px;
		}
		span{
			margin-left:3px;
			font-size: 14px;
		}
	}
}
</style>