<template>
	<view>
		<uni-status-bar></uni-status-bar>
		<view>
			<view 
				class="iconfont icon-guanbi flex align-center justify-center font-lg"
				style="width: 100rpx; height: 100rpx;"
				hover-class="bg-light"
				@click="back"
			></view>
		</view>
		
		<view class="text-center" style="padding-top: 130rpx; padding-bottom: 70rpx; font-size: 55rpx;">
				账号密码登录
		</view>
		
		<view class="px-2">
			<view class="mb-2">
				<input type="text" placeholder="昵称/手机号/邮箱" class="border-bottom p-2"/>	
			</view>
			<view class="mb-2 flex align-stretch">
				<input type="text" placeholder="请输入密码" class="border-bottom p-2 flex-1"/>
				<view class="border-bottom flex align-center justify-center font text-muted" style="width: 150rpx;">
					忘记密码？
				</view>
			</view>
		</view>
		
		<view class="py-2 px-3">
			<button class="bg-main text-white" style="border-radius: 50rpx; border: 0;" type="primary">登录</button>
		</view>
		
		<view class="flex align-center justify-center pt-2 pb-4">
			<view class="text-primary font-sm">验证码登录</view>
			<text class="text-muted mx-2">|</text>
			<view class="text-primary font-sm">登录遇到问题</view>
		</view>
		
		<view class="flex align-center justify-center">
			<view style="height: 1rpx; background-color: #dddddd; width: 100rpx;"></view>
			<view class="mx-2 text-muted">社交账号登录</view>
			<view style="height: 1rpx; background-color: #dddddd; width: 100rpx;"></view>
		</view>
	</view>
</template>

<script>
	import uniStatusBar from '@/components/uni-ui/uni-status-bar/uni-status-bar.vue'
	export default {
		components:{
			uniStatusBar
		},
		data() {
			return {
				
			};
		},
		onLoad() {
			
		},
		methods: {
			back(){
				uni.navigateBack({
					delta:1
				});
			}
		}
	};
</script>

<style>

</style>
