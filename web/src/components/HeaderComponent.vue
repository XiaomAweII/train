<template>
  <a-layout-header class="header">
    <div class="logo"/>
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="1">nav 1测试改动</a-menu-item>
      <a-menu-item key="2">nav 2</a-menu-item>
      <a-menu-item key="3">nav 3</a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script>
import {defineComponent, ref} from 'vue';

export default defineComponent({
  name: "HeaderComponent",
  setup() {

    return {
      selectedKeys1: ref(['2']),
    };
  },
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<!--scoped的意思是这个css样式只在当前页面生效, 不使用的话就.body{}-->
<style scoped>

</style>
