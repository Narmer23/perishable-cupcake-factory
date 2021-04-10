import cupcakeFactoryApi from "src/CupcakeFactoryApi";


export default {

  async login(credentials) {
    let { data } = await cupcakeFactoryApi.post("/rest/login", credentials);
    return data;  
  }

};