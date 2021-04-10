<template>
  <q-dialog ref="dialog" @hide="onDialogHide">
    <q-card class="q-dialog-plugin">
      <q-card-section>
        <div class="text-h6">
          Unità di misura
        </div>
      </q-card-section>
      <q-card-section>
        <q-input v-model="internalUom.name" label="Nome" placeholder="e.g. 'Grammi'" :error="$v.internalUom.name.$error" />
        <q-input v-model="internalUom.label" label="Etichetta" placeholder="e.g. 'g'" :error="$v.internalUom.label.$error" />
      </q-card-section>

      <q-card-actions align="right">
        <q-btn color="primary" label="Salva" :disable="$v.internalUom.$invalid" @click="onOKClick" />
        <q-btn color="primary" label="Cancel" flat @click="onCancelClick" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
import { required } from "vuelidate/lib/validators";
import UomService from "src/services/UomService";

export default {
  props:["uom"],
  data(){
    return{
      internalUom: { }
    };
  },
  validations:{
    internalUom: {
      name: {required},
      label: {required}
    }
  },
  created(){
    this.internalUom = {...this.uom};
  },
  methods: {
    show () {
      this.$refs.dialog.show();
    },
    hide () {
      this.$refs.dialog.hide();
    },
    onDialogHide () {
      this.$emit("hide");
    },
    async onOKClick () {
      try{
        let uom;
        if(this.internalUom.id){
          uom = await UomService.patch(this.internalUom);
        } else {
          uom = await UomService.create(this.internalUom);
        } 
        this.$emit("ok", uom);
        this.hide();
      }catch(err){
        console.log(err);
        this.internalUom = {...this.uom};
      }    
    },
    onCancelClick () {
      this.hide();
    }
  }
};
</script>