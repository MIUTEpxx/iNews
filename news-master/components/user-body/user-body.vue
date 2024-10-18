<template>
	<view class="user-body">
	    <ul>
			<li v-for="(v,i) in item" :key="i">
				<i :class="`iconfont ${v.icon}`"></i>
				<p>{{ v.title }}</p>
			</li>
	    </ul>              
	</view>        
</template>

<script>
	export default{
		data(){
			return{
				item:[
					{title:'历史记录',icon:'icon-lishijilu'},
					{title:'我的收藏',icon:'icon-shoucang'},
					{title:'我的评论',icon:'icon-pinglun'},
					{title:'消息通知',icon:'icon-xiaoxitongzhi'},
				]
			};
		}
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