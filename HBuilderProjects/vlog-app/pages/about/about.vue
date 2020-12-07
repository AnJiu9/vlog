<template>
	<view>
		<view class="flex justify-center p-2" hover-class="bg-light">
			<image src= "/static/2.png" style="width: 200rpx;height: 200rpx; " class="rounded-circle" ></image>
		</view>
		<view class="flex justify-center flex-1 px-2">
			<text class="font text-muted">version 1.0.0</text>
		</view>
		<view class="margin-top: 20rpx">
			<uni-list-item title="新版本检测" ></uni-list-item>
			<uni-list-item title="社区用户协议" ></uni-list-item>
		</view>
	</view>
</template>

<script>
	import uniListItem from '@/components/uni-ui/uni-list-item/uni-list-item.vue';
	export default {
		components:{
			uniListItem
		},
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
