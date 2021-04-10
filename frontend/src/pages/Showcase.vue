<template>
  <q-layout view="lHh Lpr lFf">
    <q-header>
      <q-toolbar class="bg-white color-primary">
        <q-toolbar-title>
        </q-toolbar-title>

        <q-btn color="primary" flat icon="login" label="Area riservata" @click="openLoginModal">
        </q-btn>
      </q-toolbar>
    </q-header>
    <q-page-container>
      <q-page padding>
        <div class="row justify-center">
          <div class="row q-col-gutter-lg items-center justify-center" style="max-width: 1000px">
            <img alt="Logo" src="pcf_stack.svg" style="min-width:60%">
            <div class="col-12">
              <q-table
                :data="orderedBatches"
                :columns="columns"
                row-key="id"
                :pagination.sync="pagination"
              >
                <template #body="props">
                  <q-tr :props="props">
                    <q-td key="quantity" :props="props">
                      {{ props.row.quantity }}
                    </q-td>
                    <q-td key="cake" :props="props">
                      <q-btn color="primary" round flat dense size="xs" icon="search">
                        <q-popup-proxy>
                          <q-card class="full-height">
                            <q-card-section>
                              <div class="text-h6 ellipsis">
                                {{props.row.cake.name}}
                              </div>
                            </q-card-section>
                            <q-card-section class="q-pa-none">
                              <q-list dense>
                                <q-item-label header>
                                  Ingredienti
                                </q-item-label>
                                <q-item v-if="!props.row.cake.ingredients.length">
                                  <q-item-section class="text-caption">
                                    Nessun ingrediente per questo dolce!
                                  </q-item-section>
                                </q-item>
                                <q-item v-for="ingredient in props.row.cake.ingredients" :key="ingredient.id">
                                  <q-item-section class="text-caption">
                                    {{ingredient.quantity}} {{ingredient.uoM?ingredient.uoM.label:''}} {{ingredient.ingredient.name}}
                                  </q-item-section>
                                </q-item>
                              </q-list>
                            </q-card-section>
                          </q-card>
                        </q-popup-proxy>
                      </q-btn>
                      {{ props.row.cake.name }}
                    </q-td>
                    <q-td key="elapsedTime" :props="props">
                      {{ props.row.productionDate | date('calendar')}}
                    </q-td>
                    <q-td key="discount" :props="props">
                      {{ ((1 - props.row.priceModifier) * 100).toFixed(2) }} %
                    </q-td>
                    <q-td key="sellPrice" :props="props">
                      {{ (props.row.batchPrice * props.row.priceModifier).toFixed(2) }} €
                    </q-td>
                  </q-tr>
                </template>
              </q-table>
            </div>
          </div>
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>

import { mapState } from "vuex";
import LoginModal from "components/LoginModal";
import BatchService from "src/services/BatchService";
import _ from "lodash";

export default {
  data () {
    return {
      now: new Date(),
      pagination: {rowsPerPage: 15, page: 1},
      columns:[
        { name: "quantity", align: "center", label: "Quantità", sortable: true },
        { name: "cake", align: "left", label: "Dolce", sortable: true },
        { name: "elapsedTime", align: "left", label: "Sfornato", field: "quantity", sortable: true },
        { name: "discount", align: "center", label: "Sconto"},
        { name: "sellPrice", align: "center", label: "Prezzo cad.", sortable: true },
      ],
      batches:[]
    };
  },
  computed: {
    ...mapState({
      isAuthenticated: state =>state.auth.isAuthenticated,
    }),
    orderedBatches(){
      return _.orderBy(this.batches, "productionDate", ["desc"]);
    }
  },
  async mounted(){
    this.batches = await BatchService.getAvailable(+this.now);
  },
  methods:{
    openLoginModal(){     
      if(this.isAuthenticated){
        this.$router.push("/admin");
      }else{
        this.$q.dialog({
          component: LoginModal,
          parent: this
        }).onOk((user)=>{
          this.$store.commit("auth/changeUser", user);
          this.$router.push("/admin");
        });
      }
    }
  }
};

</script>
