import {Loading} from "quasar";

export default ({router, Vue, store}) => {
  router.beforeEach(async (to, from, next) => {
     
    Loading.show();
    try{

      let isAuthenticated = store.state.auth.isAuthenticated;
                  
      if(!isAuthenticated && !to.meta.public){
        next("/");
      } else {                                                      
        next();
      }
      Loading.hide();     
    }catch(err){
      Loading.hide();
      console.log(err);
      next("/");
    }    
  });
  router.afterEach(() => {
    Vue.nextTick(function () {
      Loading.hide();
    });
  });
};
