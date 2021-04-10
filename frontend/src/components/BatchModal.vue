<template>
  <q-dialog ref="dialog" @hide="onDialogHide">
    <q-card class="q-dialog-plugin">
      <q-card-section>
        <div class="text-h6">
          Nuova produzione
        </div>
      </q-card-section>
      <q-card-section>
        <q-select
          v-model="batch.cake"
          label="Dolce"
          :options="cakes"
          option-label="name"
          emit-value
          map-options
          :error="$v.batch.cake.$error"
          @input="$set(batch, 'batchPrice', batch.cake.defaultPrice)"
        />
        <q-input v-model="batch.quantity" type="number" label="Quantità" :error="$v.batch.quantity.$error" />
        <q-input v-model="batch.batchPrice" type="number" label="Prezzo di vendita" :error="$v.batch.batchPrice.$error" />
        <q-input v-model="batch.productionDate" label="Data di produzione">
          <template #prepend>
            <q-icon name="event" class="cursor-pointer">
              <q-popup-proxy transition-show="scale" transition-hide="scale">
                <q-date v-model="batch.productionDate" now-btn :mask="dateFormat">
                  <div class="row items-center justify-end">
                    <q-btn v-close-popup label="Close" color="primary" flat />
                  </div>
                </q-date>
              </q-popup-proxy>
            </q-icon>
          </template>

          <template #append>
            <q-icon name="access_time" class="cursor-pointer">
              <q-popup-proxy transition-show="scale" transition-hide="scale">
                <q-time v-model="batch.productionDate" now-btn :mask="dateFormat" format24h>
                  <div class="row items-center justify-end">
                    <q-btn v-close-popup label="Close" color="primary" flat />
                  </div>
                </q-time>
              </q-popup-proxy>
            </q-icon>
          </template>
        </q-input>
      </q-card-section>
      
      <q-card-actions align="right">
        <q-btn color="primary" label="Salva" :disable="$v.batch.$invalid" @click="onOKClick" />
        <q-btn color="primary" label="Cancel" flat @click="onCancelClick" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
import { required } from "vuelidate/lib/validators";
import CakeService from "src/services/CakeService";
import BatchService from "src/services/BatchService";
import { date } from "quasar";

const DATE_FORMAT = "YYYY-MM-DD HH:mm";

export default {
  data(){
    return{
      dateFormat: DATE_FORMAT,
      batch: {
        productionDate: date.formatDate(Date.now(), DATE_FORMAT)
      },
      cakes:[]
    };
  },
  validations:{
    batch: {
      quantity:{required},
      cake: {required},
      batchPrice: {required},
      productionDate:{required}
    }
  },
  async created(){
    this.cakes = await CakeService.getAll();
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
        const dateObj = date.extractDate(this.batch.productionDate, DATE_FORMAT);
        this.batch.productionDate = +dateObj;
        let batch = await BatchService.create(this.batch);
        this.$emit("ok", batch);
        this.hide();
      }catch(err){
        console.log(err);
      }    
    },
    onCancelClick () {
      this.hide();
    }
  }
};
</script>