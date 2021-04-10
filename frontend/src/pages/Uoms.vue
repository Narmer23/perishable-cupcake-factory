<template>
  <q-page padding>
    <div class="row q-col-gutter-md">
      <div class="col-12">
        <div class="row items-center justify-between">
          <div class="col text-h6 text-secondary">
            Unità di misura
          </div>
          <div class="col-auto">
            <q-btn label="Crea nuova unità di misura" icon="add" color="primary" @click="createNewUom"></q-btn>
          </div>
        </div>
      </div>
      <div v-if="!loading && !uoms.length" class="col-12">
        Non ci sono unità di misura disponibili.
        <q-btn label="Creane una" color="primary" @click="createNewUom"></q-btn>
      </div>
      <div v-for="uom in uoms" :key="uom.id" class="col-auto">
        <q-card style="min-width: 300px">
          <q-card-section>
            <div class="row justify-between items-center q-col-gutter-md">
              <div class="col">
                <div class="text-h6 ellipsis">
                  {{uom.name}} ({{uom.label}})
                </div>
              </div>
              <div class="col-auto">
                <q-btn color="grey-7" round flat dense icon="edit" @click="modifyUom(uom)" />
                <q-btn flat round dense icon="delete" color="negative" @click="deleteUom(uom.id)" />
              </div>
            </div>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script>

import UomsService from "src/services/UomService";
import UomModal from "src/components/UomModal";

export default {
  data () {
    return {
      loading: true,
      uoms: []
    };
  },
  async mounted(){
    try{
      this.uoms = await UomsService.getAll();
    }catch(err){
      this.$q.notify({
        type: "negative",
        message: "Errore nel recuperare la lista degli uomi"
      });
    }finally{
      this.loading = false;
    }
  },
  methods:{
    createNewUom () {
      this.$q.dialog({
        component: UomModal,
        parent: this,
        uom: {}
      }).onOk((uom)=>{
        this.uoms.push(uom);        
      });
    },
    modifyUom (uom) {
      this.$q.dialog({
        component: UomModal,
        parent: this,
        uom
      }).onOk((uom)=>{
        let index = this.uoms.findIndex(u=>u.id==uom.id);        
        this.uoms.splice(index, 1, uom);
      });
    },
    async deleteUom(uomId){
      await UomsService.delete(uomId);
      let index = this.uoms.findIndex(i=>i.id==uomId);
      if(index>=0){
        this.uoms.splice(index, 1);
      }
    }
  }
};
</script>
