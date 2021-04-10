<template>
  <q-page padding>
    <div class="row q-col-gutter-md">
      <div class="col-12">
        <div class="row items-center justify-between">
          <div class="col text-h6 text-secondary">
            Ingredienti
          </div>
          <div class="col-auto">
            <q-btn label="Crea nuovo ingrediente" icon="add" color="primary" @click="createNewIngredient"></q-btn>
          </div>
        </div>
      </div>
      <div v-if="!loading && !ingredients.length" class="col-12">
        Non ci sono ingredienti disponibili.
        <q-btn label="Creane uno" color="primary" @click="createNewIngredient"></q-btn>
      </div>
      <div v-for="ingredient in ingredients" :key="ingredient.id" class="col-sm-4 col-md-3">
        <q-card style="min-width: 300px" class="fit">
          <q-card-section>
            <div class="row justify-between items-center q-col-gutter-md">
              <div class="col">
                <div class="text-h6 ellipsis">
                  {{ingredient.name}}
                </div>
              </div>
              <div class="col-auto">
                <q-btn color="grey-7" round flat dense icon="edit" @click="modifyIngredient(ingredient)" />
                <q-btn flat round dense icon="delete" color="negative" :disable="ingredient.cakes.length>0" @click="deleteIngredient(ingredient.id)" />
              </div>
            </div>
          </q-card-section>

          <q-card-section class="q-pa-none">
            <q-list dense>
              <q-item-label header>
                Usato per
              </q-item-label>
              <q-item v-if="!ingredient.cakes.length">
                <q-item-label class="text-caption">
                  Nessun dolce usa questo ingrediente
                </q-item-label>
              </q-item>
              <q-item v-for="cake in ingredient.cakes" :key="cake.id">
                <q-item-label class="text-caption">
                  {{cake.cake.name}}
                </q-item-label>
              </q-item>
            </q-list>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script>

import IngredientsService from "src/services/IngredientService";

export default {
  data () {
    return {
      loading: true,
      ingredients: []
    };
  },
  async mounted(){
    try{
      this.ingredients = await IngredientsService.getAll();
    }catch(err){
      this.$q.notify({
        type: "negative",
        message: "Errore nel recuperare la lista degli ingredienti"
      });
    }finally{
      this.loading = false;
    }
  },
  methods:{
    createNewIngredient () {
      this.$q.dialog({
        title: "Nuovo ingrediente",
        message: "Inserisci il nome dell'ingrediente",
        prompt: {
          model: "",
          type: "text" // optional
        },
        cancel: true,
        persistent: true
      }).onOk(async name => {
        let newIngredient = { name };
        let ingredient = await IngredientsService.create(newIngredient);
        this.ingredients.push(ingredient);
      });
    },
    modifyIngredient(ingredient){
      this.$q.dialog({
        title: "Modifica ingrediente",
        message: "Inserisci il nome dell'ingrediente",
        prompt: {
          model: ingredient.name,
          type: "text" // optional
        },
        cancel: true,
        persistent: true
      }).onOk(async name => {
        ingredient.name = name;
        await IngredientsService.patch(ingredient);
        
      });
    },
    async deleteIngredient(ingredientId){
      await IngredientsService.delete(ingredientId);
      let index = this.ingredients.findIndex(i=>i.id==ingredientId);
      if(index>=0){
        this.ingredients.splice(index, 1);
      }
    }
  }
};
</script>
