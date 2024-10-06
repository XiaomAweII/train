<template>
  <a-layout-sider width="200" style="background: #fff">
    <a-menu
        v-model:selectedKeys="selectedKeys"
        mode="inline"
        :style="{height: '100%',borderRight: 0}"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome">
          <coffee-outlined/> &nbsp; 欢迎
        </router-link>
      </a-menu-item>
      <a-menu-item key="/about">
        <router-link to="/about">
          <user-outlined/> &nbsp; 关于
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-sider>
</template>

<script>
import {defineComponent, ref, watch} from 'vue';
import router from '@/router';

export default defineComponent({
  name: "SiderComponent",
  setup() {
    const selectedKeys = ref([]);

    watch(() => router.currentRoute.value.path, (newValue) => {
      console.log('watch: ', newValue);
      selectedKeys.value = [];//每次有路由变化都需要清空
      selectedKeys.value.push(newValue);
    }), {immediate: true};

    return {
      selectedKeys,
    };
  },
});
</script>

<style scoped>

</style>
