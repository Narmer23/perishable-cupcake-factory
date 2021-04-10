<template>
  <q-page padding>
    <div class="row q-col-gutter-md">
      <div class="col-12">
        <div class="row items-center justify-between">
          <div class="col text-h6 text-secondary">
            Produzione
          </div>
          <div class="col-auto">
            <q-btn label="Nuova produzione" icon="add" color="primary" @click="createNewBatch"></q-btn>
          </div>
        </div>      
      </div>
      <div class="col-12">
        <q-table
          :data="orderedBatches"
          :columns="columns"
          row-key="id"
          :pagination="{rowsPerPage: 15, page: 1}"
        >
          <template #body="props">
            <q-tr :props="props">
              <q-td key="quantity" :props="props">
                {{ props.row.quantity }}
              </q-td>
              <q-td key="cake" :props="props">
                {{ props.row.cake.name }}
              </q-td>
              <q-td key="productionDate" :props="props">
                {{ props.row.productionDate | date('LLL')}}
              </q-td>
              <q-td key="elapsedTime" :props="props">
                {{ props.row.productionDate | date('calendar')}}
              </q-td>
              <q-td key="price" :props="props">
                {{ props.row.batchPrice.toFixed(2) }} €
              </q-td>
              <q-td key="discount" :props="props">
                {{ ((1 - props.row.priceModifier) * 100).toFixed(2) }} %
              </q-td>
              <q-td key="sellPrice" :props="props">
                {{ (props.row.batchPrice * props.row.priceModifier).toFixed(2) }} €
              </q-td>
              <q-td key="expired" :props="props" class="text-bold">
                <div v-if="props.row.expired" class="text-negative">
                  SCADUTO
                </div>
                <div v-else-if="props.row.priceModifier<1" class="text-warning">
                  IN SCADENZA
                </div>
              </q-td>
              <q-td key="actions" :props="props">
                <q-btn flat dense round icon="delete" color="negative" @click="deleteBatch(props.row.id)" />
              </q-td>
            </q-tr>
          </template>
        </q-table>
      </div>
    </div>
  </q-page>
</template>

<script>

import BatchService from "src/services/BatchService";
import BatchModal from "src/components/BatchModal";
import _ from "lodash";

export default {
  data(){
    return{
      columns:[
        { name: "quantity", align: "center", label: "Quantità disponibile", sortable: true },
        { name: "cake", align: "left", label: "Dolce", sortable: true },
        { name: "productionDate", align: "center", label: "Data di produzione", sortable: true },
        { name: "elapsedTime", align: "left", label: "Sfornato", field: "quantity", sortable: true },
        { name: "price", align: "center", label: "Prezzo", sortable: true },
        { name: "discount", align: "center", label: "Sconto"},
        { name: "sellPrice", align: "center", label: "Prezzo di vendita", sortable: true },
        { name: "expired", align: "center" },
        { name: "actions", align: "center" },
      ],
      batches:[]
    };
  },
  computed:{
    orderedBatches(){
      return _.orderBy(this.batches, "productionDate", ["desc"]);
    }
  },
  async mounted(){
    this.batches = await BatchService.getAllWithExpire(+new Date());
  },
  methods:{
    createNewBatch () {
      this.$q.dialog({
        component: BatchModal,
        parent: this,
        cake: {}
      }).onOk(async ()=>{
        this.batches = await BatchService.getAllWithExpire(+new Date());
      });
    },
    async deleteBatch(batchId){
      await BatchService.delete(batchId);
      let index = this.batches.findIndex(i=>i.id==batchId);
      if(index>=0){
        this.batches.splice(index, 1);
      }
    }
  }
};
</script>