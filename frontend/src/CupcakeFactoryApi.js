import axios from "axios";
import store from "src/store";


let axiosInstance = axios.create({
  baseURL: process.env.VUE_APP_BACKEND_ENDPOINT,
  timeout: 60000,
  paramsSerializer: (params) => {
    const keys = Object.keys(params);
    let options = "";

    keys.forEach((key) => {
      const isParamTypeObject = typeof params[key] === "object";
      const isParamTypeArray = isParamTypeObject && (params[key].length >= 0);

      if (!isParamTypeObject) {
        options += `${key}=${params[key]}&`;
      }

      if (isParamTypeObject && isParamTypeArray) {
        params[key].forEach((element) => {
          options += `${key}=${element}&`;
        });
      }
    });
    return options ? options.slice(0, -1) : options;
  }
});

axiosInstance.interceptors.request.use((request) => {

  if(store.state.auth.user && store.state.auth.user.token)
    request.headers["Authorization"] = `Bearer ${store.state.auth.user.token}`;
  return request;
}, function (error) {
  console.log(error);
  return Promise.reject(error.response);
});

export default axiosInstance;