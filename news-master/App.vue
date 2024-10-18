<script>
	export default {
		 globalData: {//全局数据
		    isLoggedIn: false,// 默认为未登录状态
		    userId: 0,// 存储用户id
		  },
		onLaunch: function() {
			console.log('App Launch')
			 this.globalData.isLoggedIn = false; // 默认为未登录状态
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/* 对图标进行全局注册 */
	@import '/static/iconfont.css';
view{
	box-sizing: border-box;
}
</style>
