<script>
export default {
	
  data() {
    return {
	  tempHeadImg:'',
	  picurl:'../../static/images/logo2.png',
    };
  },
  onLoad(options) {
  	// 获取传递的picurl参数
  	this.picurl = options.picurl;
  },
  methods: {
    	updateImg() {
    		uni.chooseImage({
    			sourceType: ['album'], //从相册选择
    			success: chooseImageRes => {
					this.tempHeadImg=chooseImageRes.tempFilePaths[0];
    				//console.log('成功: ', this.tempHeadImg);
					//this.upload()
    			},
    			fail: err => {
    				this.myToast('图片上传失败', 'none');
    			}
    		});
    	},	
		deselect(){
			//取消选择的图片
			this.tempHeadImg='';
		},
		upload(){
			//上传选择的图片
			// 检查是否有临时图片路径
			if (this.tempHeadImg) {
			  const uploadUrl = "http://localhost:9090/user/uploadHeadImg"; // 你的上传图片接口地址
			  uni.uploadFile({
			    url: uploadUrl,
			    filePath: this.tempHeadImg,//图片临时文件路径
			    name: 'file', // 这是发送到服务器的文件参数名称
			    formData: {
			      userId: getApp().globalData.userId // 附加其他数据:用户ID
			    },
			    success: (uploadRes) => {
					if(uploadRes.data=='sucess'){
						uni.showToast({
						  title: '新头像上传成功!',
						  icon: 'success',
						  duration: 1500 // 弹窗显示的时间，单位毫秒
						});
											
						// 1.5秒后回到上一个页面
						setTimeout(() => {
						  uni.navigateBack({
						    delta: 1 // 返回上一级页面
						  });
						}, 1500);       
					}
			    },
			    fail: (err) => {
			      console.error(err);
			      console.error('图片上传失败', 'none');
			    }
			  });
			} else {
			  console.error('没有选择图片', 'none');
			}    
		},
	}
};
</script>

<template>
  <view class="container">
	<p>当前头像</p>
	<view class="nowHeadImg">
		<img :src="this.picurl" alt="" />
	</view>
	
	<p>点击上传新头像</p>
    <view class="headImg" @click="updateImg" :style="{ backgroundImage: `url(${this.tempHeadImg})` }">
      <span v-if="this.tempHeadImg==''">+</span>
    </view> 
	
	<view class="buttons" v-if="this.tempHeadImg!=''">
		<button @click="upload()">确认图片</button>
		<button @click="deselect()">取消图片</button>
	</view>

  </view>
</template>



<style>
	.container{
		margin: 50px;
	    display: flex;
	    flex-direction: column;
	    align-items: center;
		
		p{
			margin-bottom: 10px;
			font-size: 18px;
			font-weight: 600;
		}
		.nowHeadImg{
			margin-bottom: 50px;
			img{
				border-radius: 10px;
				width: 150px;
				height: 150px; 
			}
		}
		.headImg {
		  display: flex;
		  align-items: center;
		  justify-content: center;	
		  width: 150px; 
		  height: 150px; 
		  background: #948e8e;
		  background-size: cover;
		  background-position: center;
		  border-radius: 10px;
		  span{
			  text-align: center;
			  font-size: 65px;
		  }
		}
		.buttons{
			display: flex;
			button{
				margin: 20px 5px 10px 5px;
			}
			
		}
	}
	
</style>
