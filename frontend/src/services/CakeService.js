import cupcakeFactoryApi from "src/CupcakeFactoryApi";

export default {

  async getAll() {
    let { data } = await cupcakeFactoryApi.get("/rest/cakes");
    return data;  
  },

  async getById(cakeId) {
    let { data } = await cupcakeFactoryApi.get(`/rest/cakes/${cakeId}`);
    return data;  
  },

  async create(cake) {
    let { data } = await cupcakeFactoryApi.post("/rest/cakes", cake);
    return data;  
  },

  async patch(cake) {
    let { data } = await cupcakeFactoryApi.patch("/rest/cakes", cake);
    return data;  
  },

  async delete(cakeId) {
    let { data } = await cupcakeFactoryApi.delete(`/rest/cakes/${cakeId}`);
    return data;  
  }

};