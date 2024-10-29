<script>
	export default {
		data(){
			return{
				searchContent:'',
				newsArr:{},
				filteredNews:{},
			}
		},
		onLoad() {
			this.getNewsData();
		},
		methods:{
			getNewsData() {
				 uni.request({
				        url: "http://localhost:9090/api/news/getAll",
				        success: res => {
				           this.newsArr=res.data;
				        }
				    })
			},
			filterNews(){
				console.log(this.newsArr);
				if (this.searchContent) {
				        this.filteredNews = this.newsArr.filter(news =>
				          news.title.toLowerCase().includes(this.searchContent.toLowerCase()) ||
				          news.author.toLowerCase().includes(this.searchContent.toLowerCase())
				        );
				}
			},
			//跳转到详情页
			goDetail(item){				
				uni.navigateTo({
					url:`/pages/detail/detail?cid=${item.classid}&id=${item.newsid}`
				})
			},
		}
	}
</script>


<template>
	<view class="search">
		<view class="search-hd">
			<view class="search-input">
				<textarea placeholder="请输入搜索内容" v-model="searchContent" />
			</view>
			<view class="search-button" @click="filterNews()">
				<i class="iconfont icon-icon_sent"></i>
			</view>
		</view>
		<view class="content" >
			<div class="row" v-for="item in filteredNews" :key="item.id">
				<newsbox :item="item" @click.native="goDetail(item)"></newsbox>
			</div>
		</view>
		
	</view>
	
</template>



<style lang="scss">
	.search-hd{
		position: fixed;
		width: 100%;
		left: 0;
		top: calc( var(--window-top));
		z-index: 1030;	    
		margin-bottom: 6;
		
		padding: 10px;
		display: flex;
		background: #f7f7fa;
		box-shadow: 0 0 3px 1px #adadad;
		.search-input{
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
		.search-button{
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
	.content{
		margin: 80px 10px 10px 10px;
		.row{
			margin-top: 10px;
			padding: 10px;
			border-bottom: 3px solid #f0f0f0;
		}
	}
</style>