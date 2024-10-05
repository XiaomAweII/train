<template>
  <a-layout id="components-layout-demo-top-side">
    <HeaderComponent></HeaderComponent>
    <a-layout>
      <SiderComponent></SiderComponent>
      <a-layout-content :style="{background:'#fff',padding:'24px'}">
        <router-view></router-view>
      </a-layout-content>
    </a-layout>

  </a-layout>
</template>
<script>
import {defineComponent} from 'vue';
import HeaderComponent from "@/components/HeaderComponent.vue";
import SiderComponent from "@/components/SiderComponent.vue";


export default defineComponent({
  components: {
    SiderComponent,
    HeaderComponent,
  },
  setup() {
    return {};
  },
});
</script>
<style>
#components-layout-demo-top-side .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>