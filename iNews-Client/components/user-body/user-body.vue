<template>
	<view class="user-body">
	    <ul>
			<li v-for="(v,i) in item" :key="i">
				<view class="item" @click="goTo(v.router)">
					<i :class="`iconfont ${v.icon}`"></i>
					<p>{{ v.title }}</p>
				</view>
			</li>
	    </ul>              
	</view>        
</template>

<script>
	export default{
		data(){
			return{
				item:[
					{title:'历史记录',icon:'icon-lishijilu',router:'/pages/user/history'},
					{title:'我的收藏',icon:'icon-shoucang',router:'/pages/user/favorites'},
					{title:'我的评论',icon:'icon-pinglun',router:'/pages/user/my-comments'},
					{title:'消息通知',icon:'icon-xiaoxitongzhi',router:'/pages/user/message'},
				]
			};
		},
		methods:{
			goTo(router){
				if(getApp().globalData.isLoggedIn==false){
					// 弹出提示
					 uni.showToast({
					   title: '请登录后操作',
					   icon:"error",
					   duration: 1000 // 弹窗显示的时间，单位毫秒
					 });
					 return;
				}
				uni.navigateTo({
					url:router		
				})
			},
		},
	}
</script>

<style>
	.user-body{
	    border-radius: 10px;
	    margin: 15px 10px;
	    background-color: white;
	    box-shadow: 0 0 6px 1px #00000023;
	}
	.user-body ul{
	    display: flex;
	    justify-content: space-between;
	    padding: 20px;
	    text-align: center;
		
		p{
			color: #666;
		}
		i{
		    font-size: 30px; 
			color: #666;
		}
	}
	.user-body ul li::marker{
		content: none;
	}
</style>