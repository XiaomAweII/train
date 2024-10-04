<template>
  <a-layout id="components-layout-demo-top-side">
    <HeaderComponent></HeaderComponent>
    <a-layout-content style="padding: 0 50px">
      <a-breadcrumb style="margin: 16px 0">
        <a-breadcrumb-item>Home</a-breadcrumb-item>
        <a-breadcrumb-item>List</a-breadcrumb-item>
        <a-breadcrumb-item>App</a-breadcrumb-item>
      </a-breadcrumb>
      <a-layout style="padding: 24px 0; background: #fff">
        <SiderComponent></SiderComponent>
        <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
          所有会员总数:{{ count }}
        </a-layout-content>
      </a-layout>
    </a-layout-content>
    <a-layout-footer style="text-align: center">
      Ant Design ©2018 Created by Ant UED
    </a-layout-footer>
  </a-layout>
</template>
<script>
import {defineComponent, ref} from 'vue';//ref用来声明基本的数据类型,reactive用来声明对象或对象数组
import HeaderComponent from "@/components/HeaderComponent.vue";
import SiderComponent from "@/components/SiderComponent.vue";
import axios from "axios";
import {notification} from "ant-design-vue";

export default defineComponent({
  components: {
    SiderComponent,
    HeaderComponent,
  },
  setup() {
    const count = ref(0);
    axios.get("/member/member/count").then((response) => {
      let data = response.data;
      if (data.success) {
        count.value = data.content;
      } else {
        notification.error({description: data.message});
      }
    });

    return {
      count,
    };
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