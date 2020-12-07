<template>
	<view>
		<uni-collapse>
			<uni-collapse-item title="今日槽点？" showAnimation>
				<view class="content">
					<input type="text" placeholder="看着你写" class="border-bottom p-2"/>
				</view>
			</uni-collapse-item>
			<uni-collapse-item title="吐槽吧" showAnimation>
				<view class="content">
					<input type="text" placeholder="瓜子备好了" class="border-bottom p-2"/>
				</view>
			</uni-collapse-item>
		</uni-collapse>
		<view class="py-2 px-3">
			<button 
				class="bg-main text-white" 
				style="border-radius: 50rpx; border: 0; " 
				type="primary">设置</button>
		</view>
	</view>
</template>

<script>
	import uniCollapse from '@/components/uni-ui/uni-collapse/uni-collapse.vue';
	import uniCollapseItem from '@/components/uni-ui/uni-collapse-item/uni-collapse-item.vue';
	export default {
		components:{
			uniCollapse,
			uniCollapseItem 
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
