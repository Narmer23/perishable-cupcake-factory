import cupcakeFactoryApi from "src/CupcakeFactoryApi";

export default {

  async getAll() {
    let { data } = await cupcakeFactoryApi.get("/rest/uoms");
    return data;  
  },

  async getById(uomId) {
    let { data } = await cupcakeFactoryApi.get(`/rest/uoms/${uomId}`);
    return data;  
  },

  async create(uom) {
    let { data } = await cupcakeFactoryApi.post("/rest/uoms", uom);
    return data;  
  },

  async patch(uom) {
    let { data } = await cupcakeFactoryApi.patch("/rest/uoms", uom);
    return data;  
  },

  async delete(uomId) {
    let { data } = await cupcakeFactoryApi.delete(`/rest/uoms/${uomId}`);
    return data;  
  }

};