import cupcakeFactoryApi from "src/CupcakeFactoryApi";

export default {

  async getAll() {
    let { data } = await cupcakeFactoryApi.get("/rest/ingredients");
    return data;  
  },

  async getById(ingredientId) {
    let { data } = await cupcakeFactoryApi.get(`/rest/ingredients/${ingredientId}`);
    return data;  
  },

  async create(ingredient) {
    let { data } = await cupcakeFactoryApi.post("/rest/ingredients", ingredient);
    return data;  
  },

  async patch(ingredient) {
    let { data } = await cupcakeFactoryApi.patch("/rest/ingredients", ingredient);
    return data;  
  },

  async delete(ingredientId) {
    let { data } = await cupcakeFactoryApi.delete(`/rest/ingredients/${ingredientId}`);
    return data;  
  }

};