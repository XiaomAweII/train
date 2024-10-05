<template>
  <p>
    <a-button type="primary" @click="showModal">新增</a-button>
  </p>
  <a-table :dataSource="dataSource" :columns="columns"/>
  <a-modal v-model:visible="visible" title="乘车人" @ok="handleOk" ok-text="确认" cancel-text="取消">
    <a-form :model="passenger" :label-col="{span: 4}" :wrapper-col="{ span: 20 }">
      <a-form-item label="姓名">
        <a-input v-model:value="passenger.name"/>
      </a-form-item>
      <a-form-item label="身份证">
        <a-input v-model:value="passenger.idCard"/>
      </a-form-item>
      <a-form-item label="类型">
        <a-select v-model:value="passenger.type">
          <a-select-option value="1">成人</a-select-option>
          <a-select-option value="2">儿童</a-select-option>
          <a-select-option value="3">学生</a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
import {defineComponent, ref, reactive} from 'vue';
import {notification} from "ant-design-vue";
import axios from "axios";

export default defineComponent({
  setup() {
    const visible = ref(false);

    //跟表中的字段保持一致
    const passenger = reactive({
      id: undefined,
      memberId: undefined,
      name: undefined,
      idCard: undefined,
      type: undefined,
      createTime: undefined,
      updateTime: undefined
    });

    const dataSource = [{
      key: '1',
      name: '孙悟空',
      age: 31,
      address: '花果山水帘洞'
    }, {
      key: '2',
      name: '猪八戒',
      age: 24,
      address: '高老庄'
    }, {
      key: '3',
      name: '沙悟净',
      age: 26,
      address: '流沙河'
    }];

    const columns = [{
      title: '姓名',
      dataIndex: 'name',
      key: 'name'
    }, {
      title: '年龄',
      dataIndex: 'age',
      key: 'age'
    }, {
      title: '住址',
      dataIndex: 'address',
      key: 'address'
    }]

    const showModal = () => {
      visible.value = true;
    };

    const handleOk = () => {
      axios.post("/member/passenger/save", passenger).then((response) => {
        let data = response.data;
        if (data.success) {
          notification.success({description: "保存成功!"});
          visible.value = false;
        } else {
          notification.error({description: data.message});
        }
      });
    };

    return {
      passenger,
      visible,
      showModal,
      handleOk,
      dataSource,
      columns,
    };
  },
});

</script>

<style>
</style>