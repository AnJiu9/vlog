<template>
	<view>
		<input type="text" v-model="newpassword" placeholder="输入新密码" class="border-bottom p-2"/>
		<input type="text" v-model="confirmpassword" placeholder="输入确认密码" class="border-bottom p-2"/>
		<view class="py-2 px-3">
			<button 
				class="bg-main text-white" 
				style="border-radius: 50rpx; border: 0; width: 500rpx; margin-top: 15rpx;" 
				type="primary">设置</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				newpassword:'',
				confirmpassword:''
			}
		},
		
		methods: {
			
		}
	}
</script>

<style>

</style>
