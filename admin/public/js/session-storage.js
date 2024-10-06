// 所有的session key都在这里统一定义，可以避免多个功能使用同一个key
SESSION_ORDER = "SESSION_ORDER";
SESSION_TICKET_PARAMS = "SESSION_TICKET_PARAMS";

SessionStorage = {
    get: function (key) {
        //原声的只能操作字符串,所以封装了一下
        var v = sessionStorage.getItem(key);//h5自带的内置变量,会话缓存,还有个localStorage,这个是哪怕关闭页面,下一次访问还是能访问缓存
        if (v && typeof(v) !== "undefined" && v !== "undefined") {
            return JSON.parse(v);
        }
    },
    set: function (key, data) {
        sessionStorage.setItem(key, JSON.stringify(data));
    },
    remove: function (key) {
        sessionStorage.removeItem(key);
    },
    clearAll: function () {
        sessionStorage.clear();
    }
};
