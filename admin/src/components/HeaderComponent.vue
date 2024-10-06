<template>
  <a-layout-header class="header">
    <div class="logo"/>
    <div style="float: right;color: white;">
      您好: {{ member.mobile }} &nbsp;&nbsp;
      <!-- 使用router-link标签+to来跳转页面, 相当于a标签+href-->
      <router-link to="/login" style="color: white">
        退出登录
      </router-link>
    </div>
    <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome">
          <coffee-outlined/> &nbsp; 欢迎
        </router-link>
      </a-menu-item>
      <a-menu-item key="/passenger">
        <router-link to="/passenger">
          <user-outlined/> &nbsp; 乘车人管理
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script>
import {defineComponent, ref, watch} from 'vue';
import store from "@/store";
import router from '@/router';

export default defineComponent({
  name: "HeaderComponent",
  setup() {
    /*
    * 因为header只是显示, 不会改member, 所以声明成普通变量就可以, 不需要响应式变量
    * */
    let member = store.state.member;
    const selectedKeys = ref([]);

    watch(() => router.currentRoute.value.path, (newValue) => {
      console.log('watch: ', newValue);
      selectedKeys.value = [];
      selectedKeys.value.push(newValue);
    }), {immediate: true};

    return {
      member,
      selectedKeys
    };
  },
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<!--scoped的意思是这个css样式只在当前页面生效, 不使用的话就.body{}-->
<style scoped>

</style>
