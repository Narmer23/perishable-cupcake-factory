import cupcakeFactoryApi from "src/CupcakeFactoryApi";

export default {

  async getAvailable(untilDate) {
    let { data } = await cupcakeFactoryApi.get("/rpc/batches/available", {params:{untilDate}});
    return data;  
  },

  async getAllWithExpire(expireTarget) {
    let { data } = await cupcakeFactoryApi.get("/rpc/batches/all", {params:{expireTarget}});
    return data;  
  },

  async getAll() {
    let { data } = await cupcakeFactoryApi.get("/rest/batches");
    return data;  
  },

  async getById(batchId) {
    let { data } = await cupcakeFactoryApi.get(`/rest/batches/${batchId}`);
    return data;  
  },

  async create(batch) {
    let { data } = await cupcakeFactoryApi.post("/rest/batches", batch);
    return data;  
  },

  async patch(batch) {
    let { data } = await cupcakeFactoryApi.patch("/rest/batches", batch);
    return data;  
  },

  async delete(batchId) {
    let { data } = await cupcakeFactoryApi.delete(`/rest/batches/${batchId}`);
    return data;  
  }

};