<template>
  <q-dialog ref="dialog" @hide="onDialogHide">
    <q-card class="q-dialog-plugin" style="min-width: 60vw">
      <q-card-section>
        <div class="text-h6">
          Nuovo dolce
        </div>
      </q-card-section>
      <q-card-section>
        <q-input v-model="internalCake.name" label="Nome" :error="$v.internalCake.name.$error" />
        <q-input v-model="internalCake.defaultPrice" type="number" label="Prezzo default" :error="$v.internalCake.defaultPrice.$error" />
      </q-card-section>
      <q-card-section>
        <div class="text-subtitle1">
          Ricetta
        </div>
        <div class="row q-col-gutter-sm items-center">
          <div class="col-2">
            <q-input
              v-model="newIngredient.quantity"
              label="Quantità"
              type="number"
              :error="$v.newIngredient.quantity.$error"
            />
          </div>
          <div class="col-3">
            <q-select
              v-model="newIngredient.uom"
              label="Unità di misura"
              :options="uoms"
              emit-value
              map-options
              :option-label="opt => Object(opt) === opt && `${opt.name} (${opt.label})` "
              :error="$v.newIngredient.uom.$error"
            >
              <template #append>
                <q-btn round dense flat icon="add" @click.stop @click="createNewUom" />
              </template>
            </q-select>
          </div>
          <div class="col-5">
            <q-select
              v-model="newIngredient.ingredient"
              label="Ingrediente"
              :options="ingredients"
              option-label="name"
              emit-value
              map-options
              :error="$v.newIngredient.ingredient.$error"
            >
              <template #append>
                <q-btn round dense flat icon="add" @click.stop @click="createNewIngredient" />
              </template>
            </q-select>
          </div>
          <div class="col-2">
            <q-btn icon="add" class="full-width" color="positive" flat :disable="$v.newIngredient.$invalid" @click="addIngredient(newIngredient)"></q-btn>
          </div>
        </div>
        <q-list dense bordered>
          <q-item v-if="!internalCake.ingredients.length">
            <q-item-section>
              Nessun ingrediente inserito
            </q-item-section>
          </q-item>
          <q-item v-for="(ingredient, i) in internalCake.ingredients" :key="i">
            <q-item-section>
              {{ingredient.quantity}} {{ingredient.uoM.label}} {{ingredient.ingredient.name}}
            </q-item-section>
            <q-item-section side>
              <q-btn color="negative" dense round flat icon="delete" @click="removeIngredient(i)" />
            </q-item-section>
          </q-item>
        </q-list>
      </q-card-section>
      
      <q-card-actions align="right">
        <q-btn color="primary" label="Salva" :disable="$v.internalCake.$invalid" @click="onOKClick" />
        <q-btn color="primary" label="Cancel" flat @click="onCancelClick" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
import { required } from "vuelidate/lib/validators";
import UomService from "src/services/UomService";
import CakeService from "src/services/CakeService";
import IngredientService from "src/services/IngredientService";
import UomModal from "src/components/UomModal";

export default {
  props:["cake"],
  data(){
    return{
      internalCake: { ingredients:[] },
      uoms:[],
      ingredients:[],
      newIngredient:{}
    };
  },
  validations:{
    newIngredient:{
      ingredient: {required},
      uom: {required},
      quantity: {required}
    },
    internalCake: {
      name: {required},
      defaultPrice: {required},
      ingredients:{
        required,
        $each: {
          ingredient:{required},
          uoM: {required},
          quantity: {required}
        }
      }
    }
  },
  async created(){
    this.uoms = await UomService.getAll();
    this.ingredients = await IngredientService.getAll();
    let internalCake = {...this.cake};
    if(this.cake.ingredients && this.cake.ingredients.length){
      internalCake.ingredients = this.cake.ingredients.map(i=>({ingredient:i.ingredient.id, uoM:i.uom.id, quantity:i.quantity}));
    }else{
      internalCake.ingredients = [];
    }
    this.internalCake = internalCake;
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
        let cake;
        if(this.internalCake.id){
          cake = await CakeService.patch(this.internalCake);
        } else {
          cake = await CakeService.create(this.internalCake);
        } 
        this.$emit("ok", cake);
        this.hide();
      }catch(err){
        console.log(err);
      }    
    },
    onCancelClick () {
      this.hide();
    },
    addIngredient(newIngredient){
      let ingredient = {
        quantity: newIngredient.quantity,
        ingredient:{
          id: newIngredient.ingredient.id,
          name: newIngredient.ingredient.name
        },
        uoM:{
          label: newIngredient.uom.label,
          id: newIngredient.uom.id
        }
      };
      this.internalCake.ingredients.push(ingredient);
      this.newIngredient = {};
    },
    removeIngredient(index){
      this.internalCake.ingredients.splice(index, 1);
    },
    createNewIngredient () {
      this.$q.dialog({
        title: "Nuovo ingrediente",
        message: "Inserisci il nome dell'ingrediente",
        prompt: {
          model: "",
          type: "text"
        },
        cancel: true,
        persistent: true
      }).onOk(async name => {
        let newIngredient = { name };
        let ingredient = await IngredientService.create(newIngredient);
        this.ingredients.push(ingredient);
        this.$set(this.newIngredient, "ingredient", ingredient);
      });
    },
    createNewUom () {
      this.$q.dialog({
        component: UomModal,
        parent: this,
        uom: {}
      }).onOk((uom)=>{
        this.uoms.push(uom);
        this.$set(this.newIngredient, "uom", uom);
   
      });
    }
  }
};
</script>