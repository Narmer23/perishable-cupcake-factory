(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[5],{a916:function(e,t,n){"use strict";n("e6cf");var i=n("9440");t["a"]={async getAll(){let{data:e}=await i["a"].get("/rest/ingredients");return e},async getById(e){let{data:t}=await i["a"].get(`/rest/ingredients/${e}`);return t},async create(e){let{data:t}=await i["a"].post("/rest/ingredients",e);return t},async patch(e){let{data:t}=await i["a"].patch("/rest/ingredients",e);return t},async delete(e){let{data:t}=await i["a"].delete(`/rest/ingredients/${e}`);return t}}},f995:function(e,t,n){"use strict";n.r(t);var i=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("q-page",{attrs:{padding:""}},[n("div",{staticClass:"row q-col-gutter-md"},[n("div",{staticClass:"col-12"},[n("div",{staticClass:"row items-center justify-between"},[n("div",{staticClass:"col text-h6 text-secondary"},[e._v("\n          Ingredienti\n        ")]),n("div",{staticClass:"col-auto"},[n("q-btn",{attrs:{label:"Crea nuovo ingrediente",icon:"add",color:"primary"},on:{click:e.createNewIngredient}})],1)])]),e.loading||e.ingredients.length?e._e():n("div",{staticClass:"col-12"},[e._v("\n      Non ci sono ingredienti disponibili.\n      "),n("q-btn",{attrs:{label:"Creane uno",color:"primary"},on:{click:e.createNewIngredient}})],1),e._l(e.ingredients,(function(t){return n("div",{key:t.id,staticClass:"col-sm-4 col-md-3"},[n("q-card",{staticClass:"fit",staticStyle:{"min-width":"300px"}},[n("q-card-section",[n("div",{staticClass:"row justify-between items-center q-col-gutter-md"},[n("div",{staticClass:"col"},[n("div",{staticClass:"text-h6 ellipsis"},[e._v("\n                "+e._s(t.name)+"\n              ")])]),n("div",{staticClass:"col-auto"},[n("q-btn",{attrs:{color:"grey-7",round:"",flat:"",dense:"",icon:"edit"},on:{click:function(n){return e.modifyIngredient(t)}}}),n("q-btn",{attrs:{flat:"",round:"",dense:"",icon:"delete",color:"negative",disable:t.cakes.length>0},on:{click:function(n){return e.deleteIngredient(t.id)}}})],1)])]),n("q-card-section",{staticClass:"q-pa-none"},[n("q-list",{attrs:{dense:""}},[n("q-item-label",{attrs:{header:""}},[e._v("\n              Usato per\n            ")]),t.cakes.length?e._e():n("q-item",[n("q-item-label",{staticClass:"text-caption"},[e._v("\n                Nessun dolce usa questo ingrediente\n              ")])],1),e._l(t.cakes,(function(t){return n("q-item",{key:t.id},[n("q-item-label",{staticClass:"text-caption"},[e._v("\n                "+e._s(t.cake.name)+"\n              ")])],1)}))],2)],1)],1)],1)}))],2)])},a=[],s=(n("e6cf"),n("a916")),r={data(){return{loading:!0,ingredients:[]}},async mounted(){try{this.ingredients=await s["a"].getAll()}catch(e){this.$q.notify({type:"negative",message:"Errore nel recuperare la lista degli ingredienti"})}finally{this.loading=!1}},methods:{createNewIngredient(){this.$q.dialog({title:"Nuovo ingrediente",message:"Inserisci il nome dell'ingrediente",prompt:{model:"",type:"text"},cancel:!0,persistent:!0}).onOk((async e=>{let t={name:e},n=await s["a"].create(t);this.ingredients.push(n)}))},modifyIngredient(e){this.$q.dialog({title:"Modifica ingrediente",message:"Inserisci il nome dell'ingrediente",prompt:{model:e.name,type:"text"},cancel:!0,persistent:!0}).onOk((async t=>{e.name=t,await s["a"].patch(e)}))},async deleteIngredient(e){await s["a"].delete(e);let t=this.ingredients.findIndex((t=>t.id==e));t>=0&&this.ingredients.splice(t,1)}}},l=r,c=n("2877"),d=n("9989"),o=n("9c40"),g=n("f09f"),u=n("a370"),m=n("1c1c"),p=n("0170"),y=n("66e5"),f=n("eebe"),v=n.n(f),w=Object(c["a"])(l,i,a,!1,null,null,null);t["default"]=w.exports;v()(w,"components",{QPage:d["a"],QBtn:o["a"],QCard:g["a"],QCardSection:u["a"],QList:m["a"],QItemLabel:p["a"],QItem:y["a"]})}}]);