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

</style>