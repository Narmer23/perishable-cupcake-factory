<template>
  <q-page padding>
    <div class="row q-col-gutter-md items-stretch">
      <div class="col-12">
        <div class="row items-center justify-between">
          <div class="col text-h6 text-secondary">
            Cupcakes e torte
          </div>
          <div class="col-auto">
            <q-btn label="Crea nuovo dolce" icon="add" color="primary" @click="createNewCake"></q-btn>
          </div>
        </div>
      </div>
      <div v-if="!loading && !cakes.length" class="col-12">
        Non ci sono dolci disponibili.
        <q-btn label="Creane uno" color="primary" @click="createNewCake"></q-btn>
      </div>
      <div v-for="cake in cakes" :key="cake.id" class="col-auto">
        <q-card style="min-width: 300px" class="full-height">
          <div class="column full-height">
            <q-card-section>
              <div class="row justify-between items-center">
                <div class="text-h6 ellipsis">
                  {{cake.name}}
                </div>
                <q-chip icon="euro" :label="cake.defaultPrice.toFixed(2)" color="primary" text-color="white" class="text-bold" />
              </div>
            </q-card-section>

            <q-card-section class="q-pa-none col">
              <q-list dense>
                <q-item-label header>
                  Ingredienti
                </q-item-label>
                <q-item v-if="!cake.ingredients.length">
                  <q-item-section class="text-caption">
                    Nessun ingrediente per questo dolce!
                  </q-item-section>
                </q-item>
                <q-item v-for="ingredient in cake.ingredients" :key="ingredient.id">
                  <q-item-section class="text-caption">
                    {{ingredient.quantity}} {{ingredient.uoM?ingredient.uoM.label:''}} {{ingredient.ingredient.name}}
                  </q-item-section>
                </q-item>
              </q-list>
            </q-card-section>

            <q-separator />

            <q-card-actions class="justify-end">
              <q-btn flat label="Elimina" icon="delete" color="negative" @click="deleteCake(cake.id)" />
            </q-card-actions>
          </div>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script>

import CakesService from "src/services/CakeService";
import CakeModal from "src/components/CakeModal";

export default {
  data () {
    return {
      loading: true,
      cakes: []
    };
  },
  async mounted(){
    try{
      this.cakes = await CakesService.getAll();
    }catch(err){
      this.$q.notify({
        type: "negative",
        message: "Errore nel recuperare la lista dei dolci"
      });
    }finally{
      this.loading = false;
    }
  },
  methods:{
    createNewCake () {
      this.$q.dialog({
        component: CakeModal,
        parent: this,
        cake: {}
      }).onOk((cake)=>{
        this.cakes.push(cake);        
      });
    },
    async deleteCake(cakeId){
      await CakesService.delete(cakeId);
      let index = this.cakes.findIndex(i=>i.id==cakeId);
      if(index>=0){
        this.cakes.splice(index, 1);
      }
    }
  }
};
</script>
